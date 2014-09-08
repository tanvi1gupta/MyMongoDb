package com.tengen.week2;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class InsertTest {

	/**
	 * @param args
	 * @throws UnknownHostException 
	 */
	public static void main(String[] args) throws UnknownHostException {
//		MongoClient client = new MongoClient();
//		DB courseDB = client.getDB("course");
//		DBCollection collection = courseDB.getCollection("insertTest");
//		
//		collection.drop();
//		for(int i=0;i<10;i++)
//			collection.insert(new BasicDBObject("x", new Random().nextInt(100)));
//		
//		System.out.println("Find One: ");
//		DBObject doc = collection.findOne();
//		System.out.println(doc);
//		
//		System.out.println("\nFind All");
//		DBCursor cursor = collection.find();
//		try
//		{
//			while(cursor.hasNext())
//			{
//				DBObject cur = cursor.next();
//				System.out.println(cur);;
//			}
//		}
//		finally
//		{
//			cursor.close();
//		}
//		
//		
//		System.out.println("\nCount");
//		
//		System.out.println(collection.count());
		//doc.removeField("_id");  //removes _id field
		test();
//		MongoClient c =  new MongoClient();
//        DB db = c.getDB("test");
//        DBCollection animals = db.getCollection("animals");
//        animals.drop();
//        BasicDBObject animal = new BasicDBObject("animal", "monkey");
//
//        animals.insert(animal);
//        System.out.println(animals.count());
//        animal.removeField("animal");
//        animal.append("animal", "cat");
//        animals.insert(animal);
//        System.out.println(animals.count());
//        animal.removeField("animal");
//        animal.append("animal", "lion");
//        animals.insert(animal);
//        System.out.println(animals.count());
		
	}

	private static void test() throws UnknownHostException {
		MongoClient c =  new MongoClient();
        DB db = c.getDB("test");
        DBCollection album = db.getCollection("album");
        DBCollection image = db.getCollection("image");
        DBObject al=null;
        int count =0;
        long countImage = image.count();
        for(long i=0;i<countImage;i++){
        	List<Long> list = new ArrayList<Long>();
        	list.add(i);
        	al = new BasicDBObject();
        	al.put("images", new BasicDBObject("$in", list));
        	DBCursor cur = album.find(al);
        	//System.out.println(cur);
        	int flag = 0;
        	while(cur.hasNext())
        	{
        		//System.out.println(cur.next());
        		flag = 1;
        		break;
        	}
        	if(flag==0)
        	{
        		count++;
        		//System.out.println(i);
        		image.remove(new BasicDBObject("_id", i));
        	}
        		
        }
        System.out.println("count "+count);
	}
	
	

}
