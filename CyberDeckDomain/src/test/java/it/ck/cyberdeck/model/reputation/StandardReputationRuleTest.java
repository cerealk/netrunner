package it.ck.cyberdeck.model.reputation;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import it.ck.cyberdeck.fixtures.CardTestFactory;
import it.ck.cyberdeck.fixtures.IdentityTestFactory;
import it.ck.cyberdeck.model.Identity;

import org.junit.Test;

public class StandardReputationRuleTest {

	@Test
	public void aCardOfDifferentFactionThanTheIdentityAddsToReputation() {
		ReputationRule rule = getReputationRule(IdentityTestFactory.getAnarchIdentity());
		assertThat(rule.calculateReputationCost(CardTestFactory.getShaperCard()), is(equalTo(5)));
	}

	private ReputationRule getReputationRule( Identity identity) {
		StandardReputationRule rule = new StandardReputationRule();
		rule.setIdentity(identity);
		return rule;
	}

	@Test
	public void aCardOfTheSameFactionOfTheIdentityNeverAddsToReputation(){
		ReputationRule rule = getReputationRule( IdentityTestFactory.getAnarchIdentity());
		assertThat(rule.calculateReputationCost(CardTestFactory.getAnarchCard()), is(equalTo(0)));
	}
	
	@Test
	public void theReputationCostDependsOnTheCardCount(){
		ReputationRule rule = getReputationRule( IdentityTestFactory.getAnarchIdentity());
		Integer cardCount = 2;
		assertThat(rule.calculateReputationCost(CardTestFactory.getShaperCard(),cardCount), is(equalTo(10)));
	}
	
	@Test 
	public void anagendaneverAddsToReputation(){
		ReputationRule rule = getReputationRule( IdentityTestFactory.getAnarchIdentity());
		assertThat(rule.calculateReputationCost(CardTestFactory.getHBAgendaCard()), is(equalTo(0)));
	}
	
	@Test (expected = IllegalStateException.class)
	public void ifTheCardPassedIsNullAnIllegalStateExcIsThrown(){
		ReputationRule rule = getReputationRule( IdentityTestFactory.getAnarchIdentity());
		rule.calculateReputationCost(null);
	}
	
	@Test(expected=IllegalStateException.class)
	public void ifTheIDentityIsNotSettedAnIllegalStateExcIsThrown(){
		ReputationRule rule = getReputationRule(null);
		rule.calculateReputationCost(null);
	}

}
