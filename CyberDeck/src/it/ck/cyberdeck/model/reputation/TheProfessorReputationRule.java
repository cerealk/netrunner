package it.ck.cyberdeck.model.reputation;

import it.ck.cyberdeck.model.Card;

import java.io.Serializable;

public class TheProfessorReputationRule extends StandardReputationRule implements ReputationRule, Serializable{

	private static final long serialVersionUID = 3785794330610535103L;

	@Override
	public Integer calculateReputationCost(Card card, Integer cardCount) {
		return super.calculateReputationCost(card, cardCount - 1);
	}

}
