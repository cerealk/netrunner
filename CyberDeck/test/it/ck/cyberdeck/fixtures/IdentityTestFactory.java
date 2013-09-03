package it.ck.cyberdeck.fixtures;

import it.ck.cyberdeck.model.Faction;
import it.ck.cyberdeck.model.Identity;
import it.ck.cyberdeck.model.Side;

public class IdentityTestFactory{
	public static Identity getArarchIdentity() {
		Identity identity = new Identity("name", Side.RUNNER, Faction.ANARCH, 45, 15);
		return identity;
	}

	public static Identity getTheProfessor() {
		Identity theProfessor = new Identity("The Professor", Side.RUNNER, Faction.SHAPER, 45, 1);
		return theProfessor;
	}
}