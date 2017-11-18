package it.ck.cyberdeck.presentation.activity;

import it.ck.cyberdeck.R;
import it.ck.cyberdeck.model.Card;
import it.ck.cyberdeck.presentation.BaseCyberDeckActivity;
import it.ck.cyberdeck.presentation.CyberDeckApp;
import it.ck.cyberdeck.presentation.adapter.GalleryPageAdapter;
import it.ck.cyberdeck.presentation.fragment.CardDetailFragment;

import java.util.List;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

public class CardGalleryActivity extends BaseCyberDeckActivity {

	private GalleryPageAdapter galleryPagerAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Card card = getCard(savedInstanceState);
		setContentView(R.layout.activity_card_gallery);
		galleryPagerAdapter = new GalleryPageAdapter(getCardList(), getSupportFragmentManager());
		
		ViewPager gallery = findViewById(R.id.gallery);
		gallery.setAdapter(galleryPagerAdapter);
		gallery.setCurrentItem(getCardByOrdinal(card));
	}

	private Card getCard(Bundle savedInstanceState) {
		if(savedInstanceState == null){
			return (Card) getIntent().getExtras().getSerializable(CardDetailFragment.ARG_ITEM_ID);
		}else {
			return (Card) savedInstanceState.getSerializable(CardDetailFragment.ARG_ITEM_ID);
		}
	}

	private int getCardByOrdinal(Card card) {
		return galleryPagerAdapter.getCardOrdinal(card);
	}

	private List<Card> getCardList() {
		return ((CyberDeckApp) getApplication()).getDeckService().loadCardLibrary().getCardList();
	}

}
