package it.ck.cyberdeck.model;

import java.io.Serializable;

import org.apache.commons.lang3.Range;

import static it.ck.cyberdeck.model.StatusCode.*;

public class DeckStatus implements Serializable {
	private static final long serialVersionUID = 1L;

	private StatusCode statusCode;
	private Reason reason;
	private Integer cardCount;

	private Integer minSize;

	private Integer agendaPoints;

	private Range<Integer> agendaRange;

	private Integer reputation;

	private Integer reputationCap;

	public DeckStatus(StatusCode sc, Integer minSize, Integer reputationCap) {
		this.statusCode = sc;
		this.minSize = minSize;
		this.reputationCap = reputationCap;
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
	
	protected void updateCardCount(Integer cardCount){
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

	public Integer getAgendaPoints() {
		return agendaPoints;
	}

	protected void updateAgendaPoints(Integer ap) {
		this.agendaPoints = ap;
	}

	public Range<Integer> getAgendaRange() {
		return agendaRange;
	}

	protected void setAgendaRange(Range<Integer> pointRange) {
		agendaRange = pointRange;
	}

	protected void setReputation(Integer reputation) {
		this.reputation = reputation;
	}
	
	public Integer getReputationCap(){
		return reputationCap;
	}

	public Integer getReputation(){
		return reputation;
	}
}
