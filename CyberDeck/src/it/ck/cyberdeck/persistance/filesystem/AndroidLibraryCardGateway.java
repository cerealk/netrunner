package it.ck.cyberdeck.persistance.filesystem;

import it.ck.cyberdeck.R;
import it.ck.cyberdeck.model.LibraryCardGateway;

import java.io.*;

import com.google.gson.stream.JsonReader;

import android.content.Context;
import android.content.res.Resources;

public class AndroidLibraryCardGateway extends JsonLibraryCardGateway
		implements LibraryCardGateway {

	private Context context;

	
	public AndroidLibraryCardGateway(Context context) {
		super();
		this.context = context;
	}

	@Override
	protected JsonReader readLibrarySource() {
		return getReader(R.raw.carddata);
	}

	private JsonReader getReader(int resourceId) {
		InputStream inputStream = getResources().openRawResource(
				resourceId);
		return new JsonReader(new InputStreamReader(inputStream));
	}

	private String getLineSeparator() {
		String ls = System.getProperty("line.separator");
		return ls;
	}

	private Resources getResources() {
		return context.getResources();
	}

	@Override
  protected void persist(String destinationName, String deckDataString) {
	  File destDir = getDeckDir();
	  File deckFile = new File(destDir, destinationName);
	  try {
	    FileOutputStream fos = new FileOutputStream(deckFile);
	    fos.write(deckDataString.getBytes());
	    fos.close();
    } catch (FileNotFoundException e) {
    	e.printStackTrace();
    } catch (IOException e) {
    	e.printStackTrace();
    }
	  
  }

	@Override
	protected File getDeckDir() {
	  return context.getDir("decks", Context.MODE_PRIVATE);
  }

	
	
  protected String readSource(BufferedReader reader) throws IOException {
	  StringBuilder stringBuilder = new StringBuilder();
	    String line = null;
			String ls = getLineSeparator();
	    while ((line = reader.readLine()) != null) {
				stringBuilder.append(line);
				stringBuilder.append(ls);
			}
	    reader.close();
	  
	  return stringBuilder.toString();
  }

	private File getDeckFile(String name) {
	  File deckSource = new File(getDeckDir(), name);
	  return deckSource;
  }

	private BufferedReader getReader(File deckSource)
      throws FileNotFoundException {
	  FileInputStream fis = new FileInputStream(deckSource);
	  BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
	  return reader;
  }

	@Override
  protected JsonReader readSource(String sourceName) throws FileNotFoundException {
		BufferedReader reader = null;
			reader = getReader(getDeckFile(sourceName));
			return new JsonReader(reader);
  
  }

	@Override
  protected String getDeckUri(String name) {
	  return name;
  }

}
