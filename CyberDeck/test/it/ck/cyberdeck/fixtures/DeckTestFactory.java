package it.ck.cyberdeck.fixtures;

import static it.ck.cyberdeck.fixtures.IdentityTestFactory.*;
import it.ck.cyberdeck.model.Deck;
import it.ck.cyberdeck.model.Identity;

public class DeckTestFactory {

	public static Deck getEmptyDeck() {
		return getDeck(getArarchIdentity());
	}
	
	public static Deck getDeck(Identity identity) {
		return new Deck(identity);
	}

}
