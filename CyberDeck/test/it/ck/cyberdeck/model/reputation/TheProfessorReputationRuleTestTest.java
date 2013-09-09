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
		TheProfessorReputationRule rule = getTheProfessorReputationRule();
		assertThat(rule.calculateReputationCost(CardTestFactory.getAnarchCard()), is(equalTo(0)));
	}

	private TheProfessorReputationRule getTheProfessorReputationRule() {
		TheProfessorReputationRule rule = new TheProfessorReputationRule();
		rule.setIdentity(IdentityTestFactory.getTheProfessor());
		return rule;
	}
	
	@Test
	public void theSecondCardOfATypeAddsItsCostToReputation() {
		ReputationRule rule = getTheProfessorReputationRule();
		assertThat(rule.calculateReputationCost(CardTestFactory.getAnarchCard(),2), is(equalTo(5)));
	}

}
