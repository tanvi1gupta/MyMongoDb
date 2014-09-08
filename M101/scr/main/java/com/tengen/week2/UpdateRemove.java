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

public class UpdateRemove {

	/**
	 * @param args
	 * @throws UnknownHostException 
	 */
	
	private static DBCollection createCollection() throws UnknownHostException {
		MongoClient client = new MongoClient();
		DB db = client.getDB("course");
		return db.getCollection("names");
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
	
		MongoClient client = new MongoClient();
		DB courseDB = client.getDB("course");
		DBCollection collection = createCollection();
		collection.drop();
		List<String> names = Arrays.asList("alice","bob","cathy","david","ethan");
		for(String name:names)
		{
			collection.insert(new BasicDBObject("_id", name));
		}
		
		//wholesale update of the document
		collection.update(new BasicDBObject("_id","alice"), new BasicDBObject("age", 24));
		
		//using $set, will not remove other key,value pair already existing in the doc
		collection.update(new BasicDBObject("_id","alice"),new BasicDBObject("$set", new BasicDBObject("gender","F")));
		
		//frank will not be in the collection
		collection.update(new BasicDBObject("_id","frank"),new BasicDBObject("$set", new BasicDBObject("gender","F")));
		
		//3rd parameter is upsert
		collection.update(new BasicDBObject("_id","frank"),new BasicDBObject("$set", new BasicDBObject("gender","M")), true, false);
		
		//4th parameter is multi
		collection.update(new BasicDBObject(),new BasicDBObject("$set", new BasicDBObject("title","DR")), false , true);
		
		collection.remove(new BasicDBObject("_id", "alice"));
		
		printCollection(collection);
	}

}
