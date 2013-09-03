package it.ck.cyberdeck.model;

public class StandardReputationRule implements ReputationRule {

	private Identity identity;

	public StandardReputationRule(Identity identity) {
		this.identity = identity;
	}

	@Override
	public Integer calculateReputationCost(Card card) {
		return calculateReputationCost(card, 1);
	}

	@Override
	public Integer calculateReputationCost(Card card, Integer cardCount) {
		if(card.sameFactionAs(identity)){
			return 0;
		}
		return card.getReputation() * cardCount;
	}

}
