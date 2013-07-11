package it.ck.cyberdeck;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import it.ck.cyberdeck.app.DeckService;
import it.ck.cyberdeck.app.DeckServiceImpl;
import it.ck.cyberdeck.model.*;

import org.junit.Test;

public class DeckServicetest {

	 
	
	private DeckService ds = new DeckServiceImpl();
	
	@Test
  public void withTheDeckServiceICanCreateADeck() throws Exception {
	  Identity identity = new Identity("identity", Side.RUNNER, Faction.ANARCH, 45, 15);
		String deckName = "testDeck";
		Deck deck = ds.createDeck(identity, deckName);
		assertThat(deck, equalTo(new Deck(identity, deckName)));
  }
	
//	@Test
//	public void withTheDsICanaddACardToadeck(){
//		Identity identity = new Identity("identity", Side.RUNNER, Faction.ANARCH, 45, 15);
//		String deckName = "testDeck";
//		Deck deck = new Deck(identity, deckName);
//		
//		assertThat(deck.size(), equalTo(0));
//		
//		Card card = new Card("carta", Side.RUNNER, 2);
//		
//		ds.addCard(deck, card);
//		assertThat(deck.size(), equalTo(1));
//	}

}
