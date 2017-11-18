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
	}

	private static LruCache<CacheKey, Bitmap> imageCache = new LruCache<>(
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
		bmp = BitmapFactory.decodeResource(context.getResources(), getFactionImageId(side, faction));
		imageCache.put(key, bmp);
		return bmp;
	}

	private static int getFactionImageId(Side side,
																			 Faction identity) {
		int resImg = R.drawable.ic_launcher;
		switch (identity) {
		case ANARCH:
			resImg = R.drawable.anarchs;
			break;
		case CRIMINAL:
			resImg = R.drawable.criminal;
			break;
		case SHAPER:
			resImg = R.drawable.shaper;
			break;
		case HAAS_BIOROID:
			resImg = R.drawable.hb;
			break;
		case JINTEKI:
			resImg = R.drawable.jinteki;
			break;
		case WEYLAND_CONSORTIUM:
			resImg = R.drawable.wayland;
			break;
		case NBN:
			resImg = R.drawable.nbn;
			break;
		case NEUTRAL:
			if (Side.CORP.equals(side)) {
				resImg = R.drawable.n_blue;
			} else {
				resImg = R.drawable.n_red;
			}

			break;
		default:
			break;
		}
		return resImg;
	}

}
