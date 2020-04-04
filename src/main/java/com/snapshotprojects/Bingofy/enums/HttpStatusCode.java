package com.snapshotprojects.Bingofy.enums;

public enum HttpStatusCode {

	// Success
	SUCCESS(200),

	// validation
	VALIDATION_FAILED(407),

	// FAIL
	INTERNAL_SERVER_ERROR(500), REQUEST_TIMEOUT(504);

	private int ordinal;

	HttpStatusCode(int ordinal) {
		this.ordinal = ordinal;
	}

	public int getOrdinal() {
		return ordinal;
	}

}
