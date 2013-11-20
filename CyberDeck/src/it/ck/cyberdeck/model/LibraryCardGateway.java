package it.ck.cyberdeck.model;

import java.util.List;

public interface LibraryCardGateway {

	CardLibrary loadCardLibrary();

	void saveDeck(Deck deck);

	Deck loadDeck(String name);

	List<String> deckNames();

	void deleteDeck(String deckName);
}