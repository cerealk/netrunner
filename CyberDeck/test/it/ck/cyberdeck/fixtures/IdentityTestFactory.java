package it.ck.cyberdeck.fixtures;

import it.ck.cyberdeck.model.CardKey;
import it.ck.cyberdeck.model.CardSet;
import it.ck.cyberdeck.model.Faction;
import it.ck.cyberdeck.model.Identity;
import it.ck.cyberdeck.model.Side;

public class IdentityTestFactory{
	public static Identity getArarchIdentity() {
		return getIdentity(Side.RUNNER, Faction.ANARCH);
	}

	public static Identity getTheProfessor() {
		Identity theProfessor = new Identity("The Professor", Side.RUNNER, Faction.SHAPER, 45, 1, new CardKey(CardSet.CREATION_AND_CONTROL, 29));
		return theProfessor;
	}
	
	public static Identity getHBIdentity(){
		return getIdentity(Side.CORP, Faction.HAAS_BIOROID);
	}

	public static Identity getIdentity(Side side, Faction faction) {
		int minimumCardCount = 15;
		return getIdentity(side, faction, minimumCardCount);
	}

	public static Identity getIdentity(Side side, Faction faction, Integer minimumCardCount) {
		CardKey key = new CardKey(CardSet.CORE, 1);
		Identity identity = new Identity("name", side, faction, minimumCardCount, 15, key);
		return identity;
	}
	
	public static Identity getLowCountIdentity() {
		return getIdentity(Side.RUNNER, Faction.ANARCH, 2);
	}
	
	public static Identity getDefaultIdentity(Integer minimumCardCount) {
		return getIdentity(Side.RUNNER, Faction.SHAPER, minimumCardCount);
	}
}