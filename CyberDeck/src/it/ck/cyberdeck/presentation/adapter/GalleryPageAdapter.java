package it.ck.cyberdeck.presentation.adapter;

import it.ck.cyberdeck.CardDetailFragment;
import it.ck.cyberdeck.model.Card;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class GalleryPageAdapter extends FragmentStatePagerAdapter {

	private List<Card> cards;

	public GalleryPageAdapter(List<Card> cards, FragmentManager fm) {
		super(fm);
		this.cards = cards;
	}

	@Override
	public Fragment getItem(int arg0) {
		return CardDetailFragment.newInstance(cards.get(arg0));
	}

	@Override
	public int getCount() {
		return cards.size();
	}

}
