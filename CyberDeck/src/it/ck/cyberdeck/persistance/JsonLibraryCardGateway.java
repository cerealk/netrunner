package it.ck.cyberdeck.persistance;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import it.ck.cyberdeck.model.*;

public abstract class JsonLibraryCardGateway implements LibraryCardGateway {

	public JsonLibraryCardGateway() {
		super();
	}

	@Override
	public List<Card> loadCards() {
	    Gson gson = getGson();
	    return gson.fromJson(readLibrarySource(), getType());
	  }

	protected abstract String readLibrarySource();

	private Type getType() {
	    Type collectionType = new TypeToken<Collection<Card>>() {
	    }.getType();
	    return collectionType;
	  }

	private Gson getGson() {
	    GsonBuilder builder = new GsonBuilder();
		builder.registerTypeAdapter(Integer.class, new IntegerDeserializer());
		builder.registerTypeAdapter(Faction.class, new FactionDeserializer());
		builder.registerTypeAdapter(CardSet.class, new CardSetDeserializer());
		builder.registerTypeAdapter(Side.class, new SideDeserializer());
	    Gson gson = builder.create();
	    return gson;
	  }

}