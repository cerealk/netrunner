package it.ck.cyberdeck.presentation.service;

import java.io.File;

import it.ck.cyberdeck.model.CardKey;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class AndroidFSImageService implements ImageService {

	private Context context;

	public AndroidFSImageService(Context context){
		this.context = context;
	}
	
	@Override
	public Bitmap getCardImage(CardKey key) {
		File bmp = new File(context.getDir("cards", Context.MODE_PRIVATE), key.getCardCode()+".png"); 
		if (bmp.exists()){
			return BitmapFactory.decodeFile(bmp.getPath());
		}
		
		return null;
	}

}
