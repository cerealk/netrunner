package it.ck.cyberdeck.persistance;

import java.util.List;

import it.ck.cyberdeck.model.*;

public class CachedGateway implements LibraryCardGateway{

	private LibraryCardGateway delegate;

	private CardLibrary cl;
	
	public CachedGateway(LibraryCardGateway delegate) {
	  this.delegate = delegate;
  }

	@Override
  public CardLibrary getCardLibrary() {
	  if(this.cl == null){
	  	cl = delegate.getCardLibrary();
	  }
	  return cl;
  }

	@Override
  public void saveDeck(Deck deck) {
	  delegate.saveDeck(deck);
  }

	@Override
  public Deck loadDeck(String name) {
	  return delegate.loadDeck(name);
  }

	@Override
  public List<String> deckNames() {
	  return delegate.deckNames();
  }

	@Override
  public void deleteDeck(String deckName) {
		delegate.deleteDeck(deckName);	  
  }

}
