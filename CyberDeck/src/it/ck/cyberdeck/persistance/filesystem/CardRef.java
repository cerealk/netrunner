package it.ck.cyberdeck.persistance.filesystem;


public class CardRef {
	private String cardCode;
	private Integer count;
	
	public CardRef(String card, Integer count) {
	  super();
	  this.cardCode = card;
	  this.count = count;
  }
	
	public String getCard(){
		return cardCode;
	}
	
	public Integer getCount(){
		return count;
	}
	
}
