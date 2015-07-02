package com.ritesh.resourcescheduler.concurrent;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.ritesh.resourcescheduler.message.ConcreteMessage;
import com.ritesh.resourcescheduler.message.ConcreteMessageCreator;
import com.ritesh.resourcescheduler.message.MessageCreator;
import com.ritesh.resourcescheduler.scheduler.SimpleFIFOPriorisationStrategy;

import static org.junit.Assert.assertEquals;

public class ConcreteMessageProducerConsumerTest {

		private ConcreteMessageProducerConsumer cmpc;
		
		@Before
		public void setUp() {
			cmpc = new ConcreteMessageProducerConsumer(new SimpleFIFOPriorisationStrategy());
		}
		
		@Test
		public void testSimpleAddRemove () {
			MessageCreator mc = new ConcreteMessageCreator();
			ConcreteMessage msg = null;
			cmpc.addMessage((ConcreteMessage) mc.createNormalMessage(1L, "DATA"));
			msg = cmpc.removeMessage();
			assertEquals(1L, msg.getGroupID());
		}
		
		@After
		public void tearDown () {
			cmpc = null;
		}
}
