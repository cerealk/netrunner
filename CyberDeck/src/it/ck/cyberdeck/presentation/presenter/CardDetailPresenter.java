package it.ck.cyberdeck.presentation.presenter;

import it.ck.cyberdeck.model.Card;
import it.ck.cyberdeck.presentation.CardDetailView;
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
			cardDetailFragment.setCardImage(cardImage);
		}

	}

}
