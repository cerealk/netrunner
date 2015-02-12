package it.ck.cyberdeck.persistance.filesystem;

import it.ck.cyberdeck.model.LibraryCardGateway;

import java.io.*;

import com.google.gson.stream.JsonReader;

public class FileSystemLibraryCardGateway extends JsonLibraryCardGateway
    implements LibraryCardGateway {

	private static final String DECK_BASE_PATH = "CyberDeckDomain/src/test/resources/decks/";
	private String filePath;

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
			e.printStackTrace();
		}

	}

	@Override
	protected JsonReader readSource(String name) throws FileNotFoundException {

		
			File jsData = new File(name);

			InputStream inputStream = new FileInputStream(jsData);
			return new JsonReader(new InputStreamReader(inputStream));

		
	}

	@Override
  protected JsonReader readLibrarySource() throws FileNotFoundException {
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
