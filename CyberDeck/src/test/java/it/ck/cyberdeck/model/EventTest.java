package it.ck.cyberdeck.model;

import static org.junit.Assert.*;

import org.hamcrest.Matchers;
import org.junit.Test;

public class EventTest {

	@Test
	public void givenAnEvent_iCanGetTheSource() {
		Deck deck = new Deck(new Identity(null, null, null, null, null, null), "deck");
		Event event = new Event(deck);
		Deck source = event.getSource();
		assertThat(source, Matchers.is(deck));
	}
	
}
