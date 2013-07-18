package it.ck.cyberdeck.model;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import it.ck.cyberdeck.model.CardCounter.CardNotFoundException;
import it.ck.cyberdeck.model.Deck.TooManyCardOfTheSameTypeException;
import it.ck.cyberdeck.model.Deck.TooManyOutOfFactionCardsException;
import it.ck.cyberdeck.model.Deck.WrongSideException;

import org.junit.*;

public class DeckTest {

  private Deck deck;

  @Before
  public void setup() {
    deck = getDeck(getIdentity(2));
  }

  @Test
  public void eachDeckHasAnIdentity() {
    Deck deck = getDeck();
    Identity identity = deck.getIdentity();
    assertThat(identity, is(not(nullValue())));
  }

  @Test
  public void anEptyDeckHasJustItsIdentity() throws Exception {
    assertThat(deck.size(), is(equalTo(0)));
  }

  @Test
  public void anEmptyDeckIsNotValid() throws Exception {
    assertThat(deck.isValid(), is(false));
  }

  @Test
  public void whenIAddACardToADeckTheCardCountIsIncremented() throws Exception {
    int deckSize = deck.size();
    deck.add(getCard());
    assertThat(deck.size(), is(equalTo(deckSize + 1)));
  }
  
  @Test
  public void aValidDeckShouldContainAtLeastANumberOfCardsAsTheIdentityStates() throws Exception {
    deck.add(getCard());
    deck.add(getCard());
    assertThat(deck.isValid(), is(true));
  }

  @Test
  public void aDeckWithLessCardThanTheMinimumIsInvalid() throws Exception {
    deck.add(getCard());
    assertThat(deck.isValid(), is(not(true)));
  }

  @Test(expected = TooManyCardOfTheSameTypeException.class)
  public void aDeckCantHaveMoreThanThreeCopiesPerCard() throws Exception {
    deck.add(getCard());
    deck.add(getCard());
    deck.add(getCard());
    deck.add(getCard());
  }

  @Test
  public void whenIAddACardItsCountIsUpdated() {
    deck.add(getCard());
    assertThat(deck.cardCount(getCard()), is(equalTo(1)));
  }

  @Test
  public void anyCardCanBeRemoved() throws Exception {
    deck.add(getCard("card1", Side.RUNNER));
    deck.add(getCard("card1", Side.RUNNER));
    assertThat(deck.cardCount(getCard("card1", Side.RUNNER)), is(equalTo(2)));
    deck.remove(getCard("card1", Side.RUNNER));
    assertThat(deck.cardCount(getCard("card1", Side.RUNNER)), is(equalTo(1)));

  }

  @Test(expected = CardNotFoundException.class)
  public void aCardNotInTheDeckCantBeRemoved() throws Exception {
    deck.remove(getCard("card2", Side.RUNNER));
  }

  @Test(expected = WrongSideException.class)
  public void aDeckCanHaveOnlyCardsOfTheSameSideOfItsIdentity() throws Exception {
    Identity identity = new Identity("identity RUNNER", Side.RUNNER, Faction.SHAPER, 2, 15);
    deck = getDeck(identity);
    deck.add(getCard("name", Side.CORP));
  }

  @Test(expected = TooManyOutOfFactionCardsException.class)
  public void aDeckCanHaveOutOfFactionCardsUpToTheReputationOfTheIdentity() throws Exception {
    Card card1 = new Card("card1", Side.RUNNER, Faction.ANARCH, 5, null);
    Card card2 = new Card("card2", Side.RUNNER, Faction.ANARCH, 5, null);
    Card card3 = new Card("card3", Side.RUNNER, Faction.ANARCH,5, null);
    Card card4 = new Card("card4", Side.RUNNER,Faction.ANARCH, 5, null);
    deck.add(card1);
    deck.add(card2);
    deck.add(card3);
    deck.add(card4);
  }
  
  @Test
  @Ignore
  public void theDeckSize_defineTheNumberOfRequiredAgendaPoints() throws Exception {
    fail("not yet implemented!!");
  }
  
  @Test
  public void iCanAddMoreCopiesOfACard(){
    deck.add(getCard(), 2);
  }

  private Deck getDeck(Identity identity) {
    return new Deck(identity);
  }

  private Identity getIdentity(int minimumCardCount) {
    return new Identity("identity: " + minimumCardCount, Side.RUNNER, Faction.SHAPER, minimumCardCount, 15);
  }

  private Card getCard() {
    return getCard("std", Side.RUNNER);
  }

  private Card getCard(String name, Side side) {
    Card card = new Card(name, side,Faction.SHAPER, 1, null);
    return card;
  }

  private Deck getDeck() {
    return getDeck(getIdentity());
  }

  private Identity getIdentity() {
    return getIdentity(2);
  }

}
