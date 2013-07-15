package it.ck.cyberdeck.model;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import it.ck.cyberdeck.persistance.FileSystemLibraryCardGateway;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hamcrest.*;
import org.junit.*;

public class CardLibraryTest {

  private CardLibrary cl;

  @Before
  public void setup() {
    cl = getCardLibrary();
  }

  @Test
  public void test() throws Exception {
    assertThat(cl.size(), is(equalTo(233)));
    assertThat(cl.getCardList().size(), is(equalTo(233)));
  }

  private CardLibrary getCardLibrary() {
    FileSystemLibraryCardGateway loader = new FileSystemLibraryCardGateway();
    List<Card> loadCards = loader.loadCards();
    CardLibrary cl = new CardLibrary();
    cl.addAll(loadCards);
    return cl;
  }

  @Test
  public void eachCardHasSet(){
	  for (Card card : cl.getCardList()) {
		  assertThat(card.getSet(), is(notNullValue()));
		
	}
  }
  
  @Test
  public void eachCardHasAnImageName() throws Exception {
    for (Card card : cl.getCardList()) {
      assertThat(card.getImageName(), is(not(emptyString())));
    }
  }

  @Test
  public void eachCardHasAName() throws Exception {
    for (Card card : cl.getCardList()) {
      assertThat(card.getName(), is(not(emptyString())));
    }
  }
  
  @Test
  public void eachCardHasASide(){
	  for (Card card : cl.getCardList()) {
	      assertThat(card.getSide(), is(not(nullValue())));
	  }  
  }
  
  @Test
  public void eachCardHasAType(){
  	for (Card card : cl.getCardList()) {
      assertThat(card.getType(), is(not(nullValue())));
  	}
  }
  
  @Test
  public void iCanGetTheIdentities() throws Exception {
	  List<Identity> identities = cl.getIdentities();
	  assertThat(identities.size()>0, is(true));
	  for(Identity card : identities){
	  	assertThat(card.side(), is(not(nullValue())));
	  	assertThat(card.faction(), is(not(nullValue())));
	  	assertThat(card.name(), is(not(nullValue())));
	  }
  }

  @Test
  public void iCanGetTheCardsGroupedByType() throws Exception {
	  List<CardGroup> list = cl.getCardGroups(Side.RUNNER);
	  assertThat(list, is(not(nullValue())));
	  assertThat(list.size() > 0 , is(true));
  }
  
  protected Matcher<? super String> emptyString() {
    return new TypeSafeMatcher<String>() {

      @Override
      public void describeTo(Description description) {
        description.appendText("the string is not empty");

      }

      @Override
      protected boolean matchesSafely(String item) {
        return StringUtils.isEmpty(item);
      }

    };
  }
}
