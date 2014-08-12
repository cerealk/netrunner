package it.ck.cyberdeck.model;

import java.io.Serializable;

public class CardData implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public String name;
	public String cost;
	public Side side;
	public Faction faction;
	public CardType type;
	public String subtype;
	public Integer influence = 0;
	public String strength;
	public Integer agendapoints;
	public Integer memory;
	public Integer trash;
	public String errata;
	public Boolean unique;
	public String text;
	public CardSet set;
	public Integer num;
	public Integer count;
	public Integer link;
	public String illustrator;
	public Integer identitytop;
	public Integer identitybottom;
	
}
