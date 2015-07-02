package com.ritesh.resourcescheduler.exceptions;

public class TerminatedGroupException extends Exception {
	private static final long serialVersionUID = 1L;
	private static final String ERROR_MESSAGE = "Received a message from a terminated group. ";
	
	public TerminatedGroupException(Long groupID) {
		super(ERROR_MESSAGE + "Group ID: " + groupID);
	}

	public TerminatedGroupException(String message) {
		super(ERROR_MESSAGE + " " + message);
	}
	
	public TerminatedGroupException() {
		super(ERROR_MESSAGE);
	}

}
