package it.ck.cyberdeck.presentation.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import java.util.List;

import it.ck.cyberdeck.R;
import it.ck.cyberdeck.app.DeckService;
import it.ck.cyberdeck.model.Deck;
import it.ck.cyberdeck.model.Identity;
import it.ck.cyberdeck.presentation.BaseCyberDeckActivity;
import it.ck.cyberdeck.presentation.adapter.IdentityAdapter;

public class NewDeckActivity extends BaseCyberDeckActivity {

	private EditText deckNameText;
	private Spinner deckIdentity;
	private List<Identity> identities;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_deck_wizard);
		final DeckService deckService = getDeckService();
		this.identities = deckService.loadCardLibrary().getIdentities();

		this.deckNameText = findViewById(R.id.field_deck_name);
		this.deckIdentity = findViewById(R.id.deck_identity_spinner);
		SpinnerAdapter dataAdapter = new IdentityAdapter(getApplicationContext(),
		    identities);
		deckIdentity.setAdapter(dataAdapter);

		Button createDeck = findViewById(R.id.create_deck);

		createDeck.setOnClickListener(v -> {

      Deck deck = deckService.createDeck(getSelectedIdentity(), deckNameText
          .getText().toString());
      deckService.saveDeck(deck);
      Intent intent = new Intent(NewDeckActivity.this, DeckActivity.class);
      intent.putExtra(DeckActivity.DECK_ARG_ID, deck);
      startActivity(intent);
    });
	}

	protected Identity getSelectedIdentity() {
		int position = deckIdentity.getSelectedItemPosition();
		return identities.get(position);
	}

}
