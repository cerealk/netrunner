package it.ck.cyberdeck.model.command.implementation;

import static it.ck.cyberdeck.fixtures.CardTestFactory.getCard;
import static it.ck.cyberdeck.fixtures.DeckTestFactory.getEmptyDeck;
import static it.ck.cyberdeck.fixtures.DeckTestFactory.twoCardDeck;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import it.ck.cyberdeck.model.Card;
import it.ck.cyberdeck.model.Deck;
import it.ck.cyberdeck.model.Deck.TooManyCardOfTheSameTypeException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class AddCardCommandTest {
	
	@Rule public ExpectedException expectedException = ExpectedException.none();
	
	@Test
	public void givenAnEmptyDeckICanAddACard() throws Exception {
		Deck deck = getEmptyDeck();
		Card card = getCard();
		AddCardCommand addCommand = new AddCardCommand(card, deck, null);
		addCommand.doExecute();
		assertThat(deck.size(), is(1));
	}

	@Test
	public void givenADeckICanAddACard() throws Exception {
		Deck deck = twoCardDeck();
		AddCardCommand addCardCommand = new AddCardCommand(getCard(), deck, null);
		addCardCommand.doExecute();
		assertThat(deck.size(), is(3));
	}
	
	@Test
	public void gicenADeckItCannotHaveMoreThan3copiesOfACard() throws Exception {
		Deck deck = twoCardDeck();
		AddCardCommand addCardCommand = new AddCardCommand(getCard(), deck, null);
		addCardCommand.doExecute();
		expectedException.expect(TooManyCardOfTheSameTypeException.class);
		addCardCommand.doExecute();
	}
	
	
}
