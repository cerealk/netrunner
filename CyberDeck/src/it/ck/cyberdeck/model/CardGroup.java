package it.ck.cyberdeck.model;

import java.io.Serializable;
import java.util.*;

public class CardGroup implements Serializable {

	private static final long serialVersionUID = 1L;
  
	private CardType type;
  
  private List<Card> cards = new ArrayList<Card>();
	
  public CardGroup(CardType type) {
		this.type = type;
  }
  
  public void add(Card card){
  	cards.add(card);
  }
  
  public CardType getType(){
  	return this.type;
  }
  
  public List<Card> getCards(){
  	return Collections.unmodifiableList(cards);
  }

}
