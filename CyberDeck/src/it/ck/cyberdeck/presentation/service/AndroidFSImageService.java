package it.ck.cyberdeck.presentation.service;

import it.ck.cyberdeck.model.CardKey;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

public class AndroidFSImageService implements ImageService {

	private Context context;

	public AndroidFSImageService(Context context){
		this.context = context;
		
	}
	
	@Override
	public Bitmap getCardImage(CardKey key) {
		File bmp = getFile(getImageKey(key)); 
		return loadBitmap(key, bmp);
	}
	
	@Override
	public Bitmap getCardImage(CardKey key, int tmbPixWidth, int tmbPixHeight) {
		File bmp = getFile(getImageKey(key));
		if(!bmp.exists()){
			saveBitmapFromURL(key);
			createAndSaveThumbnail(key);
		}
        
        return decodeBitmap(bmp, tmbPixWidth, tmbPixHeight);
	}
	
	@Override
	public Bitmap getCardThumbnail(CardKey key) {
		File bmp = getFile(getThumbnailKey(key)); 
		return loadBitmap(key, bmp);
	}
	
	@Override
	public Bitmap getCardThumbnail(CardKey key, int tmbPixWidth, int tmbPixHeight) {
		File bmp = getFile(getThumbnailKey(key));
		if(!bmp.exists()){
			saveBitmapFromURL(key);
			createAndSaveThumbnail(key);
		}
        
        return decodeBitmap(bmp, tmbPixWidth, tmbPixHeight);
	}
	
	private Bitmap loadBitmap(CardKey key, File bmpFile) {
		if (!bmpFile.exists()){
			saveBitmapFromURL(key);
			createAndSaveThumbnail(key);
		}
		return BitmapFactory.decodeFile(bmpFile.getPath());
	}

	private void createAndSaveThumbnail(CardKey key) {
		Bitmap bmp = BitmapFactory.decodeFile(getFile(getImageKey(key)).getPath());
		  final int THUMBNAIL_SIZE = 100;
		  float factor = THUMBNAIL_SIZE / (float) bmp.getHeight();
	      bmp = Bitmap.createScaledBitmap(bmp, (int) (bmp.getWidth() * factor), THUMBNAIL_SIZE, false);  
          
          File thumbnailFile = getFile(getThumbnailKey(key));
          FileOutputStream fos = null;
			try {
				if(!thumbnailFile.exists())
					thumbnailFile.createNewFile();
				fos = new FileOutputStream(thumbnailFile);
				bmp.compress(Bitmap.CompressFormat.PNG, 70, fos);
			} catch (IOException e) {
				e.printStackTrace();
			}
			finally{
				if(fos!=null)
					try {
						fos.close();
					}catch (IOException e) {}
			}
          
	}

	private String getThumbnailKey(CardKey key) {
		return key.getCardCode()+"_tmb.png";
	}
	


	private String getImageKey(CardKey key) {
		return key.getCardCode()+".png";
	}

	@SuppressLint("NewApi")
	private File getFile(String key) {
		return new File(context.getExternalFilesDir("cards"), key);
	}

	private Bitmap decodeBitmap(File file, int tmbPixWidth, int tmbPixHeight) {
		String filePath = file.getPath();
		if(tmbPixHeight == 0 || tmbPixWidth == 0)
			return BitmapFactory.decodeFile(filePath);
		else{
			
			final BitmapFactory.Options options = new BitmapFactory.Options();
	        options.inJustDecodeBounds = true;
			
			BitmapFactory.decodeFile(filePath,options);
			
			options.inSampleSize = calculateInSampleSize(options, tmbPixWidth, tmbPixHeight);
			
			options.inJustDecodeBounds = false;
			
			return BitmapFactory.decodeFile(filePath, options);
		}
	}

	
	private void saveBitmapFromURL(CardKey key) {
		try {
			String urlString = "http://netrunnercards.info/web/bundles/netrunnerdbcards/images/cards/300x418/"+ key.getCardCode() +".png";
			URL url = new URL(urlString);
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setDoInput(true);
			connection.connect();
			InputStream input = connection.getInputStream();
			saveInputStream(input, key);

		} catch (IOException e) {
			e.printStackTrace();
			Log.e("getBmpFromUrl error: ", e.getMessage().toString());
		}
	}
	
	private void saveInputStream(InputStream input, CardKey key) throws IOException {
		try {
			File bmp = getFile(getImageKey(key));
			final OutputStream output = new FileOutputStream(bmp);
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
	
	private static int calculateInSampleSize(BitmapFactory.Options options,
            int reqWidth, int reqHeight) {

		final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);

            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;

            final float totalPixels = width * height;
            final float totalReqPixelsCap = reqWidth * reqHeight * 2;

            while (totalPixels / (inSampleSize * inSampleSize) > totalReqPixelsCap) {
                inSampleSize++;
            }
        }
        return inSampleSize;
    }
}
