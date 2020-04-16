package com.snapshotprojects.Bingofy.enums;

public enum HttpStatusCode {

	// Success
	SUCCESS(200),

	// validation
	VALIDATION_FAILED(407),
	
	NOT_ACCEPTABLE(406),
	UNAUTHORIZED(401),
	// FAIL
	INTERNAL_SERVER_ERROR(500),
	
	REQUEST_TIMEOUT(504),
	
	CONFLICT(409),
	
	CREATED(201);
	
	private int ordinal;

	HttpStatusCode(int ordinal) {
		this.ordinal = ordinal;
	}

	public int getOrdinal() {
		return ordinal;
	}

}
