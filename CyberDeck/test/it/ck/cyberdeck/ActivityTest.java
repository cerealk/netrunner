package it.ck.cyberdeck;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class ActivityTest {

	@Test
	@Ignore
	public void testGenericInstanztiation() {
		CardListActivity activity = Robolectric.buildActivity(CardListActivity.class).create().get();
		int act = activity.getResources().getIdentifier("_01001", "drawable", "it.ck.cyberdeck");
		assertThat(Integer.valueOf(0x7f020000), equalTo(act));
	}

}
