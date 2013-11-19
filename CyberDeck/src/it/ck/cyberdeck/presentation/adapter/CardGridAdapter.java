package it.ck.cyberdeck.presentation.adapter;

import it.ck.cyberdeck.R;
import it.ck.cyberdeck.model.Card;
import it.ck.cyberdeck.model.CardKey;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;

public class CardGridAdapter  extends BaseAdapter {
	    private Context context;
		private List<Card> cards;
		private int tmbPixHeight;
		private int tmbPixWidth;
		protected ImageLoader imageLoader;
		
		
	    public CardGridAdapter(Context c, List<Card> cards) {
	        context = c;
			this.cards = cards;
			tmbPixHeight = c.getResources().getDimensionPixelSize(R.dimen.image_thumbnail_size);
			tmbPixWidth = c.getResources().getDimensionPixelSize(R.dimen.image_thumbnail_size);
			
			imageLoader = ImageLoader.getInstance();
			
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
	    	ImageView imageView;
	        if (convertView == null) { 
	            imageView = new ImageView(context);
	            imageView.setLayoutParams(new GridView.LayoutParams(tmbPixWidth, tmbPixHeight));
	        } else {
	            imageView = (ImageView) convertView;
	        }
	        
	        CardKey key = getItem(position).getKey();
	        
	        String urlString = "http://netrunnercards.info/web/bundles/netrunnerdbcards/images/cards/300x418/"+ key.getCardCode() +".png";
	        
	        imageLoader.displayImage(urlString, imageView);
	        return imageView;
	    }

}
