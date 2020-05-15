package com.snapshotprojects.Bingofy.entities;

import java.util.ArrayList;
import java.util.HashSet;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.ToString;

import java.util.Set;

@Entity(name = "recipe")
@Table(name = "recipe")
@ToString
public class Recipe {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String recipeTitle;
	private String recipeDescription;
	@Column(name = "recipe_sharing_users")
	private ArrayList<String> sharingUseremail = new ArrayList<String>();
	private String recipeType;
	@ManyToOne
	@JoinColumn(name = "userId")
	private ApplicationUser user;

	public Recipe(Long id, String recipeTitle, String recipeDescription, ArrayList<String> sharingUseremail,
			String recipeType, ApplicationUser user) {
		super();
		this.id = id;
		this.recipeTitle = recipeTitle;
		this.recipeDescription = recipeDescription;
		this.sharingUseremail = sharingUseremail;
		this.recipeType = recipeType;
		this.user = user;
	}

	public Recipe() {
		// TODO Auto-generated constructor stub
	}

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

	public ArrayList<String> getSharingUseremail() {
		return sharingUseremail;
	}

	public void setSharingUseremail(ArrayList<String> sharingUseremail) {
		this.sharingUseremail = sharingUseremail;
	}

	public String getRecipeType() {
		return recipeType;
	}

	public void setRecipeType(String recipeType) {
		this.recipeType = recipeType;
	}

	public ApplicationUser getUser() {
		return user;
	}

	public void setUser(ApplicationUser user) {
		this.user = user;
	}

	/*
	 * @Override public String toString() { return "Recipe [id=" + id +
	 * ", recipeTitle=" + recipeTitle + ", recipeDescription=" + recipeDescription +
	 * ", sharingUseremail=" + sharingUseremail + ", recipeType=" + recipeType +
	 * ", user=" + user + ", getId()=" + getId() + ", getRecipeTitle()=" +
	 * getRecipeTitle() + ", getRecipeDescription()=" + getRecipeDescription() +
	 * ", getSharingUseremail()=" + getSharingUseremail() + ", getRecipeType()=" +
	 * getRecipeType() + ", getUser()=" + getUser() + ", getClass()=" + getClass() +
	 * ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]"; }
	 */

}
