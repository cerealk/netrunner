package it.ck.cyberdeck.presentation;

import it.ck.cyberdeck.R;
import it.ck.cyberdeck.R.layout;
import it.ck.cyberdeck.R.menu;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class NewDeckWizard extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_deck_wizard);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new_deck_wizard, menu);
		return true;
	}

}
