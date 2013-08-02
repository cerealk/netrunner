package it.ck.cyberdeck.presentation.adapter;

import it.ck.cyberdeck.R;
import it.ck.cyberdeck.model.Card;
import it.ck.cyberdeck.model.Faction;
import it.ck.cyberdeck.presentation.util.ImageFactory;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.*;
import android.widget.*;

@SuppressLint("DefaultLocale")
public class CardLibraryArrayAdapter extends ArrayAdapter<Card> {

	private final Context context;
	private List<Card> values;
	private final List<Card> totalValues;

	public CardLibraryArrayAdapter(Context context, List<Card> values) {
		super(context, R.layout.rowlayout, new ArrayList<Card>(values));
		this.context = context;
		this.values = new ArrayList<Card>(values);
		this.totalValues = new ArrayList<Card>(values);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
		    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View rowView = inflater.inflate(R.layout.rowlayout, parent, false);

		TextView textView = (TextView) rowView.findViewById(R.id.cardName);

		ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
		Card card = getItem(position);
		textView.setText(card.getName());

		Faction identity = card.getFaction();
		int resImg = ImageFactory.gefFactionImageId(context, identity);

		imageView.setImageResource(resImg);

		return rowView;
	}

	@Override
	public Filter getFilter() {

		Filter filter = new Filter() {

			@SuppressWarnings("unchecked")
			@Override
			protected void publishResults(CharSequence constraint,
			    FilterResults results) {
				values = (List<Card>) results.values;
				clear();
				addAll(values);
				notifyDataSetChanged();
			}

			@Override
			protected FilterResults performFiltering(CharSequence constraint) {

				FilterResults results = new FilterResults();
				ArrayList<Card> filteredCard = new ArrayList<Card>();

				constraint = constraint.toString().toLowerCase();
				for (int i = 0; i < totalValues.size(); i++) {
					Card card = totalValues.get(i);
					if (card.getName().toLowerCase().startsWith(constraint.toString())) {
						filteredCard.add(card);
					}
				}

				results.count = filteredCard.size();
				results.values = filteredCard;

				return results;
			}
		};

		return filter;
	}

}
