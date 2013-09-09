package it.ck.cyberdeck.model;

import java.io.Serializable;

public class CardClassifier implements Serializable, Comparable<CardClassifier>{
  private static final long serialVersionUID = 1L;

  private Side side;
	private Faction faction;
	private CardType type;
	private String subtype;

	public CardClassifier(Side side, Faction faction, CardType type,
      String subtype) {
				this.side = side;
				this.faction = faction;
				this.type = type;
				this.subtype = subtype;
  }

	public boolean isNeutral() {
	  return this.faction.equals(Faction.NEUTRAL);
  }

	public boolean sameFactionAs(Identity identity) {
	  return faction.equals(identity.faction());
  }

	public boolean isIdentity() {
	  return type.equals(CardType.IDENTITY);
  }

	public boolean sameSideAs(Identity identity) {
	  return side.equals(identity.side());
  }

	protected Side getSide() {
	  return side;
  }

	protected Faction getFaction() {
	  return faction;
  }

	protected CardType getType() {
	  return type;
  }

	public boolean isAgenda() {
	  
	  return CardType.AGENDA.equals(type);
  }

	@Override
	public int compareTo(CardClassifier another) {
		return this.type.compareTo(another.type);
	}
}