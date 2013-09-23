package it.ck.cyberdeck.presentation.presenter;

import it.ck.cyberdeck.model.Card;
import it.ck.cyberdeck.presentation.CardView;
import it.ck.cyberdeck.presentation.DownloaderView;
import it.ck.cyberdeck.presentation.service.ImageTask;
import it.ck.cyberdeck.presentation.CyberDeckApp;

public class CardDetailPresenter {

	private CardView cardDetailView;
	private Card card;

	public CardDetailPresenter(CardView cardDetailView, Card card) {
		this.cardDetailView = cardDetailView;
		this.card=card;
	}

	public void populateView() {
		if (card != null) {
			DownloaderView dlv = (DownloaderView)cardDetailView;
			ImageTask downloader = new ImageTask(dlv , card.getKey(), ((CyberDeckApp)dlv.getContext().getApplicationContext()).getImageService());
			downloader.execute();
		}

	}

}
