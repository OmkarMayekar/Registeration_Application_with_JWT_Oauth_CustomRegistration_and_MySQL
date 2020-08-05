package com.snapshotprojects.Bingofy.enums;

import java.util.Arrays;

public enum ResponseFlag {

	REGISTRATION_SUCCESSFULL("User registered successfully"),
	REGISTRATION_UNSUCCESSFULL("User was not registered successfully"),
	USER_WITH_SAME_USERNAME_ALREADY_EXISTS("User with same username already exists"),
	USER_WITH_SAME_EMAIL_ALREADY_EXISTS("User with same e-mail already exists"),
	USERS_GIVEN_ACCESS("Users are given access to ownser's list"),
	USERS_NOT_GIVEN_ACCESS("Users are given access to ownser's list"),
	USER_NOT_PRESENT("User is not found in database"),
	EXT_BAD_REQUEST("External error - Bad request"),
	INVALID_CREDS("Invalid_credentials"),
	ITEMS_UPDATED("Items requeted were updated to users list"),
	ITEMS_WERE_NOT_UPDATED("Items requested were not updated to users list"),
	ITEMS_RETRIVED_SUCCESSFULLY("Items retrived successfully"),
	ITEMS_NOT_RETRIVED_SUCCESSFULLY("Items not retrived successfully"),
	EXT_TECH_ISSUE("External error - PIPL technical issue"),
	INTERNAL_ERROR("Internal error"),
	UNKNOWN("UNKNOWN"),
	DATABASE_UPDATED("Database Updated Successfully"),
	RECIPE_ADDED_SUCCESSFULLY("Recipe added successfully"),
	RECIPE_ADDED_UNSUCCESSFULLY("Recipe was not added successfully"),
	RECIPE_RETRIEVED_SUCCESSFULLY("Recipe retrieved successfully"),
	RECIPE_RETRIEVED_UNSUCCESSFULLY("Recipe was not retrieved successfully"),
	FEEDBACK_SENT_SUCCESSFULLY("Feedback was sent"),
	FEEDBACK_SENT_UNSUCCESSFULLY("Feedback was not sent"),
	JSON_RETRIVED("Json Retrived");

	private String serviceResponseFlag;

	/*
	 * public static final Set<Object> SERVICE_ERROR_FLAG =
	 * Collections.unmodifiableSet(new HashSet<>( Arrays.asList(new
	 * ResponseFlag[]{EXT_BAD_REQUEST, EXT_CALLS_EXCEEDED, EXT_TECH_ISSUE,
	 * INTERNAL_ERROR})));
	 */

	ResponseFlag(String serviceResponseFlag) {
		this.serviceResponseFlag = serviceResponseFlag;
	}

	public String getserviceResponseFlag() {
		return this.serviceResponseFlag;
	}

	public static ResponseFlag getserviceResponseFlagByFlag(String flag) {
		if (null == flag) {
			return UNKNOWN;
		}
		return Arrays.stream(ResponseFlag.values())
				.filter(ddf -> ddf.getserviceResponseFlag().equalsIgnoreCase(flag.trim())).findFirst()
				.orElse(ResponseFlag.UNKNOWN);
	}
}
