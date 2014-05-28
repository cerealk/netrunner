package it.ck.cyberdeck.presentation.adapter;

import it.ck.cyberdeck.R;
import it.ck.cyberdeck.model.CardEntry;
import it.ck.cyberdeck.model.Deck;

import org.apache.commons.lang3.StringUtils;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

public class CardEntryListViewAdapter extends ArrayAdapter<CardEntry> {

	private Context context;
	private Deck deck;
	private Typeface font;
	private ImageLoader imageLoader;

	public CardEntryListViewAdapter(Context context, Deck deck){
		super(context, R.layout.composite_deck_entry_layout);
		this.context = context;
		this.deck = deck;
		this.font = Typeface.createFromAsset( context.getAssets(), "fontawesome-webfont.ttf" );
		imageLoader = ImageLoader.getInstance();
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		View rowView;
		
		if(convertView == null)
			rowView = inflater.inflate(R.layout.composite_deck_entry_layout, parent, false);
		else 
			rowView = convertView;
		
		ImageView icon = (ImageView) rowView.findViewById(R.id.icon);
		TextView cardName = (TextView) rowView.findViewById(R.id.cardName);
		TextView cardReputation = (TextView) rowView.findViewById(R.id.reputation);
		cardReputation.setTypeface(font);
		TextView countText = (TextView) rowView.findViewById(R.id.countText);
		CardEntry entry = getItem(position);
		cardName.setText(getCardNameText(entry));
		cardReputation.setText(getCardReputationText(entry));
		countText.setText(getCardCountText(entry));
		String urlString = "http://netrunnerdb.com/web/bundles/netrunnerdbcards/images/cards/en-large/"+ entry.getKey().getCardCode() +".png";
		imageLoader.displayImage(urlString, icon);
		return rowView;
	}

	private CharSequence getCardReputationText(CardEntry entry) {
		Integer repoCost = deck.getIdentity().calculateReputationCost(entry.getCard());
		
		if (repoCost > 0){
			return StringUtils.repeat(context.getResources().getString(R.string.reputation_dot), repoCost);
		}
		return "";
	}

	private String getCardCountText(CardEntry entry) {
		return String.valueOf(entry.getCount());
	}

	private String getCardNameText(CardEntry entry) {
		return entry.getCard().getName();
	}
	
}
