package it.ck.cyberdeck.model;

import java.util.*;

public class CardLibrary {

  private Map<CardKey, Card> cards = new HashMap<CardKey, Card>();
  
  public CardLibrary() {
  }

  public List<Card> getCardList() {
  	List<Card> cardList = new ArrayList<Card>();
  	cardList.addAll(cards.values());
  	Collections.sort(cardList);
    return Collections.unmodifiableList(cardList);
  }
  
  public int size() {
    return cards.size();
  }

	public List<Identity> getIdentities() {
	  
		List<Identity> identities = new ArrayList<Identity>();
		for(Card card : cards.values()){
	  	if(card.isIdentity())
	  		identities.add(new Identity(card));
	  }
	  return Collections.unmodifiableList(identities);
  }

	public List<Card> getCardList(Identity identity) {
	  List<Card> result = new ArrayList<Card>();
	  for(Card card : cards.values()){
	  	if(card.isCompatibleWith(identity)){
	  		result.add(card);
	  	}
	  }
	  return result;
  }

	public void addAll(List<Card> cardList) {
		for (Card card : cardList) {
	    cards.put(card.getKey(), card);
    }
  }

	public List<CardGroup> getCardGroups(Side side) {
	  Map<CardType, CardGroup> cardGroups = new HashMap<CardType,CardGroup>();
	  for(Card card : cards.values()){
	  	if (card.getSide().equals(side)){
	  		CardGroup cardGroup = cardGroups.get(card.getType());
	  		if(cardGroup == null){
	  			cardGroup = new CardGroup(card.getType());
	  		}
	  		cardGroup.add(card);
	  		cardGroups.put(card.getType(), cardGroup);
	  	}
	  }
	  
	  return Collections.unmodifiableList(new ArrayList<CardGroup>(cardGroups.values()));
	  
  }

	public List<CardGroup> getCardGroups(Identity identity) {
	  Map<CardType, CardGroup> cardGroups = new HashMap<CardType,CardGroup>();
	  for(Card card : cards.values()){
	  	if (card.getSide().equals(identity.side())){
	  		CardGroup cardGroup = cardGroups.get(card.getType());
	  		if(cardGroup == null){
	  			cardGroup = new CardGroup(card.getType());
	  		}
	  		if(card.isCompatibleWith(identity)){
	  			cardGroup.add(card);
	  			cardGroups.put(card.getType(), cardGroup);
	  		}
	  	}
	  }
	  
	  return Collections.unmodifiableList(new ArrayList<CardGroup>(cardGroups.values()));
  }
	
	public Card getCard(CardKey key){
		return cards.get(key);
	}
}
