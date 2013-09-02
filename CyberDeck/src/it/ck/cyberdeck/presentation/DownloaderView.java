package it.ck.cyberdeck.presentation;

import android.content.Context;
import android.graphics.Bitmap;

public interface DownloaderView {
	
	Context getContext();
	void showProgress();
	void setImage(Bitmap bmp);

}
