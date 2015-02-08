package it.ck.cyberdeck.persistance.filesystem;

import it.ck.cyberdeck.model.CardKey;


public class CardRef {
	private CardKey key;
	private Integer count;
	
	public CardRef(CardKey card, Integer count) {
	  super();
	  this.key = card;
	  this.count = count;
  }
	
	public CardKey getCard(){
		return key;
	}
	
	public Integer getCount(){
		return count;
	}
	
}
