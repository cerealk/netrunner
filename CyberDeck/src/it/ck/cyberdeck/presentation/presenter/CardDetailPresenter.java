package it.ck.cyberdeck.presentation.presenter;

import it.ck.cyberdeck.model.Card;
import it.ck.cyberdeck.presentation.CardDetailView;
import it.ck.cyberdeck.presentation.DownloaderView;
import it.ck.cyberdeck.presentation.service.ImageDownloader;

public class CardDetailPresenter {

	private CardDetailView cardDetailView;
	private Card card;

	public CardDetailPresenter(CardDetailView cardDetailView, Card card) {
		this.cardDetailView = cardDetailView;
		this.card=card;
	}

	public void populateView() {
		if (card != null) {
			String url = "http://netrunnercards.info/web/bundles/netrunnerdbcards/images/cards/300x418/"+ card.getKey().getCardCode() +".png";
			ImageDownloader downloader = new ImageDownloader(url , (DownloaderView)cardDetailView, card.getKey());
			downloader.execute();
		}

	}

}
