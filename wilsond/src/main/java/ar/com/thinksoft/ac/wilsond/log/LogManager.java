package ar.com.thinksoft.ac.wilsond.log;

import java.net.URL;

import org.apache.log4j.Category;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.helpers.Loader;

public class LogManager {
	
	private static Logger log = null;
	
	public Logger getInstance(String clase){
		if(LogManager.log == null){
			LogManager.log = Logger.getLogger(clase); 
			URL url = Loader.getResource("log4jWilsond.properties");
			PropertyConfigurator.configure(url);
		}
		return LogManager.log;
	}
	
	public void setLog(Logger log) {
		LogManager.log = log;
	}

	public  Logger getLog() {
		return log;
	}

	public static Category getInstance(Class<?> class1) {
		if(LogManager.log == null){
			LogManager.log = Logger.getLogger(class1);
			URL url = Loader.getResource("log4jWilsond.properties");
			PropertyConfigurator.configure(url);
		}
		return LogManager.log;
	}


}
