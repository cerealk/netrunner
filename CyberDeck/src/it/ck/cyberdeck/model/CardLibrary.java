package it.ck.cyberdeck.model;

import java.util.ArrayList;
import java.util.List;

public class CardLibrary {

  private List<Card> cards;

  public CardLibrary(LibraryCardGateway loader) {
    this.cards = loader.loadCards();
  }

  public List<Card> getCardList() {
    return new ArrayList<Card>(cards);
  }

  public int size() {
    return cards.size();
  }
}
