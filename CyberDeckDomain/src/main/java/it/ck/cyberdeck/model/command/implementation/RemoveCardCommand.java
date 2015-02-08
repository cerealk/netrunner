package it.ck.cyberdeck.model.command.implementation;

import it.ck.cyberdeck.model.Card;
import it.ck.cyberdeck.model.Deck;
import it.ck.cyberdeck.model.Notifier;
import it.ck.cyberdeck.model.command.Command;

public class RemoveCardCommand extends AbstractNotifierCommand implements Command {

	private Card card;
	private Deck deck;
	public RemoveCardCommand(Card card, Deck deck, Notifier notifier) {
		super(notifier);
		this.card = card;
		this.deck = deck;
	}

	protected String getSuccessMessage() {
		return "Card " + card.getName() + " removed";
	}

	protected void doExecute() {
		if(card != null)
			deck.remove(card);
	}

}