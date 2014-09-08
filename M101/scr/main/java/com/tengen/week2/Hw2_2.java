package com.tengen.week2;

import java.net.UnknownHostException;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.QueryBuilder;

public class Hw2_2 {
	private static DBCollection createCollection() throws UnknownHostException {
		MongoClient client = new MongoClient();
		DB db = client.getDB("students");
		return db.getCollection("grades");
	}

	private static void printCollection(DBCollection collection) {
		DBCursor cursor = collection.find();
		System.out.println(collection.count());
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
//		/printCollection(collection);
		
		QueryBuilder builder = QueryBuilder.start("type").is("homework");
		DBCursor cursor = collection.find(builder.get()).sort(new BasicDBObject("student_id",1).append("score",1));
		int count=0;
		try
		{
			while(cursor.hasNext())
			{
				DBObject cur = cursor.next();
				if(count%2==0)
					collection.remove(cur);
				count++;
			}
		}
		finally
		{
			cursor.close();
		}
		printCollection(collection);
				
		
		
	}


}
