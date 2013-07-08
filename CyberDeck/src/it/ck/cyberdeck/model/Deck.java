package it.ck.cyberdeck.model;

public class Deck {
  public static class TooManyOutOfFactionCardsException extends RuntimeException {
    private static final long serialVersionUID = -413205268787698514L;
  }

  public static class TooManyCardOfTheSameTypeException extends RuntimeException {
    private static final long serialVersionUID = -1394260444077910210L;
  }

  public static class WrongSideException extends RuntimeException {
    private static final long serialVersionUID = 8630797373356331560L;
  }

  private final Identity identity;
  private final CardCollection cards = new CardCollection();

  public Deck(Identity identity) {
    this.identity = identity;
  }

  public Identity getIdentity() {
    return identity;
  }

  public void add(Card card) {
    if (!(identity.getSide().equals(card.getSide()))) {
      throw new WrongSideException();
    }
    if (cards.getCount(card) >= 3) {
      throw new TooManyCardOfTheSameTypeException();
    }
    if (!checkReputation(card)) {
      throw new TooManyOutOfFactionCardsException();
    }
    cards.add(card);
  }

  public void remove(Card card) {
    cards.remove(card);
  }

  public int size() {
    return cards.size();
  }

  public Boolean isValid() {
    return checkSize();
  }

  public Integer cardCount(Card card) {
    return cards.getCount(card);
  }

  private boolean checkReputation(Card card) {
    Integer reputation = cards.calculateReputation(identity.getFaction());
    reputation += card.calculateInfluenceCost(identity.getFaction());
    return reputation <= identity.getReputationCap();
  }

  private boolean checkSize() {
    return identity.checkSize(size());
  }

  public void add(Card card, int numberOfCopies) {
    for(int i = 0;i<numberOfCopies; i++ )
      add(card);
  }

}
