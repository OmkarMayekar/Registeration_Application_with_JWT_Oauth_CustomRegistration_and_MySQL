package com.snapshotprojects.Bingofy.responses;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.snapshotprojects.Bingofy.enums.ResponseFlag;

@Component
public class ServiceResponse {

	@JsonIgnore
	private int statusCode;

	@JsonProperty("errorMessage")
	private String errorMessage;

	@JsonProperty("service_flag")
	private ResponseFlag serviceFlag;

	public ServiceResponse() {
	}

	public ServiceResponse(ResponseFlag serviceFlag) {
		super();
		this.serviceFlag = serviceFlag;
	}

	public ResponseFlag getserviceFlag() {
		return serviceFlag;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int i) {
		this.statusCode = i;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String extBadRequest) {
		this.errorMessage = extBadRequest;
	}

	@Override
	public String toString() {
		return "ServiceResponse [statusCode=" + statusCode + ", errorMessage=" + errorMessage + ", serviceFlag="
				+ serviceFlag + "]";
	}

	public void setserviceFlag(ResponseFlag string) {
		this.serviceFlag = string;
	}

}
