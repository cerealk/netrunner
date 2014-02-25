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
			this.notifier.notify(getSuccessMessage());
		} catch (DeckException e) {
			this.notifier.notify(e.getMessage());
		}
	}

	protected abstract void doExecute();

	protected abstract String getSuccessMessage();

}