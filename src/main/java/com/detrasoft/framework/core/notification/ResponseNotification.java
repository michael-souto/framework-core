package com.detrasoft.framework.core.notification;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ResponseNotification {
	protected Instant timestamp;
	protected Integer status;
	protected String title;
	protected String detail;
	protected String path;
	protected Object data;
	
	@Builder.Default
	protected List<Message> messages = new ArrayList<Message>();

	public void addError(String fieldName, String message) {
		messages.add(Message.builder().target(fieldName).type(MessageType.error).description(message).build());
	}

	public void addInfo(String fieldName, String message) {
		messages.add(Message.builder().target(fieldName).type(MessageType.info).description(message).build());
	}

	public void addSuccess(String fieldName, String message) {
		messages.add(Message.builder().target(fieldName).type(MessageType.success).description(message).build());
	}

	public void addWarning(String fieldName, String message) {
		messages.add(Message.builder().target(fieldName).type(MessageType.warning).description(message).build());
	}
}
