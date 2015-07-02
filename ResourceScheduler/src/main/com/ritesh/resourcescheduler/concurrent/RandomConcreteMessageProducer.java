package com.ritesh.resourcescheduler.concurrent;

import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ritesh.resourcescheduler.message.ConcreteMessage;
import com.ritesh.resourcescheduler.message.ConcreteMessageCreator;
import com.ritesh.resourcescheduler.message.MessageCreator;

public class RandomConcreteMessageProducer implements Runnable {
	private static final Logger LOGGER = LogManager.getLogger();	

	private ConcreteMessageProducerConsumer cmpc;
	final private int NUMBER_OF_MESSAGE_GROUPS;
	
	public RandomConcreteMessageProducer (final int NUMBER_OF_MESSAGE_GROUPS, ConcreteMessageProducerConsumer cmpc) {
		this.NUMBER_OF_MESSAGE_GROUPS = NUMBER_OF_MESSAGE_GROUPS ;
		this.cmpc = cmpc;
	}

	@Override
	public void run() {
		MessageCreator mc = new ConcreteMessageCreator();
		ConcreteMessage msg = null;
		Random rn = new Random();
		while (true) {
			int i = rn.nextInt(NUMBER_OF_MESSAGE_GROUPS);
			if (i == 0) {
				i = rn.nextInt(NUMBER_OF_MESSAGE_GROUPS) + 1;
				LOGGER.debug("Creating Termination message...");
				msg = (ConcreteMessage) mc.createTerminationMessage(i, "DATA");
			} else {
				LOGGER.debug("Creating Normal message...");
				msg = (ConcreteMessage) mc.createNormalMessage(i, "DATA");
			}
			try {
				LOGGER.info("Generated msg with groupID " + i);
				cmpc.addMessage(msg);
				Thread.sleep(1000L);
			} catch (InterruptedException e) {
				LOGGER.error(e.getMessage());
			}
		}
	}
}
