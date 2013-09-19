package it.ck.cyberdeck.presentation.activity;

import it.ck.cyberdeck.R;
import it.ck.cyberdeck.model.Card;
import it.ck.cyberdeck.presentation.BaseCyberDeckActivity;
import it.ck.cyberdeck.presentation.fragment.CardDetailFragment;
import it.ck.cyberdeck.presentation.fragment.CardListFragment;
import android.content.Intent;
import android.os.Bundle;

/**
 * An activity representing a list of CardLibrary. This activity has different
 * presentations for handset and tablet-size devices. On handsets, the activity
 * presents a list of items, which when touched, lead to a
 * {@link CardDetailActivity} representing item details. On tablets, the
 * activity presents the list of items and item details side-by-side using two
 * vertical panes.
 * <p>
 * The activity makes heavy use of fragments. The list of items is a
 * {@link CardListFragment} and the item details (if present) is a
 * {@link CardDetailFragment}.
 * <p>
 * This activity also implements the required {@link CardListFragment.Callbacks}
 * interface to listen for item selections.
 */
public class CardListActivity extends BaseCyberDeckActivity implements
		CardListFragment.Callbacks {

	/**
	 * Whether or not the activity is in two-pane mode, i.e. running on a tablet
	 * device.
	 */
	private boolean mTwoPane;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_card_list);

		if (findViewById(R.id.card_detail_container) != null) {
			// The detail container view will be present only in the
			// large-screen layouts (res/values-large and
			// res/values-sw600dp). If this view is present, then the
			// activity should be in two-pane mode.
			mTwoPane = true;

			// In two-pane mode, list items should be given the
			// 'activated' state when touched.
			((CardListFragment) getSupportFragmentManager().findFragmentById(
					R.id.card_list)).setActivateOnItemClick(true);
		}
		
	}

	/**
	 * Callback method from {@link CardListFragment.Callbacks} indicating that
	 * the item with the given ID was selected.
	 */
	@Override
	public void onItemSelected(Card card) {
		if (mTwoPane) {
			// In two-pane mode, show the detail view in this activity by
			// adding or replacing the detail fragment using a
			// fragment transaction.
			Bundle arguments = new Bundle();
			arguments.putSerializable(CardDetailFragment.ARG_ITEM_ID, card);
			CardDetailFragment fragment = new CardDetailFragment();
			fragment.setArguments(arguments);
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.card_detail_container, fragment).commit();

		} else {
			// In single-pane mode, simply start the detail activity
			// for the selected item ID.
			Intent detailIntent = new Intent(this, CardGalleryActivity.class);
			detailIntent.putExtra(CardDetailFragment.ARG_ITEM_ID, card);
			startActivity(detailIntent);
		}
		
	}
}
