package it.ck.cyberdeck.model;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class CardEntry implements Serializable {
  private static final long serialVersionUID = 1L;
	private Card card;
	private Integer count;
	
	public CardEntry(Card card, Integer count) {
	  this.card = card;
	  this.count = count;
  }

	public Card getCard() {
		return card;
	}

	public Integer getCount() {
		return count;
	}

	public CardKey getKey(){
		return card.getKey();
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CardEntry other = (CardEntry) obj;
		return EqualsBuilder.reflectionEquals(this, other);
	}

	public String getName() {
		return card.getName();
	}
	
	
	
}
