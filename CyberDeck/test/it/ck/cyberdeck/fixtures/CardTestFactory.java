package it.ck.cyberdeck.fixtures;

import it.ck.cyberdeck.model.Card;
import it.ck.cyberdeck.model.CardData;
import it.ck.cyberdeck.model.CardSet;
import it.ck.cyberdeck.model.CardType;
import it.ck.cyberdeck.model.Faction;
import it.ck.cyberdeck.model.Side;

public class CardTestFactory{
	public static Card getAnarchCard() {
		Faction faction = Faction.ANARCH;
		return getCard(faction);
	}
	public static Card getShaperCard() {
		Faction shaper = Faction.SHAPER;
		return getCard(shaper);
	}
	
	public static Card getCard(Faction faction) {
		return new Card("Name", Side.RUNNER, faction, 5, null);
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
	
}