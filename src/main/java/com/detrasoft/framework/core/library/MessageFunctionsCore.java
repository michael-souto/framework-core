package com.detrasoft.framework.core.library;
import java.util.List;
import com.detrasoft.framework.core.notification.Message;
import com.detrasoft.framework.core.notification.MessageType;

public class MessageFunctionsCore {

	public static void addMessage(List<Message> messages, String message, MessageType type) {
		messages.add(Message.builder().type(type).description(message).build());
	}

    public static void addErrorMessage(List<Message> messages, String message) {
		messages.add(Message.builder().type(MessageType.error).description(message).build());
	}

	public static void addInfoMessage(List<Message> messages, String message) {
		messages.add(Message.builder().type(MessageType.info).description(message).build());
	}

	public static void addSuccessMessage(List<Message> messages, String message) {
		messages.add(Message.builder().type(MessageType.success).description(message).build());
	}

	public static void addWarningMessage(List<Message> messages, String message) {
		messages.add(Message.builder().type(MessageType.warning).description(message).build());
	}

    public static void addMessage(List<Message> messages, String target, String message, MessageType type) {
		messages.add(Message.builder().target(target).type(type).description(message).build());
	}

    public static void addErrorMessage(List<Message> messages, String target, String message) {
		messages.add(Message.builder().target(target).type(MessageType.error).description(message).build());
	}

	public static void addInfoMessage(List<Message> messages, String target, String message) {
		messages.add(Message.builder().target(target).type(MessageType.info).description(message).build());
	}

	public static void addSuccessMessage(List<Message> messages, String target, String message) {
		messages.add(Message.builder().target(target).type(MessageType.success).description(message).build());
	}

	public static void addWarningMessage(List<Message> messages, String target, String message) {
		messages.add(Message.builder().target(target).type(MessageType.warning).description(message).build());
	}
	//
	public static void addMessage(List<Message> messages, String code, String target, String message, MessageType type) {
		messages.add(Message.builder().target(target).type(type).code(code).description(message).build());
	}

    public static void addErrorMessage(List<Message> messages, String code, String target, String message) {
		messages.add(Message.builder().target(target).type(MessageType.error).code(code).description(message).build());
	}

	public static void addInfoMessage(List<Message> messages, String code, String target, String message) {
		messages.add(Message.builder().target(target).type(MessageType.info).code(code).description(message).build());
	}

	public static void addSuccessMessage(List<Message> messages, String code, String target, String message) {
		messages.add(Message.builder().target(target).type(MessageType.success).code(code).description(message).build());
	}

	public static void addWarningMessage(List<Message> messages, String code, String target, String message) {
		messages.add(Message.builder().target(target).type(MessageType.warning).code(code).description(message).build());
	}

}
