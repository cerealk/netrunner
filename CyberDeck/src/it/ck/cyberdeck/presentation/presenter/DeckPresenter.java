package it.ck.cyberdeck.presentation.presenter;

import it.ck.cyberdeck.model.Card;
import it.ck.cyberdeck.model.CardEntry;
import it.ck.cyberdeck.model.Deck;
import it.ck.cyberdeck.presentation.DeckView;

public class DeckPresenter {

	private Deck deck;
	private DeckView deckPublisher;

	public DeckPresenter(Deck deck, DeckView deckPublisher) {
		this.deck = deck;
		this.deckPublisher = deckPublisher;
	}
	
	public void publish(){
		deckPublisher.publishDeckName(deck.name());
		deckPublisher.publishIdentityName(deck.getIdentity().name());
		deckPublisher.publishCardList(deck.cards());
		deckPublisher.publishDeckStatus(deck.checkStatus());
	}

	public Deck getDeck() {
		return deck;
	}

	public void remove(Card card) {
		deck.remove(card);
	}

	public void removeAll(Card card) {
		deck.removeAll(card);
	}

	public CardEntry get(int position) {
		return deck.cards().get(position);
	}
	
}
