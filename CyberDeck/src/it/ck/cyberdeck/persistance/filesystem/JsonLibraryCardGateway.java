package it.ck.cyberdeck.persistance.filesystem;

import it.ck.cyberdeck.model.*;
import it.ck.cyberdeck.persistance.*;

import java.io.File;
import java.lang.reflect.Type;
import java.util.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public abstract class JsonLibraryCardGateway implements LibraryCardGateway {


	private Gson gson;

	public JsonLibraryCardGateway() {
		this.gson = getGson();
	}

	@Override
	public List<String> deckNames() {
		List<String> deckNames = new ArrayList<String>();
		File deckDir = getDeckDir();
		File[] files = deckDir.listFiles();
		for(File deckFile : files){
			deckNames.add(deckFile.getName());
		}
		return deckNames;
	}
	
	@Override
  public Deck loadDeck(String name) {
		DeckData data = gson.fromJson(readSource(getDeckUri(name)), DeckData.class);
		CardLibrary cl = getCardLibrary();
		Card identityCard = cl.getCard(data.getIdentity());
		Deck deck = new Deck(new Identity(identityCard), data.getName());
		for (CardRef ref : data.getCards()) {
	    deck.add(cl.getCard(ref.getCard()), ref.getCount());
    }
		return deck;
  }

	protected abstract String getDeckUri(String name);
	
	protected abstract File getDeckDir();
	
	@Override
  public CardLibrary getCardLibrary() {
	  CardLibrary cl = new CardLibrary();
	  cl.addAll(loadCards());
	  return cl;
  }


	protected List<Card> loadCards() {
		List<CardData> data = loadRawData();
		List<Card> cards = new ArrayList<Card>();
		for (CardData cardData : data) {
			cards.add(new Card(cardData));
		}
		return cards;
	}

	@Override
	public void saveDeck(Deck deck) {
		String destinationName = deck.name();

		DeckData deckData = buildDeckData(deck);

		String deckDataString = gson.toJson(deckData);
		persist(destinationName, deckDataString);
	}

	protected abstract void persist(String destinationName, String deckDataString);


	private DeckData buildDeckData(Deck deck) {
		DeckData data = new DeckData();

		data.setName(deck.name());
		data.setIdentity(deck.getIdentity().key());

		List<CardEntry> entries = deck.cards();
		for (CardEntry cardEntry : entries) {
			data.addCardRef(cardEntry.getKey(), cardEntry.getCount());
		}
		return data;
	}

	protected List<CardData> loadRawData() {
		return gson.fromJson(readLibrarySource(), getType());
	}

	protected abstract String readLibrarySource();
	
	protected abstract String readSource(String sourceName);
	
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