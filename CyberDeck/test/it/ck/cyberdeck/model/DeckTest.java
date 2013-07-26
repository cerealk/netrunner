package it.ck.cyberdeck.model;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import it.ck.cyberdeck.model.CardCounter.CardNotFoundException;
import it.ck.cyberdeck.model.Deck.CantBeAttachedException;
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
  public void anmEptyDeckHasJustItsIdentity() throws Exception {
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
  
  @Test(expected = TooManyCardOfTheSameTypeException.class)
  public void testName() throws Exception {
	  deck.add(getUniqueCard());
	  deck.add(getUniqueCard());
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
  
  @Test
  public void iCanRemoveAllTheCopiesOfACard() throws Exception {
  	Card card = getCard("card1", Side.RUNNER);
		deck.add(card);
    deck.add(card);
    assertThat(deck.cardCount(card), is(equalTo(2)));
    deck.removeAll(card);
    assertThat(deck.cardCount(card), is(equalTo(0)));
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
    Card card1 = getCard("card1", Side.RUNNER, Faction.ANARCH, 1, 5);
    Card card2 = getCard("card2", Side.RUNNER, Faction.ANARCH, 2, 5);
    Card card3 = getCard("card3", Side.RUNNER, Faction.ANARCH, 3, 5);;
    Card card4 = getCard("card4", Side.RUNNER, Faction.ANARCH, 4, 5);;
    deck.add(card1);
    deck.add(card2);
    deck.add(card3);
    deck.add(card4);
  }
  
  

	private Card getCard(String name, Side side, Faction anarch, int num,
      int reputation) {
  	CardData cardData = new CardData();
  	cardData.name = name;
  	cardData.set = CardSet.CORE;
  	cardData.identity = anarch;
  	cardData.side = side;
  	cardData.loyalty = reputation;
  	cardData.num =num;
  	cardData.unique = false;
	  Card card1 = new Card(cardData);
	  return card1;
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
  
  @Test
  public void givenADeckICanAddACardOfTheSameFaction(){
  	Deck deck = new Deck(getIdentity(45), "testDeck");
  	CardData cardData = new CardData();
  	cardData.set = CardSet.CORE;
  	cardData.identity = Faction.SHAPER;
  	cardData.side = Side.RUNNER;
  	cardData.loyalty = 2;
  	cardData.num =3;
  	cardData.unique = false;
  	
		Card card = new Card(cardData);
  	deck.add(card);
  	assertThat(deck.cardCount(card), is(1));
  }
  
  @Test
  public void givenACardOutOfFactionICanAddItIfItCanBeAttached() throws Exception {
  	Deck deck = new Deck(getIdentity(45), "testDeck");
  	CardData cardData = new CardData();
  	cardData.set = CardSet.CORE;
  	cardData.identity = Faction.ANARCH;
  	cardData.side = Side.RUNNER;
  	cardData.loyalty = 2;
  	cardData.num =3;
  	cardData.unique = false;
		Card card = new Card(cardData );
  	deck.add(card);
  	assertThat(deck.cardCount(card), is(1));
  }
  
  @Test(expected=CantBeAttachedException.class)
  public void givenACardOutOfFactionICantAddItIfITCantBeAttached() throws Exception {
  	Deck deck = new Deck(getIdentity(45), "testDeck");
  	CardData cardData = new CardData();
  	cardData.set = CardSet.CORE;
  	cardData.identity = Faction.ANARCH;
  	cardData.side = Side.RUNNER;
  	cardData.loyalty = null;
  	cardData.num =3;
  	cardData.unique = false;
		Card card = new Card(cardData );
  	deck.add(card);
  	assertThat(deck.cardCount(card), is(1));
  }
  
  @Test(expected=CantBeAttachedException.class)
  public void givenANeutralCardICantAddItIfITCantBeAttached() throws Exception {
  	Deck deck = new Deck(getIdentity(45), "testDeck");
  	CardData cardData = new CardData();
  	cardData.set = CardSet.CORE;
  	cardData.identity = Faction.ANARCH;
  	cardData.side = Side.RUNNER;
  	cardData.loyalty = null;
  	cardData.num =3;
  	cardData.unique = false;
		Card card = new Card(cardData );
  	deck.add(card);
  	assertThat(deck.cardCount(card), is(1));
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
  
  private Card getUniqueCard(){
  	CardData data = new CardData();
  	data.name = "name";
  	data.side = Side.RUNNER;
  	data.identity = Faction.SHAPER;
  	data.loyalty = 1;
  	data.unique = true;
  	Card card = new Card(data);
  	return card;
  }

  private Card getCard(String name, Side side) {
  	CardData data = new CardData();
  	data.name = name;
  	data.side = side;
  	data.identity = Faction.SHAPER;
  	data.loyalty = 1;
  	data.unique = false;
    Card card = new Card(data);
    return card;
  }

  private Deck getDeck() {
    return getDeck(getIdentity());
  }

  private Identity getIdentity() {
    return getIdentity(2);
  }
  

}
