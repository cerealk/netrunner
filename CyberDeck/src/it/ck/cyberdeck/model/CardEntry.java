package it.ck.cyberdeck.model;

import java.io.Serializable;

public class CardEntry implements Serializable {
  private static final long serialVersionUID = 1L;
	private Card card;
	private int count;
	
	public CardEntry(Card card, int count) {
	  this.card = card;
	  this.count = count;
  }

	public Card getCard() {
		return card;
	}

	public int getCount() {
		return count;
	}


	
}
