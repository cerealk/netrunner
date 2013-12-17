package it.ck.cyberdeck.model.group;

import it.ck.cyberdeck.model.Card;
import it.ck.cyberdeck.model.CardEntry;
import it.ck.cyberdeck.model.CardType;
import it.ck.cyberdeck.model.Deck;
import it.ck.cyberdeck.model.Identity;
import it.ck.cyberdeck.model.utils.CardKeyComparator;
import it.ck.cyberdeck.model.utils.EntryNameComparator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ElementGroupBuilder <T> {
	

	public ElementGroupBuilder(){
	}
	
	public Map<CardType, ElementGroup<Card>> populateCardGroup(Identity identity, List<Card> cards) {
		Map<CardType, ElementGroup<Card>> cardGroups = new HashMap<CardType, ElementGroup<Card>>();
		for (Card card : cards) {
			if (card.getSide().equals(identity.side())) {
				ElementGroup<Card> cardGroup = cardGroups.get(card.getType());
				if (cardGroup == null) {
					cardGroup = new ElementGroup<Card>(card.getType(), new CardKeyComparator());
				}
				if (identity.canUse(card)) {
					cardGroup.add(card);
					cardGroups.put(card.getType(), cardGroup);
				}
			}
		}
		return cardGroups;
	}
	
	public Map<CardType, ElementGroup<CardEntry>> populateCardGroup(Deck deck){
		Map<CardType, ElementGroup<CardEntry>> cardGroups = new HashMap<CardType, ElementGroup<CardEntry>>();
		List<CardEntry> cards = deck.cards();
		for (CardEntry cardEntry : cards) {
				ElementGroup<CardEntry> cardGroup = cardGroups.get(cardEntry.getCard().getType());
				if (cardGroup == null) {
					cardGroup = new ElementGroup<CardEntry>(cardEntry.getCard().getType(), new EntryNameComparator());
				}
					cardGroup.add(cardEntry);
					cardGroups.put(cardEntry.getCard().getType(), cardGroup);
		}
		return cardGroups;
	}
}
