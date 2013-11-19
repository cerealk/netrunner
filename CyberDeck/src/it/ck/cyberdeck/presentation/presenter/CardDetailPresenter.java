package it.ck.cyberdeck.presentation.presenter;

import it.ck.cyberdeck.model.Card;
import it.ck.cyberdeck.presentation.CardView;

import com.nostra13.universalimageloader.core.ImageLoader;

public class CardDetailPresenter {

	private CardView cardDetailView;
	private Card card;
	
	

	public CardDetailPresenter(CardView cardDetailView, Card card) {
		this.cardDetailView = cardDetailView;
		this.card=card;
	}

	public void populateView() {
		if (card != null) {
			ImageLoader loader = ImageLoader.getInstance();
			
			String urlString = "http://netrunnercards.info/web/bundles/netrunnerdbcards/images/cards/300x418/"+ card.getKey().getCardCode() +".png";
			
			loader.displayImage(urlString, cardDetailView.getCardImageView());
		}

	}

}
