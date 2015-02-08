package it.ck.cyberdeck.persistance;

import it.ck.cyberdeck.model.Faction;

import java.lang.reflect.Type;

import com.google.gson.*;

public class FactionSerializer implements JsonSerializer<Faction> {

	@Override
	public JsonElement serialize(Faction src, Type type,
			JsonSerializationContext context) {
		return new JsonPrimitive(src.toString());
	}

}
