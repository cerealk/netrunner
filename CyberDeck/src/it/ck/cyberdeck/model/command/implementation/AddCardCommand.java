package it.ck.cyberdeck.model.command.implementation;

import it.ck.cyberdeck.model.Card;
import it.ck.cyberdeck.model.Deck;
import it.ck.cyberdeck.model.DeckException;
import it.ck.cyberdeck.model.Notifier;
import it.ck.cyberdeck.model.command.Command;

public class AddCardCommand implements Command {

	private Card card;
	private Deck deck;
	private Notifier notifier;

	public AddCardCommand(Card card, Deck deck, Notifier notifier) {
		this.card = card;
		this.deck = deck;
		this.notifier = notifier;
	}

	@Override
	public void execute() {
		try {
			deck.add(card);
			this.notifier.notify(card.getName() + " added");
		} catch (DeckException e) {
			this.notifier.notify(e.getMessage());
		}
	}

}