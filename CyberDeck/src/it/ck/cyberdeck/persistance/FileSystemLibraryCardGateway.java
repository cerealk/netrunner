package it.ck.cyberdeck.persistance;

import it.ck.cyberdeck.model.LibraryCardGateway;

import java.io.*;


public class FileSystemLibraryCardGateway extends JsonLibraryCardGateway implements LibraryCardGateway {

  @Override
  protected String readLibrarySource() {

    StringBuilder stringBuilder = new StringBuilder();
    BufferedReader reader = null;
    try {
    	
    	File jsData = new File("res/raw/carddata.js");
    	
      InputStream inputStream =new FileInputStream(jsData);
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

}
