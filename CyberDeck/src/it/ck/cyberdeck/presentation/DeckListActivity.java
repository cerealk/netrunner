package it.ck.cyberdeck.presentation;

import java.util.List;

import it.ck.cyberdeck.R;
import it.ck.cyberdeck.app.DeckService;
import it.ck.cyberdeck.model.Deck;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.*;

public class DeckListActivity extends Activity {

	private DeckService service;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_deck_list);
		service = ((CyberDeckApp)getApplication()).getDeckService();
		ListView deckListview = (ListView) findViewById(R.id.listViewDecks);
		
		CyberDeckApp app = (CyberDeckApp) getApplication();
		
		List<String> names = app.getDeckService().deckNames();
		String[] deckNames = new String[names.size()];
		names.toArray(deckNames);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1,
				deckNames);
		deckListview.setAdapter(adapter);
		
		deckListview.setOnItemClickListener(new ListView.OnItemClickListener(){

			@Override
      public void onItemClick(AdapterView<?> parent, View view, int pos,
          long id) {
	      String deckName = (String) parent.getItemAtPosition(pos);
	      Deck deck = service.loadDeck(deckName);
	      Intent intent = new Intent(DeckListActivity.this, NewDeckWizard.class);
	      intent.putExtra("deck", deck);
	      startActivity(intent);
	      
      }});
		
		Button newDeckButton = (Button) findViewById(R.id.button1);
		newDeckButton.setOnClickListener(new View.OnClickListener(){

			@Override
      public void onClick(View v) {
	      Intent intent = new Intent(DeckListActivity.this, DeckActivity.class);
	      startActivity(intent);
	      
      }
			
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.deck_list, menu);
		return true;
	}

}
