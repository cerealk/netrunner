package it.ck.cyberdeck.presentation.adapter;

import java.util.List;

import it.ck.cyberdeck.CardDetailFragment;
import it.ck.cyberdeck.model.CardEntry;
import it.ck.cyberdeck.model.Deck;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class DeckDetailPageAdapter extends FragmentStatePagerAdapter {

	private List<CardEntry> cards;

	public DeckDetailPageAdapter(Deck deck, FragmentManager fm) {
		super(fm);
		this.cards = deck.cards();
	}

	@Override
	public Fragment getItem(int position) {
		return CardDetailFragment.newInstance(cards.get(position).getCard());
	}

	@Override
	public int getCount() {
		return cards.size();
	}

	public int getCardEntryOrdinal(CardEntry cardEntry) {
		return cards.indexOf(cardEntry);
	}

}
