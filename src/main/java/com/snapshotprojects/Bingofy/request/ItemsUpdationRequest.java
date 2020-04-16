package com.snapshotprojects.Bingofy.request;

import java.util.ArrayList;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ItemsUpdationRequest {
	
	@NotNull(message = "list input is incorrect or it cannot be missing/empty")
	@JsonProperty("list_of_items_To_be_added")
	ArrayList<String> listOfItemsToBeAdded;

	@NotNull(message = "e-mail input incorrect or email cannot be missing/empty")
	@JsonProperty("email_address")
	private String email;

	public ArrayList<String> getListOfItemsToBeAdded() {
		return listOfItemsToBeAdded;
	}

	public void setListOfItemsToBeAdded(ArrayList<String> listAccessignUserEmail) {
		this.listOfItemsToBeAdded = listAccessignUserEmail;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "CustomItemsUtilityClass [listOfItemsToBeAdded=" + listOfItemsToBeAdded + ", email=" + email
				+ ", getListOfItemsToBeAdded()=" + getListOfItemsToBeAdded() + ", getEmail()=" + getEmail()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}
}
