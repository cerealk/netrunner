package it.ck.cyberdeck.model.command.implementation;

import it.ck.cyberdeck.model.Card;
import it.ck.cyberdeck.model.Deck;
import it.ck.cyberdeck.model.Notifier;
import it.ck.cyberdeck.model.command.Command;
import it.ck.cyberdeck.model.command.DeckCommandFactory;

public class DefaultDeckCommandFactory implements DeckCommandFactory {

	@Override
	public Command createAddCardCommand(Card toAdd, Deck target,
			Notifier notifier) {
		return new AddCardCommand(toAdd, target, notifier);
	}
	
	@Override
	public Command createRemoveCardCommand(Card toAdd, Deck target,
			Notifier notifier) {
		return new RemoveCardCommand(toAdd, target, notifier);
	}

}
