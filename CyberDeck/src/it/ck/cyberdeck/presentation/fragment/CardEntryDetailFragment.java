package it.ck.cyberdeck.presentation.fragment;

import android.graphics.Bitmap;
import it.ck.cyberdeck.presentation.BaseImageFragment;
import it.ck.cyberdeck.presentation.CardEntryView;

public class CardEntryDetailFragment extends BaseImageFragment implements CardEntryView {
	
	@Override
	public void setCardImage(Bitmap cardImage) {
		this.setImage(cardImage);
	}

	@Override
	public void setCardQuantity() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setCardQuantityMax() {
		// TODO Auto-generated method stub
		
	}

}
