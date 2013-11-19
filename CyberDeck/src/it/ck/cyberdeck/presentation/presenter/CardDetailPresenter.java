package it.ck.cyberdeck.presentation.presenter;

import it.ck.cyberdeck.R;
import it.ck.cyberdeck.model.Card;
import it.ck.cyberdeck.presentation.CardView;
import android.graphics.Bitmap;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

public class CardDetailPresenter {

	private CardView cardDetailView;
	private Card card;
	private DisplayImageOptions options;
	
	

	public CardDetailPresenter(CardView cardDetailView, Card card) {
		this.cardDetailView = cardDetailView;
		this.card=card;
		this.options = new DisplayImageOptions.Builder()
						.showImageOnLoading(R.drawable.ic_stub)
						.showImageForEmptyUri(R.drawable.ic_empty)
						.showImageOnFail(R.drawable.ic_error)
						.cacheInMemory(true)
						.cacheOnDisc(true)
						.bitmapConfig(Bitmap.Config.RGB_565)
						.build();
	}

	public void populateView() {
		if (card != null) {
			ImageLoader loader = ImageLoader.getInstance();
			
			String urlString = "http://netrunnercards.info/web/bundles/netrunnerdbcards/images/cards/300x418/"+ card.getKey().getCardCode() +".png";
			
			loader.displayImage(urlString, cardDetailView.getCardImageView(), options);
		}

	}

}
