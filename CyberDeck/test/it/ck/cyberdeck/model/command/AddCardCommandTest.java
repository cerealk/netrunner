package it.ck.cyberdeck.model.command;

import static org.junit.Assert.*;

import static it.ck.cyberdeck.fixtures.DeckTestFactory.*;
import static it.ck.cyberdeck.fixtures.CardTestFactory.*;
import static org.hamcrest.Matchers.*;
import it.ck.cyberdeck.model.Card;
import it.ck.cyberdeck.model.Deck;
import it.ck.cyberdeck.model.DeckException;
import it.ck.cyberdeck.model.Notifier;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

public class AddCardCommandTest {

    @Rule public JUnitRuleMockery context = new JUnitRuleMockery();
	private Notifier notifier = context.mock(Notifier.class);
	
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

	@Test
	public void givenAnEmptyDeckICanAddACard() throws Exception {
		Deck deck = getEmptyDeck();
		Card card = getCard();
		Command addCommand = new AddCardCommand(card, deck, getDoNothingNotifier());
		addCommand.execute();
		assertThat(deck.size(), is(1));
	}

	@Test
	public void givenADeckICanAddACard() throws Exception {
		Deck deck = twoCardDeck();
		Command addCardCommand = new AddCardCommand(getCard(), deck, getDoNothingNotifier());
		addCardCommand.execute();
		assertThat(deck.size(), is(3));
	}
	
	@Test
	public void whenACardIsAddedTheUserIsNotified() throws Exception {
		context.checking(new Expectations(){{
			oneOf(notifier).notify("std added");
		}});
		
		Deck deck = twoCardDeck();
		Command addCardCommand = new AddCardCommand(getCard(), deck, notifier);
		addCardCommand.execute();
		assertThat(deck.size(), is(3));
	}
	
	@Test
	public void anErrorInTheoperationIsCorrectlyNotified() throws Exception {
		
		
		context.checking(new Expectations(){{
			oneOf(notifier).notify("std added");
			oneOf(notifier).notify("card limit exceeded");
		}});
		
		Deck deck = twoCardDeck();
		Command addCardCommand = new AddCardCommand(getCard(), deck, notifier);
		addCardCommand.execute();
		addCardCommand.execute();
		assertThat(deck.size(), is(3));
	}
	
	private Notifier getDoNothingNotifier() {
		return new Notifier() {
			@Override
			public void notify(String message) {
			}
		};
	}
	
}
