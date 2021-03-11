package com.educandoweb.api.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError{
	private static final long serialVersionUID = 1L;
	
	private List<FieldMessage> fieldMessages = new ArrayList<>();
	
	public ValidationError(Integer status, String message, Long timeStamp) {
		super(status, message, timeStamp);
	}

	public List<FieldMessage> getErrors() {
		return fieldMessages;
	}

	public void addError(String fieldName, String message) {
		fieldMessages.add(new FieldMessage(fieldName,message));
	}

}
