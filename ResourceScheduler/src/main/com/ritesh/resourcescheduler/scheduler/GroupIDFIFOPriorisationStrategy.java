package com.ritesh.resourcescheduler.scheduler;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

import com.ritesh.resourcescheduler.message.ConcreteMessage;

public class GroupIDFIFOPriorisationStrategy implements ConcreteMessagePriorisationStrategy {

	public GroupIDFIFOPriorisationStrategy () {
	}
	
	@Override
	public ConcreteMessage pollMessage(Object messagesMap) {
		LinkedHashMap <Long, LinkedList<ConcreteMessage>> messages = (LinkedHashMap <Long, LinkedList<ConcreteMessage>>) messagesMap;
		ConcreteMessage cm = null;
		for (Map.Entry<Long, LinkedList<ConcreteMessage>> entry : messages.entrySet()) {
			LinkedList<ConcreteMessage> sameGroupMessagesList = (LinkedList<ConcreteMessage>) entry.getValue();
			if (sameGroupMessagesList != null && !sameGroupMessagesList.isEmpty()) {
				cm = sameGroupMessagesList.pollFirst();
				break;
			}
		}
		return cm;
	}
	
	@Override
	public void addMessage(ConcreteMessage msg, Object messagesMap) {
		LinkedHashMap <Long, LinkedList<ConcreteMessage>> messages = (LinkedHashMap <Long, LinkedList<ConcreteMessage>>) messagesMap;
		LinkedList<ConcreteMessage> sameGroupMessagesList = messages.get(msg.getGroupID());
		if (sameGroupMessagesList == null) {
			sameGroupMessagesList = new LinkedList<ConcreteMessage>();
			messages.put(msg.getGroupID(), sameGroupMessagesList);
		}
		
		sameGroupMessagesList.add(msg);
		
	}
	
	@Override
	public Object initializeConcreteMessageContainer(Object messages) {
		messages = new LinkedHashMap <Long, LinkedList<ConcreteMessage>>();
		return messages;
	}
	
}
