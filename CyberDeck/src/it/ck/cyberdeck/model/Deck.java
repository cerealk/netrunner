package it.ck.cyberdeck.model;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.builder.*;

public class Deck implements Serializable {

	private static final long serialVersionUID = 1L;

	public class CantBeAttachedException extends DeckException {
		private static final long serialVersionUID = 1L;
	}

	public static class TooManyOutOfFactionCardsException extends DeckException {
		private static final long serialVersionUID = -413205268787698514L;
	}

	public static class TooManyCardOfTheSameTypeException extends DeckException {
		private static final long serialVersionUID = -1394260444077910210L;
	}

	public static class WrongSideException extends DeckException {
		private static final long serialVersionUID = 8630797373356331560L;
	}

	private final Identity identity;
	private final CardCounter cards = new CardCounter();
	private String name;

	public Deck(Identity identity) {
		this.identity = identity;
	}

	public Deck(Identity identity, String name) {
	  this(identity);
	  this.name = name;
  }

	public Identity getIdentity() {
		return identity;
	}

	public void add(Card card) {
		if (!(card.sameSideAs(identity))) {
			throw new WrongSideException();
		}
		if (cards.getCount(card) >= 3) {
			throw new TooManyCardOfTheSameTypeException();
		}
		if(!card.isCompatibleWith( getIdentity())){
			throw new CantBeAttachedException();
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
		Integer reputation = cards.calculateReputation(identity);
		reputation += card.calculateInfluenceCost(identity);
		return reputation <= identity.reputationCap();
	}

	private boolean checkSize() {
		return identity.checkSize(size());
	}

	public void add(Card card, int numberOfCopies) {
		for (int i = 0; i < numberOfCopies; i++)
			add(card);
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public String name() {
		return this.name;
	}

	public List<CardEntry> cards(){
		return cards.getEntries();
	}
	
	//TODO: rivedere se al posto di questo equals è meglio utilizzare un oggetto "chiave"
	@Override
  public boolean equals(Object o) {
		if (o == null)
			return false;
		if(! (o instanceof Deck))
			return false;
		Deck other = (Deck) o;
	  return new EqualsBuilder().append(this.identity, other.identity).append(this.name, other.name).isEquals();
  }

	@Override
  public int hashCode() {
	  return new HashCodeBuilder().append(identity).append(name).hashCode();
  }
	
	
}
