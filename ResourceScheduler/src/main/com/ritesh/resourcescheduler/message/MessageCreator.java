package com.ritesh.resourcescheduler.message;

/**
 * 
 * @author Ritesh
 * Pattern: Factory Method
 */
public interface MessageCreator {
	public Message createNormalMessage(long groupID, String data);
	public Message createTerminationMessage(long groupID, String data);
	
}
