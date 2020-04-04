package com.snapshotprojects.Bingofy.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserDTO {
	@JsonProperty("email_address")
	private String email;
	@JsonProperty("username")
	private String username;
	@JsonProperty("password")
	private String password;
	@JsonProperty("isAdmin")
	private boolean isAdmin;

	public UserDTO() {
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "UserDTO [email=" + email + ", username=" + username + ", password=" + password + "]";
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

}
