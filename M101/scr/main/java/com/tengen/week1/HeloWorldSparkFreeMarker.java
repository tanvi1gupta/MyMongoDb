package com.tengen.week1;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

public class HeloWorldSparkFreeMarker {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		final Configuration config = new Configuration();
		config.setClassForTemplateLoading(HelloWorldFreeMarker.class, "/");
		Spark.get(new Route("/") {
			
			@Override
			public Object handle(Request arg0, Response arg1) {
				StringWriter writer = new StringWriter();
				try {
					Template hellot = config.getTemplate("resources/hello.ftl");
					Map<String, Object> hellomap = new HashMap<String, Object>();
					hellomap.put("name","FreeMarker");
					hellot.process(hellomap, writer);
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
