package it.ck.cyberdeck.model.command.implementation;

import static it.ck.cyberdeck.fixtures.CardTestFactory.getCard;
import static it.ck.cyberdeck.fixtures.DeckTestFactory.twoCardDeck;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import it.ck.cyberdeck.model.Card;
import it.ck.cyberdeck.model.CardCounter.CardNotFoundException;
import it.ck.cyberdeck.model.Deck;
import it.ck.cyberdeck.model.Notifier;
import it.ck.cyberdeck.model.Side;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class RemoveAllCommandTest {

	@Rule public ExpectedException expectedException = ExpectedException.none();
	
	public class RemoveAllCommand extends AbstractNotifierCommand{

		private Deck deck;
		private Card card;

		public RemoveAllCommand(Deck deck, Card card, Notifier notifier) {
			super(notifier);
			this.deck = deck;
			this.card = card;
		}

		@Override
		protected void doExecute() {
			if(card!=null)
				deck.removeAll(card);
			
		}

		@Override
		protected String getSuccessMessage() {
			return "Card " + card.getName() + " removed";
		}

	}

	private Deck deck = twoCardDeck();
	
	@Test
	public void canRemoveAllCardsOfAType() {
		Card card = getCard();
		executeRemoveAllCommand(card) ;
		assertThat(deck.cardCount(card), is(0));
	}
	
	@Test
	public void whenIRemoveACardNotpresent_anExceptionIsThrown() throws Exception {
		Card card = getCard("ciccio", Side.RUNNER);
		expectedException.expect(CardNotFoundException.class);
		executeRemoveAllCommand(card);
		
	}

	private void executeRemoveAllCommand(Card card) {
		RemoveAllCommand command = new RemoveAllCommand(deck, card, null);
		command.doExecute();
	}

}
