package it.ck.cyberdeck.presentation.presenter;

import it.ck.cyberdeck.app.DeckService;
import it.ck.cyberdeck.model.Card;
import it.ck.cyberdeck.model.CardEntry;
import it.ck.cyberdeck.model.Deck;
import it.ck.cyberdeck.model.command.Command;
import it.ck.cyberdeck.model.command.DeckCommandFactory;
import it.ck.cyberdeck.model.command.implementation.DefaultDeckCommandFactory;
import it.ck.cyberdeck.presentation.DeckView;

public class DeckPresenter  {

	private Deck deck;
	private DeckView deckPublisher;
	private DeckService deckService;

	public DeckPresenter(Deck deck, DeckView deckPublisher, DeckService deckService) {
		this.deck = deck;
		this.deckPublisher = deckPublisher;
		this.deckService = deckService;
	}
	
	public void publish(){
		deckPublisher.publishDeckName(deck.name());
		deckPublisher.publishIdentity(deck.getIdentity());
		deckPublisher.publishEntryList(deck.cards());
		deckPublisher.publishDeckStatus(deck.checkStatus());
	}

	public Deck getDeck() {
		return deck;
	}

	public void remove(Card card) {
		
		DeckCommandFactory commandFactory = new DefaultDeckCommandFactory();
		Command removeCardCommand = commandFactory.createRemoveCardCommand(card, deck, deckPublisher);
		removeCardCommand.execute();
		saveDeck();
	}

	private void saveDeck() {
		deckService.saveDeck(deck);
	}

	public void removeAll(Card card) {
		DeckCommandFactory commandFactory = new DefaultDeckCommandFactory();
		Command removeCardCommand = commandFactory.createRemoveAllCommand(card, deck, deckPublisher);
		removeCardCommand.execute();
		saveDeck();
	}

	public CardEntry get(int position) {
		return deck.cards().get(position);
	}
	
	public void addCard(Card card){
		
		DeckCommandFactory commandFactory = new DefaultDeckCommandFactory();
		Command addCommand = commandFactory.createAddCardCommand(card, deck, deckPublisher);
		addCommand.execute();
		saveDeck();
		
	}

}
