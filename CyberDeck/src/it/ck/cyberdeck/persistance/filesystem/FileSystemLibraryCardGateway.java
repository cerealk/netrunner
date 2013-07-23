package it.ck.cyberdeck.persistance.filesystem;

import it.ck.cyberdeck.model.LibraryCardGateway;

import java.io.*;

public class FileSystemLibraryCardGateway extends JsonLibraryCardGateway
    implements LibraryCardGateway {

	private static final String DECK_BASE_PATH = "test/resources/decks/";
	private String filePath;

	public FileSystemLibraryCardGateway() {
		this.filePath = "res/raw/carddata.js";
	};

	public FileSystemLibraryCardGateway(String path) {
		this.filePath = path;
	}

	@Override
	protected void persist(String destinationName, String deckDataString) {
		try {
			FileOutputStream fos = new FileOutputStream(new File(DECK_BASE_PATH+ destinationName + ".js"));
			fos.write(deckDataString.getBytes());
			fos.flush();
			fos.close();
		} catch (IOException e) {
		}

	}

	@Override
	protected String readSource(String name) {

		StringBuilder stringBuilder = new StringBuilder();
		BufferedReader reader = null;
		try {
			File jsData = new File(name);

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
  protected String readLibrarySource() {
	  return readSource(filePath);
  }

	protected String getDeckUri(String name) {
	  return DECK_BASE_PATH + name + ".js";
  }

	@Override
  protected File getDeckDir() {
			return new File(DECK_BASE_PATH);
  }
	
	
	
}
