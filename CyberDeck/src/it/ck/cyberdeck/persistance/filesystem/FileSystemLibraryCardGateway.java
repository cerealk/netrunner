package it.ck.cyberdeck.persistance.filesystem;

import it.ck.cyberdeck.model.LibraryCardGateway;

import java.io.*;

public class FileSystemLibraryCardGateway extends JsonLibraryCardGateway
    implements LibraryCardGateway {

	private String filePath;

	public FileSystemLibraryCardGateway() {
		this.filePath = "res/raw/carddata.js";
	};

	public FileSystemLibraryCardGateway(String path) {
		this.filePath = path;
	}

	@Override
	protected String readLibrarySource() {

		StringBuilder stringBuilder = new StringBuilder();
		BufferedReader reader = null;
		try {
			File jsData = new File(filePath);

			InputStream inputStream = new FileInputStream(jsData);
			reader = new BufferedReader(new InputStreamReader(inputStream));
			String line = null;
			String ls = System.getProperty("line.separator");

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

	@Override
	protected void persist(String destinationName, String deckDataString) {
		try {

			FileOutputStream fos = new FileOutputStream(new File(destinationName));
			OutputStreamWriter osw = new OutputStreamWriter(fos);
			osw.append(deckDataString);
			osw.flush();
			osw.close();
		} catch (IOException e) {
		}

	}

}
