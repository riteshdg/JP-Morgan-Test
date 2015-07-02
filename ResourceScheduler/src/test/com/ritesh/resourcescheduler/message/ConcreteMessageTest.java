package com.ritesh.resourcescheduler.message;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Test;

public class ConcreteMessageTest {
	private ConcreteMessage msg;
	
	@After
	public void setUp() throws Exception {
		msg = null;
	}
	
	@Test
	public void normalMessageAllParams () {
		msg = new ConcreteMessage(1, "DATA", ConcreteMessage.MessageType.NORMAL);
		assertEquals(ConcreteMessage.MessageType.NORMAL, msg.getType());
		assertTrue(1 == msg.getGroupID());
		assertEquals("DATA", msg.getData());
	}
	
	@Test
	public void terminationMessageAllParams () {
		msg = new ConcreteMessage(1, "DATA", ConcreteMessage.MessageType.TERMINATION);
		assertEquals(ConcreteMessage.MessageType.TERMINATION, msg.getType());
		assertTrue(1 == msg.getGroupID());
		assertEquals("DATA", msg.getData());
	}

	@Test
	public void normalMessageDefaultType () {
		msg = new ConcreteMessage(1, "DATA");
		assertEquals(ConcreteMessage.MessageType.NORMAL, msg.getType());
		assertTrue(1 == msg.getGroupID());
		assertEquals("DATA", msg.getData());
	}

	@Test
	public void normalMessageDefaultParameters () {
		msg = new ConcreteMessage();
		assertEquals(ConcreteMessage.MessageType.NORMAL, msg.getType());
		assertTrue(ConcreteMessage.DEFAULT_GROUP_ID == msg.getGroupID());
		assertEquals(ConcreteMessage.DEFAULT_DATA, msg.getData());
	}
}
