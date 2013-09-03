package it.ck.cyberdeck.model;

public interface ReputationRule {
	Integer calculateReputationCost(Card card);
	Integer calculateReputationCost(Card card, Integer cardCount);
}
