package it.ck.cyberdeck.model;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;
import org.junit.Test;

public class CardKeyTest {

	@Test
	public void theStringOfACardKeyisItsCode() {
		CardKey key = new CardKey(CardSet.CORE, 15);
		assertThat(key.toString(), is(equalTo(key.getCardCode())));
		assertThat(key.toString(), is("01015"));
	}
	
	@Test
	public void theCodeOfAKeyIstheCodeofItsSetplusTheNumberPAdded(){
		CardKey key = new CardKey(CardSet.CORE, 15);
		assertThat(key.getCardCode(), is("01015"));
	}

}
