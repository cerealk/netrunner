package it.ck.cyberdeck;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class ActivityTest {

	@Test
	public void testGenericInstanztiation() {
		
		CardListActivity activity = Robolectric.buildActivity(CardListActivity.class).create().get();
		int act = activity.getResources().getIdentifier("_01001", "drawable", "it.ck.cyberdeck");
		assertThat(Integer.valueOf(0x7f020000), equalTo(act));
	}

}
