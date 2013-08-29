package it.ck.cyberdeck.presentation.service;

import it.ck.cyberdeck.model.CardKey;
import android.graphics.Bitmap;

public interface ImageService {

	Bitmap getCardImage(CardKey key);
}
