package com.tengen.week2;

import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class FindAndModify {

	private static DBCollection createCollection() throws UnknownHostException {
		MongoClient client = new MongoClient();
		DB db = client.getDB("course");
		return db.getCollection("findModifyTest");
	}

	private static void printCollection(DBCollection collection) {
		DBCursor cursor = collection.find();
		try
		{
			while(cursor.hasNext())
			{
				DBObject cur = cursor.next();
				System.out.println(cur);;
			}
		}
		finally
		{
			cursor.close();
		}
	}


	public static void main(String[] args) throws UnknownHostException {
	
		DBCollection collection = createCollection();
		collection.drop();
		
		final String counterId = "abc";
		int first;
		int numNeeded;
		
		numNeeded = 2;
		first = getRange(counterId, numNeeded, collection);
		System.out.println("range: "+ first+ " - "+(first+numNeeded-1));
		
		numNeeded = 3;
		first = getRange(counterId, numNeeded, collection);
		System.out.println("range: "+ first+ " - "+(first+numNeeded-1));
		
		numNeeded = 10;
		first = getRange(counterId, numNeeded, collection);
		System.out.println("range: "+ first+ " - "+(first+numNeeded-1));
	}

	private static int getRange(String counterId, int numNeeded, DBCollection collection) {

		//select , what all fields you want,  sort, doc delete after update, update , want value after update , upsert
		DBObject doc =collection.findAndModify(new BasicDBObject("_id", counterId), null, null, false, new BasicDBObject("$inc", new BasicDBObject("counter", numNeeded)), 
				true, true);
		return (Integer)doc.get("counter") - numNeeded + 1;
	}


}
