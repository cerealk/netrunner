package it.ck.cyberdeck.presentation;

import it.ck.cyberdeck.R;
import it.ck.cyberdeck.model.Card;
import it.ck.cyberdeck.model.CardGroup;
import it.ck.cyberdeck.model.CardLibrary;
import it.ck.cyberdeck.model.DeckException;
import it.ck.cyberdeck.presentation.adapter.CardLibraryExpandableListAdapter;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.Toast;

public class AddCardActivity extends BaseDeckActivity {

	private CardLibraryExpandableListAdapter adapter;
	private Toast toast;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_expandable_card);
		
		ExpandableListView expListView = (ExpandableListView) findViewById(R.id.expandableListView1);
		CardLibrary cl = ((CyberDeckApp) getApplication()).getDeckService().loadCardLibrary();
		
		List<CardGroup> values = cl.getCardGroupsWithoutIdentities(getDeck().getIdentity());
		adapter = new CardLibraryExpandableListAdapter(this, values);
		expListView.setAdapter(adapter);

	    expListView.setOnChildClickListener(new OnChildClickListener() {
	
				@Override
	      public boolean onChildClick(ExpandableListView parent, View v,
	          int groupPosition, int childPosition, long id) {
		      
				  Card cardToBeAdded = (Card) getListAdapter().getChild(groupPosition, childPosition);
				  try{
				  	presenter.addCard(cardToBeAdded);
				  	showToast("Card " + cardToBeAdded.getName() + " added succesfully");
				  }catch (DeckException e){
				  	showToast(e.getMessage());
				  }
					return true;
	      }
	
	    });
	}

	private void showToast(String toastText) {
		if (toast!=null)
			toast.cancel();
		toast = Toast.makeText(this, toastText, Toast.LENGTH_SHORT);
		toast.show();
	}
	
	protected CardLibraryExpandableListAdapter getListAdapter() {
		return adapter;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.add_expandable_card, menu);
		return true;
	}
	
	@Override
  public void onBackPressed() {
    Intent intent = new Intent();
    intent.putExtra(BaseDeckActivity.DECK_ARG_ID, getDeck());
    setResult(RESULT_OK, intent);  
	  super.onBackPressed();
  }

}
