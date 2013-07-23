package it.ck.cyberdeck.presentation;

import it.ck.cyberdeck.R;
import it.ck.cyberdeck.app.DeckService;
import it.ck.cyberdeck.model.Deck;
import it.ck.cyberdeck.model.Identity;
import it.ck.cyberdeck.presentation.adapter.IdentityAdapter;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.*;

public class NewDeckWizard extends Activity {

	private EditText deckNameText;
	private Spinner deckIdentity;
	private Button createDeck;
	private DeckService deckService;
	private List<Identity> identities;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_deck_wizard);
		this.deckService = ((CyberDeckApp) getApplication()).getDeckService();
		this.identities = deckService.loadCardLibrary().getIdentities();

		this.deckNameText = (EditText) findViewById(R.id.field_deck_name);
		this.deckIdentity = (Spinner) findViewById(R.id.deck_identity_spinner);
		SpinnerAdapter dataAdapter = new IdentityAdapter(getApplicationContext(),
		    identities);
		deckIdentity.setAdapter(dataAdapter);

		this.createDeck = (Button) findViewById(R.id.create_deck);

		this.createDeck.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Deck deck = deckService.createDeck(getSelectedIdentity(), deckNameText
				    .getText().toString());
				deckService.saveDeck(deck);
				Intent intent = new Intent(NewDeckWizard.this, DeckActivity.class);
				intent.putExtra("deck", deck);
				startActivity(intent);
			}

		});
	}

	protected Identity getSelectedIdentity() {
		int position = deckIdentity.getSelectedItemPosition();
		return identities.get(position);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.new_deck_wizard, menu);
		return true;
	}

}
