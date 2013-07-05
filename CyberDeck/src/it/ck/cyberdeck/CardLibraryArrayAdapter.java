package it.ck.cyberdeck;

import it.ck.cyberdeck.R;
import it.ck.cyberdeck.model.Card;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CardLibraryArrayAdapter extends ArrayAdapter<Card> {

	private static final String PACKAGE_NAME = "it.ck.androidnetrunnerlibrarymanager";
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

		int resImg = R.drawable.ic_launcher;
		switch (card.getIdentity()) {
		case ANARCH:
			resImg = context.getResources().getIdentifier("anarchs",
					"drawable", PACKAGE_NAME);
			break;
		case CRIMINAL:
			resImg = context.getResources().getIdentifier("criminal",
					"drawable", PACKAGE_NAME);
			break;
		case SHAPER:
			resImg = context.getResources().getIdentifier("shaper", "drawable",
					PACKAGE_NAME);
			break;
		case HAAS_BIOROID:
			resImg = context.getResources().getIdentifier("hb", "drawable",
					PACKAGE_NAME);
			break;
		case JINTEKI:
			resImg = context.getResources().getIdentifier("jinteki",
					"drawable", PACKAGE_NAME);
			break;
		case WEYLAND_CONSORTIUM:
			resImg = context.getResources().getIdentifier("wayland",
					"drawable", PACKAGE_NAME);
			break;
		case NBN:
			resImg = context.getResources().getIdentifier("nbn", "drawable",
					PACKAGE_NAME);
			break;
		default:
			break;
		}

		imageView.setImageResource(resImg);

		return rowView;
	}

}
