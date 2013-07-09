package it.ck.cyberdeck.model;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Identity implements Serializable {
	
  private static final long serialVersionUID = 1L;
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

	public Integer getMinDeckSize() {
		return minDeckSize;
	}

	public Side getSide() {
		return side;
	}

	public Faction getFaction() {
		return faction;
	}

	public Integer getReputationCap() {
		return reputationCap;
	}

	public boolean checkSize(int size) {
		return this.minDeckSize <= size;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public String name() {
		return this.name;
	}

}
