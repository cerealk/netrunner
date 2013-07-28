package it.ck.cyberdeck.app;

import it.ck.cyberdeck.model.*;

import java.util.List;

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
		CardLibrary cl = loader.loadCardLibrary();
		return cl;
	}

	@Override
  public List<String> deckNames() {
	  return loader.deckNames();
  }

	@Override
  public void saveDeck(Deck deck) {
	  loader.saveDeck(deck);
	  
  }

	@Override
  public Deck loadDeck(String deckName) {
	  return loader.loadDeck(deckName);
  }

	@Override
  public void deleteDeck(String deckName) {
	  loader.deleteDeck(deckName);
	  
  }

}
