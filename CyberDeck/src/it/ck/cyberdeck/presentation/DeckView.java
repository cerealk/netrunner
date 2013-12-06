package it.ck.cyberdeck.presentation;

import it.ck.cyberdeck.model.CardEntry;
import it.ck.cyberdeck.model.DeckStatus;
import it.ck.cyberdeck.model.Identity;

import java.util.List;

public interface DeckView {
	
	void publishIdentity(Identity identity);
	void publishDeckName(String deckName);
	void publishEntryList(List<CardEntry> cards);
	void publishDeckStatus(DeckStatus checkStatus);
	
}
