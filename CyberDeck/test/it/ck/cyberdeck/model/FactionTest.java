package it.ck.cyberdeck.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class FactionTest {

	@Test(expected=IllegalArgumentException.class)
	public void aNullStringCannotBeAFaction() {
		Faction.fromString(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void anEmptyStringCannotBeAFaction(){
		Faction.fromString("");
	}

}
