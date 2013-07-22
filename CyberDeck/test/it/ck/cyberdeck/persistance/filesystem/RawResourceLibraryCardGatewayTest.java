package it.ck.cyberdeck.persistance.filesystem;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import it.ck.cyberdeck.CardListActivity;
import it.ck.cyberdeck.persistance.filesystem.AndroidLibraryCardGateway;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class RawResourceLibraryCardGatewayTest {

	@Test
	public void theRawResourceCanBeLoaded() {
		CardListActivity activity = Robolectric.buildActivity(CardListActivity.class).create().get();
		AndroidLibraryCardGateway libraryGateway = new AndroidLibraryCardGateway(activity);
		String source = libraryGateway.readLibrarySource();
		
		assertThat(source, is(notNullValue()));
		assertThat(source, is(not(equalTo(""))));
		System.out.println(source);
	}

}
