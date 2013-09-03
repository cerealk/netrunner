package it.ck.cyberdeck.model.reputation;

import java.io.Serializable;

import it.ck.cyberdeck.model.Card;
import it.ck.cyberdeck.model.Identity;

public class TheProfessorReputationRule extends StandardReputationRule implements ReputationRule, Serializable{

	private static final long serialVersionUID = 3785794330610535103L;

	public TheProfessorReputationRule(Identity theProfessor) {
		super(theProfessor);
	}

	@Override
	public Integer calculateReputationCost(Card card, Integer cardCount) {
		return super.calculateReputationCost(card, cardCount - 1);
	}

}
