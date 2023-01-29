package com.detrasoft.framework.core.notification;

import java.util.ArrayList;
import java.util.List;

public class ResponseNotification {

	protected List<Message> messages;

	public List<Message> getMessages() {
		if (this.messages == null) {
			this.messages = new ArrayList<>();
		}
		return messages;
	}

	public ResponseNotification() {
		this.messages = new ArrayList<Message>();
	}
}
