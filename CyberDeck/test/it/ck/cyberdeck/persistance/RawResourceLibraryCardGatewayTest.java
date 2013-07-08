package it.ck.cyberdeck.persistance;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import it.ck.cyberdeck.CardListActivity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class RawResourceLibraryCardGatewayTest {

	@Test
	public void theRawResourceCanBeLoaded() {
		CardListActivity activity = Robolectric.buildActivity(CardListActivity.class).create().get();
		RawResourceLibraryCardGateway libraryGateway = new RawResourceLibraryCardGateway(activity.getResources());
		String source = libraryGateway.readLibrarySource();
		
		assertThat(source, is(notNullValue()));
		assertThat(source, is(not(equalTo(""))));
		System.out.println(source);
	}

}
