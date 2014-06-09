package it.ck.cyberdeck.model;

public interface EventSourceListener {

	void handleEvent(Event event);

}
