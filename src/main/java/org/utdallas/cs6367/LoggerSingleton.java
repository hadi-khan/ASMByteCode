package org.utdallas.cs6367;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LoggerSingleton {
	
	private static LoggerSingleton logger_instance = null;
	private Logger logger = null;
	private Handler fileHandler;
	
	private LoggerSingleton() {
		
		logger = Logger.getLogger("codeCoverageLogger");
		
		try {
			fileHandler = new FileHandler("./logfile.log"); //TODO
			SimpleFormatter simple = new SimpleFormatter();
			fileHandler.setFormatter(simple);
			
			logger.addHandler(fileHandler);
		} catch (IOException e) {
			//TODO
		}
	}
	
	public static LoggerSingleton getInstance() {
		if (logger_instance == null) {
			logger_instance = new LoggerSingleton();
		}
		
		return logger_instance;
	}
	
	
	public void logMessage(String message) {
		logger.info(message);
	}
}
