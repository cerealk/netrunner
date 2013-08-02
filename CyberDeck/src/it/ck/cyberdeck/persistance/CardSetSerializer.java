package it.ck.cyberdeck.persistance;

import it.ck.cyberdeck.model.CardSet;

import java.lang.reflect.Type;

import com.google.gson.*;

public class CardSetSerializer implements JsonSerializer<CardSet> {

	@Override
	public JsonElement serialize(CardSet src, Type type,
			JsonSerializationContext context) {
		return new JsonPrimitive(src.getName());
	}

}
