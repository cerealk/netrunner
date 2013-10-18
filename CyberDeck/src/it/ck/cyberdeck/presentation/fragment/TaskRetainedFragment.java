package it.ck.cyberdeck.presentation.fragment;

import it.ck.cyberdeck.model.CardLibrary;
import it.ck.cyberdeck.presentation.service.AndroidFSImageService;
import it.ck.cyberdeck.presentation.service.ImageService;
import it.ck.cyberdeck.presentation.service.MassDownloadTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.TextView;

public class TaskRetainedFragment extends Fragment {
	
	private CardLibrary cardLibrary;
	private TextView advancement;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
		ImageService is = new AndroidFSImageService(getActivity());
		MassDownloadTask task = new MassDownloadTask(is, cardLibrary, advancement);
		task.execute();
	}
	
}