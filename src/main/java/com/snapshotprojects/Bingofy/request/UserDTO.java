package com.snapshotprojects.Bingofy.request;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.snapshotprojects.Bingofy.enums.ApplicationUserRole;

public class UserDTO {
	@NotNull(message = "e-mail input incorrect or e-mail cannot be missing/empty")
	@JsonProperty("email_address")
	private String email;
	@NotNull(message = "username input incorrect or username cannot be missing/empty")
	@JsonProperty("username")
	private String username;
	@NotNull(message = "password input incorrect or password cannot be missing/empty")
	@JsonProperty("password")
	private String password;
	@JsonProperty("role")
	private ApplicationUserRole role;

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

	public ApplicationUserRole getRole() {
		return role;
	}

	public void setRole(ApplicationUserRole role) {
		this.role = role;
	}
}