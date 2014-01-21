package it.ck.cyberdeck.model.command;

import static org.junit.Assert.*;

import org.junit.Test;

public class AddCardCommandTest {

	public class AddCardCommand implements Command {

		@Override
		public void execute() {
			
		}

	}

	@Test
	public void givenADeckICanAddACard() throws Exception {
		
		Command addCommand = new AddCardCommand();
		addCommand.execute();
	}
	
}
