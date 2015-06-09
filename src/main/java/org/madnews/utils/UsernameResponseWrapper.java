package org.madnews.utils;

public class UsernameResponseWrapper {

	private String username;
	private boolean in_db;
	
	public UsernameResponseWrapper(String username, boolean in_db) {
		this.username = username;
		this.in_db = in_db;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public boolean isIn_db() {
		return in_db;
	}
	public void setIn_db(boolean in_db) {
		this.in_db = in_db;
	}

}
