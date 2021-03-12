package com.epsilon.training.exceptions;

public class NotXMLSerializableException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public NotXMLSerializableException() {
		super();
	}

	public NotXMLSerializableException(String msg) {
		super(msg);
	}

}
