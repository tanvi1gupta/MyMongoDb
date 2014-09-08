package com.tengen.week2;

import java.net.UnknownHostException;
import java.util.Random;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.QueryBuilder;

public class DotNotationTest {

	/**
	 * @param args
	 * @throws UnknownHostException 
	 */
	public static void main(String[] args) throws UnknownHostException {
		MongoClient client = new MongoClient();
		DB courseDB = client.getDB("course");
		DBCollection lines = courseDB.getCollection("dotNotationTest");
		lines.drop();
		Random rand = new Random();
		for(int i=0;i<10;i++)
		{
			lines.insert(
					new BasicDBObject("_id", i)
					.append("start",
							new BasicDBObject("x",rand.nextInt(90)+10 )
								.append("y", rand.nextInt(90)+10 ) )
					.append("end", 
							new BasicDBObject("x",rand.nextInt(90)+10 )
								.append("y", rand.nextInt(90)+10 ) )
					);
		
		}
		//all doc where start. x>50
		DBObject builder = QueryBuilder.start("start.x").greaterThan(50).get();

		
		System.out.println("\nCount");
		long count = lines.count(builder);
		System.out.println(count);
		
		System.out.println("\nFind All");
		DBCursor cursor = lines.find(builder, new BasicDBObject("start.y", true).append("start.x", true));
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

}
