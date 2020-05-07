package com.snapshotprojects.Bingofy.request;

import java.util.ArrayList;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ItemsUpdationRequest {
	
	@NotNull(message = "list input is incorrect or it cannot be missing/empty")
	@JsonProperty("list_of_items_To_be_added")
	ArrayList<String> listOfItemsToBeAdded;

	@NotNull(message = "username input incorrect or username cannot be missing/empty")
	@JsonProperty("username")
	private String username;

	public ArrayList<String> getListOfItemsToBeAdded() {
		return listOfItemsToBeAdded;
	}

	public void setListOfItemsToBeAdded(ArrayList<String> listOfItemsToBeAdded) {
		this.listOfItemsToBeAdded = listOfItemsToBeAdded;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "ItemsUpdationRequest [listOfItemsToBeAdded=" + listOfItemsToBeAdded + ", username=" + username
				+ ", getListOfItemsToBeAdded()=" + getListOfItemsToBeAdded() + ", getUsername()=" + getUsername()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}
}
