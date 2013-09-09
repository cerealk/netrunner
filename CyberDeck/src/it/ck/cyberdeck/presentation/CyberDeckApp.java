package it.ck.cyberdeck.presentation;

import it.ck.cyberdeck.app.DeckService;
import it.ck.cyberdeck.app.DeckServiceImpl;
import it.ck.cyberdeck.persistance.CachedGateway;
import it.ck.cyberdeck.persistance.filesystem.AndroidLibraryCardGateway;
import it.ck.cyberdeck.presentation.service.AndroidFSImageService;
import it.ck.cyberdeck.presentation.service.ImageService;
import android.app.Application;

public class CyberDeckApp extends Application {

	private DeckService ds;

	public DeckService getDeckService() {
		if (ds == null) {
			ds = createDeckService();

		}

		return ds;
	}

	private DeckService createDeckService() {
		return new DeckServiceImpl(new CachedGateway(new AndroidLibraryCardGateway(this)));
	}

	public ImageService getImageService() {
		return new AndroidFSImageService(this);
	}

}
