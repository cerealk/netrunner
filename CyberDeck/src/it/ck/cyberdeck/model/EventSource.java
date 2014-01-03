package it.ck.cyberdeck.model;

import java.util.ArrayList;
import java.util.List;

public class EventSource {

	private List<EventSourceListener> eventSourceListener = new ArrayList<EventSourceListener>();

	public void addListener(EventSourceListener eventSourceListener) {
		this.eventSourceListener.add(eventSourceListener);
	}

	public void notifyListeners(final Event event) {
		for (EventSourceListener esl : eventSourceListener)
			esl.handleEvent(event);
		
	}

}
