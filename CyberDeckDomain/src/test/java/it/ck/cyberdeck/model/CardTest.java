package it.ck.cyberdeck.model;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;
import static it.ck.cyberdeck.fixtures.CardTestFactory.*;
import org.junit.Test;

public class CardTest {

	@Test
	public void twoCardsWithTheSameKeyAreEqual() {
		Card card1 = getCard();
		Card card2 = getCard();
		assertThat(card1.equals(card2), is(true));
		assertThat(card2.equals(card1), is(true));
	}
	
	@Test
	public void aCardCanBeEqualToACardOnly(){
		Card card1 = getCard();
		assertThat(card1.equals(""), is(false));
	}
	
	
	

}
