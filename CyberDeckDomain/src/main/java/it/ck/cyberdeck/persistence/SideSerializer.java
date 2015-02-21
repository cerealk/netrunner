package it.ck.cyberdeck.persistence;

import it.ck.cyberdeck.model.Side;

import java.lang.reflect.Type;

import com.google.gson.*;

public class SideSerializer implements JsonSerializer<Side> {

	@Override
	public JsonElement serialize(Side src, Type type,
			JsonSerializationContext context) {
		return new JsonPrimitive(src.toString());
	}

}
