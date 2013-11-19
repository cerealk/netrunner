package it.ck.cyberdeck.presentation;

import it.ck.cyberdeck.app.DeckService;
import it.ck.cyberdeck.app.DeckServiceImpl;
import it.ck.cyberdeck.persistance.CachedGateway;
import it.ck.cyberdeck.persistance.filesystem.AndroidLibraryCardGateway;
import android.app.Application;
import android.content.Context;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
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
		
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
				.threadPriority(Thread.NORM_PRIORITY - 2)
				.memoryCacheSizePercentage(50)
				.denyCacheImageMultipleSizesInMemory()
				.discCacheFileNameGenerator(new Md5FileNameGenerator())
				.tasksProcessingOrder(QueueProcessingType.LIFO)
				.writeDebugLogs() // Remove for release app
				.build();
		// Initialize ImageLoader with configuration.
		ImageLoader.getInstance().init(config);
	}

}
