package it.ck.cyberdeck.presentation;

import it.ck.cyberdeck.R;
import it.ck.cyberdeck.model.Deck;
import it.ck.cyberdeck.presentation.adapter.DeckAdapter;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class DeckActivity extends Activity implements DeckPublisher{

	private TextView deckName;
	private TextView identityName;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_deck);
		

		deckName = (TextView) findViewById(R.id.deck_name);
		identityName = (TextView) findViewById(R.id.deck_identity);
		Deck deck;
		if (savedInstanceState != null){
			deck = (Deck) savedInstanceState.getSerializable("deck");
		}	else {
			deck = (Deck) getIntent().getSerializableExtra("deck");
		}
		DeckAdapter adapter = new DeckAdapter(deck);
		adapter.adapt(this);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.deck, menu);
		return true;
	}

	@Override
	public void publishIdentityName(String identityName) {
		this.identityName.setText(identityName);
		
	}

	@Override
	public void publishDeckName(String deckName) {
		this.deckName.setText(deckName);
	}

}
