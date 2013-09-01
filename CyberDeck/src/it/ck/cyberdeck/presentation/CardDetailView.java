package it.ck.cyberdeck.presentation;

import android.graphics.Bitmap;
import it.ck.cyberdeck.model.Card;

public interface CardDetailView {
	
	Card getCard();

	void setCardImage(Bitmap cardImage);

}
