package it.ck.cyberdeck.model;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;
import static it.ck.cyberdeck.fixtures.CardTestFactory.*;
import org.junit.Test;


public class CardEntryTest  {
	@Test
	public void twoEntriesAreEqualsIfTheyHaveTheSameCardAndTheSameNumber() throws Exception {
		CardEntry entry1 = getEntry();
		CardEntry entry2 = getEntry();
		assertThat(entry1, is(equalTo(entry2)));
	}
	
	@Test
	public void twoEqualEntriesHaveTheSameHashCode(){
		CardEntry entry1 = getEntry();
		CardEntry entry2 = getEntry();
		assertThat(entry1, is(equalTo(entry2)));
		assertThat(entry1.hashCode(), is(equalTo(entry2.hashCode())));
	}

	@Test
	public void anEntryCannotBeEqualToANonEntryObject() throws Exception {
		CardEntry entry1 = getEntry();
		assertThat(entry1.equals(""), is(false));
	}
	
	@Test
	public void aCardISAlwaysEqualToItself() throws Exception {
		CardEntry entry1 = getEntry();
		assertThat(entry1.equals(entry1), is(true));
	}
	
	@Test
	public void anEntryisNotEqualToNull() throws Exception {
		CardEntry entry1 = getEntry();
		assertThat(entry1.equals(null), is(false));
	}
	
	private CardEntry getEntry() {
		return new CardEntry(getCard(), 1);
	}
	
	@Test
	public void iCanGetTheCardOfEveryEntry() throws Exception {
		CardEntry entry = getEntry();
		assertThat(entry.getCard(), is(not(nullValue())));
	}
}
