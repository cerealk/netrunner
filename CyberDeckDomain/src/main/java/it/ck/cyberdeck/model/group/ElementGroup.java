package it.ck.cyberdeck.model.group;

import it.ck.cyberdeck.model.CardType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ElementGroup<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	private CardType type;

	private List<T> elements = new ArrayList<>();

	private Comparator<T> sorter;

	public ElementGroup(CardType type, Comparator<T> sorter) {
		this.type = type;
		this.sorter = sorter;
	}

	public void add(T card) {
		elements.add(card);
	}

	public CardType getType() {
		return this.type;
	}

	public List<T> getCards() {
		Collections.sort(elements, sorter);
		return Collections.unmodifiableList(elements);
	}

}
