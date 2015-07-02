package com.ritesh.resourcescheduler.scheduler;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Semaphore;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ritesh.resourcescheduler.concurrent.ConcreteMessageProducerConsumer;
import com.ritesh.resourcescheduler.exceptions.TerminatedGroupException;
import com.ritesh.resourcescheduler.gateway.GatewayMediator;
import com.ritesh.resourcescheduler.message.ConcreteMessage;

public class ResourceScheduler implements Runnable {
	private static final Logger LOGGER = LogManager.getLogger();	

	Semaphore availableResources;
	ConcreteMessageProducerConsumer cmpc;
	GatewayMediator gateway;
	Set<Long> terminatedGroups;
	
	public ResourceScheduler (ConcreteMessageProducerConsumer cmpc, Semaphore availableResources,
			GatewayMediator gateway) {
		this.cmpc               = cmpc;
		this.availableResources = availableResources;
		this.gateway            = gateway;
		terminatedGroups        = new HashSet<Long>();
	}
	
	public void run () {
		ConcreteMessage msg = null;
		while (true) {
			try {
				msg = cmpc.removeMessage();
				LOGGER.info("Take msg of group " + msg.getGroupID() + " of type " + msg.getType().name() + " to process");
				switch (msg.getType()) {
					case NORMAL : processNormalMessage(msg);
							break;
					case TERMINATION : processTerminationMessage(msg);
							break;
				}
			} catch (TerminatedGroupException e) {
				LOGGER.error(e.getMessage());
			} catch (Exception e) {
				LOGGER.error(e.getMessage());
			}
		}	
	}
	
	private void processNormalMessage (ConcreteMessage msg) throws Exception {
		if (terminatedGroups.contains(msg.getGroupID())) {
			throw new TerminatedGroupException(msg.getGroupID());
		} else {
			availableResources.acquire();
			gateway.send(msg);
		}
	}
	
	private void processTerminationMessage (ConcreteMessage msg) throws Exception {
		if (terminatedGroups.contains(msg.getGroupID())) {
			throw new TerminatedGroupException(msg.getGroupID());
		} else {
			terminatedGroups.add(msg.getGroupID());
		}
	}
}
