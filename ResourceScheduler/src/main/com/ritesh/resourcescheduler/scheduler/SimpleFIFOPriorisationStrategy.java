package com.ritesh.resourcescheduler.scheduler;

import java.util.LinkedList;

import com.ritesh.resourcescheduler.message.ConcreteMessage;

public class SimpleFIFOPriorisationStrategy implements ConcreteMessagePriorisationStrategy {

	public SimpleFIFOPriorisationStrategy () {
	}
	
	@Override
	public ConcreteMessage pollMessage(Object messagesList) {
		LinkedList<ConcreteMessage> messages = (LinkedList<ConcreteMessage>) messagesList;
		ConcreteMessage cm = messages.pollFirst();
		return cm;
	}
	
	@Override
	public void addMessage(ConcreteMessage msg, Object messagesList) {
		LinkedList<ConcreteMessage> messages = (LinkedList<ConcreteMessage>) messagesList;
		messages.add(msg);
	}
	
	@Override
	public Object initializeConcreteMessageContainer(Object messages) {
		messages = new LinkedList<ConcreteMessage>();
		return messages;
	}
	
}
