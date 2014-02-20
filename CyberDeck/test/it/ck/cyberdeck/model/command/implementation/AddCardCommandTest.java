package it.ck.cyberdeck.model.command.implementation;

import static it.ck.cyberdeck.fixtures.CardTestFactory.getCard;
import static it.ck.cyberdeck.fixtures.DeckTestFactory.getEmptyDeck;
import static it.ck.cyberdeck.fixtures.DeckTestFactory.twoCardDeck;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import it.ck.cyberdeck.model.Card;
import it.ck.cyberdeck.model.Deck;
import it.ck.cyberdeck.model.Notifier;
import it.ck.cyberdeck.model.command.Command;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

public class AddCardCommandTest {

    @Rule public JUnitRuleMockery context = new JUnitRuleMockery();
	private Notifier notifier = context.mock(Notifier.class);
	
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
