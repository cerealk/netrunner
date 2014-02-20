package it.ck.cyberdeck.model.command.implementation;

import static it.ck.cyberdeck.fixtures.CardTestFactory.getCard;
import static it.ck.cyberdeck.fixtures.DeckTestFactory.getEmptyDeck;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import it.ck.cyberdeck.model.Card;
import it.ck.cyberdeck.model.Deck;
import it.ck.cyberdeck.model.Notifier;
import it.ck.cyberdeck.model.Side;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

public class RemoveCardCommandTest {

	@Rule
	public JUnitRuleMockery context = new JUnitRuleMockery();

	private Deck deck = getDeck();

	private Notifier notifier = context.mock(Notifier.class);

	@Test
	public void givenADeckWithACard_ICanRemoveTheCard() {

		Card cardToRemove = getCard();
		expectNotification();
		executeRemoveCardCommand(cardToRemove);
		assertThat(deck.cardCount(cardToRemove), is(0));
	}

	@Test
	public void whenIRemove_aNullCard_nothingHappens() throws Exception {

		executeRemoveCardCommand(null);
		assertThat(deck.size(), is(1));
	}

	@Test
	public void whenIRemoveACardNotpresent_theUserIsNotified() throws Exception {
		Card card = getCard("ciccio", Side.RUNNER);
		expectNotification();

		executeRemoveCardCommand(card);
	}

	private void expectNotification() {
		context.checking(new Expectations() {
			{
				oneOf(notifier).notify(with(any(String.class)));
			}
		});
	}

	private Deck getDeck() {
		Deck deck = getEmptyDeck();
		Card card = getCard();
		deck.add(card);
		return deck;
	}

	private void executeRemoveCardCommand(Card card) {
		RemoveCardCommand command = new RemoveCardCommand(card, deck, notifier);
		command.execute();
	}
}
