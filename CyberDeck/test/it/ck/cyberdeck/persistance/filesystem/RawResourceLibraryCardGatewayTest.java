package it.ck.cyberdeck.persistance.filesystem;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import it.ck.cyberdeck.CardListActivity;
import it.ck.cyberdeck.model.*;
import it.ck.cyberdeck.model.reputation.StandardReputationRule;

import java.io.*;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import com.google.gson.stream.JsonReader;

import android.content.Context;

@RunWith(RobolectricTestRunner.class)
public class RawResourceLibraryCardGatewayTest {

	private AndroidLibraryCardGateway gw = new AndroidLibraryCardGateway(
	    Robolectric.buildActivity(CardListActivity.class).create().get());
	private CardListActivity activity = Robolectric.buildActivity(CardListActivity.class).create().get();;

	@Test
	public void theRawResourceCanBeLoaded() throws Exception {
		JsonReader source = gw.readLibrarySource();

		assertThat(source, is(notNullValue()));
		assertThat(Boolean.valueOf(source.hasNext()), is(true));
//		System.out.println(source);
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
