package it.ck.cyberdeck.presentation.service;

import it.ck.cyberdeck.model.CardKey;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class ResourceImageService implements ImageService {

	private Context context;

	public ResourceImageService(Context context){
		this.context = context;
	}
	
	@Override
	public Bitmap getCardImage(CardKey key) {
		int resId = getResources().getIdentifier("_"+ key.getCardCode(), "drawable", "it.ck.cyberdeck");
		Bitmap result = BitmapFactory.decodeResource(getResources(), resId);
		key.getCardCode();
		return result;
	}

	private Resources getResources() {
		return context.getResources();
	}

}
