/*
 * @author Maver1ck
 * */

package it.ck.cyberdeck.presentation.service;

import it.ck.cyberdeck.model.CardKey;
import it.ck.cyberdeck.presentation.DownloaderView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

public class ImageDownloader extends AsyncTask<Void, Integer, Void> {

	private String url;
	private FileOutputStream fos;
	private DownloaderView dlv;
	private Bitmap bmp;
	private CardKey key;
	private Context context;

	public ImageDownloader(String url, DownloaderView dlv, CardKey key) {
		this.url = url;
		this.dlv = dlv;
		this.context = dlv.getContext();
		this.key = key;
	}
	
	@Override
	protected void onPreExecute() {
		dlv.showProgress();
		super.onPreExecute();
	}


	@Override
	protected Void doInBackground(Void... arg0) {
		bmp = getBitmapFromURL(url);
		return null;
	}

	@Override
	protected void onPostExecute(Void result) {
		saveImage();
		dlv.setImage(bmp);
		super.onPostExecute(result);
	}

	public Bitmap getBitmapFromURL(String link) {
		try {
			URL url = new URL(link);
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setDoInput(true);
			connection.connect();
			InputStream input = connection.getInputStream();
			Bitmap myBitmap = BitmapFactory.decodeStream(input);
			
			return myBitmap;

		} catch (IOException e) {
			e.printStackTrace();
			Log.e("getBmpFromUrl error: ", e.getMessage().toString());
			return null;
		}
	}
	
	private void saveImage() {

		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		bmp.compress(Bitmap.CompressFormat.PNG, 100, bytes);
		File file = new File(context.getDir("cards", Context.MODE_PRIVATE), key.getCardCode() + ".png");
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			fos = new FileOutputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			fos.write(bytes.toByteArray());
			fos.flush();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}


}
