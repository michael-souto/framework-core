package com.detrasoft.framework.core.service;

import java.io.Serializable;

import com.detrasoft.framework.core.exception.ProcessException;
import com.detrasoft.framework.core.notification.TypeMessage;

public abstract class GenericProcessService<T> extends GenericService implements Serializable {

	private static final long serialVersionUID = 3166881201338795238L;

	public T process(T object) {
		T objectReturn = null;
		clearMessages();

		try {
			beforeProcess(object);

			if (!hasFatalError())
				validateProcess(object);

			if (!hasFatalError())
				objectReturn = processingOperations(object);

			if (!hasFatalError())
				afterProcess(objectReturn);

			processBackInCaseFatalError();

		} catch (Exception e) {
			if (!e.getClass().equals(ProcessException.class)) {
				addMessage("Erro", TypeMessage.error, e.getMessage());
				throw new ProcessException(e.getMessage());
			} else
				throw e;
		}

		return objectReturn;
	}

	public T revertProcess(Object object) {
		T objectReturn = null;
		clearMessages();

		try {
			beforeRevertProcess(object);

			if (!hasFatalError())
				validateRevertProcess(object);

			if (!hasFatalError())
				objectReturn = revertProcessingOperations(object);

			if (!hasFatalError())
				afterRevertProcess(objectReturn);

			processBackInCaseFatalError();

		} catch (Exception e) {
			if (!e.getClass().equals(ProcessException.class)) {
				addMessage("Erro", TypeMessage.error, e.getMessage());
				throw new ProcessException(e.getMessage());
			} else
				throw e;
		}
		return objectReturn;
	}

	public abstract T openProcess(Object object);

	protected void processBackInCaseFatalError() {
	}

	protected void beforeProcess(T object) {
	}

	protected void afterProcess(T object) {
	}

	protected void beforeRevertProcess(Object object) {
	}

	protected void afterRevertProcess(T object) {
	}

	protected abstract T processingOperations(T object);

	protected abstract T revertProcessingOperations(Object object);

	public abstract void validateProcess(T object);

	protected abstract void validateRevertProcess(Object object);
}
