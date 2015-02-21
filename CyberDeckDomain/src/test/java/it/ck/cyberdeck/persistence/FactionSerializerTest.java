package it.ck.cyberdeck.persistence;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import it.ck.cyberdeck.model.Faction;

import org.junit.Test;

import com.google.gson.JsonElement;

public class FactionSerializerTest {

	private FactionSerializer ser = new FactionSerializer();	
	@Test
	public void testSerialize() {
		JsonElement element = ser.serialize(Faction.ANARCH, null, null);
		assertThat(element, is(not(nullValue())));
		assertThat(element.toString(), is("\"ANARCH\""));
	}

}
