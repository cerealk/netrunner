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

	@Override
	public Bitmap getCardImage(CardKey key, int tmbPixWidth, int tmbPixHeight) {
		
		File bmp = new File(context.getDir("cards", Context.MODE_PRIVATE), key.getCardCode()+".png"); 
		if (bmp.exists()){

			final BitmapFactory.Options options = new BitmapFactory.Options();
	        options.inJustDecodeBounds = true;
			
			BitmapFactory.decodeFile(bmp.getPath(),options);
			
			options.inSampleSize = calculateInSampleSize(options, tmbPixWidth, tmbPixHeight);
			
			options.inJustDecodeBounds = false;
			
			return BitmapFactory.decodeFile(bmp.getPath(),options);
		}
        
        return null;
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
