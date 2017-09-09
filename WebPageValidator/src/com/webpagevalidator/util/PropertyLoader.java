package com.webpagevalidator.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyLoader {
	public String getProperty(String key){
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream input = classLoader.getResourceAsStream("config.properties");
		// ...
		Properties properties = new Properties();
		try {
			properties.load(input);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println("Value for "+key+" is "+properties.getProperty(key));
		return properties.getProperty(key);
	}
}
