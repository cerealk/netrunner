package it.ck.cyberdeck.persistence;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;
import it.ck.cyberdeck.model.CardLibrary;
import it.ck.cyberdeck.model.LibraryCardGateway;
import it.ck.cyberdeck.model.reputation.StandardReputationRuleFactory;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

public class CachedGatewayTest {
	@Rule public JUnitRuleMockery context = new JUnitRuleMockery();

	private LibraryCardGateway delegate = context.mock(LibraryCardGateway.class);
	
	private CachedGateway gw = new CachedGateway(delegate);
	
	@Test
	public void itDelegatesTheCallToDeckNames() {
		
		context.checking(new Expectations(){{
			oneOf(delegate).deckNames();
		}});
		
		gw.deckNames();
	}
	
	@Test
	public void itDelegatesTheCallToDeleteDeck() throws Exception {
		context.checking(new Expectations(){{
			oneOf(delegate).deleteDeck("");
		}});
		
		gw.deleteDeck("");
	}
	
	@Test
	public void itDelegatesTheCallToLoadDeck() throws Exception {
		context.checking(new Expectations(){{
			oneOf(delegate).loadDeck("");
		}});
		
		gw.loadDeck("");
	}

	@Test
	public void itDelegatesTheCallToSaveDeck() throws Exception {
		context.checking(new Expectations(){{
			oneOf(delegate).saveDeck(null);
		}});
		
		gw.saveDeck(null);
	}
	
	@Test
	public void itCachesTheCallsToLoadLibrary(){
		context.checking(new Expectations(){{
			oneOf(delegate).loadCardLibrary();will(returnValue(new CardLibrary(new StandardReputationRuleFactory())));
		}});
		
		CardLibrary cardLibrary = gw.loadCardLibrary();
		assertThat(cardLibrary, is(not(nullValue())));
		assertThat(gw.loadCardLibrary(),  is(sameInstance(cardLibrary)));
	}
}
