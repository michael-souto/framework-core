package com.detrasoft.framework.core.service;

import java.util.ArrayList;
import java.util.List;

import com.detrasoft.framework.core.library.MessageFunctionsCore;
import com.detrasoft.framework.core.notification.Message;
import com.detrasoft.framework.core.notification.MessageType;
import com.detrasoft.framework.core.resource.Translator;
import com.detrasoft.framework.enums.CodeMessages;

public abstract class GenericService {

    protected List<Message> messages = new ArrayList<>();
    
	public List<Message> getMessages() {
		return messages;
	}
	
	public void clearMessages() {
		this.messages.clear();
	}

	public boolean hasFatalError() {
		boolean fatalError = false;
		for (Message messageService : messages) {
			if (messageService.getType() == MessageType.error) {
				fatalError = true;
				break;
			} else
				fatalError = false;
		}
		return fatalError;
	}
	
	public boolean hasFatalError(List<Message> messages) {
		boolean fatalError = false;
		for (Message messageService : messages) {
			if (messageService.getType() == MessageType.error) {
				fatalError = true;
				break;
			} else
				fatalError = false;
		}
		return fatalError;
	}
		
	public void addMessageTranslated(CodeMessages code, String target, MessageType type) {
		String message = Translator.getTranslatedText(code.toString(),true);
		MessageFunctionsCore.addMessage(messages, code.toString(), target, message, type);
	}
	
	public void addMessageTranslated(CodeMessages code, String target, MessageType type, Object ...args) {
		String message = Translator.getTranslatedText(code.toString().toLowerCase(), args);
        MessageFunctionsCore.addMessage(messages, code.toString(), target, message, type);
	}
	
	public void addMessageTranslated(String code, String target, MessageType type, Object ...args) {
		String message = Translator.getTranslatedText(code.toString(), args);
        MessageFunctionsCore.addMessage(messages, code.toString(), target, message, type);
	}
	
	protected Throwable getRootCause(Throwable throwable) {
		Throwable cause = throwable.getCause();
		if (cause != null && cause != throwable) {
			return getRootCause(cause);
		} else {
			return throwable;
		}
	}
}
