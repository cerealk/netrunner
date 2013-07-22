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

		StringBuilder stringBuilder = new StringBuilder();
		BufferedReader reader = null;
		try {
			reader = getReader(R.raw.carddata);
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
	  File destDir = getDestDir();
	  File deckFile = new File(destDir, destinationName);
	  try {
	    FileOutputStream fos = new FileOutputStream(deckFile);
	    fos.write(deckDataString.getBytes());
	    fos.close();
    } catch (FileNotFoundException e) {
    } catch (IOException e) {
    }
	  
  }

	private File getDestDir() {
	  return context.getDir("decks", Context.MODE_PRIVATE);
  }

	@Override
  protected String readSource(String name) {
	  File deckSource = new File(getDestDir(), name);
	  StringBuilder stringBuilder = new StringBuilder();
	  BufferedReader reader = null;
	  try {
			reader = getReader(deckSource);
	    String line = null;
			String ls = getLineSeparator();
	    while ((line = reader.readLine()) != null) {
				stringBuilder.append(line);
				stringBuilder.append(ls);
			}
	    reader.close();
    } catch (FileNotFoundException e) {
    } catch (IOException e) {
    }finally {
    	if (reader != null)
				try {
					reader.close();
				} catch (IOException e) {
				}
    }
	  
	  return stringBuilder.toString();
  }

	private BufferedReader getReader(File deckSource)
      throws FileNotFoundException {
	  FileInputStream fis = new FileInputStream(deckSource);
	  BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
	  return reader;
  }

}
