package it.ck.cyberdeck.persistence.filesystem;

import it.ck.cyberdeck.model.CardKey;


class CardRef {
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
