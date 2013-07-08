package it.ck.cyberdeck;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class ActivityTest {

	@Test
	public void test() {
		int act = new CardListActivity().getResources().getIdentifier("_01001", "drawable", "it.ck.cyberdeck");
		assertThat(Integer.valueOf(0x7f020000), equalTo(act));
	}

}
