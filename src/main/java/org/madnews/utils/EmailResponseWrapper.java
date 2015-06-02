package org.madnews.utils;

public class EmailResponseWrapper {

	private String email;
	private boolean in_db;
	
	public EmailResponseWrapper(String email, boolean in_db) {
		this.email = email;
		this.in_db = in_db;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean isIn_db() {
		return in_db;
	}
	public void setIn_db(boolean in_db) {
		this.in_db = in_db;
	}

}
