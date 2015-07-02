package com.ritesh.resourcescheduler.gateway;

import java.util.concurrent.Semaphore;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ritesh.resourcescheduler.message.Message;
import com.ritesh.resourcescheduler.resource.ResourceMock;

public class GatewayMediator implements Gateway {
	private static final Logger LOGGER = LogManager.getLogger();	

	private Semaphore availableResources;
	public final static long RESOURCE_PROCESSING_TIME_MILIS = 5000L;

	public GatewayMediator(Semaphore availableResources) {
		this.availableResources = availableResources;
	}
	
	@Override
	public void send(Message msg) {
		LOGGER.info("Message sent to the gateway");
		ResourceMock resource = getNextAvailableResource(msg);
		new Thread(resource).start();
	}
	
	public void inform () {
		LOGGER.info("Realease resource");
		availableResources.release();
	}
	
	public ResourceMock getNextAvailableResource (Message msg) {
		ResourceMock resource = new ResourceMock(this, msg, RESOURCE_PROCESSING_TIME_MILIS);
		return resource;
	}
}
