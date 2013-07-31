package it.ck.cyberdeck.model;

import java.io.Serializable;
import java.util.*;
import java.util.Map.Entry;

public class CardCounter implements Serializable{
  
  private static final long serialVersionUID = 1L;
  private Map<Card, Integer> count = new HashMap<Card, Integer>();


	public class CardNotFoundException extends RuntimeException {
    private static final long serialVersionUID = -4688094597415515668L;
  }
  
  public void add(Card card) {
    Integer currentCount = getCount(card);
    count.put(card, ++currentCount);
  }

  public Integer getCount(Card card) {
    Integer currentCount = count.get(card);
    if (currentCount == null) {
      currentCount = Integer.valueOf(0);
    }
    return currentCount;
  }

  public void remove(Card card) {
    Integer cardCount = getCount(card);
    if (cardCount.intValue() == 0){
    	throw new CardNotFoundException();
    }
    cardCount--;
    if(cardCount == 0){
    	count.remove(card);
    }else {
    	count.put(card, cardCount);
    }
  }

  public Integer size() {
    Integer totalCount = 0;
    for (Integer singleCardCount : count.values()) {
      totalCount += singleCardCount;
    }
    return totalCount;
  }

  public Integer calculateReputation(Identity identity) {
    Integer reputation = 0;
    Set<Card> cards = count.keySet();
    for (Card card : cards) {
      reputation += card.calculateInfluenceCost(identity) * getCount(card);
    }
    return reputation;
  }

	public List<CardEntry> getEntries() {
	  List<CardEntry> entries = new ArrayList<CardEntry>();
	  Set<Entry<Card,Integer>> cards = count.entrySet();
    for (Map.Entry<Card, Integer> entry : cards) {
      entries.add(new CardEntry(entry.getKey(), entry.getValue()));
    }
	  return entries;
  }

	public void removeAll(Card card) {
	  count.remove(card);
  }

	public int countAgendaPoints() {
    int ap = 0;
    Set<Card> cards = count.keySet();
    for (Card card : cards) {
      ap += card.getAgendapoints() * getCount(card);
    }
    return ap;
  }
}