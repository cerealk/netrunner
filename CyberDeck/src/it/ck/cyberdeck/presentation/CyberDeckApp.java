package it.ck.cyberdeck.presentation;

import it.ck.cyberdeck.app.DeckService;
import it.ck.cyberdeck.app.DeckServiceImpl;
import it.ck.cyberdeck.persistance.CachedGateway;
import it.ck.cyberdeck.persistance.filesystem.AndroidLibraryCardGateway;
import it.ck.cyberdeck.presentation.service.AndroidFSImageService;
import it.ck.cyberdeck.presentation.service.ImageService;
import android.app.Application;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

public class CyberDeckApp extends Application {

	private DeckService ds;
	private LruCache<String, Bitmap> imageCache;

	public CyberDeckApp() {
		final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);

		final int cacheSize = maxMemory / 4;

		this.imageCache = new LruCache<String, Bitmap>(cacheSize) {
			@Override
			protected int sizeOf(String key, Bitmap bitmap) {
				return bitmap.getRowBytes() * bitmap.getHeight() / 1024;
			}
		};
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

	public LruCache<String, Bitmap> getImageCache() {

		return this.imageCache;

	}

	public ImageService getImageService() {
		return new AndroidFSImageService(this);
	}

}
