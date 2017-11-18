package it.ck.cyberdeck.presentation.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import it.ck.cyberdeck.R;
import it.ck.cyberdeck.presentation.BaseCyberDeckActivity;

public class WelcomeActivity extends BaseCyberDeckActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);

		final Button buttonLibrary = findViewById(R.id.button_library);

		buttonLibrary.setOnClickListener(v -> {
      Intent intent = new Intent(WelcomeActivity.this,
          CardListActivity.class);
      startActivity(intent);
    });

		final Button buttonNewDeck = findViewById(R.id.button_new_deck);
		buttonNewDeck.setOnClickListener(v -> {
      Intent intent = new Intent(WelcomeActivity.this,
          NewDeckActivity.class);
      startActivity(intent);
    });

		final Button buttonDeckList = findViewById(R.id.button_deck_list);
		buttonDeckList.setOnClickListener(v -> {
      Intent intent = new Intent(WelcomeActivity.this,
          DeckListActivity.class);
      startActivity(intent);

    });
	}
	
}
