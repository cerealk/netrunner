package it.ck.cyberdeck.presentation.adapter;

import it.ck.cyberdeck.model.Deck;
import it.ck.cyberdeck.presentation.DeckPublisher;

public class DeckAdapter {

	private Deck deck;

	public DeckAdapter(Deck deck) {
		this.deck = deck;
	}
	
	public void adapt(DeckPublisher target){
		target.publishDeckName(deck.name());
		target.publishIdentityName(deck.getIdentity().name());
		target.publishCardList(deck.cards());
		target.publishDeckStatus(deck.checkStatus());
	}
	
}
