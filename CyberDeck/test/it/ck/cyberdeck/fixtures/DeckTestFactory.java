package it.ck.cyberdeck.fixtures;

import static it.ck.cyberdeck.fixtures.CardTestFactory.getCard;
import static it.ck.cyberdeck.fixtures.CardTestFactory.getCorpCardWithAgenda;
import static it.ck.cyberdeck.fixtures.IdentityTestFactory.getArarchIdentity;
import static it.ck.cyberdeck.fixtures.IdentityTestFactory.getHBIdentity;
import it.ck.cyberdeck.model.Deck;
import it.ck.cyberdeck.model.Identity;

public class DeckTestFactory {

	public static Deck getEmptyDeck() {
		return getDeck(getArarchIdentity());
	}
	
	public static Deck getDeck(Identity identity) {
		return new Deck(identity);
	}
	public static Deck getCorpDeckWithOneAgenda() {
		Deck corpDeck = getCorpDeck();
		corpDeck.add(getCorpCardWithAgenda("agenda1", 3, 1));
		return corpDeck;
	}

	public static Deck getCorpDeck() {
		Identity identity = getHBIdentity();
		Deck corpDeck = new Deck(identity, "corp deck");
		return corpDeck;
	}

	public static Deck twoCardDeck() {
		Deck deck = getEmptyDeck();
		addTwoCards(deck);
		return deck;
	}

	public static void addTwoCards(Deck deck) {
		deck.add(getCard());
		deck.add(getCard());
	}
}
