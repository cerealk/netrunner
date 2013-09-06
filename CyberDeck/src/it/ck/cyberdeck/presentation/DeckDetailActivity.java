package it.ck.cyberdeck.presentation;

import it.ck.cyberdeck.CardDetailFragment;
import it.ck.cyberdeck.R;
import it.ck.cyberdeck.model.CardEntry;
import it.ck.cyberdeck.model.Deck;
import it.ck.cyberdeck.presentation.adapter.DeckDetailPageAdapter;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;

public class DeckDetailActivity extends FragmentActivity {

	
	public static final String DECK_ARG_ID = "deck_arg_id";
	private DeckDetailPageAdapter adapter;
	private Deck deck;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_deck_detail);
		
		CardEntry entry = getEntry(savedInstanceState);
		deck = getDeck(savedInstanceState);
		adapter = new DeckDetailPageAdapter(deck, getSupportFragmentManager());
		
		ViewPager gallery = (ViewPager) findViewById(R.id.deck_gallery);
		gallery.setAdapter(adapter);
		gallery.setCurrentItem(getEntryByOrdinal(entry));
	}

	private int getEntryByOrdinal(CardEntry entry) {
		return adapter.getCardEntryOrdinal(entry);
	}

	private CardEntry getEntry(Bundle savedInstanceState) {
		CardEntry entry;
		if(savedInstanceState == null){
			entry = (CardEntry) getIntent().getExtras().getSerializable(CardDetailFragment.ARG_ITEM_ID);
		}else {
			entry = (CardEntry) savedInstanceState.getSerializable(CardDetailFragment.ARG_ITEM_ID);
		}
		return entry;
	}
	
	private Deck getDeck(Bundle savedInstanceState) {
		Deck deck;
		if(savedInstanceState == null){
			deck = (Deck) getIntent().getExtras().getSerializable(DECK_ARG_ID);
		}else {
			deck = (Deck) savedInstanceState.getSerializable(DECK_ARG_ID);
		}
		return deck;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.deck_detail, menu);
		return true;
	}
	
	@Override
    protected void onSaveInstanceState(Bundle outState) {
		outState.putSerializable("deck", deck);
		super.onSaveInstanceState(outState);
    }

}
