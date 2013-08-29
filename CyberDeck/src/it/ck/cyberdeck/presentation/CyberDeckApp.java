package it.ck.cyberdeck.presentation;

import it.ck.cyberdeck.app.*;
import it.ck.cyberdeck.persistance.CachedGateway;
import it.ck.cyberdeck.persistance.filesystem.AndroidLibraryCardGateway;
import it.ck.cyberdeck.presentation.service.FileImageService;
import it.ck.cyberdeck.presentation.service.ImageService;
import android.app.Application;
import android.content.Context;

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
		// TODO Auto-generated method stub
		return new FileImageService(this);
	}

}
