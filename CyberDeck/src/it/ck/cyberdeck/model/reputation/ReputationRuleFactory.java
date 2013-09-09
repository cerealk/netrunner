package it.ck.cyberdeck.model.reputation;

import it.ck.cyberdeck.model.CardKey;

public interface ReputationRuleFactory {

	ReputationRule createRule(CardKey cardKey);

}
