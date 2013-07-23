package it.ck.cyberdeck.model;

import java.util.List;


public interface LibraryCardGateway {

	CardLibrary getCardLibrary();
	
  void saveDeck(Deck deck);
  
  Deck loadDeck(String name);

	List<String> deckNames();
}