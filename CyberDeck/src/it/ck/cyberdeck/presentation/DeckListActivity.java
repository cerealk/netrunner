package it.ck.cyberdeck.presentation;

import it.ck.cyberdeck.R;
import it.ck.cyberdeck.model.Deck;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class DeckListActivity extends BaseCyberDeckActivity {

	private ArrayAdapter<String> adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_deck_list);
		ListView deckListview = (ListView) findViewById(R.id.listViewDecks);

		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1);
		loadDecks();
		deckListview.setAdapter(adapter);

		deckListview.setOnItemClickListener(new ListView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int pos,
					long id) {
				String deckName = (String) parent.getItemAtPosition(pos);
				Deck deck = getDeckService().loadDeck(deckName);
				Intent intent = new Intent(DeckListActivity.this,
						DeckActivity.class);
				intent.putExtra(BaseDeckActivity.DECK_ARG_ID, deck);
				startActivity(intent);

			}
		});
		registerForContextMenu(deckListview);
		Button newDeckButton = (Button) findViewById(R.id.button1);
		newDeckButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(DeckListActivity.this,
						NewDeckWizard.class);
				startActivity(intent);

			}

		});

	}

	@Override
	protected void onStart() {
		super.onStart();
		loadDecks();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.deck_list, menu);
		return true;
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
