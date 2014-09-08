package com.tengen.week1;

import java.io.StringWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;
import freemarker.template.Configuration;
import freemarker.template.Template;

public class SparkPost {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//post request
		final Configuration config = new Configuration();
		config.setClassForTemplateLoading(SparkRoutes.class, "/");
		
				Spark.get(new Route("/") {
			
					@Override
					public Object handle(Request arg0, Response arg1) {
				
						try {
						Map<String,Object> fruitsMap= new HashMap<String,Object>();
						fruitsMap.put("fruits", Arrays.asList("Apple", "banana", "orange","mango"));
						
							Template fruitTemp = config.getTemplate("resources/fruit.ftl");
							StringWriter writer = new StringWriter();
							fruitTemp.process(fruitsMap, writer);
							//System.out.println(writer);
							return writer;
						} catch (Exception e) {
							halt(500);
							e.printStackTrace();
							return null;
						} 
					}
				});
				Spark.post(new Route("/favorite_fruit") {
					
					@Override
					public Object handle(Request req, Response arg1) {
						System.out.println(req.queryParams("fruit"));
					final String fruit = req.queryParams("fruit");
					if(fruit==null)
						return "pick a fruit";
					else 
						return "you picked "+fruit;
					}
				});

	}

}
