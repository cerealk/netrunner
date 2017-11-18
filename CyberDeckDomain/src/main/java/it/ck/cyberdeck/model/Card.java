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

	public Card(String name, Side side, Faction faction, int reputation,
			CardKey key) {
		this.name = name;
		this.key = key;
		this.classifier = new CardClassifier(side, faction, null, null);
		this.reputation = reputation;
	}

	public Card(CardData cardData) {
		key = new CardKey(cardData.set, cardData.num);
		this.name = cardData.name;
		this.classifier = new CardClassifier(cardData.side, cardData.faction,
				cardData.type, cardData.subtype);
		this.cost = cardData.cost;
		this.reputation = cardData.influence;
		this.strength = cardData.strength;
		this.agendapoints = cardData.agendapoints;
		this.memory = cardData.memory;
		this.trash = cardData.trash;
		this.errata = cardData.errata;
		this.unique = cardData.unique;
		this.text = cardData.text;
		this.count = cardData.count;
		this.link = cardData.link;
		this.illustrator = cardData.illustrator;
		this.minDeckSize = cardData.mindecksize;
		this.maxReputation = cardData.identitybottom;
	}

	public CardKey getKey() {
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
		return agendapoints == null ? 0 : agendapoints;
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

	public boolean isNeutral() {
		return this.classifier.isNeutral();
	}

	public boolean sameFactionAs(Identity identity) {
		return this.classifier.sameFactionAs(identity);
	}

	public boolean sameSideAs(Identity identity) {
		return this.classifier.sameSideAs(identity);
	}

	public boolean isAgenda() {
		return this.classifier.isAgenda();
	}

	public boolean canBeAttached() {
		return reputation != null;
	}

	public String getCardCode() {
		return key.getCardCode();
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this.key);
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof Card && this.key.equals(((Card) obj).key);
	}

	public boolean isIdentity() {
		return this.classifier.isIdentity();
	}

	public Side getSide() {
		return this.classifier.getSide();
	}

	public Faction getFaction() {
		return this.classifier.getFaction();
	}

	public CardType getType() {
		return this.classifier.getType();
	}

	@Override
	public int compareTo(Card another) {
		if (this.classifier.compareTo(another.classifier) == 0){
			return this.name.compareTo(another.name);
		}
		return this.classifier.compareTo(another.classifier);
	}
}
