package it.ck.cyberdeck.persistance;

import it.ck.cyberdeck.model.Side;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class SideSerializer implements JsonSerializer<Side> {

	@Override
	public JsonElement serialize(Side src, Type type,
			JsonSerializationContext context) {
		return new JsonPrimitive(src.toString());
	}

}
