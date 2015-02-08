package it.ck.cyberdeck.model.command.implementation;

import it.ck.cyberdeck.model.Card;
import it.ck.cyberdeck.model.Deck;
import it.ck.cyberdeck.model.Notifier;

public class RemoveAllCommand extends AbstractNotifierCommand{

	private Deck deck;
	private Card card;

	public RemoveAllCommand(Card card, Deck deck, Notifier notifier) {
		super(notifier);
		this.deck = deck;
		this.card = card;
	}

	@Override
	protected void doExecute() {
		if(card!=null)
			deck.removeAll(card);
		
	}

	@Override
	protected String getSuccessMessage() {
		return "Card " + card.getName() + " removed";
	}

}