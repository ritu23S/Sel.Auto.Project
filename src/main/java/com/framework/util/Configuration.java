package com.framework.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

public class Configuration {
	public static String getConfig(String key) throws Exception {
Properties properties = new Properties();	
		
		File file = new File("config.properties");
		FileInputStream fileInputStream = new FileInputStream(file);
		properties.load(fileInputStream);		
		return properties.getProperty(key);

}
}