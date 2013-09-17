package it.ck.cyberdeck.presentation;

import it.ck.cyberdeck.R;
import it.ck.cyberdeck.model.CardEntry;
import it.ck.cyberdeck.presentation.adapter.DeckDetailPageAdapter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

public class DeckDetailActivity extends BaseDeckActivity {

	
	private DeckDetailPageAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_deck_detail);
		
		CardEntry entry = getEntry(savedInstanceState);
		adapter = new DeckDetailPageAdapter(getDeck(), getSupportFragmentManager());

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
	
}
