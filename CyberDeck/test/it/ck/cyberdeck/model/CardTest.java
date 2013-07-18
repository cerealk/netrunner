package it.ck.cyberdeck.model;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Test;

public class CardTest {

  @Test
  public void aNeutralCardNeverAddToReputation() {
    Card card = new Card("Name", Side.RUNNER, 5);
    card.setIdentity(Faction.NEUTRAL);
    Identity identity = new Identity("name", Side.RUNNER, Faction.ANARCH, 45, 15);
    assertThat(card.calculateInfluenceCost(identity), is(0));
    
    card.setIdentity(Faction.ANARCH);
    assertThat(card.calculateInfluenceCost(identity), is(0));
    
    card.setIdentity(Faction.CRIMINAL);
    assertThat(card.calculateInfluenceCost(identity), is(5));
    
    card.setIdentity(Faction.SHAPER);
    assertThat(card.calculateInfluenceCost(identity), is(5));
  }

}
