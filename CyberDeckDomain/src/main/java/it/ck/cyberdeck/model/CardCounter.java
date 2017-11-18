package it.ck.cyberdeck.model;

import java.io.Serializable;
import java.util.*;
import java.util.Map.Entry;

public class CardCounter implements Serializable {

	private static final long serialVersionUID = 1L;
	private Map<Card, Integer> count = new TreeMap<>();

	public class CardNotFoundException extends DeckException {

		private static final long serialVersionUID = -4688094597415515668L;

		public CardNotFoundException(Card card){
			super("Card " + card.getName() + " not found");
		}
	}

	public void add(Card card) {
		Integer currentCount = getCount(card);
		updateCount(card, ++currentCount);
	}

	public Integer getCount(Card card) {
		Integer currentCount = count.get(card);
		if (currentCount == null) {
			currentCount = 0;
		}
		return currentCount;
	}

	public void remove(Card card) {
		checkCardExistence(card);
		updateCount(card, getCount(card) -1);
	}

	public void removeAll(Card card) {
		checkCardExistence(card);
		count.remove(card);
	}

	private void checkCardExistence(Card card) {
		Integer cardCount = getCount(card);
		if (cardCount == 0) {
			throw new CardNotFoundException(card);
		}
	}

	private void updateCount(Card card, Integer cardCount) {
		if (cardCount == 0) {
			count.remove(card);
		} else {
			count.put(card, cardCount);
		}
	}

	public Integer size() {
		Integer totalCount = 0;
		for (Integer singleCardCount : count.values()) {
			totalCount += singleCardCount;
		}
		return totalCount;
	}

	public Integer calculateReputation(Identity identity) {
		Integer reputation = 0;
		Set<Card> cards = count.keySet();
		for (Card card : cards) {
			reputation += identity
					.calculateReputationCost(card, getCount(card));
		}
		return reputation;
	}

	public List<CardEntry> getEntries() {
		List<CardEntry> entries = new ArrayList<>();
		Set<Entry<Card, Integer>> cards = count.entrySet();
		for (Map.Entry<Card, Integer> entry : cards) {
			entries.add(new CardEntry(entry.getKey(), entry.getValue()));
		}
		return entries;
	}

	public int countAgendaPoints() {
		int ap = 0;
		Set<Card> cards = count.keySet();
		for (Card card : cards) {
			ap += card.getAgendapoints() * getCount(card);
		}
		return ap;
	}
}