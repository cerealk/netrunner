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
	private int reqWidth;
	private int reqHeight;
	private boolean scale;
	private CardKey key;

	public ImageTask(String url, DownloaderView dlv, CardKey key) {
		this.url = url;
		this.dlv = dlv;
		this.targetFile =  new File(dlv.getContext().getDir("cards", Context.MODE_PRIVATE), key.getCardCode()+".png");
	}
	
	public ImageTask(String url, DownloaderView dlv, CardKey key, int reqWidth, int reqHeight) {
		this.url = url;
		this.dlv = dlv;
		this.key = key;
		this.reqWidth = reqWidth;
		this.reqHeight = reqHeight;
		this.scale=true;
		
		this.targetFile =  new File(dlv.getContext().getDir("cards", Context.MODE_PRIVATE), key.getCardCode()+".png");
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
		if (!targetFile.exists()){
			saveBitmapFromURL(url);
		}
		bmp =  decodeBitmap();
		return null;
	}

	private Bitmap decodeBitmap() {
		if(!scale)
			return BitmapFactory.decodeFile(targetFile.getPath());
		else{
			final BitmapFactory.Options options = new BitmapFactory.Options();
	        options.inJustDecodeBounds = true;
			
			BitmapFactory.decodeFile(targetFile.getPath(),options);
			
			options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
			
			options.inJustDecodeBounds = false;
			
			return BitmapFactory.decodeFile(targetFile.getPath(), options);
		}
	}

	private int calculateInSampleSize(BitmapFactory.Options options,
            int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            // Calculate ratios of height and width to requested height and width
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);

            // Choose the smallest ratio as inSampleSize value, this will guarantee a final image
            // with both dimensions larger than or equal to the requested height and width.
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;

            // This offers some additional logic in case the image has a strange
            // aspect ratio. For example, a panorama may have a much larger
            // width than height. In these cases the total pixels might still
            // end up being too large to fit comfortably in memory, so we should
            // be more aggressive with sample down the image (=larger inSampleSize).

            final float totalPixels = width * height;

            // Anything more than 2x the requested pixels we'll sample down further
            final float totalReqPixelsCap = reqWidth * reqHeight * 2;

            while (totalPixels / (inSampleSize * inSampleSize) > totalReqPixelsCap) {
                inSampleSize++;
            }
        }
        return inSampleSize;
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
