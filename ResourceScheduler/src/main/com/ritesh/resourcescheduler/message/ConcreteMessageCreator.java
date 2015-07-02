package com.ritesh.resourcescheduler.message;

public class ConcreteMessageCreator implements MessageCreator {

	@Override
	public ConcreteMessage createNormalMessage(long groupID, String data) {
		ConcreteMessage cm = new ConcreteMessage(groupID, data, ConcreteMessage.MessageType.NORMAL);
		return cm;
	}

	@Override
	public ConcreteMessage createTerminationMessage(long groupID, String data) {
		ConcreteMessage cm = new ConcreteMessage(groupID, data, ConcreteMessage.MessageType.TERMINATION);
		return cm;
	}
}
