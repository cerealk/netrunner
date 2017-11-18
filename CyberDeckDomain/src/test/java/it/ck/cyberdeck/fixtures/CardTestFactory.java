package it.ck.cyberdeck.fixtures;

import it.ck.cyberdeck.model.Card;
import it.ck.cyberdeck.model.CardData;
import it.ck.cyberdeck.model.CardSet;
import it.ck.cyberdeck.model.CardType;
import it.ck.cyberdeck.model.Faction;
import it.ck.cyberdeck.model.Side;

public class CardTestFactory {
	public static Card getAnarchCard() {
		return getCard(Side.RUNNER, Faction.ANARCH);
	}

	public static Card getShaperCard() {
		return getCard(Side.RUNNER, Faction.SHAPER);
	}

	private static Card getCard(Side side, Faction faction) {
		return getCard("name", side, faction, 1, 5, CardType.HARDWARE);
	}

	public static Card getHBAgendaCard() {
		CardData cardData = new CardData();
		cardData.name = "agenda";
		cardData.side = Side.CORP;
		cardData.faction = Faction.HAAS_BIOROID;
		cardData.influence = 5;
		cardData.set = CardSet.CORE;
		cardData.num = 15;
		cardData.type = CardType.AGENDA;
		return new Card(cardData);
	}

	public static Card getCard(String name, Side side, Faction faction,
			int num, int reputation, CardType type) {
		CardData cardData = getRunnerCardData(name, side, faction, num, reputation,
				type);
		return new Card(cardData);
	}

	private static CardData getRunnerCardData(String name, Side side,
			Faction faction, int num, int reputation, CardType type) {
		CardData cardData = new CardData();
		cardData.name = name;
		cardData.set = CardSet.CORE;
		cardData.faction = faction;
		cardData.side = side;
		cardData.influence = reputation;
		cardData.num = num;
		cardData.unique = false;
		cardData.type = type;
		return cardData;
	}

	public static Card getUniqueCard() {
		CardData data = getRunnerCardData("name", Side.RUNNER, Faction.SHAPER, 15, 1,
				CardType.HARDWARE);
		data.unique = true;
		return new Card(data);
	}

	public static Card getCard(String name, Side side) {
		
		CardData data =	getRunnerCardData(name, side, Faction.SHAPER, 15, 1, CardType.HARDWARE);

		return new Card(data);
	}

	public static Card getCorpCardWithNoAgenda(String name, int num) {
		CardData data = getCorpCardData(name, num);
		return new Card(data);
	}

	private static CardData getCorpCardData(String name, int num) {
		CardData data = new CardData();
		data.name = name;
		data.side = Side.CORP;
		data.faction = Faction.HAAS_BIOROID;
		data.influence = 1;
		data.unique = false;
		data.agendapoints = 0;
		data.num = num;
		data.type = CardType.ASSET;
		return data;
	}

	public static Card getCard() {
		return getCard("std", Side.RUNNER);
	}
	
	public static Card getCorpCardWithAgenda(String name, int agendapoints, int num) {
		CardData data = new CardData();
		data.name = name;
		data.side = Side.CORP;
		data.faction = Faction.HAAS_BIOROID;
		data.influence = 1;
		data.unique = false;
		data.agendapoints = agendapoints;
		data.num = num;
		data.type = CardType.AGENDA;

		return new Card(data);
  }
}