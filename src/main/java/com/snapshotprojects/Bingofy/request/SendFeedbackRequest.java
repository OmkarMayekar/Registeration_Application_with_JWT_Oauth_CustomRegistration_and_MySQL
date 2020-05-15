package com.snapshotprojects.Bingofy.request;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Component
public class SendFeedbackRequest {

	@NotNull(message = "username input is incorrect or username cannot be missing/empty")
	@JsonProperty("username")
	private String username;

	@NotNull(message = "feedback input incorrect or feedback cannot be missing/empty")
	@JsonProperty("feedback")
	private String feedback;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	@Override
	public String toString() {
		return "SendFeedbackRequest [username=" + username + ", feedback=" + feedback + ", getUsername()="
				+ getUsername() + ", getFeedback()=" + getFeedback() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}

}
