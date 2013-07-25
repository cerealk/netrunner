package it.ck.cyberdeck.persistance;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import it.ck.cyberdeck.model.Card;
import it.ck.cyberdeck.model.CardLibrary;
import it.ck.cyberdeck.persistance.filesystem.FileSystemLibraryCardGateway;

import java.util.*;

import org.junit.Before;
import org.junit.Test;

public class TestDataParsing {


  private static final String TEST_PATH = "test/carddata.js";
  private static final String RAW_PATH = "res/raw/carddata.js";
	private CardLibrary cl;

  @Before
  public void setup() {
    cl = getCardLibrary(TEST_PATH);
  }
	
  private CardLibrary getCardLibrary(String path) {
    FileSystemLibraryCardGateway loader = new FileSystemLibraryCardGateway(path);
    List<Card> loadCards = loader.getCardLibrary().getCardList();
    CardLibrary cl = new CardLibrary();
    cl.addAll(loadCards);
    return cl;
  }

  @Test
  public void testLoyalityRetreival() throws Exception {
	  Collection<Card> cardList = cl.getCardList();
	  Card card = cardList.iterator().next();
	  assertThat(card.getReputation(), is(not(nullValue())));
  }
  
	@Test
	public void theAgendasArewithLoialtyZero() {
		Collection<Card> cards = getCardLibrary(RAW_PATH).getCardList();
		List<Card> agendas = new ArrayList<Card>(); 
		for(Card card : cards){
			if(card.isAgenda()){
				if(!card.isNeutral()){
					assertThat(card.getReputation(), is(nullValue()));
				}
				agendas.add(card);
			}
		}
		
		assertThat(Integer.valueOf(agendas.size()), is(24));
  }

}
