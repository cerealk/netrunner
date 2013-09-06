package it.ck.cyberdeck.model;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import it.ck.cyberdeck.fixtures.CardTestFactory;
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
	public void anEmptyDeckHasJustItsIdentity() throws Exception {
		assertThat(deck.size(), is(equalTo(0)));
	}

	@Test
	public void whenIAddACardToADeckTheCardCountIsIncremented() throws Exception {
		int deckSize = deck.size();
		deck.add(getCard());
		assertThat(deck.size(), is(equalTo(deckSize + 1)));
	}

	@Test
	public void anEmptyDeckIsInvalid(){
		assertThat(deck.checkStatus().status(), is(StatusCode.INVALID));
	}
	
	@Test
	public void aValidDeckShouldContainAtLeastANumberOfCardsAsTheIdentityStates()
	    throws Exception {
		twoCardDeck();
		assertThat(deck.checkStatus().status(), is(StatusCode.VALID));
	}

	@Test(expected = TooManyCardOfTheSameTypeException.class)
	public void aDeckCantHaveMoreThanThreeCopiesPerCard() throws Exception {
		twoCardDeck();
		twoCardDeck();
	}

	@Test(expected = TooManyCardOfTheSameTypeException.class)
	public void testName() throws Exception {
		deck.add(CardTestFactory.getUniqueCard());
		deck.add(CardTestFactory.getUniqueCard());
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
	public void aDeckCanHaveOnlyCardsOfTheSameSideOfItsIdentity()
	    throws Exception {
		Identity identity = new Identity("identity RUNNER", Side.RUNNER,
		    Faction.SHAPER, 2, 15);
		deck = getDeck(identity);
		deck.add(getCard("name", Side.CORP));
	}

	@Test(expected = TooManyOutOfFactionCardsException.class)
	public void aDeckCanHaveOutOfFactionCardsUpToTheReputationOfTheIdentity()
	    throws Exception {
		Card card1 = CardTestFactory.getCard("card1", Side.RUNNER, Faction.ANARCH, 1, 5);
		Card card2 = CardTestFactory.getCard("card2", Side.RUNNER, Faction.ANARCH, 2, 5);
		Card card3 = CardTestFactory.getCard("card3", Side.RUNNER, Faction.ANARCH, 3, 5);
		Card card4 = CardTestFactory.getCard("card4", Side.RUNNER, Faction.ANARCH, 4, 5);
		deck.add(card1);
		deck.add(card2);
		deck.add(card3);
		deck.add(card4);
	}

	@Test
	public void aDeckWithNotEnoughCardsHasAnInvalidStatus() {
		deck.add(getCard());
		assertThat(deck.checkStatus().status(), is(StatusCode.INVALID));
		assertThat(deck.checkStatus().reason(), is(Reason.FEW_CARDS));
	}

	@Test
	public void aDeckWithEnoughCardsHasAValidStatus(){
		twoCardDeck();
		assertThat(deck.checkStatus().status(), is(StatusCode.VALID));
	}
	
	@Test
	public void aCorpDeckShouldHaveARequiredNumberOfAgendaPoints(){
		twoCardDeck();
		assertThat(deck.checkStatus().status(), is(StatusCode.VALID));
	}
	


	@Test
	public void iCanAddMoreCopiesOfACard() {
		deck.add(getCard(), 2);
	}

	@Test
	public void givenADeckICanAddACardOfTheSameFaction() {
		Deck deck = new Deck(getIdentity(45), "testDeck");
		CardData cardData = new CardData();
		cardData.set = CardSet.CORE;
		cardData.identity = Faction.SHAPER;
		cardData.side = Side.RUNNER;
		cardData.loyalty = 2;
		cardData.num = 3;
		cardData.unique = false;

		Card card = new Card(cardData);
		deck.add(card);
		assertThat(deck.cardCount(card), is(1));
	}

	@Test
	public void givenACardOutOfFactionICanAddItIfItCanBeAttached()
	    throws Exception {
		Deck deck = new Deck(getIdentity(45), "testDeck");
		CardData cardData = new CardData();
		cardData.set = CardSet.CORE;
		cardData.identity = Faction.ANARCH;
		cardData.side = Side.RUNNER;
		cardData.loyalty = 2;
		cardData.num = 3;
		cardData.unique = false;
		Card card = new Card(cardData);
		deck.add(card);
		assertThat(deck.cardCount(card), is(1));
	}

	@Test(expected = CantBeAttachedException.class)
	public void givenACardOutOfFactionICantAddItIfITCantBeAttached()
	    throws Exception {
		Deck deck = new Deck(getIdentity(45), "testDeck");
		CardData cardData = new CardData();
		cardData.set = CardSet.CORE;
		cardData.identity = Faction.ANARCH;
		cardData.side = Side.RUNNER;
		cardData.loyalty = null;
		cardData.num = 3;
		cardData.unique = false;
		Card card = new Card(cardData);
		deck.add(card);
		assertThat(deck.cardCount(card), is(1));
	}

	@Test(expected = CantBeAttachedException.class)
	public void givenANeutralCardICantAddItIfITCantBeAttached() throws Exception {
		Deck deck = new Deck(getIdentity(45), "testDeck");
		CardData cardData = new CardData();
		cardData.set = CardSet.CORE;
		cardData.identity = Faction.ANARCH;
		cardData.side = Side.RUNNER;
		cardData.loyalty = null;
		cardData.num = 3;
		cardData.unique = false;
		Card card = new Card(cardData);
		deck.add(card);
		assertThat(deck.cardCount(card), is(1));
	}
	
	@Test
	public void ifA40_44CardCorpDeckHasNot18_19APIsInvalid(){
		Identity identity = getCorpIdentity();
		Deck corpDeck = new Deck(identity , "corp deck");
		corpDeck.add(getCorpCardWithNoAgenda("card1",1),3);
		corpDeck.add(getCorpCardWithNoAgenda("card2",2),3);
		corpDeck.add(getCorpCardWithNoAgenda("card3",3),3);
		corpDeck.add(getCorpCardWithNoAgenda("card4",4),3);
		corpDeck.add(getCorpCardWithNoAgenda("card5",5),3);
		corpDeck.add(getCorpCardWithNoAgenda("card6",6),3);
		corpDeck.add(getCorpCardWithNoAgenda("card7",7),3);
		corpDeck.add(getCorpCardWithNoAgenda("card8",8),3);
		corpDeck.add(getCorpCardWithNoAgenda("card9",9),3);
		corpDeck.add(getCorpCardWithNoAgenda("card10",10),3);
		corpDeck.add(getCorpCardWithNoAgenda("card11",11),3);
		corpDeck.add(getCorpCardWithNoAgenda("card12",12),3);
		corpDeck.add(getCorpCardWithAgenda("agenda1", 4, 13),3);
		corpDeck.add(getCorpCardWithAgenda("agenda2",3, 14),1);
		
		assertThat(corpDeck.checkStatus().status(), is(StatusCode.INVALID));
	}
	
	@Test
	public void ifA45_49CardCorpDeckHasNot20_21APIsInvalid(){
		Identity identity = getCorpIdentity();
		Deck corpDeck = new Deck(identity , "corp deck");
		corpDeck.add(getCorpCardWithNoAgenda("card1",1),3);
		corpDeck.add(getCorpCardWithNoAgenda("card2",2),3);
		corpDeck.add(getCorpCardWithNoAgenda("card3",3),3);
		corpDeck.add(getCorpCardWithNoAgenda("card4",4),3);
		corpDeck.add(getCorpCardWithNoAgenda("card5",5),3);
		corpDeck.add(getCorpCardWithNoAgenda("card6",6),3);
		corpDeck.add(getCorpCardWithNoAgenda("card7",7),3);
		corpDeck.add(getCorpCardWithNoAgenda("card8",8),3);
		corpDeck.add(getCorpCardWithNoAgenda("card9",9),3);
		corpDeck.add(getCorpCardWithNoAgenda("card10",10),3);
		corpDeck.add(getCorpCardWithNoAgenda("card11",11),3);
		corpDeck.add(getCorpCardWithNoAgenda("card12",12),3);
		corpDeck.add(getCorpCardWithNoAgenda("card13",13),3);
		corpDeck.add(getCorpCardWithNoAgenda("card13",14),3);
		corpDeck.add(getCorpCardWithAgenda("agenda1", 5, 15),3);
		corpDeck.add(getCorpCardWithAgenda("agenda2",5, 16),1);
		
		assertThat(corpDeck.checkStatus().status(), is(StatusCode.VALID));
	}
	
	@Test
	public void aCorpDeckWith40CardsShouldHave18_19AgendaPoints(){
		Identity identity = getCorpIdentity();
		Deck corpDeck = new Deck(identity , "corp deck");
		corpDeck.add(getCorpCardWithNoAgenda("card1",1),3);
		corpDeck.add(getCorpCardWithNoAgenda("card2",2),3);
		corpDeck.add(getCorpCardWithNoAgenda("card3",3),3);
		corpDeck.add(getCorpCardWithNoAgenda("card4",4),3);
		corpDeck.add(getCorpCardWithNoAgenda("card5",5),3);
		corpDeck.add(getCorpCardWithNoAgenda("card6",6),3);
		corpDeck.add(getCorpCardWithNoAgenda("card7",7),3);
		corpDeck.add(getCorpCardWithNoAgenda("card8",8),3);
		corpDeck.add(getCorpCardWithNoAgenda("card9",9),3);
		corpDeck.add(getCorpCardWithNoAgenda("card10",10),3);
		corpDeck.add(getCorpCardWithNoAgenda("card11",11),3);
		corpDeck.add(getCorpCardWithNoAgenda("card12",12),3);
		corpDeck.add(getCorpCardWithAgenda("agenda1", 5, 13),3);
		corpDeck.add(getCorpCardWithAgenda("agenda2",3, 14),1);
		
		assertThat(corpDeck.checkStatus().status(), is(StatusCode.VALID));
		
	}
	
	@Test
	public void theDeckStatusContainsTheDeckSize(){
		twoCardDeck();
		
		assertThat(deck.checkStatus().minDeckSize(), is(2));
	}

	private void twoCardDeck() {
		deck.add(getCard());
		deck.add(getCard());
	}
	
	@Test
	@Ignore
	public void theDeckStatusContainsTheMinimumDeckSize(){
		fail("not yet implemented");
	}

	private Card getCorpCardWithAgenda(String name, int agendapoints, int num) {
		CardData data = new CardData();
		data.name = name;
		data.side = Side.CORP;
		data.identity = Faction.HAAS_BIOROID;
		data.loyalty = 1;
		data.unique = false;
		data.agendapoints = agendapoints;
		data.num = num;
		Card card = new Card(data);
		
		return card;
  }

	private Card getCorpCardWithNoAgenda(String name, int num) {
		CardData data = new CardData();
		data.name = name;
		data.side = Side.CORP;
		data.identity = Faction.HAAS_BIOROID;
		data.loyalty = 1;
		data.unique = false;
		data.agendapoints = 0;
		data.num = num;
		Card card = new Card(data);
		return card;
  }

	private Identity getCorpIdentity() {
	  return new Identity("HB", Side.CORP, Faction.HAAS_BIOROID, 40, 15);
  }

	private Deck getDeck(Identity identity) {
		return new Deck(identity);
	}

	private Identity getIdentity(int minimumCardCount) {
		return new Identity("identity: " + minimumCardCount, Side.RUNNER,
		    Faction.SHAPER, minimumCardCount, 15);
	}

	private Card getCard() {
		return getCard("std", Side.RUNNER);
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
