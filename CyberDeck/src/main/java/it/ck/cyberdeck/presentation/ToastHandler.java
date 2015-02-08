package it.ck.cyberdeck.presentation;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.Toast;

public class ToastHandler {
	private Toast toast;
	private Context context;

	public ToastHandler(Context context) {
		this.context = context;
	}

	public void toast(String toastText) {
		cancelCurrentToast();
		makeToast(toastText);
		show();
	}

	private void show() {
		toast.show();
	}

	@SuppressLint("ShowToast")
	private void makeToast(String toastText) {
		toast = Toast.makeText(context, toastText, Toast.LENGTH_SHORT);
	}

	private void cancelCurrentToast() {
		if (toast != null)
			toast.cancel();
	}
}