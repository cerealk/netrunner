package it.ck.cyberdeck;

import it.ck.cyberdeck.model.Card;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * A fragment representing a single Card detail screen. This fragment is either
 * contained in a {@link CardListActivity} in two-pane mode (on tablets) or a
 * {@link CardDetailActivity} on handsets.
 */
public class CardDetailFragment extends Fragment {
	/**
	 * The fragment argument representing the item ID that this fragment
	 * represents.
	 */
	public static final String ARG_ITEM_ID = "item_id";

	/**
	 * The dummy content this fragment is presenting.
	 */
	private Card card;

	/**
	 * Mandatory empty constructor for the fragment manager to instantiate the
	 * fragment (e.g. upon screen orientation changes).
	 */
	public CardDetailFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (getArguments().containsKey(ARG_ITEM_ID)) {
			// Load the dummy content specified by the fragment
			// arguments. In a real-world scenario, use a Loader
			// to load content from a content provider.
			card =(Card) getArguments().getSerializable(ARG_ITEM_ID);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_card_detail,
				container, false);

		// Show the dummy content as text in a TextView.
		if (card != null) {
			ImageView iView = (ImageView) rootView.findViewById(R.id.card_detail);
			String imageName = "_" + card.getImageName();
			int resId = getResources().getIdentifier(imageName, "drawable", "it.ck.cyberdeck");
		    Log.d("detailFragment", "Img Name: " + imageName + ", resId: " + String.valueOf(resId) );
		    iView.setImageResource(resId);
		}

		return rootView;
	}
}
