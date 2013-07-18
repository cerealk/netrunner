package it.ck.cyberdeck.presentation;

import it.ck.cyberdeck.app.*;
import it.ck.cyberdeck.persistance.filesystem.RawResourceLibraryCardGateway;
import android.app.Application;

public class CyberDeckApp extends Application {

	private DeckService ds;
	public DeckService getDeckService(){
		if(ds == null){
			ds = createDeckService();
			
		}
		
		return ds;
	}
	private DeckService createDeckService() {
	  return new CachedDeckServiceImpl(new DeckServiceImpl(new RawResourceLibraryCardGateway(getResources())));
  }
	
}
