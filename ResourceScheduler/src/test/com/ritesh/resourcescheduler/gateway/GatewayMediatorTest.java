package com.ritesh.resourcescheduler.gateway;

import java.util.concurrent.Semaphore;

import org.junit.Before;
import org.junit.Test;

import com.ritesh.resourcescheduler.message.ConcreteMessage;
import com.ritesh.resourcescheduler.message.ConcreteMessageCreator;

import static org.junit.Assert.assertEquals;

public class GatewayMediatorTest {
	private GatewayMediator gateway;
	private Semaphore availableResources;
	
	@Before
	public void setUp() throws Exception {
		availableResources = new Semaphore(1);
		gateway = new GatewayMediator(availableResources);
	}
	
	@Test
	public void sendTest () throws InterruptedException {
		ConcreteMessageCreator cmc = new ConcreteMessageCreator();
		ConcreteMessage msg = cmc.createNormalMessage(1, "DATA");
		gateway.send(msg);
		Thread.sleep(GatewayMediator.RESOURCE_PROCESSING_TIME_MILIS * 2);
		assertEquals(1, availableResources.availablePermits());
	}
}
