package it.ck.cyberdeck.presentation;

import it.ck.cyberdeck.model.CardEntry;
import it.ck.cyberdeck.model.Deck;
import it.ck.cyberdeck.model.DeckStatus;
import it.ck.cyberdeck.presentation.presenter.DeckPresenter;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;

public class BaseDeckActivity extends BaseCyberDeckActivity implements DeckView {

	public static final String DECK_ARG_ID = "deck_arg_id";
	protected static final int REQUEST_CODE = 42;
	
	protected DeckPresenter presenter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Deck deck = initDeck(savedInstanceState);
		presenter = new DeckPresenter(deck, this, getDeckService());
	}

	protected Deck getDeck(){
		return presenter.getDeck();
	}
	
	private Deck initDeck(Bundle savedInstanceState) {
		Deck deck = null;
		if(savedInstanceState == null){
			deck  = (Deck) getIntent().getExtras().getSerializable(DECK_ARG_ID);
		}else {
			deck = (Deck) savedInstanceState.getSerializable(DECK_ARG_ID);
		}
		return deck;
	}

	@Override
    protected void onSaveInstanceState(Bundle outState) {
		outState.putSerializable(DECK_ARG_ID, presenter.getDeck());
		super.onSaveInstanceState(outState);
    }
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
			if(requestCode == REQUEST_CODE){
				if(resultCode == RESULT_OK){
					Deck deck = (Deck) data.getSerializableExtra(DECK_ARG_ID);
					presenter = new DeckPresenter(deck, this, getDeckService());
				}
			}
			
			presenter.publish();
	}

	@Override
	public void publishIdentityName(String identityName) {
	}

	@Override
	public void publishDeckName(String deckName) {
	}

	@Override
	public void publishEntryList(List<CardEntry> cards) {
	}

	@Override
	public void publishDeckStatus(DeckStatus checkStatus) {
	}
	
}
