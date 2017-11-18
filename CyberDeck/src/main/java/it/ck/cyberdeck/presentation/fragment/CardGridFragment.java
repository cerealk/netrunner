package it.ck.cyberdeck.presentation.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import it.ck.cyberdeck.R;
import it.ck.cyberdeck.model.CardLibrary;
import it.ck.cyberdeck.model.LibraryCardGateway;
import it.ck.cyberdeck.persistence.AndroidLibraryCardGateway;
import it.ck.cyberdeck.presentation.activity.CardGalleryActivity;
import it.ck.cyberdeck.presentation.adapter.CardGridAdapter;

public class CardGridFragment extends Fragment {

	private CardGridAdapter adapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LibraryCardGateway gateway = new AndroidLibraryCardGateway(this.getActivity());
		CardLibrary cardLibrary = gateway.loadCardLibrary();
		adapter = new CardGridAdapter(getActivity(), cardLibrary.getCardList());
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View rootView = inflater.inflate(R.layout.fragment_card_grid, container);
		GridView gridview = rootView.findViewById(R.id.gridview);
		gridview.setAdapter(adapter);
		gridview.setOnItemClickListener((parent, v, position, id) -> {
      Intent detailIntent = new Intent(this.getActivity(), CardGalleryActivity.class);
      detailIntent.putExtra(CardDetailFragment.ARG_ITEM_ID, adapter.getItem(position));
      startActivity(detailIntent);
      });
		return rootView;
	}
	
	
	
	
}
