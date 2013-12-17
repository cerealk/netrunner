package it.ck.cyberdeck.model.utils;

import it.ck.cyberdeck.model.Card;

import java.util.Comparator;

public class CardNameComparator implements Comparator<Card> {

	@Override
	public int compare(Card lhs, Card rhs) {
		return lhs.getName().compareTo(rhs.getName());
	}

}
