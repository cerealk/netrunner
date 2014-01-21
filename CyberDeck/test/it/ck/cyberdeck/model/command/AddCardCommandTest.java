package it.ck.cyberdeck.model.command;

import static org.junit.Assert.*;

import static it.ck.cyberdeck.fixtures.DeckTestFactory.*;
import static it.ck.cyberdeck.fixtures.CardTestFactory.*;
import static org.hamcrest.Matchers.*;
import it.ck.cyberdeck.model.Card;
import it.ck.cyberdeck.model.Deck;

import org.junit.Test;

public class AddCardCommandTest {

	public class AddCardCommand implements Command {

		private Card card;
		private Deck deck;

		public AddCardCommand(Card card, Deck deck) {
			this.card = card;
			this.deck = deck;
		}

		@Override
		public void execute() {
			deck.add(card);
		}

	}

	@Test
	public void givenAnEmptyDeckICanAddACard() throws Exception {
		Deck deck = getEmptyDeck();
		Card card = getCard();
		Command addCommand = new AddCardCommand(card, deck);
		addCommand.execute();
		assertThat(deck.size(), is(1));
	}
	
}
