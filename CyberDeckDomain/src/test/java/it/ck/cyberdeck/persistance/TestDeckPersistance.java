package it.ck.cyberdeck.persistance;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import it.ck.cyberdeck.Fixtures;
import it.ck.cyberdeck.model.*;
import it.ck.cyberdeck.model.reputation.StandardReputationRule;
import it.ck.cyberdeck.persistance.filesystem.FileSystemLibraryCardGateway;

import org.junit.Test;

public class TestDeckPersistance {

	LibraryCardGateway gw = new FileSystemLibraryCardGateway(Fixtures.RAW_PATH);

	@Test
	public void givenADeckICanpersistIt() {

		CardLibrary cl = gw.loadCardLibrary();

		Identity identity = new Identity(cl.getCard(new CardKey(CardSet.CORE, 1)), new StandardReputationRule());
		Deck deck = new Deck(identity, "testDeck1");
		
		deck.add(cl.getCard(new CardKey(CardSet.CORE, 2)));
		deck.add(cl.getCard(new CardKey(CardSet.CORE, 2)));
		deck.add(cl.getCard(new CardKey(CardSet.CORE, 3)));
		
		gw.saveDeck(deck);
		
	}

	@Test
	public void givenADeckNameICanLoadIt(){
		String name = "testDeck";
		Deck deck = gw.loadDeck(name);
		
		assertThat(deck, is(not(nullValue())));
		assertThat(deck.name(), is("testDeck"));
		assertThat(deck.size(), is(3));
		assertThat(deck.getIdentity().key().getCardCode(), is("01001"));
		
	}
	
}
