package it.ck.cyberdeck.persistance;

import it.ck.cyberdeck.model.CardType;

import java.lang.reflect.Type;

import com.google.gson.*;

public class CardTypeDeserializer implements JsonDeserializer<CardType> {

  @Override
  public CardType deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
      throws JsonParseException {
    String value = json.getAsJsonPrimitive().getAsString();
    return CardType.valueOf(value.toUpperCase());
  }

}
