package it.ck.cyberdeck.presentation.util;

import it.ck.cyberdeck.model.CardKey;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;

public class ImageLoaderFactory {

	private ImageLoader imageLoader = ImageLoader.getInstance();
	
	public void display(CardKey key, ImageView identityImg){
		String urlString = "http://netrunnerdb.com/web/bundles/netrunnerdbcards/images/cards/en-large/"+ key.getCardCode() +".png";
		imageLoader.displayImage(urlString, identityImg);
	}
}
