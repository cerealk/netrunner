package it.ck.cyberdeck.persistence;

import android.content.Context;
import android.content.res.Resources;

import com.google.gson.stream.JsonReader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import it.ck.cyberdeck.R;
import it.ck.cyberdeck.model.LibraryCardGateway;
import it.ck.cyberdeck.persistence.filesystem.JsonLibraryCardGateway;

public class AndroidLibraryCardGateway extends JsonLibraryCardGateway
        implements LibraryCardGateway {

    private Context context;


    public AndroidLibraryCardGateway(Context context) {
        super();
        this.context = context;
    }

    @Override
    public JsonReader readLibrarySource() {
        return getReader(R.raw.carddata);
    }

    private JsonReader getReader(int resourceId) {
        InputStream inputStream = getResources().openRawResource(
                resourceId);
        return new JsonReader(new InputStreamReader(inputStream));
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
        BufferedReader reader;
        reader = getReader(getDeckFile(sourceName));
        return new JsonReader(reader);

    }

    @Override
    protected String getDeckUri(String name) {
        return name;
    }

}
