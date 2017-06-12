package it.ck.cyberdeck.model.group;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.ck.cyberdeck.model.CardEntry;
import it.ck.cyberdeck.model.CardType;
import it.ck.cyberdeck.model.Deck;
import it.ck.cyberdeck.model.comparator.EntryNameComparator;

public class ElementGroupBuilder {
	
	public Map<CardType, ElementGroup<CardEntry>> populateCardGroup(Deck deck){
		Map<CardType, ElementGroup<CardEntry>> cardGroups = new HashMap<>();
		List<CardEntry> cards = deck.cards();
		for (CardEntry cardEntry : cards) {
				ElementGroup<CardEntry> cardGroup = cardGroups.get(cardEntry.getCard().getType());
				if (cardGroup == null) {
					cardGroup = new ElementGroup<>(cardEntry.getCard().getType(), new EntryNameComparator());
				}
					cardGroup.add(cardEntry);
					cardGroups.put(cardEntry.getCard().getType(), cardGroup);
		}
		return cardGroups;
	}
}
