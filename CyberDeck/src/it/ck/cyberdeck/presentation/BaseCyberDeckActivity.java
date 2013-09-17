package it.ck.cyberdeck.presentation;

import it.ck.cyberdeck.app.DeckService;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class BaseCyberDeckActivity extends FragmentActivity {

	private DeckService deckService;
	
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		this.deckService = getCyberDeckApp().getDeckService();
	}

	protected DeckService getDeckService() {
		return deckService;
	}

	protected CyberDeckApp getCyberDeckApp() {
		return (CyberDeckApp) getApplication();
	}

}
