package it.ck.cyberdeck.model.reputation;

import it.ck.cyberdeck.model.Card;

public interface ReputationRule {
	Integer calculateReputationCost(Card card);
	Integer calculateReputationCost(Card card, Integer cardCount);
}
