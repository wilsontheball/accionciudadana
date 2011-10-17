package ar.com.thinksoft.ac.webac.logging;

import java.net.URL;

import org.apache.log4j.Category;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.helpers.Loader;

public class LogFwk{
	
	private static Logger log = null;
	
	public Logger getInstance(String clase){
		if(LogFwk.log == null){
			LogFwk.log = Logger.getLogger(clase); 
			URL url = Loader.getResource("log4j.properties"); 
			PropertyConfigurator.configure(url);
		}
		return LogFwk.log;
	}
	
	public void setLog(Logger log) {
		LogFwk.log = log;
	}

	public  Logger getLog() {
		return log;
	}

	public static Category getInstance(Class<?> class1) {
		if(LogFwk.log == null){
			LogFwk.log = Logger.getLogger(class1); 
			URL url = Loader.getResource("log4j.properties"); 
			PropertyConfigurator.configure(url);
		}
		return LogFwk.log;
	}

}
