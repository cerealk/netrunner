package it.ck.cyberdeck.model.command.implementation;

import it.ck.cyberdeck.model.DeckException;
import it.ck.cyberdeck.model.Notifier;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

public class AbstractNotifierCommandTest {

	@Rule
	public JUnitRuleMockery context = new JUnitRuleMockery();
	private Notifier notifier = context.mock(Notifier.class);

	@Test
	public void aCommandNotifiesItsUser() {
		final String message = "nothing done: ok!";
		AbstractNotifierCommand command = new AbstractNotifierCommand(
				notifier) {

			@Override
			protected void doExecute() {
				// do nothing
			}

			@Override
			protected String getSuccessMessage() {
				return message;
			}
		};

		expectNotification(message);
		
		command.execute();
	}


	@Test
	public void aCommandnotifiesItsUser_alsoIfItFails() throws Exception {
		final String failMessage = "exception thrown!";
		AbstractNotifierCommand failingCommand = new AbstractNotifierCommand(notifier) {
			
			@Override
			protected String getSuccessMessage() {
				return null;
			}
			
			@Override
			protected void doExecute() {
				throw new DeckException(failMessage);
				
			}
		};
		
		expectNotification(failMessage);
		
		failingCommand.execute();
	}


	private void expectNotification(final String message) {
		context.checking(new Expectations() {
			{
				oneOf(notifier).notify(message);
			}
		});
	}
}
