package it.ck.cyberdeck.persistance.filesystem;

import java.util.ArrayList;
import java.util.List;

public class DeckData {
	private String name;
	private String identity;
	private List<CardRef> cards = new ArrayList<CardRef>();
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIdentity() {
		return identity;
	}
	public void setIdentity(String identity) {
		this.identity = identity;
	}
	public List<CardRef> getCards() {
		return cards;
	}
	public void setCards(List<CardRef> cards) {
		this.cards = cards;
	}
	
	public void addCardRef(String cardCode, Integer count){
		cards.add(new CardRef(cardCode, count));
	}
	
}
