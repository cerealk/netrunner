package it.ck.cyberdeck.model.command.implementation;

import it.ck.cyberdeck.model.DeckException;
import it.ck.cyberdeck.model.Notifier;
import it.ck.cyberdeck.model.command.Command;

public abstract class AbstractNotifierCommand implements Command{

	private Notifier notifier;

	public AbstractNotifierCommand(Notifier notifier) {
		super();
		this.notifier = notifier;
	}

	@Override
	public void execute() {
		try {
			doExecute();
			notify(getSuccessMessage());
		} catch (DeckException e) {
			notify(e.getMessage());
		}
	}

	private void notify(String successMessage) {
		this.notifier.notify(successMessage);
	}

	protected abstract void doExecute();

	protected abstract String getSuccessMessage();

}