package it.ck.cyberdeck.presentation;

import it.ck.cyberdeck.R;
import it.ck.cyberdeck.app.DeckService;
import it.ck.cyberdeck.model.*;
import it.ck.cyberdeck.presentation.adapter.CardLibraryArrayAdapter;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;

public class AddCardActivity extends Activity {

	private static final String ARG_DECK = "deck";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_card);
		DeckService ds = ((CyberDeckApp) getApplicationContext()).getDeckService();
		CardLibrary cl = ds.loadCardLibrary();
		Deck deck =  (Deck) getIntent()
				.getSerializableExtra(AddCardActivity.ARG_DECK);
		List<Card> cardList = cl.getCardList(deck.getIdentity());
		CardLibraryArrayAdapter adapter = new CardLibraryArrayAdapter(this, cardList  );
		ListView view = (ListView) findViewById(R.id.list);
		view.setAdapter(adapter);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_card, menu);
		return true;
	}

}
