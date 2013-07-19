package it.ck.cyberdeck.model;

import java.io.Serializable;

public class CardEntry implements Serializable {
  private static final long serialVersionUID = 1L;
	private Card card;
	private Integer count;
	
	public CardEntry(Card card, Integer count) {
	  this.card = card;
	  this.count = count;
  }

	public Card getCard() {
		return card;
	}

	public Integer getCount() {
		return count;
	}

	public CardKey getKey(){
		return card.getKey();
	}
	
}
