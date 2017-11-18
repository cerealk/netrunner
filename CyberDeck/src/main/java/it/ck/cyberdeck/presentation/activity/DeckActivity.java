package it.ck.cyberdeck.presentation.activity;

import it.ck.cyberdeck.R;
import it.ck.cyberdeck.model.CardEntry;
import it.ck.cyberdeck.model.DeckStatus;
import it.ck.cyberdeck.model.Identity;
import it.ck.cyberdeck.presentation.BaseDeckActivity;
import it.ck.cyberdeck.presentation.DeckView;
import it.ck.cyberdeck.presentation.adapter.CardEntryExpandableListAdapter;
import it.ck.cyberdeck.presentation.fragment.CardDetailFragment;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.ExpandableListContextMenuInfo;
import android.widget.ImageView;
import android.widget.TextView;

public class DeckActivity extends BaseDeckActivity implements DeckView {

	private TextView identityName;
	private TextView deckStatusLine;
	private ImageView identityImg;
	private ExpandableListView cardList;
	private CardEntryExpandableListAdapter listViewAdapter;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_deck);

		identityName = findViewById(R.id.deck_identity);
		deckStatusLine = findViewById(R.id.deckStatusLine);
		identityImg = findViewById(R.id.identity_image);
		listViewAdapter = new CardEntryExpandableListAdapter(
				this.getApplicationContext(), getDeck(), getImageLoaderFactory());
		cardList = findViewById(R.id.deck_cards);

		cardList.setAdapter(listViewAdapter);
		
		cardList.setOnChildClickListener((parent, v, groupPosition, childPosition, id) -> {
      CardEntry entry = listViewAdapter.getEntry(groupPosition, childPosition);
      Intent detailIntent = new Intent(DeckActivity.this,
          DeckDetailActivity.class);
      detailIntent.putExtra(BaseDeckActivity.DECK_ARG_ID,
          presenter.getDeck());
      detailIntent.putExtra(CardDetailFragment.ARG_ITEM_ID, entry);
      startActivity(detailIntent);
      return false;
    });
		
		presenter.publish();

		registerForContextMenu(cardList);

		Button addButton = findViewById(R.id.add_card_button);

		addButton.setOnClickListener(v -> {
      Intent intent = new Intent(DeckActivity.this,
          GroupedAddCardActivity.class);
      intent.putExtra(DECK_ARG_ID, presenter.getDeck());
      startActivityForResult(intent, REQUEST_CODE);
    });
		Typeface font = Typeface.createFromAsset(getApplicationContext().getAssets(), "fontawesome-webfont.ttf");
		addButton.setTypeface(font);
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		getMenuInflater().inflate(R.menu.deck_popoup_menu, menu);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		ExpandableListContextMenuInfo info = (ExpandableListContextMenuInfo) item
				.getMenuInfo();
		
		long packedPosition = info.packedPosition;
		int group = ExpandableListView.getPackedPositionGroup(packedPosition);
		int child = ExpandableListView.getPackedPositionChild(packedPosition);
		
		switch (item.getItemId()) {
		case R.id.removeCard:
			removeCard(group, child);
			return true;
		case R.id.reoveAll:
			removeAll(group, child);
			return true;
		default:
			break;
		}
		return super.onContextItemSelected(item);
	}

	private void removeCard(int group, int child) {
		
		CardEntry cardEntry = getEntry(group, child);
		presenter.remove(cardEntry.getCard());
		presenter.publish();
	}

	private void removeAll(int group, int child) {
		CardEntry cardEntry = getEntry(group, child);
		presenter.removeAll(cardEntry.getCard());
		presenter.publish();
	}

	private CardEntry getEntry(int group, int child) {
		return listViewAdapter.getEntry(group, child);
	}

	@Override
	public void publishIdentity(Identity identity) {
		this.identityName.setText(identity.name());
		loadImage(identity.key(), identityImg);
	}

	@Override
	public void publishDeckName(String deckName) {
		this.setTitle(deckName);
	}

	@Override
	public void publishEntryList() {
		listViewAdapter.updateGroups(getDeck());
		listViewAdapter.notifyDataSetChanged();
		int count = listViewAdapter.getGroupCount();
		for (int position = 1; position <= count; position++)
		    cardList.expandGroup(position - 1);
	}

	@Override
	public void publishDeckStatus(DeckStatus deckStatus) {

		String statusLine = "";
		statusLine += "Card count: "+ String.valueOf(deckStatus.cardCount()) + "/" + deckStatus.minDeckSize();
		statusLine += "\t\tRep: " + deckStatus.getReputation() + "/" + deckStatus.getReputationCap();
		if(presenter.getDeck().isCorpDeck())
			statusLine += "\nAgenda points: " + String.valueOf(deckStatus.getAgendaPoints()) + " " + deckStatus.getAgendaRange(); 
		deckStatusLine.setText(statusLine);
		
	}

}
