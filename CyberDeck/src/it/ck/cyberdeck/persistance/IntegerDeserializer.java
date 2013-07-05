package it.ck.cyberdeck.persistance;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class IntegerDeserializer implements JsonDeserializer<Integer> {

  @Override
  public Integer deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
      throws JsonParseException {
    String value = json.getAsJsonPrimitive().getAsString();
    if (value.length() == 0)
      return Integer.valueOf(0);
    else
      return Integer.valueOf(value);
  }

}
