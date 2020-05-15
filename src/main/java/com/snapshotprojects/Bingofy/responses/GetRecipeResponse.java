package com.snapshotprojects.Bingofy.responses;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.snapshotprojects.Bingofy.enums.ResponseFlag;

import lombok.ToString;

@Component
@ToString
public class GetRecipeResponse {

	@JsonIgnore
	private Long id;

	@JsonProperty("recipe_title")
	private String recipeTitle;

	@JsonProperty("recipe_description")
	private String recipeDescription;

	@JsonProperty("sharing_user_email")
	private List<String> sharingUseremail;

	@JsonProperty("recipe_type")
	private String recipeType;

	@JsonProperty("admin_user_email")
	private String adminUseremail;

	@JsonProperty("admin_username")
	private String adminUsername;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRecipeTitle() {
		return recipeTitle;
	}

	public void setRecipeTitle(String recipeTitle) {
		this.recipeTitle = recipeTitle;
	}

	public String getRecipeDescription() {
		return recipeDescription;
	}

	public void setRecipeDescription(String recipeDescription) {
		this.recipeDescription = recipeDescription;
	}

	public List<String> getSharingUseremail() {
		return sharingUseremail;
	}

	public void setSharingUseremail(List<String> sharingUseremail) {
		this.sharingUseremail = sharingUseremail;
	}

	public String getRecipeType() {
		return recipeType;
	}

	public void setRecipeType(String recipeType) {
		this.recipeType = recipeType;
	}

	public String getAdminUseremail() {
		return adminUseremail;
	}

	public void setAdminUseremail(String adminUseremail) {
		this.adminUseremail = adminUseremail;
	}

	public String getAdminUsername() {
		return adminUsername;
	}

	public void setAdminUsername(String adminUsername) {
		this.adminUsername = adminUsername;
	}

}
