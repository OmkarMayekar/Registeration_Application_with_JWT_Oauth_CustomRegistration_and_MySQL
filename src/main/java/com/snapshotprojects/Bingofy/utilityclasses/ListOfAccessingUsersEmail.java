package com.snapshotprojects.Bingofy.utilityclasses;

import java.util.ArrayList;

public class ListOfAccessingUsersEmail {
	ArrayList<String> listAccessignUserEmail;
	
	private String email;

	public ArrayList<String> getListAccessignUserEmail() {
		return listAccessignUserEmail;
	}

	public void setListAccessignUserEmail(ArrayList<String> listAccessignUserEmail) {
		this.listAccessignUserEmail = listAccessignUserEmail;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "ListOfAccessingUsersEmail [listAccessignUserEmail=" + listAccessignUserEmail + ", email=" + email + "]";
	}
}
