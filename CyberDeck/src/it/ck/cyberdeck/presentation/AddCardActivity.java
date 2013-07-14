package it.ck.cyberdeck.presentation;

import it.ck.cyberdeck.R;
import it.ck.cyberdeck.app.DeckService;
import it.ck.cyberdeck.model.*;
import it.ck.cyberdeck.presentation.adapter.CardLibraryArrayAdapter;

import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;

public class AddCardActivity extends ListActivity {

	private static final String ARG_DECK = "deck";
	private Deck deck;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_card);
		DeckService ds = ((CyberDeckApp) getApplicationContext()).getDeckService();
		CardLibrary cl = ds.loadCardLibrary();
		deck = (Deck) getIntent()
				.getSerializableExtra(AddCardActivity.ARG_DECK);
		List<Card> cardList = cl.getCardList(deck.getIdentity());
		setListAdapter(new CardLibraryArrayAdapter(this, cardList));
		
	}

	
	
	@Override
  protected void onListItemClick(ListView l, View v, int position, long id) {
	  super.onListItemClick(l, v, position, id);
	  
	  Card cardToBeAdded = (Card) getListAdapter().getItem(position);
	  deck.add(cardToBeAdded);
  }

	


	@Override
  public void onBackPressed() {
    Intent intent = new Intent();
    intent.putExtra("deck", deck);
    setResult(RESULT_OK, intent);  
	  super.onBackPressed();
  }



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_card, menu);
		return true;
	}

}
