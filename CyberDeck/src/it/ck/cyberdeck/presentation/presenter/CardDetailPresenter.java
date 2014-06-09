package it.ck.cyberdeck.presentation.presenter;

import it.ck.cyberdeck.model.Card;
import it.ck.cyberdeck.presentation.CardView;

public class CardDetailPresenter {

	private CardView cardDetailView;
	private Card card;
	
	

	public CardDetailPresenter(CardView cardDetailView, Card card) {
		this.cardDetailView = cardDetailView;
		this.card=card;
	}

	public void populateView() {
		if (card != null) {
			cardDetailView.display(card.getKey());
		}

	}

}
