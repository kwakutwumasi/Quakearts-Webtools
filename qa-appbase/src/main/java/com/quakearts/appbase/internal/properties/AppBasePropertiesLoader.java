package com.quakearts.appbase.internal.properties;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.quakearts.appbase.Main;
import com.quakearts.appbase.exception.ConfigurationException;
import com.quakearts.appbase.internal.json.Json;
import com.quakearts.appbase.internal.json.JsonObject;
import com.quakearts.appbase.internal.json.JsonValue;
import com.quakearts.appbase.internal.json.ParseException;
import com.quakearts.appbase.internal.json.JsonObject.Member;

public class AppBasePropertiesLoader {
	
	public List<Map<String, Serializable>> getAllConfigurationProperties(String fileLocation, String fileSuffix, String appName){
		List<Map<String, Serializable>> configurationProperties = new ArrayList<>();
		
		List<File> configurationFiles = listConfigurationFiles(fileLocation, fileSuffix, appName);
		for(File configurationFile:configurationFiles){
			configurationProperties.add(loadParametersFromFile(configurationFile));
		}
		
		return configurationProperties;
	}
	
	public List<File> listConfigurationFiles(String fileLocation, String fileSuffix, String appName) {
		List<File> configurationFiles = new ArrayList<>();
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		if(classLoader instanceof URLClassLoader){
			URL[] urls = ((URLClassLoader) classLoader).getURLs();
			for(URL url:urls){
				try {
					File directoryFile = new File(url.toURI());
					if(directoryFile.exists() && directoryFile.isDirectory()){
						for(File file:directoryFile.listFiles()){
							if(file.exists() 
									&& file.isFile() 
									&& file.getName().endsWith(fileSuffix))
								configurationFiles.add(file);
						}
					}
				} catch (URISyntaxException e) {
					//Skip
				}
			}
		}
		
		File configurationFilesLocation = new File(fileLocation);
		if(configurationFilesLocation.exists() && configurationFilesLocation.isDirectory()){
			for(File file:configurationFilesLocation.listFiles()){
				if(file.exists() 
						&& file.isFile() 
						&& file.getName().endsWith(fileSuffix))
					configurationFiles.add(file);
			}
		} else if(!configurationFilesLocation.isFile()){
			configurationFilesLocation.mkdirs();
			Main.log.info("Created "+configurationFilesLocation.getAbsolutePath()
				+" to store "+appName+" files (*"+fileSuffix+")");
		} else {
			Main.log.warn("File "+configurationFilesLocation.getAbsolutePath()+" is not a directory. "
					+ "This location must be a folder to hold configuration files");
		}
		
		return configurationFiles;
	}
	
	public Map<String, Serializable> loadParametersFromFile(File configurationFile){
		Map<String, Serializable> map = new HashMap<>();
		try(FileReader reader = new FileReader(configurationFile)) {
			JsonValue datasourceValue = Json.parse(reader);
			
			if(!datasourceValue.isObject()){
				throw new ConfigurationException("Invalid configuration file "+configurationFile.getAbsolutePath()+". Must be a single json object with name:value pairs.");
			}
			
			datasourceValue.asObject().forEach((c)->{
				if(c.getValue().isBoolean()){
					map.put(c.getName(), c.getValue().asBoolean());
				} else if(c.getValue().isNumber()){
					if(c.getValue().toString().contains("."))
						map.put(c.getName(), c.getValue().asDouble());
					else
						map.put(c.getName(), new Double(c.getValue().asDouble()).intValue());
				} else if(c.getValue().isString()){
					map.put(c.getName(), c.getValue().asString());
				} else if(c.getValue().isObject()){
					JsonObject object = c.getValue().asObject();
					if(object.isEmpty())
						throw new ConfigurationException("Invalid configuration parameter "+c.getName()+" found in "+configurationFile.getAbsolutePath()+". Must not be empty.");
						
					Member objectMember = object.iterator().next();
					Serializable value;
					switch (objectMember.getName()) {
					case "double":
						value = objectMember.getValue().asDouble();
						break;
					case "float":
						value = objectMember.getValue().asFloat();
						break;
					case "chars":
						value = objectMember.getValue().asString().toCharArray();
						break;
					case "char":
						value = objectMember.getValue().asString().toCharArray()[0];
						break;
					case "long":
						value = objectMember.getValue().asLong();
						break;
					default:
						throw new ConfigurationException("Invalid configuration parameter "+c.getName()+" found in "+configurationFile.getAbsolutePath()+". Conversion not understood: "+objectMember.getName());
					}
					map.put(c.getName(), value);
				}
			});
		} catch (IOException | ParseException e) {
			throw new ConfigurationException("Exception of type " 
					+ e.getClass().getName() 
					+ " was thrown. Message is " 
					+ e.getMessage()
					+ ". Exception occured whiles reading configuration file "
					+ configurationFile.getAbsolutePath(), e);
		}
		
		return map;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T get(String name, Class<T> clazz, Map<String, Serializable> configurationParameters){
		return (T) configurationParameters.get(name);
	}
	
}
