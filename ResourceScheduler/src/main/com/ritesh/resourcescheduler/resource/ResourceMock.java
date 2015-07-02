package com.ritesh.resourcescheduler.resource;

import com.ritesh.resourcescheduler.gateway.GatewayMediator;
import com.ritesh.resourcescheduler.message.Message;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ResourceMock implements Runnable {
	private static final Logger LOGGER = LogManager.getLogger();	

	private GatewayMediator gateway;
	private Message msg;
	private final long RESOURCE_PROCESSING_TIME_MILIS;
	
	public ResourceMock (GatewayMediator gateway, Message msg, final long RESOURCE_PROCESSING_TIME_MILIS) {
		this.gateway = gateway;
		this.msg     = msg;
		this.RESOURCE_PROCESSING_TIME_MILIS = RESOURCE_PROCESSING_TIME_MILIS;
	}
	
	public void run () {
		try {
			if (RESOURCE_PROCESSING_TIME_MILIS > 0)
				Thread.sleep(RESOURCE_PROCESSING_TIME_MILIS);	// Message processing mocking
			LOGGER.info("Message processed by the resource");
			msg.completed();
			gateway.inform();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			LOGGER.error(e.getMessage());
		}
		
	}
}
