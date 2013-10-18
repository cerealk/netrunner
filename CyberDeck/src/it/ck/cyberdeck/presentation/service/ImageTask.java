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
	private Bitmap bmp;
	private int reqWidth;
	private int reqHeight;
	private CardKey key;
	private ImageService imageService;

	public ImageTask(DownloaderView dlv, CardKey key, ImageService imageService) {
		this.dlv = dlv;
		this.key=key;
		this.imageService = imageService;
	}
	
	public ImageTask( DownloaderView dlv, CardKey key, ImageService imageService, int reqWidth, int reqHeight) {
		this(dlv, key, imageService);
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
