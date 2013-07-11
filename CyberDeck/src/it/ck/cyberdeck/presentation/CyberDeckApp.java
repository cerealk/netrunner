package it.ck.cyberdeck.presentation;

import it.ck.cyberdeck.app.DeckService;
import it.ck.cyberdeck.app.DeckServiceImpl;
import it.ck.cyberdeck.persistance.RawResourceLibraryCardGateway;
import android.app.Application;

public class CyberDeckApp extends Application {

	DeckService getDeckService(){
		return new DeckServiceImpl(new RawResourceLibraryCardGateway(getResources()));
	}
	
}
