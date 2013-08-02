package it.ck.cyberdeck.model;

import java.io.Serializable;

import org.apache.commons.lang3.builder.*;

public class Card implements Serializable, Comparable<Card> {

	private static final long serialVersionUID = 1L;
	
	private CardKey key;
	private String name;
	private CardClassifier classifier;

	private String cost;
	private Integer reputation;
	private String strength;
	private Integer agendapoints;
	private Integer memory;
	private Integer trash;
	private String errata;
	private Boolean unique;
	private String text;

	private Integer count;
	private Integer link;
	private String illustrator;
	private Integer minDeckSize;
	private Integer maxReputation;
	

	public Card(String name, Side side, Faction faction, int reputation, CardKey key) {
		this.name = name;
		this.key = key;
		this.classifier = new CardClassifier(side, faction, null, null);
		this.reputation = reputation;
	}

	
	public Card(CardData cardData) {
		key = new CardKey(cardData.set, cardData.num);
		this.name= cardData.name ;
		this.classifier = new CardClassifier(cardData.side,cardData.identity, cardData.type, cardData.subtype);
		this.cost= cardData.cost ;
		this.reputation= cardData.loyalty ;
		this.strength= cardData.strength ;
		this.agendapoints= cardData.agendapoints ;
		this.memory= cardData.memory ;
		this.trash= cardData.trash ;
		this.errata= cardData.errata ;
		this.unique= cardData.unique ;
		this.text= cardData.text ;
		this.count= cardData.count ;
		this.link= cardData.link ;
		this.illustrator= cardData.illustrator ;
		this.minDeckSize= cardData.identitytop ;
		this.maxReputation= cardData.identitybottom ;
  }

	public CardKey getKey(){
		return this.key;
	}
	public Integer getMinDeckSize() {
		return minDeckSize;
	}

	public Integer getMaxReputation() {
		return maxReputation;
	}

	public Integer getCount() {
		return count;
	}

	public Integer getLink() {
		return link;
	}

	public String getIllustrator() {
		return illustrator;
	}

	public String getName() {
		return name;
	}

	public String getCost() {
		return cost;
	}

	public Integer getReputation() {
		return reputation;
	}

	public String getStrength() {
		return strength;
	}

	public Integer getAgendapoints() {
		return agendapoints == null? 0 :agendapoints;
	}

	public Integer getMemory() {
		return memory;
	}

	public Integer getTrash() {
		return trash;
	}

	public String getErrata() {
		return errata;
	}

	public Boolean isUnique() {
		return unique;
	}

	public String getText() {
		return text;
	}
	
	public Integer calculateInfluenceCost(Identity identity) {
		if (sameFactionAs(identity))
			return 0;

		return reputation;

	}

	public boolean isNeutral() {
	  return this.classifier.isNeutral();
  }

	public boolean sameFactionAs(Identity identity) {
	  return this.classifier.sameFactionAs(identity);
  }
	
	public boolean sameSideAs(Identity identity){
		return this.classifier.sameSideAs(identity);
	}
	
	public boolean isAgenda(){
		return this.classifier.isAgenda();
	}
	
	public boolean canBeAttached() {
	  return reputation != null;
  }

	public String getCardCode(){
		return key.getCardCode();
	}
	
	  @Override
	  public int hashCode() {
	    return HashCodeBuilder.reflectionHashCode(this.key);
	  }

	  @Override
	  public boolean equals(Object obj) {
	  	if(!(obj instanceof Card)){
	  		return false;
	  	}
	    return this.key.equals(((Card)obj).key);
	  }
	  
	  @Override
	  public String toString(){
		  return ToStringBuilder.reflectionToString(this);
	  }

		public boolean isIdentity() {
	    return this.classifier.isIdentity();
    }


		protected Side getSide() {
	    return this.classifier.getSide();
    }


		public Faction getFaction() {
	    return this.classifier.getFaction();
    }


		protected CardType getType() {
	    return this.classifier.getType();
    }
    
		public boolean isCompatibleWith(Identity identity) {
		  return this.sameFactionAs(identity) || this.isNeutral() || this.canBeAttached();
	  }


		@Override
    public int compareTo(Card another) {
	    return this.key.compareTo(another.key);
    }
}
