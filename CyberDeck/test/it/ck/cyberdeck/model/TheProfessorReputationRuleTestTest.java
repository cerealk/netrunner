package it.ck.cyberdeck.model;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import org.junit.Test;

public class TheProfessorReputationRuleTestTest {

	@Test
	public void theFirstCardOfATypeNeverAddsToReputation() {
		ReputationRule rule = new TheProfessorReputationRule(IdentityTestFactory.getTheProfessor());
		assertThat(rule.calculateReputationCost(CardTestFactory.getAnarchCard()), is(equalTo(0)));
	}
	
	@Test
	public void theSecondCardOfATypeAddsItsCostToReputation() {
		ReputationRule rule = new TheProfessorReputationRule(IdentityTestFactory.getTheProfessor());
		assertThat(rule.calculateReputationCost(CardTestFactory.getAnarchCard(),2), is(equalTo(5)));
	}

}
