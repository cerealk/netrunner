package it.ck.cyberdeck.model.reputation;

import it.ck.cyberdeck.model.Card;
import it.ck.cyberdeck.model.Identity;

public interface ReputationRule {
	Integer calculateReputationCost(Card card);
	Integer calculateReputationCost(Card card, Integer cardCount);
	void setIdentity(Identity identity);
}
