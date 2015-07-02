package com.ritesh.resourcescheduler.main;

import java.util.concurrent.Semaphore;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ritesh.resourcescheduler.concurrent.RandomConcreteMessageProducer;
import com.ritesh.resourcescheduler.concurrent.ConcreteMessageProducerConsumer;
import com.ritesh.resourcescheduler.gateway.GatewayMediator;
import com.ritesh.resourcescheduler.scheduler.ConcreteMessagePriorisationStrategy;
import com.ritesh.resourcescheduler.scheduler.GroupIDFIFOPriorisationStrategy;
import com.ritesh.resourcescheduler.scheduler.ResourceScheduler;

public class Application {
	private static final Logger LOGGER = LogManager.getLogger();	

	public static void main(String [] args) {

		final int NUMBER_OF_RESOURCES      = 3;
		final int NUMBER_OF_MESSAGE_GROUPS = 5;
		//final String PRIORISATION_STRATEGY_CLASS = "com.ritesh.resourcescheduler.scheduler.GroupIDFIFOPriorisationStrategy";
		final String PRIORISATION_STRATEGY_CLASS = "com.ritesh.resourcescheduler.scheduler.SimpleFIFOPriorisationStrategy";
		
		try {
			Class<?> c = Class.forName(PRIORISATION_STRATEGY_CLASS);
			ConcreteMessagePriorisationStrategy strategy = (ConcreteMessagePriorisationStrategy) c.newInstance();
			
			ConcreteMessageProducerConsumer cmpc = new ConcreteMessageProducerConsumer(strategy);		
			Semaphore availableResources = new Semaphore(NUMBER_OF_RESOURCES, true);
			GatewayMediator gateway = new GatewayMediator(availableResources);
			ResourceScheduler rs = new ResourceScheduler(cmpc, availableResources, gateway);
			new Thread(rs).start();
			
			Thread messageGenerator = new Thread (new RandomConcreteMessageProducer(NUMBER_OF_MESSAGE_GROUPS, cmpc));
			messageGenerator.start();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
	}
}
