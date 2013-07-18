package it.ck.cyberdeck.model;

import java.io.Serializable;
import java.text.DecimalFormat;

import org.apache.commons.lang3.builder.*;

public class Card implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String name;
	private String cost;
	private Side side;
	private Faction identity;
	private CardType type;
	private String subtype;
	private Integer loyalty = 0;
	private String strength;
	private Integer agendapoints;
	private Integer memory;
	private Integer trash;
	private String errata;
	private Boolean unique;
	private String text;
	private CardSet set;
	private Integer num;
	private Integer count;
	private Integer link;
	private String illustrator;
	private Integer identitytop;
	private Integer identitybottom;
	

	public Integer getIdentitytop() {
		return identitytop;
	}

	public void setIdentitytop(Integer identitytop) {
		this.identitytop = identitytop;
	}

	public Integer getIdentitybottom() {
		return identitybottom;
	}

	public void setIdentitybottom(Integer identitybottom) {
		this.identitybottom = identitybottom;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getLink() {
		return link;
	}

	public void setLink(Integer link) {
		this.link = link;
	}

	public String getIllustrator() {
		return illustrator;
	}

	public void setIllustrator(String illustrator) {
		this.illustrator = illustrator;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}

	public Side getSide() {
		return side;
	}

	public void setSide(Side side) {
		this.side = side;
	}

	public Faction getIdentity() {
		return identity;
	}

	public void setIdentity(Faction faction) {
		this.identity = faction;
	}

	public CardType getType() {
		return type;
	}

	public void setType(CardType type) {
		this.type = type;
	}

	public String getSubtype() {
		return subtype;
	}

	public void setSubtype(String subtype) {
		this.subtype = subtype;
	}

	public Integer getLoyality() {
		return loyalty;
	}

	public void setLoyalty(Integer loyalty) {
		this.loyalty = loyalty;
	}

	public String getStrength() {
		return strength;
	}

	public void setStrength(String strength) {
		this.strength = strength;
	}

	public Integer getAgendapoints() {
		return agendapoints;
	}

	public void setAgendapoints(Integer agendaPoints) {
		this.agendapoints = agendaPoints;
	}

	public Integer getMemory() {
		return memory;
	}

	public void setMemory(Integer memory) {
		this.memory = memory;
	}

	public Integer getTrash() {
		return trash;
	}

	public void setTrash(Integer trash) {
		this.trash = trash;
	}

	public String getErrata() {
		return errata;
	}

	public void setErrata(String errata) {
		this.errata = errata;
	}

	public Boolean getUnique() {
		return unique;
	}

	public void setUnique(Boolean unique) {
		this.unique = unique;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public CardSet getSet() {
		return set;
	}

	public Integer getNum() {
		return num;
	}


	public void setSet(CardSet set) {
		this.set = set;
	}

	public Card(String name, Side side, int influence) {
		this.name = name;
		this.side = side;
		this.loyalty = influence;
	}

	public Integer calculateInfluenceCost(Identity identity) {
		if (sameFactionAs(identity) || isNeutral())
			return 0;

		return loyalty;

	}

	public boolean isNeutral() {
	  return this.identity.equals(Faction.NEUTRAL);
  }

	public boolean sameFactionAs(Identity identity) {
	  return this.identity.equals(identity.faction());
  }
	
	public boolean canBeAttached() {
	  return loyalty != null;
  }

	public String getImageName(){
		DecimalFormat df = new DecimalFormat("000");
		return set.getCode() + df.format(num);
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
