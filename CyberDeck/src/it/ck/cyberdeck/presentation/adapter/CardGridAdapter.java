package it.ck.cyberdeck.presentation.adapter;

import it.ck.cyberdeck.R;
import it.ck.cyberdeck.model.Card;
import it.ck.cyberdeck.model.CardKey;
import it.ck.cyberdeck.presentation.DownloaderView;
import it.ck.cyberdeck.presentation.service.ImageTask;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
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
		private Bitmap bgImg;
		private LruCache<String, Bitmap> mMemoryCache;
		
	    public CardGridAdapter(Context c, List<Card> cards) {
	        context = c;
			this.cards = cards;
			tmbPixHeight = c.getResources().getDimensionPixelSize(R.dimen.image_thumbnail_size);
			tmbPixWidth = c.getResources().getDimensionPixelSize(R.dimen.image_thumbnail_size);
			
		    final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);

		    final int cacheSize = maxMemory / 6;

		    mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {
		        @Override
		        protected int sizeOf(String key, Bitmap bitmap) {
		            return bitmap.getRowBytes() * bitmap.getHeight() / 1024;
		        }
		    };
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
	            imageView.setScaleType(ImageView.ScaleType.CENTER);
	            imageView.setPadding(8, 8, 8, 8);
	        } else {
	            imageView = (ImageDowloaderView) convertView;
	            imageView.setImage(null);
	            
	        }
	        
	        imageView.setBackgroundDrawable(getBgImg());
	        CardKey key = getItem(position).getKey();
	        Bitmap bitmap = getBitmapFromCache(key.getCardCode());
			if(bitmap!=null){
	        	imageView.setImage(bitmap);
	        }else{
				String url = "http://netrunnercards.info/web/bundles/netrunnerdbcards/images/cards/300x418/"+ key.getCardCode() +".png";
				ImageTask task = new ImageTask(url, imageView, key, tmbPixWidth, tmbPixHeight);
				imageView.setTask(task);
		        task.execute();
	        }
	        return imageView;
	    }

		private Bitmap getBitmapFromCache(String cardCode) {
			return mMemoryCache.get(cardCode);
		}
	    
	    private Drawable getBgImg() {
			
			if(this.bgImg == null){
				bgImg = BitmapFactory.decodeResource(context.getResources(), R.drawable.runner_back);
			}
			return null;
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
		
		private void addImgToCache(CardKey key, Bitmap bmp) {
			
			    if (key!=null && getBitmapFromCache(key.getCardCode()) == null && bmp!= null) {
			        mMemoryCache.put(key.getCardCode(), bmp);
			    }
			
		}
	
}