package it.ck.cyberdeck.model;

public class TheProfessorReputationRule extends StandardReputationRule implements ReputationRule {

	public TheProfessorReputationRule(Identity theProfessor) {
		super(theProfessor);
	}

	@Override
	public Integer calculateReputationCost(Card card, Integer cardCount) {
		return super.calculateReputationCost(card, cardCount - 1);
	}

}
