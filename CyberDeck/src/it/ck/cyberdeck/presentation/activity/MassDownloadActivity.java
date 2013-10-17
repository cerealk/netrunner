package it.ck.cyberdeck.presentation.activity;

import it.ck.cyberdeck.R;
import it.ck.cyberdeck.model.CardLibrary;
import it.ck.cyberdeck.presentation.BaseCardLibraryActivity;
import it.ck.cyberdeck.presentation.service.MassDownloadTask;
import android.os.Bundle;
import android.widget.TextView;

public class MassDownloadActivity extends BaseCardLibraryActivity {

	private CardLibrary cardLibrary;
	private TextView advancement;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mass_download);
		advancement = (TextView) findViewById(R.id.textView1);
		cardLibrary = getCardLibrary();
		MassDownloadTask task = new MassDownloadTask(this, cardLibrary);
		task.execute();
	}

	public void updateProgress(Integer perc) {
		advancement.setText(perc.toString() + "%");
	}

}
