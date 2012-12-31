/**
 * 
 */
package com.sid.dbconnect;

import java.io.IOException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import com.mongodb.MongoException;

/**
 * @author Siddharth
 *
 */
public class DBConnectionDAO {

	protected DB db;
	protected DBCollection collection;
	protected Mongo mongo;
	
	public void openDB() throws MongoException, IOException {
		// connect to mongoDB, ip and port number
		mongo = new Mongo("localhost", 27017);

		// get database from MongoDB,
		// if database doesn't exists, mongoDB will create it automatically
		db = mongo.getDB("testDb");
	}

	public boolean connectDBCollection(String collectionName) {
		if(null == collectionName || collectionName.isEmpty()) {
			return false;
		}
		collection = db.getCollection(collectionName);
		
		return true;
	}
	
	public BasicDBObject getBasicDBObject() {
		return new BasicDBObject();
	}
	
	public void closeDB() {
		mongo.close();
	}

}
