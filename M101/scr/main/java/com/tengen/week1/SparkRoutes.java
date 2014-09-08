package com.tengen.week1;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

public class SparkRoutes {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		Spark.get(new Route("/") {

			@Override
			public Object handle(Request arg0, Response arg1) {
				return "Hello World ";
			}
		});

		Spark.get(new Route("/test") {

			@Override
			public Object handle(Request arg0, Response arg1) {
				return "Hello World from test";
			}
		});

		//get request
		Spark.get(new Route("/echo/:thing") {

			@Override
			public Object handle(Request request, Response arg1) {
				return request.params(":thing");
			}
		});
		
		
		
		

	}

}
