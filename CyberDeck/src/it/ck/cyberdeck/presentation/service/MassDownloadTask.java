package it.ck.cyberdeck.presentation.service;

import it.ck.cyberdeck.model.Card;
import it.ck.cyberdeck.model.CardLibrary;

import java.util.List;

import android.os.AsyncTask;
import android.widget.TextView;

public class MassDownloadTask extends AsyncTask<Void, Integer, Void>{

	private CardLibrary cardLibrary;
	private ImageService is;
	private TextView progress;
	private int clSize;

	public MassDownloadTask (ImageService is, CardLibrary cardLibrary, TextView progress){
		this.is = is;
		this.cardLibrary = cardLibrary;
		this.progress = progress;
		this.clSize = cardLibrary.getCardList().size();
	}
	
	@Override
	protected Void doInBackground(Void... params) {
		List<Card> cards = cardLibrary.getCardList();
		int i=0;
		for(Card card: cards){
			
			is.getCardImage(card.getKey());
			i++;
			
			publishProgress(getWorkDone(i));
		}
		
		
		return null;
	}

	private Integer getWorkDone(int i) {
		
		return i * 100 / clSize ;
	}
	
	@Override
	protected void onProgressUpdate(Integer... values) {
		progress.setText(values[0].toString());
		super.onProgressUpdate(values);
	}

	public void setProgressView(TextView advancement) {
		this.progress = advancement;
		
	}

}
