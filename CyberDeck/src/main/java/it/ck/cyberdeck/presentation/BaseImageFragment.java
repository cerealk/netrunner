package it.ck.cyberdeck.presentation;

import it.ck.cyberdeck.R;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class BaseImageFragment extends Fragment  {

	private ImageView iView;


	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
													 Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_card_detail,
				container, false);

		iView = rootView.findViewById(R.id.card_detail);
		
		return rootView;
	}

	protected ImageView getImageView() {
		return iView;
	}
}
