package it.ck.cyberdeck.persistence;

import java.lang.reflect.Type;

import com.google.gson.*;

public class IntegerDeserializer implements JsonDeserializer<Integer> {

  @Override
  public Integer deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
      throws JsonParseException {
    String value = json.getAsJsonPrimitive().getAsString();
    if (value.length() == 0)
      return null;
    else
      return Integer.valueOf(value);
  }

}
