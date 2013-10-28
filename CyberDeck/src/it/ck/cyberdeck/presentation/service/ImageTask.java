/*
 * @author Maver1ck
 * */

package it.ck.cyberdeck.presentation.service;

import it.ck.cyberdeck.model.CardKey;
import it.ck.cyberdeck.presentation.DownloaderView;
import android.graphics.Bitmap;
import android.os.AsyncTask;

public class ImageTask extends AsyncTask<Void, Integer, Void> {

	private DownloaderView dlv;
	protected Bitmap bmp;
	protected final int reqWidth;
	protected final int reqHeight;
	protected final CardKey key;
	protected ImageService imageService;

	public ImageTask(DownloaderView dlv, CardKey key, ImageService imageService) {
		this.dlv = dlv;
		this.key=key;
		this.imageService = imageService;
		reqWidth = 0;
		reqHeight = 0;
	}
	
	public ImageTask( DownloaderView dlv, CardKey key, ImageService imageService, int reqWidth, int reqHeight) {
		this.dlv = dlv;
		this.key=key;
		this.imageService = imageService;
		this.reqWidth = reqWidth;
		this.reqHeight = reqHeight;
	}
	
	public CardKey getKey() {
		return key;
	}

	@Override
	protected void onPreExecute() {
		dlv.showProgress();
		super.onPreExecute();
	}


	@Override
	protected Void doInBackground(Void... arg0) {
		bmp = imageService.getCardImage(key, reqWidth, reqHeight);
		return null;
	}
	
	@Override
	protected void onPostExecute(Void result) {
		dlv.setImage(bmp);
		super.onPostExecute(result);
	}

	

}
