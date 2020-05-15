package com.snapshotprojects.Bingofy.request;

import java.util.ArrayList;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

@Component
public class AddRecipeRequest {

	@NotNull(message = "username input is incorrect or username cannot be missing/empty")
	@JsonProperty("username")
	private String username;

	@NotNull(message = "title input incorrect or title cannot be missing/empty")
	@JsonProperty("recipe_title")
	private String recipetitle;

	@NotNull(message = "description input incorrect or description cannot be missing/empty")
	@JsonProperty("recipe_desc")
	private String recipedesc;

	@NotNull(message = "sharing user e-mail input incorrect or e-mail cannot be missing/empty")
	@JsonProperty("sharing_user_email")
	private ArrayList<String> sharinguseremail;

	@NotNull(message = "recipe type input incorrect or recipe type cannot be missing/empty")
	@JsonProperty("recipe_type")
	private String recipetype;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRecipetitle() {
		return recipetitle;
	}

	public void setRecipetitle(String recipetitle) {
		this.recipetitle = recipetitle;
	}

	public String getRecipedesc() {
		return recipedesc;
	}

	public void setRecipedesc(String recipedesc) {
		this.recipedesc = recipedesc;
	}

	public ArrayList<String> getSharinguseremail() {
		return sharinguseremail;
	}

	public void setSharinguseremail(ArrayList<String> sharinguseremail) {
		this.sharinguseremail = sharinguseremail;
	}

	public String getRecipetype() {
		return recipetype;
	}

	public void setRecipetype(String recipetype) {
		this.recipetype = recipetype;
	}

	@Override
	public String toString() {
		return "AddRecipeRequest [username=" + username + ", recipetitle=" + recipetitle + ", recipedesc=" + recipedesc
				+ ", sharinguseremail=" + sharinguseremail + ", recipetype=" + recipetype + ", getUsername()="
				+ getUsername() + ", getRecipetitle()=" + getRecipetitle() + ", getRecipedesc()=" + getRecipedesc()
				+ ", getSharinguseremail()=" + getSharinguseremail() + ", getRecipetype()=" + getRecipetype()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}

}
