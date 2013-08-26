package it.ck.cyberdeck.presentation;

import it.ck.cyberdeck.R;
import it.ck.cyberdeck.model.Card;
import it.ck.cyberdeck.presentation.adapter.GalleryPageAdapter;

import java.util.List;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;

public class CardGalleryActivity extends FragmentActivity {

	private PagerAdapter galleryPagerAdapter;
	private ViewPager gallery;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_card_gallery);
		galleryPagerAdapter = new GalleryPageAdapter(getCardList(), getSupportFragmentManager());
		gallery = (ViewPager) findViewById(R.id.gallery);
		gallery.setAdapter(galleryPagerAdapter);
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
