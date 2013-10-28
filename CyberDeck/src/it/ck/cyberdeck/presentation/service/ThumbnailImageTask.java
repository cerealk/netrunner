package it.ck.cyberdeck.presentation.service;

import it.ck.cyberdeck.model.CardKey;
import it.ck.cyberdeck.presentation.DownloaderView;

public class ThumbnailImageTask extends ImageTask{

	public ThumbnailImageTask(DownloaderView dlv, CardKey key,
			ImageService imageService) {
		super(dlv, key, imageService);
	}

	public ThumbnailImageTask(DownloaderView imageView, CardKey key,
			ImageService imageService, int tmbPixWidth, int tmbPixHeight) {
		super(imageView, key, imageService, tmbPixWidth, tmbPixHeight);
	}

	@Override
	protected Void doInBackground(Void... arg0) {
		bmp = imageService.getCardThumbnail(key, reqWidth, reqHeight);
		return null;
	}
}
