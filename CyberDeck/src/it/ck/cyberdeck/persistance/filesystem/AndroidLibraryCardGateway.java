package it.ck.cyberdeck.persistance.filesystem;

import it.ck.cyberdeck.R;
import it.ck.cyberdeck.model.LibraryCardGateway;

import java.io.*;

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
	protected String readLibrarySource() {

		BufferedReader reader = null;
		reader = getReader(R.raw.carddata);
		try {
	    return readSource(reader);
    } catch (IOException e) {
    	e.printStackTrace();
    }
		return "";
	}

	private BufferedReader getReader(int resourceId) {
		BufferedReader reader;
		InputStream inputStream = getResources().openRawResource(
				resourceId);
		reader = new BufferedReader(new InputStreamReader(inputStream));
		return reader;
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
  protected String readSource(String sourceName) {
		BufferedReader reader = null;
	  try {
			reader = getReader(getDeckFile(sourceName));
			return readSource(reader);
    } catch (FileNotFoundException e) {
    	e.printStackTrace();
    } catch (IOException e) {
    	e.printStackTrace();
    }finally {
    	if(reader!=null){
    		try {
	        reader.close();
        } catch (IOException e) {}
    	}
    }
	  return "";
  }

	@Override
  protected String getDeckUri(String name) {
	  return name;
  }

}
