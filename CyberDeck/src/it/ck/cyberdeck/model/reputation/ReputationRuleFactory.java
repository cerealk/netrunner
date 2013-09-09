package it.ck.cyberdeck.model.reputation;

import it.ck.cyberdeck.model.Identity;

public interface ReputationRuleFactory {

	ReputationRule createRule(Identity identity);

}
