package it.ck.cyberdeck.model.command.implementation;

import it.ck.cyberdeck.model.Card;
import it.ck.cyberdeck.model.Deck;
import it.ck.cyberdeck.model.Notifier;
import it.ck.cyberdeck.model.CardCounter.CardNotFoundException;
import it.ck.cyberdeck.model.command.Command;

public class RemoveCardCommand implements Command {

	private Card card;
	private Deck deck;
	private Notifier notifier;

	public RemoveCardCommand(Card card, Deck deck, Notifier notifier) {
		this.card = card;
		this.deck = deck;
		this.notifier = notifier;
	}

	@Override
	public void execute() {
		try {
			if (card != null) {
				deck.remove(card);
				notifier.notify("Card " + card.getName() + " removed");
			}
		} catch (CardNotFoundException e) {
			notifier.notify(e.getMessage());
		}
	}

}