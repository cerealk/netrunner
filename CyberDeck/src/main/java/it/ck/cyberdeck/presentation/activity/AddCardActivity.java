package it.ck.cyberdeck.presentation.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ExpandableListView;

import java.util.List;

import it.ck.cyberdeck.R;
import it.ck.cyberdeck.model.Card;
import it.ck.cyberdeck.model.CardLibrary;
import it.ck.cyberdeck.model.group.ElementGroup;
import it.ck.cyberdeck.presentation.BaseDeckActivity;
import it.ck.cyberdeck.presentation.CyberDeckApp;
import it.ck.cyberdeck.presentation.adapter.CardLibraryExpandableListAdapter;

public class AddCardActivity extends BaseDeckActivity {

	private CardLibraryExpandableListAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_expandable_card);

		ExpandableListView expListView = findViewById(R.id.expandableListView1);
		CardLibrary cl = ((CyberDeckApp) getApplication()).getDeckService()
				.loadCardLibrary();

		List<ElementGroup<Card>> values = cl
				.getCardGroupsWithoutIdentities(getDeck().getIdentity());
		adapter = new CardLibraryExpandableListAdapter(this, values);
		expListView.setAdapter(adapter);

		expListView.setOnChildClickListener((parent, v, groupPosition, childPosition, id) -> {

      Card cardToBeAdded = (Card) getListAdapter().getChild(
          groupPosition, childPosition);
      presenter.addCard(cardToBeAdded);
      return true;
    });
	}

	private CardLibraryExpandableListAdapter getListAdapter() {
		return adapter;
	}

	@Override
	public void onBackPressed() {
		Intent intent = new Intent();
		intent.putExtra(BaseDeckActivity.DECK_ARG_ID, getDeck());
		setResult(RESULT_OK, intent);
		super.onBackPressed();
	}

}
