package com.sid.model;

/**
 * @author Siddharth
 *
 */

public class TestBean {
	
	public String userName;

	public String displayUser() {
		return "success";
	}
	
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	
}
