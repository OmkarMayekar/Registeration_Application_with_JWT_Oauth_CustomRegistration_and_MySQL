package com.snapshotprojects.Bingofy.request;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonObjectRequest {
	@NotNull(message = "e-mail input is incorrect or it cannot be missing/empty")
	@JsonProperty("username")
	private String username;

	@NotNull(message = "json input incorrect or input cannot be missing/empty")
	@JsonProperty("json")
	private String json;

	public String getUsername() {
		return username;
	}

	public void getUsername(String username) {
		this.username = username;
	}

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

	@Override
	public String toString() {
		return "JsonObjectRequest [username=" + username + ", json=" + json + ", getUsername()=" + getUsername() + ", getJson()="
				+ getJson() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}

}
