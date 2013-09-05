package it.ck.cyberdeck.fixtures;

import it.ck.cyberdeck.model.Card;
import it.ck.cyberdeck.model.CardData;
import it.ck.cyberdeck.model.CardSet;
import it.ck.cyberdeck.model.CardType;
import it.ck.cyberdeck.model.Faction;
import it.ck.cyberdeck.model.Side;

public class CardTestFactory{
	public static Card getAnarchCard() {
		return getCard(Side.RUNNER,Faction.ANARCH);
	}
	public static Card getShaperCard() {
		return getCard(Side.RUNNER,Faction.SHAPER);
	}
	
	public static Card getCard(Side side, Faction faction) {
		return getCard("name", side,faction, 1,5);
	}
	public static Card getHBAgendaCard() {
		CardData cardData = new CardData();
		cardData.name ="agenda";
		cardData.side = Side.CORP;
		cardData.identity = Faction.HAAS_BIOROID;
		cardData.loyalty = 5;
		cardData.set = CardSet.CORE;
		cardData.num = 15;
		cardData.type = CardType.AGENDA;
		return new Card(cardData);
	}
	
	
	public static Card getCard(String name, Side side, Faction faction, int num,
		    int reputation) {
			CardData cardData = new CardData();
			cardData.name = name;
			cardData.set = CardSet.CORE;
			cardData.identity = faction;
			cardData.side = side;
			cardData.loyalty = reputation;
			cardData.num = num;
			cardData.unique = false;
			Card card1 = new Card(cardData);
			return card1;
		}
	
	
	public static Card getUniqueCard() {
		CardData data = new CardData();
		data.name = "name";
		data.side = Side.RUNNER;
		data.identity = Faction.SHAPER;
		data.loyalty = 1;
		data.unique = true;
		Card card = new Card(data);
		return card;
	}
}