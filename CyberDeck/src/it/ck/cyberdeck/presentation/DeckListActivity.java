package it.ck.cyberdeck.presentation;

import java.util.List;

import it.ck.cyberdeck.R;
import it.ck.cyberdeck.app.DeckService;
import it.ck.cyberdeck.model.Deck;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.*;
import android.widget.AdapterView.AdapterContextMenuInfo;

public class DeckListActivity extends Activity {

	private DeckService service;
	private ArrayAdapter<String> adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_deck_list);
		service = ((CyberDeckApp) getApplication()).getDeckService();
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
				Deck deck = service.loadDeck(deckName);
				Intent intent = new Intent(DeckListActivity.this,
						DeckActivity.class);
				intent.putExtra("deck", deck);
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

	private void loadDecks() {
		List<String> names = service.deckNames();
		adapter.clear();

		for (String name : names) {
			adapter.add(name);
		}
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
		service.deleteDeck(adapter.getItem(position));
		loadDecks();
	}

}
