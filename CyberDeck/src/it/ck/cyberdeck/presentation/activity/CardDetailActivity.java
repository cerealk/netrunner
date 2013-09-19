package it.ck.cyberdeck.presentation.activity;

import it.ck.cyberdeck.R;
import it.ck.cyberdeck.model.Card;
import it.ck.cyberdeck.presentation.BaseCyberDeckActivity;
import it.ck.cyberdeck.presentation.fragment.CardDetailFragment;
import android.os.Bundle;

/**
 * An activity representing a single Card detail screen. This activity is only
 * used on handset devices. On tablet-size devices, item details are presented
 * side-by-side with a list of items in a {@link CardListActivity}.
 * <p>
 * This activity is mostly just a 'shell' activity containing nothing more than
 * a {@link CardDetailFragment}.
 */
public class CardDetailActivity extends BaseCyberDeckActivity {

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
