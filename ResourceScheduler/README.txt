- INSTRUCTIONS:

To run the application, please run the class:
	
	com.ritesh.resourcescheduler.main.Application

The following parameters can be configured in the 
com.ritesh.resourcescheduler.main.Application class:

- NUMBER_OF_RESOURCES
	Number of total resources
- NUMBER_OF_MESSAGE_GROUPS
	Number of total distinct groups
- PRIORISATION_STRATEGY_CLASS
	Class that implements the ConcreteMessagePriorisationStrategy interface
	and determines in which order should the messages be processed

- ASSUMPTIONS:
	* Simple scenario: A single machine running all the classes is assumed, 
	therefore the threads share objects in memory for synchronization. 
	In a scenario where each resource run in a different machine, 
	communications would be achieved through sockets instead of shared memory

- DESCRIPTION:
The messages are represented by ConcreteMessage class, which implements 
the Message interface.

The RandomConcreteMessageProducer generates random messages of two 
types (NORMAL and TERMINATION) and stores them using an instance of
the ConcreteMessageProducerConsumer class

Upon availability of the resources, the ResourceScheduler consumes
the messages and sends it through the Gateway. The gateway acts as 
a mediator between the ResourceScheduler and each particular Resource 
(ResourceMock), which processes the message and informs the Gateway
once the message processing is finished so that the Gateway is aware
of its availability

- DESIGN:
	Multithreaded implementation:
		- Producer-Consumer for message queuing/dequeuing
		- Semaphore for acquiring/releasing a resource (ResourceMock)
		
	Design patterns:
		- Factory method, for deferring Message generation to subclasses
		- Mediator, for the Gateway implementation which controls the 
		interactions between the Resources and the ResourceScheduler
		- Strategy, for flexible message prioritisation

- EXTRA CREDIT:
1.) Alternative Message Prioritisation

	The prioritisation strategy is achieved through the Strategy 
	design pattern.
	
	The specific prioritisation classes share the common interface 
	ConcreteMessagePriorisationStrategy, and they are instanced 
	from the main com.ritesh.resourcescheduler.main.Application 
	class through reflection.

2.) Termination Messages

	The message type (NORMAL/TERMINATION) is checked by the 
	ResourceScheduler before acquiring the resource. If it is a 
	TERMINATION message, the scheduler includes its GROUPID in a 
	set of terminatedGroups.
	
	If any message of the same group is received again, an exception 
	is thrown.