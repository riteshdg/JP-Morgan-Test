package com.ritesh.resourcescheduler.gateway;

import com.ritesh.resourcescheduler.message.Message;

public interface Gateway {
	public void send(Message msg);
}
