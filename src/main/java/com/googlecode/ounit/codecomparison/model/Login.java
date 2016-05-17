package com.googlecode.ounit.codecomparison.model;

public class Login {
	String user;
	String pass;
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	@Override
	public String toString() {
		return "Login [user=" + user + ", pass=" + pass + "]";
	}
	
}
