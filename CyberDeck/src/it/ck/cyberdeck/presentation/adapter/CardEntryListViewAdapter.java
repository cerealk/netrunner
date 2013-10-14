package it.ck.cyberdeck.presentation.adapter;

import it.ck.cyberdeck.R;
import it.ck.cyberdeck.model.CardEntry;
import it.ck.cyberdeck.presentation.util.ImageFactory;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.*;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CardEntryListViewAdapter extends ArrayAdapter<CardEntry> {

	private Context context;

	public CardEntryListViewAdapter(Context context){
		super(context, R.layout.deck_entry_layout);
		this.context = context;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		View rowView = inflater.inflate(R.layout.deck_entry_layout, parent, false);
		
		ImageView icon = (ImageView) rowView.findViewById(R.id.icon);
		TextView textView = (TextView) rowView.findViewById(R.id.cardName);
		TextView countText = (TextView) rowView.findViewById(R.id.countText);
		CardEntry entry = getItem(position);
		textView.setText(entry.getCard().getName());
		countText.setText(String.valueOf(entry.getCount()));
		Bitmap bmp = ImageFactory.getFactionImage(context, entry.getCard().getSide(), entry.getCard().getFaction());
		icon.setImageBitmap(bmp);
		return rowView;
	}
	
}
