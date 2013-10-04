package it.ck.cyberdeck.model;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static it.ck.cyberdeck.model.CardType.*;
import static it.ck.cyberdeck.fixtures.CardTestFactory.*;
import static it.ck.cyberdeck.fixtures.IdentityTestFactory.*;
import it.ck.cyberdeck.model.CardCounter.CardNotFoundException;
import it.ck.cyberdeck.model.Deck.CantBeAttachedException;
import it.ck.cyberdeck.model.Deck.TooManyCardOfTheSameTypeException;
import it.ck.cyberdeck.model.Deck.TooManyOutOfFactionCardsException;
import it.ck.cyberdeck.model.Deck.WrongSideException;

import org.apache.commons.lang3.Range;
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
	
	@Test
	public void aCardWithCountZeroHasNoEntry(){
		Card card = getCard("card1", Side.RUNNER);
		deck.add(card);
		assertThat(deck.cardCount(card), is(equalTo(1)));
		deck.remove(card);
		assertThat(deck.cardCount(card), is(equalTo(0)));
		assertThat(deck.cards().size(), is(0));
	}

	@Test(expected = CardNotFoundException.class)
	public void aCardNotInTheDeckCantBeRemoved() throws Exception {
		deck.remove(getCard("card2", Side.RUNNER));
	}

	@Test(expected = WrongSideException.class)
	public void aDeckCanHaveOnlyCardsOfTheSameSideOfItsIdentity()
	    throws Exception {
		Identity identity = new Identity("identity RUNNER", Side.RUNNER,
		    Faction.SHAPER, 2, 15, null);
		deck = getDeck(identity);
		deck.add(getCard("name", Side.CORP));
	}

	@Test(expected = TooManyOutOfFactionCardsException.class)
	public void aDeckCanHaveOutOfFactionCardsUpToTheReputationOfTheIdentity()
	    throws Exception {
		Card card1 = getCard("card1", Side.RUNNER, Faction.ANARCH, 1, 5, HARDWARE);
		Card card2 = getCard("card2", Side.RUNNER, Faction.ANARCH, 2, 5, HARDWARE);
		Card card3 = getCard("card3", Side.RUNNER, Faction.ANARCH, 3, 5, HARDWARE);
		Card card4 = getCard("card4", Side.RUNNER, Faction.ANARCH, 4, 5, HARDWARE);
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
		cardData.name="card";
		cardData.set = CardSet.CORE;
		cardData.identity = Faction.SHAPER;
		cardData.side = Side.RUNNER;
		cardData.loyalty = 2;
		cardData.num = 3;
		cardData.unique = false;
		cardData.type = CardType.HARDWARE;

		Card card = new Card(cardData);
		deck.add(card);
		assertThat(deck.cardCount(card), is(1));
	}

	@Test
	public void givenACardOutOfFactionICanAddItIfItCanBeAttached()
	    throws Exception {
		Deck deck = new Deck(getIdentity(45), "testDeck");
		Card card = getCard("test",Side.RUNNER, Faction.ANARCH, 3, 2, CardType.HARDWARE);
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
		Deck corpDeck = getCorpDeck();
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
		assertThat(corpDeck.checkStatus().getAgendaRange(), is(equalTo(Range.between(18,19))));
	}
	
	@Test
	public void ifA45_49CardCorpDeckHasNot20_21APIsInvalid(){
		Deck corpDeck = getCorpDeck();
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
		corpDeck.add(getCorpCardWithNoAgenda("card14",14),3);
		corpDeck.add(getCorpCardWithAgenda("agenda1", 5, 15),3);
		corpDeck.add(getCorpCardWithAgenda("agenda2",5, 16),1);
		
		assertThat(corpDeck.checkStatus().status(), is(StatusCode.VALID));
		assertThat(corpDeck.checkStatus().getAgendaRange(), is(equalTo(Range.between(20,21))));
	}
	
	@Test
	public void aCorpDeckWith40CardsShouldHave18_19AgendaPoints(){
		Deck corpDeck = getCorpDeck();
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
		assertThat(corpDeck.checkStatus().getAgendaRange(), is(equalTo(Range.between(18,19))));
		
	}
	
	@Test
	public void theDeckStatusContainsTheDeckSize(){
		twoCardDeck();
		deck.add(getCard());
		assertThat(deck.checkStatus().cardCount(), is(3));
	}
	
	@Test
	public void theDeckStatusContainsTheMinimumDeckSize(){
		twoCardDeck();
		assertThat(deck.checkStatus().minDeckSize(), is(2));
	}

	@Test
	public void theDeckStatusContainsInfoAboutTheAgendaPoints() throws Exception {
		Deck corpDeck = getCorpDeckWithOneAgenda();
		assertThat(corpDeck.checkStatus().getAgendaPoints(), is(3));
	}
	
	@Test
	public void theDeckStatusContainsInfoAboutTheCorrectAgendaPointRange() throws Exception {
		Deck corpDeck = getCorpDeckWithOneAgenda();
		assertThat(corpDeck.checkStatus().getAgendaRange(), is(equalTo(Range.between(18,19))));
	}
	
	@Test
	public void givenADeckWithARunnerIdentity_itIsARunnerDeck(){
		assertThat(deck.isCorpDeck(), is(not(true)));
	}
	
	@Test
	public void givenADeckWithACorpIdentity_itIsACorpDeck(){
		assertThat(getCorpDeck().isCorpDeck(), is(true));
	}

	@Test
	public void theDeckStatusContainsInformationsAboutTheMaxReputation() throws Exception {
		assertThat(deck.checkStatus().getReputationCap(), is(15));
	}
	
	@Test
	public void theDeckStatusContainsInformationsAboutTheActualReputation() throws Exception {
		deck.add(getAnarchCard());
		assertThat(deck.checkStatus().getReputation(), is(5));
	}
	
	private Deck getCorpDeckWithOneAgenda() {
		Deck corpDeck = getCorpDeck();
		corpDeck.add(getCorpCardWithAgenda("agenda1", 3, 1));
		return corpDeck;
	}

	private Deck getCorpDeck() {
		Identity identity = getHBIdentity();
		Deck corpDeck = new Deck(identity  , "corp deck");
		return corpDeck;
	}
	
	private Deck getDeck(Identity identity) {
		return new Deck(identity);
	}

	private Identity getIdentity(int minimumCardCount) {
		return new Identity("identity: " + minimumCardCount, Side.RUNNER,
		    Faction.SHAPER, minimumCardCount, 15, null);
	}

	private Deck getDeck() {
		return getDeck(getIdentity());
	}

	private Identity getIdentity() {
		return getIdentity(2);
	}
	

	private void twoCardDeck() {
		deck.add(getCard());
		deck.add(getCard());
	}

}
