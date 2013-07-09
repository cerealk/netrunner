package it.ck.cyberdeck.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CardCounter implements Serializable{
  
  private static final long serialVersionUID = 1L;
  private Map<Card, Integer> count = new HashMap<Card, Integer>();

  public void incrementCount(Card card) {
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

  public void decrementCount(Card card) {
    Integer cardCount = getCount(card);
    count.put(card, --cardCount);
  }

  public Integer size() {
    Integer totalCount = 0;
    for (Integer singleCardCount : count.values()) {
      totalCount += singleCardCount;
    }
    return totalCount;
  }

  public Integer calculateReputation(Set<Card> cards, Faction faction) {
    Integer reputation = 0;
    for (Card card : cards) {
      reputation += card.calculateInfluenceCost(faction) * getCount(card);
    }
    return reputation;
  }
}