package it.ck.cyberdeck.presentation;

import it.ck.cyberdeck.R;
import it.ck.cyberdeck.model.Card;
import it.ck.cyberdeck.presentation.presenter.CardDetailPresenter;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

/**
 * A fragment representing a single Card detail screen. This fragment is either
 * contained in a {@link CardListActivity} in two-pane mode (on tablets) or a
 * {@link CardDetailActivity} on handsets.
 */
public class CardDetailFragment extends Fragment implements CardDetailView, DownloaderView{
	/**
	 * The fragment argument representing the item ID that this fragment
	 * represents.
	 */
	public static final String ARG_ITEM_ID = "item_id";

	private CardDetailPresenter presenter;

	private View rootView;

	private ProgressBar pb;

	private ImageView iView;

	/**
	 * Mandatory empty constructor for the fragment manager to instantiate the
	 * fragment (e.g. upon screen orientation changes).
	 */
	public CardDetailFragment() {
	}
	
	/**
	 * factory Method
	 * @param card
	 * @return
	 */
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
		presenter = new CardDetailPresenter(this);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_card_detail,
				container, false);

		iView = (ImageView) rootView.findViewById(R.id.card_detail);
		pb = (ProgressBar) rootView.findViewById(R.id.progressBar1);
		presenter.populateView();
		return rootView;
	}

	@Override
	public Card getCard() {
		if (getArguments().containsKey(ARG_ITEM_ID)) {
			
			return (Card) getArguments().getSerializable(ARG_ITEM_ID);
		}
		throw new IllegalStateException("Card not found");
	}
	
	@Override
	public void setCardImage(Bitmap cardImage) {
		iView.setImageBitmap(cardImage);
		iView.setVisibility(View.VISIBLE);
		pb.setVisibility(View.INVISIBLE);
	}
	

	@Override
	public Context getContext() {
		return getActivity();
	}

	@Override
	public void showProgress() {
		pb.setVisibility(View.VISIBLE);
		iView.setVisibility(View.INVISIBLE);
	}
	
	@Override
	public void setImage(Bitmap bmp) {
		this.setCardImage(bmp);
	}

}
