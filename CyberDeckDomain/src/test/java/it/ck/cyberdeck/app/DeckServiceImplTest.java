package it.ck.cyberdeck.app;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;
import static it.ck.cyberdeck.fixtures.IdentityTestFactory.*;

import java.util.ArrayList;
import java.util.List;

import it.ck.cyberdeck.model.Deck;
import it.ck.cyberdeck.model.LibraryCardGateway;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

public class DeckServiceImplTest {

	@Rule public JUnitRuleMockery context = new JUnitRuleMockery();
	private LibraryCardGateway loader  = context.mock(LibraryCardGateway.class);
	private DeckService ds = new DeckServiceImpl(loader);
	
	
	@Test
	public void iCanCreateANewDeck() {

		Deck deck = ds.createDeck(getAnarchIdentity(), "deckName");
		assertThat(deck.getIdentity(), is(equalTo(getAnarchIdentity())));
		assertThat(deck.name(), is("deckName"));
	}
	
	@Test
	public void iCanSaveADeck() throws Exception {
		final Deck deck = new Deck(getAnarchIdentity(), "deckName");
		context.checking(new Expectations(){{
			oneOf(loader).saveDeck(with(deck));
		}});
		

		ds.saveDeck(deck);
	}
	
	@Test
	public void iCanGetTheDeckNames() throws Exception {
		final List<String> deckNames= new ArrayList<>();
		deckNames.add("deck1");
		deckNames.add("deck2");
		deckNames.add("deck3");
		context.checking(new Expectations(){{
			oneOf(loader).deckNames();will(returnValue(deckNames));
		}});
		List<String> nameList = ds.deckNames();
		assertThat(nameList, is(deckNames));
	}

	@Test
	public void iCanLoadADeckByName() throws Exception {
		final String deckName = "deckName";
		final Deck deck = new Deck(getAnarchIdentity(), "deckName");
		context.checking(new Expectations(){{
			oneOf(loader).loadDeck(with(deckName));will(returnValue(deck));
		}});
		

		Deck loadedDeck = ds.loadDeck(deckName);
		
		assertThat(loadedDeck, is(equalTo(deck)));
	}
	
	@Test
	public void iCanDeleteADeckByName() throws Exception {
		final String deckName = "deckName";
		context.checking(new Expectations(){{
			oneOf(loader).deleteDeck(with(deckName));
		}});
		

		ds.deleteDeck(deckName);
		
	}
	
	@Test
	public void iCanLoadACardLibrary() throws Exception {
		context.checking(new Expectations(){{
			oneOf(loader).loadCardLibrary();
		}});
		ds.loadCardLibrary();
	}
}
