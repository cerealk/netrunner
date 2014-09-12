package it.ck.cyberdeck.model.utils;

import it.ck.cyberdeck.model.Card;

import java.io.Serializable;
import java.util.Comparator;

public class CardNameComparator implements Comparator<Card>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public int compare(Card lhs, Card rhs) {
		return lhs.getName().compareTo(rhs.getName());
	}

}
