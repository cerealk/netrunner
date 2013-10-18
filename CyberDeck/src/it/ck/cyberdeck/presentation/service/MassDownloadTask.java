package it.ck.cyberdeck.presentation.service;

import it.ck.cyberdeck.model.Card;
import it.ck.cyberdeck.model.CardLibrary;
import it.ck.cyberdeck.presentation.MassDownloadView;

import java.util.List;

import android.os.AsyncTask;

public class MassDownloadTask extends AsyncTask<Void, Integer, Void>{

	private CardLibrary cardLibrary;
	private ImageService is;
	private MassDownloadView progress;
	private int clSize;

	public MassDownloadTask (ImageService is, CardLibrary cardLibrary){
		this.is = is;
		this.cardLibrary = cardLibrary;
		this.clSize = cardLibrary.getCardList().size();
	}
	
	@Override
	protected Void doInBackground(Void... params) {
		List<Card> cards = cardLibrary.getCardList();
		int i=0;
		for(Card card: cards){
			getCardImage(card);
			i++;
			publishProgress(calculateDonePercentage(i));
		}
		return null;
	}

	private void getCardImage(Card card) {
		is.getCardImage(card.getKey());
	}

	private Integer calculateDonePercentage(int i) {
		return i * 100 / clSize ;
	}
	
	@Override
	protected void onProgressUpdate(Integer... values) {
		progress.onUpdateProgress(values[0]);
		super.onProgressUpdate(values);
	}
	
	@Override
	protected void onPostExecute(Void result) {
		super.onPostExecute(result);
		progress.onTaskEnd();
	}

	public void setProgressView(MassDownloadView massDownloadActivity) {
		this.progress = massDownloadActivity;
	}

}
