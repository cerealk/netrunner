package it.ck.cyberdeck.model.reputation;

import java.io.Serializable;

import it.ck.cyberdeck.model.Card;
import it.ck.cyberdeck.model.Identity;

public class StandardReputationRule implements ReputationRule, Serializable {

	private static final long serialVersionUID = -2773284353313528940L;

	private Identity identity;


	public void setIdentity(Identity identity) {
		this.identity = identity;
	}

	@Override
	public Integer calculateReputationCost(Card card) {
		return calculateReputationCost(card, 1);
	}

	@Override
	public Integer calculateReputationCost(Card card, Integer cardCount) {
		if(identity == null){
			throw new IllegalStateException("null identity");
		}
		if(card.sameFactionAs(identity)){
			return 0;
		}
		if(card.isAgenda()){
			return 0;
		}
		return card.getReputation() * cardCount;
	}

}
