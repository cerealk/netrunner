package it.ck.cyberdeck.presentation;

import it.ck.cyberdeck.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class BaseImageFragment extends Fragment  {

	private ImageView iView;
	
	private View rootView;
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_card_detail,
				container, false);

		iView = (ImageView) rootView.findViewById(R.id.card_detail);
		
		return rootView;
	}

	public ImageView getImageView() {
		return iView;
	}
}
