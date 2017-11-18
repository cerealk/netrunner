package it.ck.cyberdeck.presentation;

import it.ck.cyberdeck.model.DeckStatus;
import it.ck.cyberdeck.model.Identity;
import it.ck.cyberdeck.model.Notifier;

public interface DeckView extends Notifier{
	
	void publishIdentity(Identity identity);
	void publishDeckName(String deckName);
	void publishEntryList();
	void publishDeckStatus(DeckStatus checkStatus);
	
}
