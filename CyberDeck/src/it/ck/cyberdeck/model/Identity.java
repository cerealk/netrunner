package it.ck.cyberdeck.model;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Identity implements Serializable {
	
  private static final long serialVersionUID = 1L;
  private CardKey key;
	private String name;
	private Integer minDeckSize;
	private Side side;
	private Faction faction;
	private Integer reputationCap;

	public Identity(String name, Side side, Faction faction,
			Integer minDeckSize, Integer reputationCap) {
		this.name = name;
		this.side = side;
		this.minDeckSize = minDeckSize;
		this.faction = faction;
		this.reputationCap = reputationCap;
	}
	
	public Identity(Card identityCard){
		this.key = identityCard.getKey(); 
		this.name = identityCard.getName();
		this.side = identityCard.getSide();
		this.faction = identityCard.getFaction();
		this.minDeckSize = identityCard.getMinDeckSize();
		this.reputationCap = identityCard.getMaxReputation();
	}

	public Side side() {
		return side;
	}

	public Faction faction() {
		return faction;
	}

	public Integer reputationCap() {
		return reputationCap;
	}

	public boolean checkSize(int size) {
		return this.minDeckSize <= size;
	}

	public boolean isCorp(){
		return Side.CORP.equals(side);
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public String name() {
		return this.name;
	}
	
	public CardKey key(){
		return key;
	}

	public Integer calculateInfluenceCost(Card card) {
		if (card.sameFactionAs(this))
			return 0;
		return card.getReputation();

	}
	
	public Integer calculateInfluenceCost(Card card, Integer count){
		return calculateInfluenceCost(card) * count;
	}
	
}
