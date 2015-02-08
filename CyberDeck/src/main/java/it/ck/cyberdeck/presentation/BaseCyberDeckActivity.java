package it.ck.cyberdeck.presentation;

import it.ck.cyberdeck.app.DeckService;
import it.ck.cyberdeck.model.CardKey;
import it.ck.cyberdeck.presentation.util.ImageLoaderFactory;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.ImageView;

public abstract class BaseCyberDeckActivity extends FragmentActivity {

	private DeckService deckService;
	protected ToastHandler toastHandler;
	private ImageLoaderFactory ilFactory;
	
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		toastHandler = new ToastHandler(this);
		this.deckService = getCyberDeckApp().getDeckService();
		this.ilFactory = getCyberDeckApp().getImageLoaderFactory();
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
	
	protected void loadImage(CardKey key, ImageView img){
		ilFactory.display(key, img);
	}
	
	protected ImageLoaderFactory getImageLoaderFactory(){
		return ilFactory;
	}

}
