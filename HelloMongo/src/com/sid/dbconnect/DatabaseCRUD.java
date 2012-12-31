/**
 * 
 */
package com.sid.dbconnect;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.WriteResult;

/**
 * @author Siddharth
 *
 */
public class DatabaseCRUD extends DBConnectionDAO {

	public WriteResult insertData(BasicDBObject document) {
		if(null == collection || null == document) {
			return null;
		}
		return collection.insert(document);
	}
	
	public DBCursor searchData(BasicDBObject searchQuery) {
		if(null == searchQuery) {
			return null;
		}
		return collection.find(searchQuery);
	}
}
