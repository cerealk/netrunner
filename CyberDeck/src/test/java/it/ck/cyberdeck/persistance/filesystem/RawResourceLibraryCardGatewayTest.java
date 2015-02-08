package it.ck.cyberdeck.persistance.filesystem;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import it.ck.cyberdeck.model.CardKey;
import it.ck.cyberdeck.model.CardLibrary;
import it.ck.cyberdeck.model.CardSet;
import it.ck.cyberdeck.model.Deck;
import it.ck.cyberdeck.model.Identity;
import it.ck.cyberdeck.model.reputation.StandardReputationRule;
import it.ck.cyberdeck.presentation.activity.WelcomeActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import android.content.Context;

import com.google.gson.stream.JsonReader;

@RunWith(RobolectricTestRunner.class)
public class RawResourceLibraryCardGatewayTest {

	private WelcomeActivity activity = Robolectric.buildActivity(WelcomeActivity.class).create().get();;
	private AndroidLibraryCardGateway gw = new AndroidLibraryCardGateway(activity);

	@Test
	public void theRawResourceCanBeLoaded() throws Exception {
		JsonReader source = gw.readLibrarySource();

		assertThat(source, is(notNullValue()));
		assertThat(Boolean.valueOf(source.hasNext()), is(true));
	}

	@Test
	public void givenADeckICanPersistIt() {
		CardLibrary cl = gw.loadCardLibrary();

		Identity identity = new Identity(cl.getCard(new CardKey(CardSet.CORE, 1)), new StandardReputationRule());
		Deck deck = new Deck(identity, "testDeck");

		deck.add(cl.getCard(new CardKey(CardSet.CORE, 2)));
		deck.add(cl.getCard(new CardKey(CardSet.CORE, 2)));
		deck.add(cl.getCard(new CardKey(CardSet.CORE, 3)));

		gw.saveDeck(deck);
	}

	@Test
	public void givenADeckNameICanLoadIt() {

		copyDeckFile("testDeck");
		String name = "testDeck";
		Deck deck = gw.loadDeck(name);

		assertThat(deck, is(not(nullValue())));
		assertThat(deck.name(), is("testDeck"));
		assertThat(deck.size(), is(3));
		assertThat(deck.getIdentity().key().getCardCode(), is("01001"));

	}
	
	@Test
	public void iCanGetTheDeckList(){
		copyDeckFile("testDeck");
		List<String> deckNames = gw.deckNames();
		assertThat(deckNames.size(), is(1));
		
	}

	@Test
	public void givenADeckICanDeleteIt(){
		String deckToBeDeleted = "deckToBeDeleted";
		copyDeckFile(deckToBeDeleted);
		gw.deleteDeck(deckToBeDeleted);
		
		File deletedDeck = new File(getDeckDir(), deckToBeDeleted);
		
		assertThat(deletedDeck.exists(), is(false));
		
	}
	
	private void copyDeckFile(String targetName) {

		InputStream inStream = null;
		OutputStream outStream = null;

		try {

			File afile = new File("test/resources/decks/testDeck.js");
			
			File dir = getDeckDir();
			File bfile = new File(dir, targetName);

			inStream = new FileInputStream(afile);
			outStream = new FileOutputStream(bfile);

			byte[] buffer = new byte[1024];

			int length;
			// copy the file content in bytes
			while ((length = inStream.read(buffer)) > 0) {

				outStream.write(buffer, 0, length);

			}

			inStream.close();
			outStream.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private File getDeckDir() {
		return activity.getApplicationContext().getDir("decks",
		    Context.MODE_PRIVATE);
	}

}
