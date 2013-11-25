package it.ck.cyberdeck.presentation;

import it.ck.cyberdeck.app.DeckService;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public abstract class BaseCyberDeckActivity extends FragmentActivity {

	private DeckService deckService;
	protected ToastHandler toastHandler;
	
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		toastHandler = new ToastHandler(this);
		this.deckService = getCyberDeckApp().getDeckService();
	}

	protected DeckService getDeckService() {
		return deckService;
	}

	protected CyberDeckApp getCyberDeckApp() {
		return (CyberDeckApp) getApplication();
	}

	protected void showToast(String toastText) {
		toastHandler.toast(toastText);
	}

}
