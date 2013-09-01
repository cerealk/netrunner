package it.ck.cyberdeck.presentation.presenter;

import it.ck.cyberdeck.model.Deck;
import it.ck.cyberdeck.presentation.DeckView;

public class DeckPresenter {

	private Deck deck;
	private DeckView deckPublisher;

	public DeckPresenter(Deck deck, DeckView deckPublisher) {
		this.deck = deck;
		this.deckPublisher = deckPublisher;
	}
	
	public void adapt(){
		deckPublisher.publishDeckName(deck.name());
		deckPublisher.publishIdentityName(deck.getIdentity().name());
		deckPublisher.publishCardList(deck.cards());
		deckPublisher.publishDeckStatus(deck.checkStatus());
	}
	
}
