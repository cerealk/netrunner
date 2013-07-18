package it.ck.cyberdeck.persistance;

import it.ck.cyberdeck.model.*;

import java.lang.reflect.Type;
import java.util.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public abstract class JsonLibraryCardGateway implements LibraryCardGateway {

	public JsonLibraryCardGateway() {
		super();
	}

	@Override
	public List<Card> loadCards(){
		List<CardData> data = loadRawData();
		List<Card> cards = new ArrayList<Card>();
		for (CardData cardData : data) {
      cards.add(new Card(cardData));
    }
		return cards;
	}
	
	protected List<CardData> loadRawData() {
    Gson gson = getGson();
    return gson.fromJson(readLibrarySource(), getType());
  }

	protected abstract String readLibrarySource();
	
	private Type getType() {
    Type collectionType = new TypeToken<Collection<CardData>>() {
    }.getType();
    return collectionType;
  }

	private Gson getGson() {
	    GsonBuilder builder = new GsonBuilder();
		builder.registerTypeAdapter(Integer.class, new IntegerDeserializer());
		builder.registerTypeAdapter(Faction.class, new FactionDeserializer());
		builder.registerTypeAdapter(CardSet.class, new CardSetDeserializer());
		builder.registerTypeAdapter(CardType.class, new CardTypeDeserializer());
		builder.registerTypeAdapter(Side.class, new SideDeserializer());
	    Gson gson = builder.create();
	    return gson;
	  }

}