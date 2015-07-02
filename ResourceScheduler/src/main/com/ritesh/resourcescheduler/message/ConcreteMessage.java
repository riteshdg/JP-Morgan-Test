package com.ritesh.resourcescheduler.message;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class ConcreteMessage implements Message {
	private static final Logger LOGGER = LogManager.getLogger();	

	public static final String DEFAULT_DATA   = "Test";
	public static final long DEFAULT_GROUP_ID = 1l;
	public enum MessageType {NORMAL, TERMINATION};

	private long groupID;
	private String data;
	private MessageType type;
	
	public ConcreteMessage (long groupID, String data, MessageType type) {
		this.groupID = groupID;
		this.data    = data;
		this.type    = type;
	}
	
	public ConcreteMessage (long groupID, String data) {
		this.groupID = groupID;
		this.data    = data;
		this.type    = MessageType.NORMAL;
	}

	public ConcreteMessage () {
		this.groupID = DEFAULT_GROUP_ID;
		this.data    = DEFAULT_DATA;
		this.type    = MessageType.NORMAL;
	}

	@Override
	public void completed() {
		LOGGER.info("Message processed");

	}

	public long getGroupID() {
		return groupID;
	}

	public void setGroupID(long groupID) {
		this.groupID = groupID;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public MessageType getType() {
		return type;
	}

	public void setType(MessageType type) {
		this.type = type;
	}

}
