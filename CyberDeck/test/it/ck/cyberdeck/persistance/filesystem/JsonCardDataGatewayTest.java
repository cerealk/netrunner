package it.ck.cyberdeck.persistance.filesystem;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import it.ck.cyberdeck.model.CardData;
import it.ck.cyberdeck.persistance.filesystem.FileSystemLibraryCardGateway;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

public class JsonCardDataGatewayTest {

	@Test
	public void givenADataFileICanGetTheCardData() {
		FileSystemLibraryCardGateway gateway = new FileSystemLibraryCardGateway();

		List<CardData> cards = gateway.loadRawData();

		assertThat(cards.size(), is(233));

		for (CardData card : cards) {
			assertThat(card.name, is(not(nullValue())));
			assertThat(card.name, is(not(StringUtils.EMPTY)));
			assertThat(card.side, is(not(nullValue())));
			assertThat(card.num, is(not(nullValue())));
			assertThat(card.identity, is(not(nullValue())));
			assertThat(card.type, is(not(nullValue())));
			assertThat(card.set, is(not(nullValue())));
		}
	}
}
