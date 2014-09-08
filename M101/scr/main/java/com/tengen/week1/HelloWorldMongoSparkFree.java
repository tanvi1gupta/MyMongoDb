package com.tengen.week1;

import java.io.StringWriter;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;
import freemarker.template.Configuration;
import freemarker.template.Template;

public class HelloWorldMongoSparkFree {

	public static void main(String[] args) throws UnknownHostException {
		
		MongoClient client = new MongoClient(new ServerAddress("localhost",27017));
		DB db = client.getDB("test");
		final DBCollection collection = db.getCollection("course");

		final Configuration config = new Configuration();
		config.setClassForTemplateLoading(HelloWorldFreeMarker.class, "/");
		Spark.get(new Route("/") {
			
			@Override
			public Object handle(Request arg0, Response arg1) {
				StringWriter writer = new StringWriter();
				try {
					Template hellot = config.getTemplate("resources/hello.ftl");
				
					DBObject obj = collection.findOne();				
//					//DBobejct ultimately extends LinkedHashMap--> which extends HashMap
					hellot.process(obj, writer);
					System.out.println(writer);
				} catch (Exception e) {
					halt(500);
					e.printStackTrace();
				} 

				return writer;
			}
		});

	}

}
