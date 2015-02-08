package it.ck.cyberdeck.model;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import it.ck.cyberdeck.model.group.ElementGroup;
import it.ck.cyberdeck.persistance.filesystem.FileSystemLibraryCardGateway;

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

  private CardLibrary getCardLibrary() {
    FileSystemLibraryCardGateway loader = new FileSystemLibraryCardGateway();
    CardLibrary cl = loader.loadCardLibrary();
    return cl;
  }

  @Test
  public void eachCardHasAnImageName() throws Exception {
    for (Card card : cl.getCardList()) {
      assertThat(card.getCardCode(), is(not(emptyString())));
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
  public void iCanGetTheCardsGroupedByTypePerSide() throws Exception {
	  List<ElementGroup<Card>> list = cl.getCardGroups(Side.RUNNER);
	  assertThat(list, is(not(nullValue())));
	  assertThat(list.size() > 0 , is(true));
  }
  
  @Test
  public void iCanGetTheCardsGroupedByTypePerIdentity() throws Exception {
	  Identity identity = new Identity("name", Side.CORP, Faction.HAAS_BIOROID, 45, 15, null);
  	List<ElementGroup<Card>> list = cl.getCardGroups(identity);
	  assertThat(list, is(not(nullValue())));
	  assertThat(list.size() > 0 , is(true));
  }
  
  @Test
  public void iCanGetTheCardsGroupedByTypePerIdentityIdentitiesExcluded() throws Exception {
	  Identity identity = new Identity("name", Side.CORP, Faction.HAAS_BIOROID, 45, 15, null);
  	List<ElementGroup<Card>> list = cl.getCardGroupsWithoutIdentities(identity);
	  assertThat(list, is(not(nullValue())));
	  assertThat(list.size() > 0 , is(true));
	  for(ElementGroup<Card> group: list){
		  assertThat(group.getType(), is(not(equalTo(CardType.IDENTITY))));
	  }
		  
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
