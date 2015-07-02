package com.ritesh.resourcescheduler.scheduler;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.ritesh.resourcescheduler.message.ConcreteMessage;
import com.ritesh.resourcescheduler.message.ConcreteMessageCreator;
import com.ritesh.resourcescheduler.message.MessageCreator;
import com.ritesh.resourcescheduler.scheduler.SimpleFIFOPriorisationStrategy;

public class SimpleFIFOPriorisationStrategyTest {
	SimpleFIFOPriorisationStrategy strategy;
	Object messages;
	
	@Before
	public void setUp () {
		strategy = new SimpleFIFOPriorisationStrategy();
		messages = strategy.initializeConcreteMessageContainer(messages);
	}
	
	@Test
	public void testStrategy () {
		
		MessageCreator mc = new ConcreteMessageCreator();
		strategy.addMessage((ConcreteMessage) mc.createNormalMessage(1, "DATA"), messages);
		strategy.addMessage((ConcreteMessage) mc.createNormalMessage(2, "DATA"), messages);
		strategy.addMessage((ConcreteMessage) mc.createNormalMessage(3, "DATA"), messages);
		strategy.addMessage((ConcreteMessage) mc.createNormalMessage(1, "DATA"), messages);

		ConcreteMessage msg = null;
		msg = strategy.pollMessage(messages);
		assertEquals(1L, msg.getGroupID());
		msg = strategy.pollMessage(messages);
		assertEquals(2L, msg.getGroupID());
	}
}
