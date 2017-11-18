package it.ck.cyberdeck.presentation.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

import it.ck.cyberdeck.R;
import it.ck.cyberdeck.model.Deck;
import it.ck.cyberdeck.presentation.BaseCyberDeckActivity;
import it.ck.cyberdeck.presentation.BaseDeckActivity;

public class DeckListActivity extends BaseCyberDeckActivity {

	private ArrayAdapter<String> adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_deck_list);
		ListView deckListView = findViewById(R.id.listViewDecks);

		adapter = new ArrayAdapter<>(this,
				android.R.layout.simple_list_item_1);
		loadDecks();
		deckListView.setAdapter(adapter);

		deckListView.setOnItemClickListener((parent, view, pos, id) -> {
      String deckName = (String) parent.getItemAtPosition(pos);
      Deck deck = getDeckService().loadDeck(deckName);
      Intent intent = new Intent(DeckListActivity.this,
          DeckActivity.class);
      intent.putExtra(BaseDeckActivity.DECK_ARG_ID, deck);
      startActivity(intent);

    });
		registerForContextMenu(deckListView);
		Button newDeckButton = findViewById(R.id.button1);
		newDeckButton.setOnClickListener(v -> {
      Intent intent = new Intent(DeckListActivity.this,
          NewDeckActivity.class);
      startActivity(intent);

    });

	}

	@Override
	protected void onStart() {
		super.onStart();
		loadDecks();
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		getMenuInflater().inflate(R.menu.deck_list_popoup_menu, menu);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item
				.getMenuInfo();
		switch (item.getItemId()) {
		case R.id.deleteDeck:
			deleteDeck(info.position);
			return true;
		default:
			break;
		}
		return super.onContextItemSelected(item);
	}

	private void deleteDeck(int position) {
		getDeckService().deleteDeck(adapter.getItem(position));
		loadDecks();
	}

	private void loadDecks() {
		List<String> names = getDeckService().deckNames();
		adapter.clear();

		for (String name : names) {
			adapter.add(name);
		}
	}
}
