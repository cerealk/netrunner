package it.ck.cyberdeck.model;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Test;

public class CardTest {

  @Test
  public void aNeutralCardNeverAddToReputation() {
    Card card = new Card("Name", Side.RUNNER, 5);
    card.setIdentity(Faction.NEUTRAL);
    assertThat(card.calculateInfluenceCost(Faction.ANARCH), is(0));
    assertThat(card.calculateInfluenceCost(Faction.CRIMINAL), is(0));
    assertThat(card.calculateInfluenceCost(Faction.SHAPER), is(0));
  }

}
