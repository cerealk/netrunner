package it.ck.cyberdeck.presentation.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import it.ck.cyberdeck.model.CardKey;
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
		File bmp = getFile(key); 
		if (bmp.exists()){
			return BitmapFactory.decodeFile(bmp.getPath());
		}
		
		return null;
	}

	private File getFile(CardKey key) {
		return new File(context.getDir("cards", Context.MODE_PRIVATE), key.getCardCode()+".png");
	}

	@Override
	public Bitmap getCardImage(CardKey key, int tmbPixWidth, int tmbPixHeight) {
		File bmp = getFile(key);
		if(!bmp.exists()){
			saveBitmapFromURL(key);
		}
        
        return decodeBitmap(key, tmbPixWidth, tmbPixHeight);
	}

	private Bitmap decodeBitmap(CardKey key, int tmbPixWidth, int tmbPixHeight) {
		String filePath = getFile(key).getPath();
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
			File bmp = getFile(key);
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
}
