package com.tengen.week2;

import java.util.Arrays;
import java.util.Date;

import com.mongodb.BasicDBObject;

public class DocumentRepresentationTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		BasicDBObject doc = new BasicDBObject();
		doc.put("userName", "jyemin");
		doc.put("birthdate", new Date(234565678));
		doc.put("programmer",  true);
		doc.put("age", 8);
		doc.put("languages", Arrays.asList("java", "c++"));
		doc.put("address", new BasicDBObject("street", "20 Main").append("town", "WestFild").append("zip", "56789"));
		}

}
