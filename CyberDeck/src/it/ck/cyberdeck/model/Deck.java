package it.ck.cyberdeck.model;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.Range;
import org.apache.commons.lang3.builder.*;

public class Deck implements Serializable {

	private static final int MIN_DECK_SIZE = 40;
	private static final long serialVersionUID = 1L;
	private static final int MAX_DECK_SIZE = 100;

	public class CantBeAttachedException extends DeckException {
		private static final long serialVersionUID = 1L;
	}

	public static class TooManyOutOfFactionCardsException extends DeckException {
		private static final long serialVersionUID = -413205268787698514L;
		@Override
    public String getMessage() {
	    return "reputation limit exceeded";
    }
	}

	public static class TooManyCardOfTheSameTypeException extends DeckException {
		private static final long serialVersionUID = -1394260444077910210L;
		@Override
    public String getMessage() {
	    return "card limit exceeded";
    }
		
	}

	public static class WrongSideException extends DeckException {
		private static final long serialVersionUID = 8630797373356331560L;
	}

	private final Identity identity;
	private final CardCounter cards = new CardCounter();
	private String name;
	private DeckStatus deckStatus;

	public Deck(Identity identity) {
		this.identity = identity;
		this.deckStatus = DeckStatus.INVALID;
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
		if (cards.getCount(card) >= 3 || (card.isUnique() && cards.getCount(card)>=1)) {
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
	

	public void removeAll(Card card) {
		cards.removeAll(card);
  }

	public int size() {
		return cards.size();
	}

	public Integer cardCount(Card card) {
		return cards.getCount(card);
	}

	private boolean checkReputation(Card card) {
		Integer reputation = cards.calculateReputation(identity);
		reputation += card.calculateInfluenceCost(identity);
		return reputation <= identity.reputationCap();
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

	public DeckStatus checkStatus() {
		checkSize();
		checkAgendaPoints();
	  return this.deckStatus;
  }

	private void checkSize() {
		Boolean checkresult = identity.checkSize(size());
		handleCheckResult(checkresult);
	}

	private void handleCheckResult(Boolean checkresult) {
	  if (checkresult)
			this.deckStatus = DeckStatus.VALID;
		else 
			this.deckStatus = DeckStatus.StatusBuilder.instance().invalid().withReason(Reason.FEW_CARDS).build();
  }
	
	private void checkAgendaPoints() {
		if(requiresAgendaPointCheck()){
			int ap = countAgendaPoints();
			
			boolean correctPointRange = checkPointRange(ap);
			if(correctPointRange)
				this.deckStatus = DeckStatus.VALID;
			else
				this.deckStatus= DeckStatus.StatusBuilder.instance().invalid().withReason(Reason.FEW_AGENDA_POINTS).build();
		}
  }

	private boolean checkPointRange(int ap) {
		return getPointRange().contains(ap);
  }

	private boolean requiresAgendaPointCheck() {
	  return this.identity.isCorp()&& size()>=MIN_DECK_SIZE;
  }

	private Range<Integer> getPointRange() {
		int minRange = MIN_DECK_SIZE;
		int maxRange = MIN_DECK_SIZE +4;
		int minAp = 18;
		int maxAp = 19;
		while (minRange < MAX_DECK_SIZE){
	  			if(Range.between(minRange, maxRange).contains(size())) {
						return Range.between(minAp, maxAp);
          }
	  minRange +=5;
	  maxRange = minRange +4;
	  minAp +=2;
	  maxAp = minAp+1;
		}			
	  return null;
	  			
  }

	private int countAgendaPoints() {
	  
	  return cards.countAgendaPoints();
  }

	
	
}
