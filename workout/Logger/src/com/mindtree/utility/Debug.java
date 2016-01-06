package com.mindtree.utility;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Debug {
	Logger log=null;
	
	static
	{
        PropertyConfigurator.configure(Config.instance().getString("log4j.file.path", "log4j.properties"));		
	}
	
	public Debug(String loggerName)
	{	
		log = Logger.getLogger(loggerName);
	}
	
	public void trace(Object message)
	{
		StackTraceElement[] stackTraceElement = Thread.currentThread().getStackTrace();
		log.trace(new StringBuilder().append(stackTraceElement[2].getClassName()).append(" ").append(stackTraceElement[2].getMethodName()).append(" ").append(stackTraceElement[2].getLineNumber()).append(" ").append(message).toString());
	}
	public void debug(Object message)
	{
		StackTraceElement[] stackTraceElement = Thread.currentThread().getStackTrace();
		log.debug(new StringBuilder().append(stackTraceElement[2].getClassName()).append(" ").append(stackTraceElement[2].getMethodName()).append(" ").append(stackTraceElement[2].getLineNumber()).append(" ").append(message).toString());
	}
	public void info(String message,Object parms[])
	{
		StackTraceElement[] stackTraceElement = Thread.currentThread().getStackTrace();
		//log.info(new StringBuilder().append(stackTraceElement[2].getClassName()).append(" ").append(stackTraceElement[2].getMethodName()).append(" ").append(stackTraceElement[2].getLineNumber()).append(" ").append(message).toString(),parms);
	}	
	public void info(Object message)
	{
		StackTraceElement[] stackTraceElement = Thread.currentThread().getStackTrace();
		log.info(new StringBuilder().append(stackTraceElement[2].getClassName()).append(" ").append(stackTraceElement[2].getMethodName()).append(" ").append(stackTraceElement[2].getLineNumber()).append(" ").append(message).toString());
	}
	public void warn(Object message)
	{
		StackTraceElement[] stackTraceElement = Thread.currentThread().getStackTrace();
		log.warn(new StringBuilder().append(stackTraceElement[2].getClassName()).append(" ").append(stackTraceElement[2].getMethodName()).append(" ").append(stackTraceElement[2].getLineNumber()).append(" ").append(message).toString());
	}
	public void fatal(Object message)
	{
		StackTraceElement[] stackTraceElement = Thread.currentThread().getStackTrace();
		log.fatal(new StringBuilder().append(stackTraceElement[2].getClassName()).append(" ").append(stackTraceElement[2].getMethodName()).append(" ").append(stackTraceElement[2].getLineNumber()).append(" ").append(message).toString());
	}
	public void error(Object message)
	{
		StackTraceElement[] stackTraceElement = Thread.currentThread().getStackTrace();
		log.error(new StringBuilder().append(stackTraceElement[2].getClassName()).append(" ").append(stackTraceElement[2].getMethodName()).append(" ").append(stackTraceElement[2].getLineNumber()).append(" ").append(message).toString());
	}	
}
