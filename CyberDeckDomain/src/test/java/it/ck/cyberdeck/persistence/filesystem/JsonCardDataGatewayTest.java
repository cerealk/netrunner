package it.ck.cyberdeck.persistence.filesystem;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

import it.ck.cyberdeck.fixtures.Fixtures;
import it.ck.cyberdeck.model.CardData;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

public class JsonCardDataGatewayTest {
	
	private FileSystemLibraryCardGateway gw = new FileSystemLibraryCardGateway(Fixtures.RAW_PATH);

	@Test
	public void givenADataFileICanGetTheCardData() {

		List<CardData> cards = gw.loadRawData();

		assertThat(cards.size(), is(523));

		for (CardData card : cards) {
			assertThat(card.name, is(not(nullValue())));
			assertThat(card.name, is(not(StringUtils.EMPTY)));
			assertThat(card.side, is(not(nullValue())));
			assertThat(card.num, is(not(nullValue())));
			assertThat(card.faction, is(not(nullValue())));
			assertThat(card.type, is(not(nullValue())));
			assertThat(card.set, is(not(nullValue())));
		}
	}
	
	@Test
	public void iCanGetTheDeckList(){
		List<String> deckNames = gw.deckNames();
		assertThat(deckNames.size()>0, is(true));
		
	}
}
