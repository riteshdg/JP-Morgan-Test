package com.ritesh.resourcescheduler.message;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ConcreteMessageCreatorTest {
	private ConcreteMessageCreator cmc;
	
	@Before
	public void setUp() {
		cmc = new ConcreteMessageCreator();
	}
	
	@After
	public void tearDown() {
		cmc = null;
	}
	
	@Test
	public void testNormalMessageCreation() {
		ConcreteMessage cm = cmc.createNormalMessage(1, "DATA");
		assertEquals(ConcreteMessage.MessageType.NORMAL, cm.getType());
		assertTrue(1 == cm.getGroupID());
		assertEquals("DATA", cm.getData());
	}

	@Test
	public void testTerminationMessageCreation() {
		ConcreteMessage cm = cmc.createTerminationMessage(1, "DATA");
		assertEquals(ConcreteMessage.MessageType.TERMINATION, cm.getType());
		assertTrue(1 == cm.getGroupID());
		assertEquals("DATA", cm.getData());
	}
}
