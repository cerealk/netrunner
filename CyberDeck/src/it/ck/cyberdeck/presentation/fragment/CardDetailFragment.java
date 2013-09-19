package it.ck.cyberdeck.presentation.fragment;

import it.ck.cyberdeck.model.Card;
import it.ck.cyberdeck.presentation.BaseImageFragment;
import it.ck.cyberdeck.presentation.CardDetailView;
import it.ck.cyberdeck.presentation.activity.CardDetailActivity;
import it.ck.cyberdeck.presentation.activity.CardListActivity;
import it.ck.cyberdeck.presentation.presenter.CardDetailPresenter;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A fragment representing a single Card detail screen. This fragment is either
 * contained in a {@link CardListActivity} in two-pane mode (on tablets) or a
 * {@link CardDetailActivity} on handsets.
 */
public class CardDetailFragment extends BaseImageFragment implements CardDetailView{
	/**
	 * The fragment argument representing the item ID that this fragment
	 * represents.
	 */
	public static final String ARG_ITEM_ID = "item_id";

	private CardDetailPresenter presenter;
	
	public static CardDetailFragment newInstance(Card card){
		CardDetailFragment fragment = new CardDetailFragment();
		Bundle bundle = new Bundle();
		bundle.putSerializable(ARG_ITEM_ID, card);
		fragment.setArguments(bundle);
		
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		presenter = new CardDetailPresenter(this, getCard());
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = super.onCreateView(inflater, container, savedInstanceState);
		presenter.populateView();
		return rootView;
	}

	private Card getCard() {
		if (getArguments().containsKey(ARG_ITEM_ID)) {
			return (Card) getArguments().getSerializable(ARG_ITEM_ID);
		}
		throw new IllegalStateException("Card not found");
	}

	@Override
	public void setCardImage(Bitmap cardImage) {
		this.setImage(cardImage);
	}

}
