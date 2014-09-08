package com.tengen.week1;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class HelloWorldFreeMarker {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Configuration config = new Configuration();
		config.setClassForTemplateLoading(HelloWorldFreeMarker.class, "/");
		try {
			Template hellot = config.getTemplate("resources/hello.ftl");
			StringWriter writer = new StringWriter();
			Map<String, Object> hellomap = new HashMap<String, Object>();
			hellomap.put("name","FreeMarker");
			hellot.process(hellomap, writer);
			System.out.println(writer);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TemplateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
