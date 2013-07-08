package it.ck.cyberdeck.model;

import java.util.HashSet;
import java.util.Set;

public class CardCollection {

  public class CardNotFoundException extends RuntimeException {
    private static final long serialVersionUID = -4688094597415515668L;
  }

  private Set<Card> cards = new HashSet<Card>();
  private CardCounter cardCounter = new CardCounter();

  public void add(Card card) {
    cards.add(card);
    cardCounter.incrementCount(card);
  }

  public Integer getCount(Card card) {
    return cardCounter.getCount(card);
  }

  public Integer size() {
    return cardCounter.size();
  }

  public void remove(Card card) {
    if (!cards.contains(card)) {
      throw new CardNotFoundException();
    }
    cards.remove(card);
    cardCounter.decrementCount(card);

  }

  public Integer calculateReputation(Faction faction) {
    return cardCounter.calculateReputation(cards, faction);
  }
}
