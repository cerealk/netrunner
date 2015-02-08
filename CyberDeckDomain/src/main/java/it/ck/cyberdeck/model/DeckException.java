package it.ck.cyberdeck.model;

public class DeckException extends RuntimeException {

	public DeckException() {
	}

	public DeckException(String message) {
		super(message);
	}

	private static final long serialVersionUID = 1L;
}
