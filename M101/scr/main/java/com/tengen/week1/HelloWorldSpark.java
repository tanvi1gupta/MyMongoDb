package com.tengen.week1;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

public class HelloWorldSpark {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		Spark.get(new Route("/") {
			
			@Override
			public Object handle(Request arg0, Response arg1) {
				return "Hello World from spark";
			}
		});
	}

}
