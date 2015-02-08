package it.ck.cyberdeck.model;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

public class EventSourceTest {

	@Rule public JUnitRuleMockery context = new JUnitRuleMockery();
	private EventSource es = new EventSource();
	
	@Test
	public void givenAnEventSource_itNotifiesItsListenerForAnEvent() throws Exception {
		final EventSourceListener eventSourceListener = context.mock(EventSourceListener.class);
		es.addListener(eventSourceListener);
		
		context.checking(new Expectations(){{
			oneOf(eventSourceListener).handleEvent(with(any(Event.class)));
		}});
		
		notifyEvent();
	}

	@Test
	public void anEventSource_canHaveMoreThanOneListener() throws Exception {
		final EventSourceListener eventhandler1 = context.mock(EventSourceListener.class, "eventHandler1");
		final EventSourceListener eventhandler2 = context.mock(EventSourceListener.class, "eventHandler2");
		
		es.addListener(eventhandler1);
		es.addListener(eventhandler2);
		
		context.checking(new Expectations(){{
			oneOf(eventhandler1).handleEvent(with(any(Event.class)));
			oneOf(eventhandler2).handleEvent(with(any(Event.class)));
		}});
		notifyEvent();
	}
	
	@Test
	public void aNullEventIsSilentlyDiscarded() throws Exception {
		final EventSourceListener eventhandler1 = context.mock(EventSourceListener.class, "eventHandler1");
		es.addListener(eventhandler1);
		context.checking(new Expectations(){{
			exactly(0).of(eventhandler1).handleEvent(null);
		}});
		es.notifyListeners(null);
	}
	
	private void notifyEvent() {
		Event event = new Event(null){};
		es.notifyListeners(event);
	}
}
