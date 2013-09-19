/*
 * @author Maver1ck
 * */

package it.ck.cyberdeck.presentation.service;

import it.ck.cyberdeck.model.CardKey;
import it.ck.cyberdeck.presentation.DownloaderView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

public class ImageTask extends AsyncTask<Void, Integer, Void> {

	private String url;
	private DownloaderView dlv;
	private Bitmap bmp;
	private File targetFile;

	public ImageTask(String url, DownloaderView dlv, CardKey key) {
		this.url = url;
		this.dlv = dlv;
		this.targetFile =  new File(dlv.getContext().getDir("cards", Context.MODE_PRIVATE), key.getCardCode()+".png");
	}
	
	@Override
	protected void onPreExecute() {
		dlv.showProgress();
		super.onPreExecute();
	}


	@Override
	protected Void doInBackground(Void... arg0) {
		if (!targetFile.exists()){
			saveBitmapFromURL(url);
		}
		bmp =  BitmapFactory.decodeFile(targetFile.getPath());
		return null;
	}

	@Override
	protected void onPostExecute(Void result) {
		dlv.setImage(bmp);
		super.onPostExecute(result);
	}

	public void saveBitmapFromURL(String link) {
		try {
			URL url = new URL(link);
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setDoInput(true);
			connection.connect();
			InputStream input = connection.getInputStream();
			saveInputStream(input);

		} catch (IOException e) {
			e.printStackTrace();
			Log.e("getBmpFromUrl error: ", e.getMessage().toString());
		}
	}
	
	private void saveInputStream(InputStream input) throws IOException {
		try {
			final OutputStream output = new FileOutputStream(targetFile);
		    try {
		        try {
		            final byte[] buffer = new byte[1024];
		            int read;

		            while ((read = input.read(buffer)) != -1)
		                output.write(buffer, 0, read);

		            output.flush();
		        } finally {
		            output.close();
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		} finally {
		    input.close();
		}
		
	}

}
