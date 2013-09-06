package it.ck.cyberdeck.presentation;

import it.ck.cyberdeck.CardDetailFragment;
import it.ck.cyberdeck.R;
import it.ck.cyberdeck.model.Card;
import it.ck.cyberdeck.presentation.adapter.GalleryPageAdapter;

import java.util.List;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;

public class CardGalleryActivity extends FragmentActivity {

	private GalleryPageAdapter galleryPagerAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Card card = null;
		card = getCard(savedInstanceState);
		setContentView(R.layout.activity_card_gallery);
		galleryPagerAdapter = new GalleryPageAdapter(getCardList(), getSupportFragmentManager());
		
		ViewPager gallery = (ViewPager) findViewById(R.id.gallery);
		gallery.setAdapter(galleryPagerAdapter);
		gallery.setCurrentItem(getCardByOrdinal(card));
	}

	private Card getCard(Bundle savedInstanceState) {
		Card card;
		if(savedInstanceState == null){
			card = (Card) getIntent().getExtras().getSerializable(CardDetailFragment.ARG_ITEM_ID);
		}else {
			card = (Card) savedInstanceState.getSerializable(CardDetailFragment.ARG_ITEM_ID);
		}
		return card;
	}

	private int getCardByOrdinal(Card card) {
		return galleryPagerAdapter.getCardOrdinal(card);
	}

	private List<Card> getCardList() {
		return ((CyberDeckApp) getApplication()).getDeckService().loadCardLibrary().getCardList();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.card_gallery, menu);
		return true;
	}

}
