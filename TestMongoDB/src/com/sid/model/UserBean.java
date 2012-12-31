/**
 * 
 */
package com.sid.model;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.WriteResult;
import com.sid.dbconnect.DatabaseCRUD;
import com.sid.utils.ProjectConstants;

/**
 * @author Siddharth
 *
 */
public class UserBean {
	public String userName;
	public String userPassword;

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
	/**
	 * @return the userPassword
	 */
	public String getUserPassword() {
		return userPassword;
	}
	/**
	 * @param userPassword the userPassword to set
	 */
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String login() {
		String returnText = ProjectConstants.LOGIN_FAILURE;
		DatabaseCRUD dbDAO = null;
		try {
			dbDAO = new DatabaseCRUD();
			dbDAO.openDB();

			boolean isCollectionAvailable = dbDAO.connectDBCollection(ProjectConstants.LOGIN_COLLECTION_NAME);

			if(isCollectionAvailable) {
				BasicDBObject searchQuery = dbDAO.getBasicDBObject();
				searchQuery.put(ProjectConstants.LOGIN_COLLECTION_USER_ID_COLUMN, userName);
				searchQuery.put(ProjectConstants.LOGIN_COLLECTION_PASSWORD_COLUMN, userPassword);

				DBCursor cursor = dbDAO.searchData(searchQuery);
				if(null != cursor && cursor.hasNext()) {
					returnText = ProjectConstants.LOGIN_SUCCESS;
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			dbDAO.closeDB();
		}
		return returnText;
	}

	public String signUp() {
		String returnText = ProjectConstants.SIGNUP_FAILURE;
		DatabaseCRUD dbDAO = null;
		try {
			dbDAO = new DatabaseCRUD();
			dbDAO.openDB();

			boolean isCollectionAvailable = dbDAO.connectDBCollection(ProjectConstants.LOGIN_COLLECTION_NAME);

			if(isCollectionAvailable) {
				BasicDBObject insertQuery = dbDAO.getBasicDBObject();
				insertQuery.put(ProjectConstants.LOGIN_COLLECTION_USER_ID_COLUMN, userName);
				insertQuery.put(ProjectConstants.LOGIN_COLLECTION_PASSWORD_COLUMN, userPassword);

				WriteResult result = dbDAO.insertData(insertQuery);
				if(null != result && null == result.getError()) {
					/*
					 * E11000 duplicate key error index: testDb.userLogin.$_id_  dup key: { : "text" }
					 */
					returnText = ProjectConstants.SIGNUP_SUCCESS;
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			dbDAO.closeDB();
		}
		return returnText;
	}
}
