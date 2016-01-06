package com.mindtree.utility;

import java.net.URL;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.apache.commons.lang.StringUtils;

public class  Config 
{   
	private static PropertiesConfiguration propertiesConfiguration = null;

    private static Config instance;
    
  
    
    //static block initialization for exception handling    
    static
    {
        try
        {
            instance = new Config();
        }
        catch(Exception e)
        {
            throw new RuntimeException("Exception occured in creating singleton instance");
        }
    }
    static Debug lessDebug = new Debug("less");
	static Debug detailDebug = new Debug("detail");  
    private Config()
    {
    	String appConfigFile = null;
    	FileChangedReloadingStrategy fileStrategy = null;
    	try
    	{
    		/*<context-param>
    		<param-name>contextConfigLocation</param-name>
    		<param-value>file:${RAPIDAPPSERVER_HOME}/SLA_AppContext.xml</param-value> 
    		</context-param>*/ 
    	
    		Map<String,String> mapEnv = System.getenv();
    		for(Map.Entry< String, String> tempMapEnv : mapEnv.entrySet())
    		{
    			System.out.println(tempMapEnv.getKey()+" : "+tempMapEnv.getValue());
    		}
    		appConfigFile = System.getenv("RAPIDAPPSERVER_HOME");    		
    		if(appConfigFile!=null && appConfigFile.trim().length()!=0)
    		{
    			appConfigFile.concat("mwatchappconf.properties");
    		}
    		else
    		appConfigFile = "/home/logeswaran/workspace-luna/workout/logUtils/mwatchappconf.properties";
    		//appConfigFile = "../conf/mwatchappconf.properties";
    		propertiesConfiguration = new PropertiesConfiguration(appConfigFile);
    		fileStrategy = new FileChangedReloadingStrategy();
    		fileStrategy.setRefreshDelay(getInt("refreshTimeLimit",5000));
    		propertiesConfiguration.setReloadingStrategy(fileStrategy);
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    }
         
    public static Config instance(){
        return instance;
    }
      
	public int getInt(final String key, int defaultValue) 
	{
		return propertiesConfiguration.getInt(key, defaultValue);
	}

	/**
	 * Get a string associated with the given property key
	 * 
	 * @param key
	 *            The property key.
	 * @return The associated string.
	 */
	public String getString(final String key) {
		// beware of List problems, so get the object and convert it to a string
		return asString(propertiesConfiguration.getProperty(key));
	}

	/**
	 * Get a string associated with the given property key
	 * 
	 * @param key
	 *            The property key.
	 * @param defaultValue
	 *            The default value.
	 * @return The associated string.
	 */
	public String getString(final String key, String defaultValue) {
		// beware of List problems, so get the object and convert it to a string
		Object o = propertiesConfiguration.getProperty(key);
		if (o == null) {
			return defaultValue;
		}
		return asString(o);
	}
	
	public List<?> getList(final String key)
	{
		List<?> value;
		value = propertiesConfiguration.getList(key);
		return value;
	}

	/**
	 * commons-configuration automatically parse a comma separated value in key
	 * and return a list, that's not what we want here, we need to conserve the
	 * commas. An appropriate method should be added soon to the API.
	 * 
	 * @param value
	 *            the value to convert, it should be either a String or a List
	 * @return the object as a string.
	 * @throws ClassCastException
	 *             if the object is not a string nor a list.
	 */
	private static String asString(Object value) {
		if (value instanceof List) {
			List<?> list = (List<?>) value;
			value = StringUtils.join(list.iterator(), ",");
		}
		return (String) value;
	}


	/**
	 * Look for a configuration file in the classpath and add it.
	 * 
	 * @param url
	 *            the url of the configuration file to load
	 */
	static void addConfiguration(URL url) throws ConfigurationException
	{
		PropertiesConfiguration configTmp = new PropertiesConfiguration(url);
		Iterator<?> configKeys = propertiesConfiguration.getKeys();
		while (configKeys.hasNext()) {
			String key = (String) configKeys.next();
			String value = (String) configTmp.getProperty(key);
			if (propertiesConfiguration.containsKey(key)) {
				System.out.println("Property " + key + " ("
						+ configTmp.getProperty(key) + ") in file " + url
						+ " override main value (" + propertiesConfiguration.getProperty(key)
						+ ")");
			}
			propertiesConfiguration.addProperty(key, value);
		}
	}

	/**
	 * Set the new properties
	 * @param prefix the prefix or null
	 * @param props the news properties
	 * @throws ConfigurationException
	 */
	public void setProperties(String prefix, Properties props) throws ConfigurationException
	{
		Enumeration<Object> propsEnum = props.keys();
		while (propsEnum.hasMoreElements()) 
		{
			String key = (String) propsEnum.nextElement();
			propertiesConfiguration.setProperty((prefix != null ? prefix + "." : "") + key, props.getProperty(key));
		}
		propertiesConfiguration.save();
	}
	
	public String getSeparator() {
		return System.getProperty("file.separator");
	}
	
	static void less()
	{
		lessDebug.debug("less debug");
		lessDebug.info("less info");	
		lessDebug.info("Ticket ID {} :: SLO from DB :: Response Time {} :: Resolution Time {}", new Object[]{"123", "12:45", "13:00"});		
	}

	static void detail()
	{
		detailDebug.debug("detail debug");
		detailDebug.info("detail info");
	}
	
	public static void main(String[] args) {
		System.out.println(Config.instance().getString("jdbc.driverClassName"));
	
		less();
		detail();
		

		
	}
}
