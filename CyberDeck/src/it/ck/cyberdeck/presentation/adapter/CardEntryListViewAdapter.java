package it.ck.cyberdeck.presentation.adapter;

import it.ck.cyberdeck.R;
import it.ck.cyberdeck.model.CardEntry;

import android.content.Context;
import android.view.*;
import android.widget.ArrayAdapter;
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
		
		TextView textView = (TextView) rowView.findViewById(R.id.cardName);
		TextView countText = (TextView) rowView.findViewById(R.id.countText);
		CardEntry card = getItem(position);
		textView.setText(card.getCard().getName());
		countText.setText(String.valueOf(card.getCount()));

		return rowView;
	}
	
}
