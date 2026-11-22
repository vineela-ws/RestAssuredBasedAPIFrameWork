package com.api.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Properties;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

public class ConfigManager {
	// WAP to read the properties files from
	// src/test/resources/config/config.properties

	private static Properties prop = new Properties();
	private static String path = "config/config.properties";
	private static String env;
	
	private ConfigManager() {
		//Private Constructor 
	}

	static {
		env = System.getProperty("env","qa");
		env = env.toLowerCase().trim();
		System.out.println("Running Test in Env"+env);

		switch (env) {
		    case "dev" -> path = "config/config.dev.properties";
		    case "qa"  -> path = "config/config.dev.properties";
		    case "uat"  -> path = "config/config.dev.properties";
		    case "default"  -> path = "config/config.dev.properties";
		    
		}
		
       InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
       
       if(input == null) {
    	   throw new RuntimeException("Cannot Find the file at the path "+ path);
       }

		// Create FileReader and load properties
		try {
			prop.load(input);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static String getProperty(String key) throws IOException {
		return prop.getProperty(key);
	}

}
