package it.ck.cyberdeck.model;

import java.io.Serializable;


public class DeckStatus implements Serializable{
	private static final long serialVersionUID = 1L;

	public static class StatusBuilder {
		
		private DeckStatus status;
		
		private StatusBuilder(){
		}
		
		public static StatusBuilder instance(){
			return new StatusBuilder();
		}
		
		public StatusBuilder invalid() {
			this.status = new DeckStatus(StatusCode.INVALID);
		  return this;
	  }

		public StatusBuilder withReason(Reason fewCards) {
			this.status.reason = fewCards;
	    return this;
    }
		
		public DeckStatus build() {
	    return this.status;
    }
  }



	public static final DeckStatus VALID = new DeckStatus(StatusCode.VALID);
	public static final DeckStatus INVALID = new DeckStatus(StatusCode.INVALID);
	private StatusCode statusCode;
	private Reason reason;

	public DeckStatus(StatusCode sc) {
		this.statusCode = sc;
	}

	public StatusCode status() {
	  return statusCode;
  }

	public Reason reason() {
		return this.reason;
  }


}
