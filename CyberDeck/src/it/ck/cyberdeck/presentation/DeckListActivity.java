package it.ck.cyberdeck.presentation;

import java.util.List;

import it.ck.cyberdeck.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.*;

public class DeckListActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_deck_list);
		
		ListView deckListview = (ListView) findViewById(R.id.listViewDecks);
		
		CyberDeckApp app = (CyberDeckApp) getApplication();
		
		List<String> names = app.getDeckService().deckNames();
		String[] deckNames = new String[names.size()];
		names.toArray(deckNames);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1,
				deckNames);
		deckListview.setAdapter(adapter);
		
		Button newDeckButton = (Button) findViewById(R.id.button_new_deck);
		newDeckButton.setOnClickListener(new View.OnClickListener(){

			@Override
      public void onClick(View v) {
	      Intent intent = new Intent(DeckListActivity.this, NewDeckWizard.class);
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
