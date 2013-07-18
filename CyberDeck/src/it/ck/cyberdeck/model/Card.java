package it.ck.cyberdeck.model;

import java.io.Serializable;
import java.text.DecimalFormat;

import org.apache.commons.lang3.builder.*;

public class Card implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private CardKey key;
	private String name;
	private String cost;
	private Side side;
	private Faction faction;
	private CardType type;
	private String subtype;
	private Integer reputation = 0;
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
		this.side = side;
		this.faction = faction;
		this.reputation = reputation;
	}

	
	public Card(CardData cardData) {
		key = new CardKey(cardData.set, cardData.num);
		this.name= cardData.name ;
		this.cost= cardData.cost ;
		this.side= cardData.side ;
		this.faction= cardData.identity ;
		this.type= cardData.type ;
		this.subtype= cardData.subtype ;
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

	public Side getSide() {
		return side;
	}

	public Faction getFaction() {
		return faction;
	}

	public CardType getType() {
		return type;
	}

	public String getSubtype() {
		return subtype;
	}

	public Integer getReputation() {
		return reputation;
	}

	public String getStrength() {
		return strength;
	}

	public Integer getAgendapoints() {
		return agendapoints;
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
		if (sameFactionAs(identity) || isNeutral())
			return 0;

		return reputation;

	}

	public boolean isNeutral() {
	  return this.faction.equals(Faction.NEUTRAL);
  }

	public boolean sameFactionAs(Identity identity) {
	  return this.faction.equals(identity.faction());
  }
	
	public boolean canBeAttached() {
	  return reputation != null;
  }

	public String getCardCode(){
		return key.getCardCode();
	}
	
	  @Override
	  public int hashCode() {
	    return HashCodeBuilder.reflectionHashCode(this);
	  }

	  @Override
	  public boolean equals(Object obj) {
	    return EqualsBuilder.reflectionEquals(this, obj);
	  }
	  
	  @Override
	  public String toString(){
		  return ToStringBuilder.reflectionToString(this);
	  }

		public boolean isIdentity() {
	    return this.type.equals(CardType.IDENTITY);
    }
}
