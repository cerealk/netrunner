package it.ck.cyberdeck.model.comparator;

import it.ck.cyberdeck.model.Card;

import java.util.Comparator;

public final class CardKeyComparator implements Comparator<Card> {
	@Override
	public int compare(Card lhs, Card rhs) {
		return lhs.getKey().compareTo(rhs.getKey());
	}
}