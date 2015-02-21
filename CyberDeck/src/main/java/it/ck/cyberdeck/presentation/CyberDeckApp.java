package it.ck.cyberdeck.presentation;

import it.ck.cyberdeck.R;
import it.ck.cyberdeck.app.DeckService;
import it.ck.cyberdeck.app.DeckServiceImpl;
import it.ck.cyberdeck.persistence.CachedGateway;
import it.ck.cyberdeck.persistence.AndroidLibraryCardGateway;
import it.ck.cyberdeck.presentation.util.ImageLoaderFactory;
import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

public class CyberDeckApp extends Application {

	private DeckService ds;

	@Override
	public void onCreate() {
		super.onCreate();
		initImageLoader(getApplicationContext());
	}



	public DeckService getDeckService() {
		if (ds == null) {
			ds = createDeckService();
		}

		return ds;
	}

	private DeckService createDeckService() {
		return new DeckServiceImpl(new CachedGateway(
				new AndroidLibraryCardGateway(this)));
	}

	public static void initImageLoader(Context context) {
		DisplayImageOptions options = new DisplayImageOptions.Builder()
									.showImageOnLoading(R.drawable.corp_back)
									.showImageForEmptyUri(R.drawable.ic_empty)
									.showImageOnFail(R.drawable.ic_error)
									.cacheInMemory(true)
									.cacheOnDisc(true)
									.bitmapConfig(Bitmap.Config.RGB_565)
									.build();
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
				.threadPriority(Thread.NORM_PRIORITY - 2)
				.memoryCacheSizePercentage(50)
				.defaultDisplayImageOptions(options)
				.denyCacheImageMultipleSizesInMemory()
				.discCacheFileNameGenerator(new Md5FileNameGenerator())
				.tasksProcessingOrder(QueueProcessingType.LIFO)
				.build();
		// Initialize ImageLoader with configuration.
		ImageLoader.getInstance().init(config);
	}



	public ImageLoaderFactory getImageLoaderFactory() {
		
		return new ImageLoaderFactory();
	}

}
