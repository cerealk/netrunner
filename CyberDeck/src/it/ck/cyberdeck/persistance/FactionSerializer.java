package it.ck.cyberdeck.persistance;

import it.ck.cyberdeck.model.Faction;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class FactionSerializer implements JsonSerializer<Faction> {

	@Override
	public JsonElement serialize(Faction src, Type type,
			JsonSerializationContext context) {
		return new JsonPrimitive(src.toString());
	}

}
