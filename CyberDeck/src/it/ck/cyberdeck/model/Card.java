package it.ck.cyberdeck.model;

import java.io.Serializable;
import java.text.DecimalFormat;

public class Card implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String name;
	private String cost;
	private Side side;
	private Faction identity;
	private String type;
	private String subtype;
	private Integer loyality = 0;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSubtype() {
		return subtype;
	}

	public void setSubtype(String subtype) {
		this.subtype = subtype;
	}

	public Integer getLoyality() {
		return loyality;
	}

	public void setLoyality(Integer loyality) {
		this.loyality = loyality;
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
		this.loyality = influence;
	}

	public Integer calculateInfluenceCost(Faction targetFaction) {
		if (this.identity.equals(targetFaction)
				|| this.identity.equals(Faction.NEUTRAL))
			return 0;
		return loyality;

	}
	
	public String getImageName(){
		DecimalFormat df = new DecimalFormat("000");
		return set.getCode() + df.format(num);
	}
}
