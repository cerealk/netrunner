package it.ck.cyberdeck.presentation.activity;

import it.ck.cyberdeck.R;
import it.ck.cyberdeck.presentation.BaseCyberDeckActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WelcomeActivity extends BaseCyberDeckActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);

		final Button buttonLibrary = (Button) findViewById(R.id.button_library);

		buttonLibrary.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(WelcomeActivity.this,
						CardListActivity.class);
				startActivity(intent);
			}
		});

		final Button buttonNewDeck = (Button) findViewById(R.id.button_new_deck);
		buttonNewDeck.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(WelcomeActivity.this,
						NewDeckActivity.class);
				startActivity(intent);
			}

		});

		final Button buttonDeckList = (Button) findViewById(R.id.button_deck_ist);
		buttonDeckList.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(WelcomeActivity.this,
						DeckListActivity.class);
				startActivity(intent);

			}

		});
	}

}
