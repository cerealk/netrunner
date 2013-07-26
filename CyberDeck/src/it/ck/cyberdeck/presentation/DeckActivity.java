package it.ck.cyberdeck.presentation;

import it.ck.cyberdeck.R;
import it.ck.cyberdeck.model.CardEntry;
import it.ck.cyberdeck.model.Deck;
import it.ck.cyberdeck.presentation.adapter.CardEntryListViewAdapter;
import it.ck.cyberdeck.presentation.adapter.DeckAdapter;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.*;

public class DeckActivity extends Activity implements DeckPublisher{

	protected static final int REQUEST_CODE = 42;
	private TextView deckName;
	private TextView identityName;
	private ListView cardList;
	private CardEntryListViewAdapter listViewAdapter;
	private DeckAdapter adapter;
	private Deck deck;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_deck);
		

		deckName = (TextView) findViewById(R.id.deck_name);
		identityName = (TextView) findViewById(R.id.deck_identity);
		if (savedInstanceState != null){
			deck = (Deck) savedInstanceState.getSerializable("deck");
		}	else {
			deck = (Deck) getIntent().getSerializableExtra("deck");
		}
		
		listViewAdapter = new CardEntryListViewAdapter(this.getApplicationContext(), deck.cards()); 
		
		cardList = (ListView) findViewById(R.id.deck_cards);
		
		cardList.setAdapter(listViewAdapter);
		
		registerForContextMenu(cardList);
		
		adapter = new DeckAdapter(deck);
		adapter.adapt(this);
		
		Button addButton = (Button) findViewById(R.id.add_card_button);
		
		addButton.setOnClickListener(new View.OnClickListener(){
			@Override
      public void onClick(View v) {
				
				Intent intent = new Intent(DeckActivity.this, AddExpandableCardActivity.class);
				intent.putExtra("deck", deck);
				startActivityForResult(intent, REQUEST_CODE);
      }
			
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.deck, menu);
		return true;
	}

	@Override
  protected void onSaveInstanceState(Bundle outState) {
		outState.putSerializable("deck", deck);
		super.onSaveInstanceState(outState);
  }
	
	@Override
  public void onCreateContextMenu(ContextMenu menu, View v,
      ContextMenuInfo menuInfo) {
	  super.onCreateContextMenu(menu, v, menuInfo);
	  getMenuInflater().inflate(R.menu.deck_popoup_menu, menu);
	}
	
	@Override
  public boolean onContextItemSelected(MenuItem item) {
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
		switch (item.getItemId()) {
		case R.id.removeCard:
			removeCard(info.position);
			return true;
		case R.id.reoveAll:
			removeAll(info.position);
			return true;
		default:
			break;
		}
	  return super.onContextItemSelected(item);
  }

	private void removeCard(int position) {
	  CardEntry cardEntry = getEntry(position);
	  deck.remove(cardEntry.getCard());
	  saveDeck();
	  publishCardList(deck.cards());
  }

	private void removeAll(int position) {
	  CardEntry cardEntry = getEntry(position);
	  deck.removeAll(cardEntry.getCard());
	  saveDeck();
	  publishCardList(deck.cards());
  }

	private CardEntry getEntry(int position) {
	  return deck.cards().get(position);
  }

	private void saveDeck() {
	  CyberDeckApp application = (CyberDeckApp) getApplication();
	  application.getDeckService().saveDeck(deck);
  }
	
	@Override
	public void publishIdentityName(String identityName) {
		this.identityName.setText(identityName);
		
	}

	@Override
	public void publishDeckName(String deckName) {
		this.deckName.setText(deckName);
	}

	@Override
  public void publishCardList(List<CardEntry> cards) {
	  listViewAdapter.clear();
	  listViewAdapter.addAll(cards);
//	  listViewAdapter.notifyDataSetChanged();
  }

	@Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(requestCode == REQUEST_CODE){
			if(resultCode == RESULT_OK){
				this.deck = (Deck) data.getSerializableExtra("deck");
			}
		}
		
		publishCardList(this.deck.cards());
  }

	
	
}
