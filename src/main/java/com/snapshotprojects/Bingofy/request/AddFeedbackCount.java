package com.snapshotprojects.Bingofy.request;

import java.util.ArrayList;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

@Component
public class AddFeedbackCount {

	@NotNull(message = "username input is incorrect or username cannot be missing/empty")
	@JsonProperty("username")
	private String username;

	@NotNull(message = "count input incorrect or count cannot be missing/empty")
	@JsonProperty("count_to_be_added")
	private int countToBeAdded;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getCountToBeAdded() {
		return countToBeAdded;
	}

	public void setCountToBeAdded(int countToBeAdded) {
		this.countToBeAdded = countToBeAdded;
	}

	@Override
	public String toString() {
		return "AddFeedbackCount [username=" + username + ", countToBeAdded=" + countToBeAdded + ", getUsername()="
				+ getUsername() + ", getCountToBeAdded()=" + getCountToBeAdded() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

}
