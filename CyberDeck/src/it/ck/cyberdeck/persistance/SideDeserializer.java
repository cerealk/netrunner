package it.ck.cyberdeck.persistance;

import it.ck.cyberdeck.model.Side;

import java.lang.reflect.Type;

import com.google.gson.*;

public class SideDeserializer implements JsonDeserializer<Side> {

	@Override
	public Side deserialize(JsonElement arg0, Type arg1,
			JsonDeserializationContext arg2) throws JsonParseException {
		return Side.valueOf(arg0.getAsString().toUpperCase());
	}

}
