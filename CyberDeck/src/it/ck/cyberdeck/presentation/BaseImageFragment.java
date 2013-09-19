package it.ck.cyberdeck.presentation;

import it.ck.cyberdeck.R;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class BaseImageFragment extends Fragment implements DownloaderView {


	private ProgressBar pb;

	private ImageView iView;
	
	private View rootView;
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_card_detail,
				container, false);

		iView = (ImageView) rootView.findViewById(R.id.card_detail);
		pb = (ProgressBar) rootView.findViewById(R.id.progressBar1);
		
		return rootView;
	}

	@Override
	public Context getContext() {
		return getActivity();
	}

	@Override
	public void showProgress() {
		pb.setVisibility(View.VISIBLE);
		iView.setVisibility(View.INVISIBLE);
	}

	@Override
	public void setImage(Bitmap bmp) {
		iView.setImageBitmap(bmp);
		iView.setVisibility(View.VISIBLE);
		pb.setVisibility(View.INVISIBLE);
		
	}
}
