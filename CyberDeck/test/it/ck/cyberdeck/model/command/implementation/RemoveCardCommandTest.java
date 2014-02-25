package it.ck.cyberdeck.model.command.implementation;

import static it.ck.cyberdeck.fixtures.CardTestFactory.getCard;
import static it.ck.cyberdeck.fixtures.DeckTestFactory.getEmptyDeck;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import it.ck.cyberdeck.model.Card;
import it.ck.cyberdeck.model.CardCounter.CardNotFoundException;
import it.ck.cyberdeck.model.Deck;
import it.ck.cyberdeck.model.Side;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class RemoveCardCommandTest {

	private Deck deck = getDeck();
	
	@Rule public ExpectedException expectedException = ExpectedException.none();

	@Test
	public void givenADeckWithACard_ICanRemoveTheCard() {

		Card cardToRemove = getCard();
		executeRemoveCardCommand(cardToRemove);
		assertThat(deck.cardCount(cardToRemove), is(0));
	}

	@Test
	public void whenIRemove_aNullCard_nothingHappens() throws Exception {

		executeRemoveCardCommand(null);
		assertThat(deck.size(), is(1));
	}

	@Test
	public void whenIRemoveACardNotpresent_anExceptionIsThrown() throws Exception {
		Card card = getCard("ciccio", Side.RUNNER);
		expectedException.expect(CardNotFoundException.class);
		executeRemoveCardCommand(card);
		
	}

	private Deck getDeck() {
		Deck deck = getEmptyDeck();
		Card card = getCard();
		deck.add(card);
		return deck;
	}

	private void executeRemoveCardCommand(Card card) {
		RemoveCardCommand command = new RemoveCardCommand(card, deck, null);
		command.doExecute();
	}
}
