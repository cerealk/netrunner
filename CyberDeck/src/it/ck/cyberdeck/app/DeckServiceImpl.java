package it.ck.cyberdeck.app;

import java.util.List;

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
		CardLibrary cl = loader.getCardLibrary();
		return cl;
	}

	@Override
  public List<String> deckNames() {
	  // TODO Auto-generated method stub
	  return null;
  }

}
