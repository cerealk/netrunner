/*
 * @author Maver1ck
 * */

package it.ck.cyberdeck.presentation.service;

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
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

/*
 * here we are going to use an AsyncTask to download the image 
 *      in background while showing the download progress
 * */

public class ImageDownloader extends AsyncTask<Void, Integer, Void> {

	private ProgressBar progressBar;
	private String url;
	private Button save;
	private Context c;
	private int progress;
	private ImageView imgView;
	private Bitmap bmp;
	private TextView percent;
	private ImageLoaderListener listener;
	private FileOutputStream fos;

	/*--- constructor ---*/
	public ImageDownloader(String url, ProgressBar pb, Button save,
			ImageView img, TextView percent, Context c, Bitmap bmp, ImageLoaderListener listener) {
	/*--- we need to pass some objects we are going to work with ---*/
		this.url = url;
		this.progressBar = pb;
		this.save = save;
		this.c = c;
		this.imgView = img;
		this.percent = percent;
		this.bmp = bmp;
		this.listener = listener;
	}
	
	/*--- we need this interface for keeping the reference to our Bitmap from the MainActivity. 
	 *  Otherwise, bmp would be null in our MainActivity*/
	public interface ImageLoaderListener {
		
		void onImageDownloaded(Bitmap bmp);
		
	}

	@Override
	protected void onPreExecute() {

		progress = 0;
		progressBar.setVisibility(View.VISIBLE);
		percent.setVisibility(View.VISIBLE);
		Toast.makeText(c, "starting download", Toast.LENGTH_SHORT).show();

		super.onPreExecute();
	}

	@Override
	protected Void doInBackground(Void... arg0) {

		bmp = getBitmapFromURL(url);

		while (progress < 100) {

			progress += 1;

			publishProgress(progress);

			/*--- an image download usually happens very fast so you would not notice 
			 * how the ProgressBar jumps from 0 to 100 percent. You can use the method below 
			 * to visually "slow down" the download and see the progress bein updated ---*/

		  SystemClock.sleep(200);

		}

		return null;
	}

	@Override
	protected void onProgressUpdate(Integer... values) {

	/*--- show download progress on main UI thread---*/
		progressBar.setProgress(values[0]);
		percent.setText(values[0] + "%");

		super.onProgressUpdate(values);
	}

	@Override
	protected void onPostExecute(Void result) {

		if (listener != null) {
			listener.onImageDownloaded(bmp);
			}
		saveImage();
		imgView.setImageBitmap(bmp);
		save.setEnabled(true);
		Toast.makeText(c, "download complete", Toast.LENGTH_SHORT).show();

		super.onPostExecute(result);
	}

	public Bitmap getBitmapFromURL(String link) {
		/*--- this method downloads an Image from the given URL, 
		 *  then decodes and returns a Bitmap object
		 ---*/
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

		/*--- this method will save your downloaded image to SD card ---*/

		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		/*--- you can select your preferred CompressFormat and quality. 
		 * I'm going to use JPEG and 100% quality ---*/
		bmp.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
		/*--- create a new file on SD card ---*/
		File file = new File(c.getDir("decks", Context.MODE_PRIVATE), "myDownloadedImage.png");
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		/*--- create a new FileOutputStream and write bytes to file ---*/
		try {
			fos = new FileOutputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			fos.write(bytes.toByteArray());
			fos.close();
			Toast.makeText(c, "Image saved", Toast.LENGTH_SHORT).show();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}


}
