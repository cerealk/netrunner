package it.ck.cyberdeck.model;

import java.io.Serializable;
import static it.ck.cyberdeck.model.StatusCode.*;

public class DeckStatus implements Serializable {
	private static final long serialVersionUID = 1L;

	private StatusCode statusCode;
	private Reason reason;
	private Integer cardCount;

	private Integer minSize;

	public DeckStatus(StatusCode sc, Integer minSize) {
		this.statusCode = sc;
		this.minSize = minSize;
	}

	public StatusCode status() {
		return statusCode;
	}

	public Reason reason() {
		return this.reason;
	}

	public Integer cardCount() {
		return cardCount;
	}
	
	public void updateCardCount(Integer cardCount){
		this.cardCount = cardCount;
	}

	public void invalid(Reason reason) {
		this.statusCode=INVALID;
		this.reason=reason;
	}

	public void valid() {
		this.statusCode = VALID;
		this.reason = null;
		
	}

	public Integer minDeckSize() {
		return minSize;
	}
}
