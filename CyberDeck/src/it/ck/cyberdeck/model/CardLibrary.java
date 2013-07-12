package it.ck.cyberdeck.model;

import java.util.*;

public class CardLibrary {

  private List<Card> cards;

  public CardLibrary(LibraryCardGateway loader) {
    this.cards = loader.loadCards();
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
}
