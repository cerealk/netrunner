package it.ck.cyberdeck.model.command.implementation;

import it.ck.cyberdeck.model.Card;
import it.ck.cyberdeck.model.Deck;
import it.ck.cyberdeck.model.Notifier;
import it.ck.cyberdeck.model.command.Command;

public class AddCardCommand extends AbastractNotifierCommand implements Command {

	private Card card;
	private Deck deck;
	public AddCardCommand(Card card, Deck deck, Notifier notifier) {
		super(notifier);
		this.card = card;
		this.deck = deck;
	}

	@Override
	protected String getSuccessMessage() {
		return card.getName() + " added";
	}

	@Override
	protected void doExecute() {
		deck.add(card);
	}

}