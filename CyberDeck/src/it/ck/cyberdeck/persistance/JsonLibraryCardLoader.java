package it.ck.cyberdeck.persistance;

import it.ck.cyberdeck.model.Card;
import it.ck.cyberdeck.model.LibraryCardLoader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.Collection;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class JsonLibraryCardLoader implements LibraryCardLoader {

  @Override
  public List<Card> loadCards() {
    Gson gson = getGson();
    return gson.fromJson(readLibrarySource(), getType());
  }

  private Type getType() {
    Type collectionType = new TypeToken<Collection<Card>>() {
    }.getType();
    return collectionType;
  }

  private Gson getGson() {
    GsonBuilder builder = new GsonBuilder();
    builder.registerTypeAdapter(Integer.class, new IntegerDeserializer());
    Gson gson = builder.create();
    return gson;
  }

  private String readLibrarySource() {

    StringBuilder stringBuilder = new StringBuilder();
    BufferedReader reader = null;
    try {
      URL url = this.getClass().getResource("/it/ck/carddb/netrunner/data/carddata.js");
      InputStream inputStream = url.openConnection().getInputStream();
      reader = new BufferedReader(new InputStreamReader(inputStream));
      String line = null;
      String ls = System.getProperty("line.separator");

      while ((line = reader.readLine()) != null) {
        stringBuilder.append(line);
        stringBuilder.append(ls);
      }

    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (reader != null)
        try {
          reader.close();
        } catch (IOException e) {
        }
    }
    return stringBuilder.toString();
  }

}
