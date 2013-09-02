package it.ck.cyberdeck.presentation.presenter;

import it.ck.cyberdeck.model.Card;
import it.ck.cyberdeck.presentation.CardDetailView;
import it.ck.cyberdeck.presentation.DownloaderView;
import it.ck.cyberdeck.presentation.service.ImageDownloader;
import it.ck.cyberdeck.presentation.service.ImageService;
import android.graphics.Bitmap;

public class CardDetailPresenter {

	private CardDetailView cardDetailFragment;
	private Card card;
	private ImageService imgService;

	public CardDetailPresenter(CardDetailView cardDetailFragment,
			ImageService imgService) {
		this.cardDetailFragment = cardDetailFragment;
		this.imgService = imgService;
		setCard();
	}

	private void setCard() {
		card = cardDetailFragment.getCard();
	}

	public void populateView() {
		if (card != null) {
			Bitmap cardImage = imgService.getCardImage(card.getKey());
			if (cardImage == null){
				String url = "http://netrunnercards.info/web/bundles/netrunnerdbcards/images/cards/300x418/"+ card.getKey().getCardCode() +".png";
				ImageDownloader downloader = new ImageDownloader(url , (DownloaderView)cardDetailFragment, card.getKey());
				downloader.execute();
			} else {
				cardDetailFragment.setCardImage(cardImage);
			}
		}

	}

}
