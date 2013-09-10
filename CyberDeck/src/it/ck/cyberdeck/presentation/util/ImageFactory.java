package it.ck.cyberdeck.presentation.util;

import it.ck.cyberdeck.R;
import it.ck.cyberdeck.model.Faction;
import it.ck.cyberdeck.model.Side;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.util.LruCache;

public final class ImageFactory {
	private ImageFactory() {
	};

	private static final String PACKAGE_NAME = "it.ck.cyberdeck";

	private static LruCache<CacheKey, Bitmap> imageCache = new LruCache<ImageFactory.CacheKey, Bitmap>(
			10);

	private static class CacheKey {

		private Side side;
		private Faction faction;

		public CacheKey(Side side, Faction faction) {
			this.side = side;
			this.faction = faction;
		}

		@Override
		public boolean equals(Object o) {
			if(this == o)
				return true;
			if (o instanceof CacheKey){
				CacheKey other = (CacheKey) o;
				return this.side.equals(other.side)&&this.faction.equals(other.faction);
			}
			return super.equals(o);
		}

		@Override
		public int hashCode() {
			return this.side.hashCode() + this.faction.hashCode();
		}
		
		

	}

	public static Bitmap getFactionImage(Context context, Side side,
			Faction faction) {

		CacheKey key = new CacheKey(side, faction);
		Bitmap bmp = imageCache.get(key);
		if (bmp != null)
			return bmp;
		int resId = getFactionImageId(context, side, faction);
		bmp = BitmapFactory.decodeResource(context.getResources(), resId);
		imageCache.put(key, bmp);
		return bmp;
	}

	private static int getFactionImageId(Context context, Side side,
			Faction identity) {
		int resImg = R.drawable.ic_launcher;
		switch (identity) {
		case ANARCH:
			resImg = context.getResources().getIdentifier("anarchs",
					"drawable", PACKAGE_NAME);
			break;
		case CRIMINAL:
			resImg = context.getResources().getIdentifier("criminal",
					"drawable", PACKAGE_NAME);
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
			resImg = context.getResources().getIdentifier("jinteki",
					"drawable", PACKAGE_NAME);
			break;
		case WEYLAND_CONSORTIUM:
			resImg = context.getResources().getIdentifier("wayland",
					"drawable", PACKAGE_NAME);
			break;
		case NBN:
			resImg = context.getResources().getIdentifier("nbn", "drawable",
					PACKAGE_NAME);
			break;
		case NEUTRAL:
			if (Side.CORP.equals(side)) {
				resImg = context.getResources().getIdentifier("n_blue",
						"drawable", PACKAGE_NAME);
			} else {
				resImg = context.getResources().getIdentifier("n_red",
						"drawable", PACKAGE_NAME);
			}
			;
			break;
		default:
			break;
		}
		return resImg;
	}

}
