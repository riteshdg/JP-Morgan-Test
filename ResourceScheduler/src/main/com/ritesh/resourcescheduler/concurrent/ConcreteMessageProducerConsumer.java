package com.ritesh.resourcescheduler.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ritesh.resourcescheduler.message.ConcreteMessage;
import com.ritesh.resourcescheduler.scheduler.ConcreteMessagePriorisationStrategy;

public class ConcreteMessageProducerConsumer {
	private static final Logger LOGGER = LogManager.getLogger();	

	private final Lock mutex         = new ReentrantLock(true);
	private final Condition notEmpty = mutex.newCondition();
	
	private Object messages;
	private int pendingMessages;
	private ConcreteMessagePriorisationStrategy strategy;
	
	public ConcreteMessageProducerConsumer (ConcreteMessagePriorisationStrategy strategy) {
		pendingMessages = 0;
		this.strategy   = strategy;
		messages        = strategy.initializeConcreteMessageContainer(messages);

	}
	
	public void addMessage (ConcreteMessage msg) {
		mutex.lock();
		try {
			LOGGER.info("[addMessage] Start");
			strategy.addMessage(msg, messages);
			++pendingMessages;
			if (pendingMessages == 1) {
				notEmpty.signal();
			}
			LOGGER.info("[addMessage] End");
		} finally {
			mutex.unlock();
		}
	}
	
	public ConcreteMessage removeMessage () {
		mutex.lock();
		ConcreteMessage cm = null;
		try {
			LOGGER.info("[removeMessage] Start");
			while (pendingMessages == 0) {
				try {
					notEmpty.await();
				} catch (InterruptedException e) {
					LOGGER.error(e.getMessage());
				}
			}

			cm = strategy.pollMessage(messages);
			--pendingMessages;
			LOGGER.info("[removeMessage] End");
		} finally {
			mutex.unlock();
		}
		
		return cm;
	}
}
