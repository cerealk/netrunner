package it.ck.cyberdeck.persistence.filesystem;

import it.ck.cyberdeck.model.CardKey;

import java.util.ArrayList;
import java.util.List;

public class DeckData {
	private String name;
	private CardKey identity;
	private List<CardRef> cards = new ArrayList<>();
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public CardKey getIdentity() {
		return identity;
	}
	public void setIdentity(CardKey identity) {
		this.identity = identity;
	}
	public List<CardRef> getCards() {
		return cards;
	}
	public void addCardRef(CardKey cardKey, Integer count){
		cards.add(new CardRef(cardKey, count));
	}
	
}
