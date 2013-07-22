package it.ck.cyberdeck.model;


public interface LibraryCardGateway {

	CardLibrary getCardLibrary();
	
  void saveDeck(Deck deck);
  
  Deck loadDeck(String name);
}