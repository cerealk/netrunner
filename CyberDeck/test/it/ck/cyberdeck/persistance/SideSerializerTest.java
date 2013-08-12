package it.ck.cyberdeck.persistance;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import it.ck.cyberdeck.model.Side;

import org.junit.Test;

import com.google.gson.JsonElement;

public class SideSerializerTest {

	private SideSerializer ser = new SideSerializer();	
	@Test
	public void testSerialize() {
		JsonElement element = ser.serialize(Side.CORP, null, null);
		assertThat(element, is(not(nullValue())));
		assertThat(element.toString(), is("\"CORP\""));
	}

}
