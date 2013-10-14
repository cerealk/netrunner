package it.ck.cyberdeck.presentation.service;

import java.util.List;

import it.ck.cyberdeck.model.Card;
import it.ck.cyberdeck.model.CardLibrary;
import it.ck.cyberdeck.presentation.MassDownloadActivity;
import android.os.AsyncTask;

public class MassDownloadTask extends AsyncTask<Void, Integer, Void>{

	private MassDownloadActivity activity;
	private CardLibrary cardLibrary;

	public MassDownloadTask (MassDownloadActivity activity, CardLibrary cardLibrary){
		this.activity = activity;
		this.cardLibrary = cardLibrary;
		
	}
	
	@Override
	protected Void doInBackground(Void... params) {
		List<Card> cards = cardLibrary.getCardList();
		ImageService is = new AndroidFSImageService(activity);
		int i=0;
		for(Card card: cards){
			
			is.getCardImage(card.getKey());
			i++;
			activity.updateProgress(getWorkDone(i));
		}
		
		
		return null;
	}

	private Integer getWorkDone(int i) {
		return i/cardLibrary.getCardList().size() *100;
	}

}
