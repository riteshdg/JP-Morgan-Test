package com.ritesh.resourcescheduler.scheduler;

import com.ritesh.resourcescheduler.message.ConcreteMessage;

public interface ConcreteMessagePriorisationStrategy {
	public Object initializeConcreteMessageContainer(Object messages);
	public void addMessage(ConcreteMessage cm, Object messages);
	public ConcreteMessage pollMessage(Object messages);
}
