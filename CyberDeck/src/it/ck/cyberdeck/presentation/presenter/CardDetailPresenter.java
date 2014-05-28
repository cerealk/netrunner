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
			
			String urlString = "http://netrunnerdb.com/web/bundles/netrunnerdbcards/images/cards/en-large/"+ card.getKey().getCardCode() +".png";
			
			loader.displayImage(urlString, cardDetailView.getCardImageView());
		}

	}

}
