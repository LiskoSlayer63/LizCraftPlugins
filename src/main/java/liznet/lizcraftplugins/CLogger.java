package liznet.lizcraftplugins;

import org.apache.logging.log4j.Logger;

public class CLogger {
	
	private static Logger LOGGER = null;
	
	public static void init(Logger Log){
		LOGGER = Log;
	}
	
	public static void info(String message){
		if(LOGGER != null)
			LOGGER.info(message);
	}
	
	public static void debug(String message){
		if(LOGGER != null)
			LOGGER.debug(message);
	}
	
	public static void warn(String message){
		if(LOGGER != null)
			LOGGER.warn(message);
	}
	
	public static void error(String message){
		if(LOGGER != null)
			LOGGER.error(message);
	}
}
