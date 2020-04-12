package com.snapshotprojects.Bingofy.request;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.snapshotprojects.Bingofy.utilityclasses.ListOfAccessingUsersEmail;

public class OwnerUserEmail {
	@NotNull(message = "email cannot be missing or empty")
	@JsonProperty("email_address")
	private String email;
	
	public OwnerUserEmail() {
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "OwnerUserEmail [email=" + email + ", getEmail()=" + getEmail() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	
}
