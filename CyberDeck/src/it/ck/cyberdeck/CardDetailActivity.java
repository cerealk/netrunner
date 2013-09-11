package it.ck.cyberdeck;

import it.ck.cyberdeck.model.Card;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

/**
 * An activity representing a single Card detail screen. This activity is only
 * used on handset devices. On tablet-size devices, item details are presented
 * side-by-side with a list of items in a {@link CardListActivity}.
 * <p>
 * This activity is mostly just a 'shell' activity containing nothing more than
 * a {@link CardDetailFragment}.
 */
public class CardDetailActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_card_detail);

		if (savedInstanceState == null) {
			// Create the detail fragment and add it to the activity
			// using a fragment transaction.
			CardDetailFragment fragment = CardDetailFragment.newInstance((Card) getIntent()
					.getSerializableExtra(CardDetailFragment.ARG_ITEM_ID));
			getSupportFragmentManager().beginTransaction()
					.add(R.id.card_detail_container, fragment).commit();
		}
	}

}
