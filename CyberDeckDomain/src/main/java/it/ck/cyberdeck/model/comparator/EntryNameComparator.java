package it.ck.cyberdeck.model.comparator;

import it.ck.cyberdeck.model.CardEntry;

import java.util.Comparator;

public class EntryNameComparator implements Comparator<CardEntry> {

	@Override
	public int compare(CardEntry lhs, CardEntry rhs) {
		return lhs.getName().compareTo(rhs.getName());
	}

}
