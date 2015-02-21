package it.ck.cyberdeck.persistence;

import it.ck.cyberdeck.model.CardSet;

import java.lang.reflect.Type;

import com.google.gson.*;

public class CardSetDeserializer implements JsonDeserializer<CardSet> {

  @Override
  public CardSet deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
      throws JsonParseException {
    String value = json.getAsJsonPrimitive().getAsString();
    return CardSet.findByName(value);
  }

}
