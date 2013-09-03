package it.ck.cyberdeck.model.reputation;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;
import it.ck.cyberdeck.fixtures.CardTestFactory;
import it.ck.cyberdeck.fixtures.IdentityTestFactory;
import it.ck.cyberdeck.model.reputation.ReputationRule;
import it.ck.cyberdeck.model.reputation.TheProfessorReputationRule;

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
