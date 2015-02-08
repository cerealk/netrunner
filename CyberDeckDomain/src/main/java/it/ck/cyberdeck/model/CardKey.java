package it.ck.cyberdeck.model;

import java.io.Serializable;
import java.text.DecimalFormat;

import org.apache.commons.lang3.builder.*;

public class CardKey implements Serializable, Comparable<CardKey> {
	private static final long serialVersionUID = 1L;

	private CardSet set;
	private Integer num;

	private static final DecimalFormat df = new DecimalFormat("000");

	public CardKey(CardSet set, Integer num) {
		this.set = set;
		this.num = num;
	}

	public String getCardCode() {
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
	public String toString() {
		return getCardCode();
	}

	public int compareTo(CardKey that) {
		if (Integer.valueOf(this.set.getCode()) < Integer.valueOf(that.set.getCode()))
			return -1;
		if (Integer.valueOf(this.set.getCode()) > Integer.valueOf(that.set.getCode()))
			return 1;

		return this.num.compareTo(that.num);
	}

}