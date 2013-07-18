package it.ck.cyberdeck.persistance;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import it.ck.cyberdeck.model.Card;
import it.ck.cyberdeck.model.CardLibrary;
import it.ck.cyberdeck.persistance.FileSystemLibraryCardGateway;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class TestDataParsing {


  private static final String TEST_PATH = "test/carddata.js";
	private CardLibrary cl;

  @Before
  public void setup() {
    cl = getCardLibrary();
  }
	
  private CardLibrary getCardLibrary() {
    FileSystemLibraryCardGateway loader = new FileSystemLibraryCardGateway(TEST_PATH);
    List<Card> loadCards = loader.loadCards();
    CardLibrary cl = new CardLibrary();
    cl.addAll(loadCards);
    return cl;
  }

  @Test
  public void testLoyalityRetreival() throws Exception {
	  List<Card> cardList = cl.getCardList();
	  Card card = cardList.get(0);
	  assertThat(card.getReputation(), is(not(nullValue())));
  }
  
	@Test
	public void test() {
  	}

}
