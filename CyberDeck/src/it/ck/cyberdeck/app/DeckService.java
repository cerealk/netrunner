package it.ck.cyberdeck.app;

import it.ck.cyberdeck.model.Card;
import it.ck.cyberdeck.model.Identity;

public interface DeckService {

	void addCard(Card card);
	
	void createDeck(Identity identity);

}
