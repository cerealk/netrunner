package it.ck.cyberdeck.model;

import java.util.*;

public class CardLibrary {

  private List<Card> cards = new ArrayList<Card>();

  public CardLibrary() {
  }

  public List<Card> getCardList() {
    return Collections.unmodifiableList(cards);
  }
  
  public int size() {
    return cards.size();
  }

	public List<Identity> getIdentities() {
	  
		List<Identity> identities = new ArrayList<Identity>();
		for(Card card : cards){
	  	if(card.isIdentity())
	  		identities.add(new Identity(card));
	  }
	  return Collections.unmodifiableList(identities);
  }

	public List<Card> getCardList(Identity identity) {
	  List<Card> result = new ArrayList<Card>();
	  for(Card card : cards){
	  	if(identity.isCompatibleWith(card)){
	  		result.add(card);
	  	}
	  }
	  return result;
  }

	public void addAll(List<Card> cardList) {
		this.cards.addAll(cardList);
  }

	public List<CardGroup> getCardGroups(Side side) {
	  Map<CardType, CardGroup> cardGroups = new HashMap<CardType,CardGroup>();
	  for(Card card : cards){
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
}
