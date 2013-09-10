package it.ck.cyberdeck.presentation.adapter;

import it.ck.cyberdeck.R;
import it.ck.cyberdeck.model.Identity;
import it.ck.cyberdeck.presentation.util.ImageFactory;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.*;
import android.widget.*;

public class IdentityAdapter extends BaseAdapter implements SpinnerAdapter {

	private List<Identity> identities;
	private Context context;

	public IdentityAdapter(Context context, List<Identity> values) {
		this.context = context;
		this.identities = values;

	}

	@Override
	public int getCount() {
		return identities.size();
	}

	@Override
	public Object getItem(int position) {
		return identities.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO: verificare se serve
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View spinView;
		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) context
			    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			spinView = inflater.inflate(R.layout.rowlayout, null);
		} else {
			spinView = convertView;
		}

		TextView textView = (TextView) spinView.findViewById(R.id.cardName);

		ImageView imageView = (ImageView) spinView.findViewById(R.id.icon);
		
		Identity identity = getIdentity(position);
		
		textView.setText(identity.name());
		Bitmap bmp = ImageFactory.getFactionImage(context, identity.side(), identity.faction());
		imageView.setImageBitmap(bmp );
		return spinView;
	}

	private Identity getIdentity(int position) {
	  return (Identity) getItem(position);
  }

}
