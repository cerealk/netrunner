package it.ck.cyberdeck.app;

import it.ck.cyberdeck.model.*;

public interface DeckService {

	Deck createDeck(Identity identity, String name);

	CardLibrary loadCardLibrary();

}
