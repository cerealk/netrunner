package it.ck.cyberdeck.presentation;

import it.ck.cyberdeck.model.CardLibrary;

public class BaseCardLibraryActivity extends BaseCyberDeckActivity {
	protected CardLibrary getCardLibrary(){
		return getDeckService().loadCardLibrary();
	}
}
