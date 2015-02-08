package it.ck.cyberdeck.model;

public class Event {

	private Deck deck;

	public Event(Deck deck) {
		this.deck = deck;
	}

	public Deck getSource() {
		return deck;
	}

}
