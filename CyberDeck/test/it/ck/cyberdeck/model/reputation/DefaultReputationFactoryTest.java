package it.ck.cyberdeck.model.reputation;

import static it.ck.cyberdeck.fixtures.IdentityTestFactory.*;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class DefaultReputationFactoryTest {

	@Test
	public void givenACardKeyICanGetAReputationRule() {
		ReputationRuleFactory rrf = new StandardReputationRuleFactory();
		ReputationRule rule = rrf.createRule(getArarchIdentity());
		assertThat(rule instanceof StandardReputationRule, is(true));
	}
	
	@Test 
	public void theProfessorHasACustomReputationRule(){
		ReputationRuleFactory rrf = new StandardReputationRuleFactory();
		ReputationRule rule = rrf.createRule(getTheProfessor());
		assertThat(rule instanceof TheProfessorReputationRule, is(true));
	}
	

}
