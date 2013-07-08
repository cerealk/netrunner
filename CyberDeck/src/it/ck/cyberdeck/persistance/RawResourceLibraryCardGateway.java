package it.ck.cyberdeck.persistance;

import it.ck.cyberdeck.R;
import it.ck.cyberdeck.model.LibraryCardGateway;

import java.io.*;

import android.content.res.Resources;

public class RawResourceLibraryCardGateway extends JsonLibraryCardGateway
		implements LibraryCardGateway {

	private Resources resources;

	
	public RawResourceLibraryCardGateway(Resources resources) {
		super();
		this.resources = resources;
	}

	@Override
	protected String readLibrarySource() {

		StringBuilder stringBuilder = new StringBuilder();
		BufferedReader reader = null;
		try {
			reader = getReader();
			String line = null;
			String ls = getLineSeparator();

			while ((line = reader.readLine()) != null) {
				stringBuilder.append(line);
				stringBuilder.append(ls);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null)
				try {
					reader.close();
				} catch (IOException e) {
				}
		}
		return stringBuilder.toString();
	}

	private BufferedReader getReader() {
		BufferedReader reader;
		InputStream inputStream = getResources().openRawResource(
				R.raw.carddata);
		reader = new BufferedReader(new InputStreamReader(inputStream));
		return reader;
	}

	private String getLineSeparator() {
		String ls = System.getProperty("line.separator");
		return ls;
	}

	private Resources getResources() {
		return resources;
	}

}
