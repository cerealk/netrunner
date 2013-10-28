package it.ck.cyberdeck.presentation.adapter;

import it.ck.cyberdeck.R;
import it.ck.cyberdeck.model.Card;
import it.ck.cyberdeck.model.CardKey;
import it.ck.cyberdeck.presentation.CyberDeckApp;
import it.ck.cyberdeck.presentation.DownloaderView;
import it.ck.cyberdeck.presentation.service.ImageTask;
import it.ck.cyberdeck.presentation.service.ThumbnailImageTask;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask.Status;
import android.support.v4.util.LruCache;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class CardGridAdapter  extends BaseAdapter {
	    private Context context;
		private List<Card> cards;
		private int tmbPixHeight;
		private int tmbPixWidth;
		private LruCache<String, Bitmap> imageCache;
		
	    public CardGridAdapter(Context c, List<Card> cards) {
	        context = c;
			this.cards = cards;
			tmbPixHeight = c.getResources().getDimensionPixelSize(R.dimen.image_thumbnail_size);
			tmbPixWidth = c.getResources().getDimensionPixelSize(R.dimen.image_thumbnail_size);
			imageCache = getCyberDeckApp().getImageCache();
	    }

		private CyberDeckApp getCyberDeckApp() {
			return (CyberDeckApp)context.getApplicationContext();
		}

	    public int getCount() {
	        return cards.size();
	    }

	    public Card getItem(int position) {
	        return cards.get(position);
	    }

	    public long getItemId(int position) {
	        return 0;
	    }

	    public View getView(int position, View convertView, ViewGroup parent) {
	    	ImageDowloaderView imageView;
	        if (convertView == null) { 
	            imageView = new ImageDowloaderView(context);
	            imageView.setLayoutParams(new GridView.LayoutParams(tmbPixWidth, tmbPixHeight));
	            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
	            imageView.setPadding(8, 8, 8, 8);
	            imageView.setBackgroundResource(R.drawable.runner_back);
	        } else {
	            imageView = (ImageDowloaderView) convertView;
	            imageView.setImage(null);
	            
	        }
	        
	        imageView.setBackgroundResource(R.drawable.runner_back);
	        CardKey key = getItem(position).getKey();
	        Bitmap bitmap = getBitmapFromCache(key.getCardCode());
			if(bitmap!=null){
	        	imageView.setImage(bitmap);
	        }else{
				ImageTask task = new ThumbnailImageTask(imageView, key,getCyberDeckApp().getImageService(), tmbPixWidth, tmbPixHeight);
				imageView.setTask(task);
		        task.execute();
	        }
	        return imageView;
	    }

		private Bitmap getBitmapFromCache(String cardCode) {
			return imageCache.get(cardCode);
		}
		
		private void addImgToCache(CardKey key, Bitmap bmp) {
		    if (key!=null && getBitmapFromCache(key.getCardCode()) == null && bmp!= null) {
		        imageCache.put(key.getCardCode(), bmp);
		    }
		}
	    
		class ImageDowloaderView extends ImageView implements DownloaderView{

			private ImageTask task;

			public ImageDowloaderView(Context context) {
				super(context);
			}

			@Override
			public void showProgress() {
			}

			@Override
			public void setImage(Bitmap bmp) {
				if (task!=null)
					addImgToCache(task.getKey(), bmp);
				this.setImageBitmap(bmp);
			}
			
			

			public void setTask(ImageTask task){
				if(this.task != null){
					Status status = task.getStatus();
					if(!status.equals(Status.FINISHED)){
						if(!this.task.getKey().equals(task.getKey()))
						this.task.cancel(true);
					}
				}
				this.task = task;
			}
	    	
	    }
		
}
