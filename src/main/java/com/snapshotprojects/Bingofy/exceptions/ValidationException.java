package com.snapshotprojects.Bingofy.exceptions;

import java.io.Serializable;

public class ValidationException extends Exception implements Serializable {

	private static final long serialVersionUID = 1L;

	private int httpCode;

	private String message;

	public ValidationException(int httpCode, String message) {
		super();
		this.httpCode = httpCode;
		this.message = message;
	}

	public int getHttpCode() {
		return httpCode;
	}

	public void setHttpCode(int httpCode) {
		this.httpCode = httpCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
