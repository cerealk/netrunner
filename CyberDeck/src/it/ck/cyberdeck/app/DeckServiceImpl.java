package it.ck.cyberdeck.app;

import it.ck.cyberdeck.model.*;

public class DeckServiceImpl implements DeckService {

	private LibraryCardGateway loader;
	
	public DeckServiceImpl(LibraryCardGateway loader){
		this.loader = loader;
	}

	@Override
	public Deck createDeck(Identity identity, String name) {
		return new Deck(identity, name);
	}
	
	@Override
	public CardLibrary loadCardLibrary(){
		CardLibrary cl = new CardLibrary();
		cl.addAll(loader.loadCards());
		return cl;
	}

}
