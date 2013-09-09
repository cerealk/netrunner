package it.ck.cyberdeck.model;

import it.ck.cyberdeck.model.reputation.ReputationRule;
import it.ck.cyberdeck.model.reputation.StandardReputationRule;

import java.io.Serializable;

public class Identity implements Serializable {
	
  private static final long serialVersionUID = 1L;
  private CardKey key;
	private String name;
	private Integer minDeckSize;
	private Side side;
	private Faction faction;
	private Integer reputationCap;
	private ReputationRule reputationRule = new StandardReputationRule(this);

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
	
	public String name() {
		return this.name;
	}
	
	public CardKey key(){
		return key;
	}

	public Integer calculateReputationCost(Card card) {
		
		return reputationRule.calculateReputationCost(card);

	}
	
	public Integer calculateReputationCost(Card card, Integer count){
		return reputationRule.calculateReputationCost(card, count);
	}

	public Integer minSize() {
		return minDeckSize;
	}

	public boolean canUse(Card card) {
		return card.sameFactionAs(this) || card.isNeutral()
				|| card.canBeAttached();
	}
	
}
