package it.ck.cyberdeck.model;

import java.text.DecimalFormat;

import org.apache.commons.lang3.builder.*;

public class CardKey {
	private CardSet set;
	private Integer num;

	public CardKey(CardSet set, Integer num) {
		this.set = set;
		this.num = num;
	}

public String getCardCode(){
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
	
}