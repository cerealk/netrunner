package it.ck.cyberdeck.presentation.adapter;

import it.ck.cyberdeck.R;
import it.ck.cyberdeck.model.CardEntry;

import java.util.List;

import android.content.Context;
import android.view.*;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CardEntryListViewAdapter extends ArrayAdapter<CardEntry> {

	private Context context;
	private List<CardEntry> values;

	public CardEntryListViewAdapter(Context context, List<CardEntry> values){
		super(context, R.layout.deck_entry_layout, values);
		this.context = context;
		this.values = values;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		View rowView = inflater.inflate(R.layout.deck_entry_layout, parent, false);
		
		TextView textView = (TextView) rowView.findViewById(R.id.label);
		TextView countText = (TextView) rowView.findViewById(R.id.countText);
		CardEntry card = values.get(position);
		textView.setText(card.getCard().getName());
		countText.setText(String.valueOf(card.getCount()));

		return rowView;
	}
	
}
