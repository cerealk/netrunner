package it.ck.cyberdeck.model.reputation;

import it.ck.cyberdeck.model.CardKey;
import it.ck.cyberdeck.model.CardSet;
import it.ck.cyberdeck.model.Identity;

public class StandardReputationRuleFactory implements ReputationRuleFactory {

	private static final CardKey THE_PROFESSOR_KEY = new CardKey(CardSet.CREATION_AND_CONTROL, 29);

	@Override
	public ReputationRule createRule(Identity identity) {
		if(identity.key().equals(THE_PROFESSOR_KEY)){
			return new TheProfessorReputationRule(identity);
		}
		return new StandardReputationRule(identity);
	}

}
