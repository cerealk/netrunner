package it.ck.cyberdeck.presentation.adapter;

import it.ck.cyberdeck.R;
import it.ck.cyberdeck.model.Card;
import it.ck.cyberdeck.model.Faction;
import it.ck.cyberdeck.presentation.util.ImageFactory;

import java.util.List;

import android.content.Context;
import android.view.*;
import android.widget.*;

public class CardLibraryArrayAdapter extends ArrayAdapter<Card> {

	private final Context context;
	private final List<Card> values;

	public CardLibraryArrayAdapter(Context context, List<Card> values) {
		super(context, R.layout.rowlayout, values);
		this.context = context;
		this.values = values;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		View rowView = inflater.inflate(R.layout.rowlayout, parent, false);
		
		TextView textView = (TextView) rowView.findViewById(R.id.label);
		
		ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
		Card card = values.get(position);
		textView.setText(card.getName());

		Faction identity = card.getIdentity();
		int resImg = ImageFactory.gefFactionImageId(context, identity);

		imageView.setImageResource(resImg);

		return rowView;
	}

	

}
