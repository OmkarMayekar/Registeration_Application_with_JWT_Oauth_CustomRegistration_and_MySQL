package com.snapshotprojects.Bingofy.exceptions;

import java.io.Serializable;

public class ServiceApiException extends Exception implements Serializable {

	private static final long serialVersionUID = 1L;

	private int httpCode;

	private String message;

	private String responseJson;

	public ServiceApiException(int httpCode, String message, String responseJson) {
		super();
		this.httpCode = httpCode;
		this.message = message;
		this.responseJson = responseJson;
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

	public String getResponseJson() {
		return responseJson;
	}

	public void setResponseJson(String responseJson) {
		this.responseJson = responseJson;
	}

}
