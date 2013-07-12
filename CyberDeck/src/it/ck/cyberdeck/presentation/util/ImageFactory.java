package it.ck.cyberdeck.presentation.util;

import it.ck.cyberdeck.R;
import it.ck.cyberdeck.model.Faction;
import android.content.Context;

public final class ImageFactory {
	private ImageFactory() {
	};

	private static final String PACKAGE_NAME = "it.ck.cyberdeck";

	public static int gefFactionImageId(Context context, Faction identity) {
		int resImg = R.drawable.ic_launcher;
		switch (identity) {
		case ANARCH:
			resImg = context.getResources().getIdentifier("anarchs", "drawable",
			    PACKAGE_NAME);
			break;
		case CRIMINAL:
			resImg = context.getResources().getIdentifier("criminal", "drawable",
			    PACKAGE_NAME);
			break;
		case SHAPER:
			resImg = context.getResources().getIdentifier("shaper", "drawable",
			    PACKAGE_NAME);
			break;
		case HAAS_BIOROID:
			resImg = context.getResources().getIdentifier("hb", "drawable",
			    PACKAGE_NAME);
			break;
		case JINTEKI:
			resImg = context.getResources().getIdentifier("jinteki", "drawable",
			    PACKAGE_NAME);
			break;
		case WEYLAND_CONSORTIUM:
			resImg = context.getResources().getIdentifier("wayland", "drawable",
			    PACKAGE_NAME);
			break;
		case NBN:
			resImg = context.getResources().getIdentifier("nbn", "drawable",
			    PACKAGE_NAME);
			break;
		default:
			break;
		}
		return resImg;
	}
}
