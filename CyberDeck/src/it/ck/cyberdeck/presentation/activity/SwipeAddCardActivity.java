package it.ck.cyberdeck.presentation.activity;

import it.ck.cyberdeck.R;
import it.ck.cyberdeck.model.Card;
import it.ck.cyberdeck.model.CardLibrary;
import it.ck.cyberdeck.model.group.ElementGroup;
import it.ck.cyberdeck.presentation.BaseDeckActivity;
import it.ck.cyberdeck.presentation.CyberDeckApp;
import it.ck.cyberdeck.presentation.adapter.CardGridAdapter;
import it.ck.cyberdeck.presentation.presenter.DeckPresenter;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class SwipeAddCardActivity extends BaseDeckActivity {

	public static class DummySectionFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		public static final String ARG_SECTION_NUMBER = "section_number";
		private ElementGroup<Card> cardGroup;

		public DummySectionFragment() {

		};

		public static DummySectionFragment newInstance(
				ElementGroup<Card> cardGroup, DeckPresenter presenter) {
			DummySectionFragment fragment = new DummySectionFragment();
			Bundle args = new Bundle();
			args.putSerializable(DummySectionFragment.ARG_SECTION_NUMBER,
					cardGroup);
			fragment.setArguments(args);
			return fragment;
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {

			View rootView = inflater.inflate(R.layout.fragment_card_grid,
					container, false);
			cardGroup = (ElementGroup<Card>) getArguments().getSerializable(ARG_SECTION_NUMBER);
			GridView gridView = (GridView) rootView.findViewById(R.id.gridview);
			CardGridAdapter adapter = new CardGridAdapter(this.getActivity().getApplicationContext(), cardGroup.getCards());
			gridView.setAdapter(adapter);
			OnItemClickListener listener = new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View v,
						int position, long id) {
					Card card = cardGroup.getCards().get(position);
					getSwipeActivity().addCard(card);
					getSwipeActivity().showToast(
							"Card " + card.getName() + " added succesfully");
				}

				private SwipeAddCardActivity getSwipeActivity() {
					return (SwipeAddCardActivity) DummySectionFragment.this
							.getActivity();
				}
			};
			gridView.setOnItemClickListener(listener);
			return rootView;
		}

	}

	public class SectionsPagerAdapter extends FragmentStatePagerAdapter {

		private List<ElementGroup<Card>> cardGroups;

		public SectionsPagerAdapter(FragmentManager fm,
				List<ElementGroup<Card>> cardGroups) {
			super(fm);
			this.cardGroups = cardGroups;
		}

		@Override
		public Fragment getItem(int position) {
			return DummySectionFragment.newInstance(cardGroups.get(position),
					SwipeAddCardActivity.this.presenter);
		}

		@Override
		public int getCount() {
			return cardGroups.size();
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return cardGroups.get(position).getType().name();
		}
	}

	private SectionsPagerAdapter sectionsPagerAdapter;
	private ViewPager viewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.swipe_add_card_activity);

		CardLibrary cl = ((CyberDeckApp) getApplication()).getDeckService()
				.loadCardLibrary();
		List<ElementGroup<Card>> cardGroups = cl
				.getCardGroupsWithoutIdentities(getDeck().getIdentity());

		sectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager(), cardGroups);

		viewPager = (ViewPager) findViewById(R.id.pager);
		viewPager.setAdapter(sectionsPagerAdapter);

	}

	public void addCard(Card card) {
		presenter.addCard(card);
	}

	@Override
	public void onBackPressed() {
		Intent intent = new Intent();
		intent.putExtra(BaseDeckActivity.DECK_ARG_ID, getDeck());
		setResult(RESULT_OK, intent);
		super.onBackPressed();
	}

}
