package it.ck.cyberdeck.model;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
import it.ck.cyberdeck.persistance.JsonLibraryCardLoader;

import org.apache.commons.lang3.StringUtils;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Test;

public class CardLibraryTest {

  private CardLibrary cl;

  @Before
  public void setup() {
    cl = getCardLibrary();
  }

  @Test
  public void test() throws Exception {
    assertThat(cl.size(), is(equalTo(213)));
    assertThat(cl.getCardList().size(), is(equalTo(213)));
  }

  private CardLibrary getCardLibrary() {
    JsonLibraryCardLoader loader = new JsonLibraryCardLoader();
    CardLibrary cl = new CardLibrary(loader);
    return cl;
  }

  @Test
  public void eachCardHasAFurl() throws Exception {
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
