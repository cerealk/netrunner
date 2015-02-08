package it.ck.cyberdeck.model.reputation;

import it.ck.cyberdeck.model.CardKey;
import it.ck.cyberdeck.model.CardSet;

public class StandardReputationRuleFactory implements ReputationRuleFactory {

	private static final CardKey THE_PROFESSOR_KEY = new CardKey(CardSet.CREATION_AND_CONTROL, 29);

	@Override
	public ReputationRule createRule(CardKey key) {
		if(key.equals(THE_PROFESSOR_KEY)){
			return new TheProfessorReputationRule();
		}
		return new StandardReputationRule();
	}

}
