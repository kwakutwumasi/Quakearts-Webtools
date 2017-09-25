package com.quakearts.appbase.internal.properties;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import com.quakearts.appbase.Main;
import com.quakearts.appbase.exception.ConfigurationException;
import com.quakearts.appbase.internal.json.Json;
import com.quakearts.appbase.internal.json.JsonObject;
import com.quakearts.appbase.internal.json.JsonValue;
import com.quakearts.appbase.internal.json.ParseException;
import com.quakearts.appbase.internal.json.JsonObject.Member;

public class AppBasePropertiesLoader {
	
	public List<ConfigurationPropertyMap> getAllConfigurationProperties(String fileLocation, String fileSuffix, String appName){
		List<ConfigurationPropertyMap> configurationProperties = new ArrayList<>();
		
		List<File> configurationFiles = listConfigurationFiles(fileLocation, fileSuffix, appName);
		for(File configurationFile:configurationFiles){
			configurationProperties.add(loadParametersFromFile(configurationFile));
		}
		
		return configurationProperties;
	}
	
	public List<File> listConfigurationFiles(String fileLocation, String fileSuffix, String appName) {
		List<File> configurationFiles = new ArrayList<>();		
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
	
	public ConfigurationPropertyMap loadParametersFromFile(File configurationFile){
		ConfigurationPropertyMap map = new ConfigurationPropertyMap();
		try(FileReader reader = new FileReader(configurationFile)) {
			JsonValue datasourceValue = Json.parse(reader);
			
			if(!datasourceValue.isObject()){
				throw new ConfigurationException("Invalid configuration file "+configurationFile.getAbsolutePath()+". Must be a single json object with name:value pairs.");
			}
			
			datasourceValue.asObject().forEach((c)->{
				if(c.getValue().isBoolean()){
					map.put(c.getName(), c.getValue().asBoolean());
				} else if(c.getValue().isNumber()){
					if(c.getValue().toString().matches("[\\d]*\\.[\\d]+"))
						map.put(c.getName(), c.getValue().asDouble());
					else if(c.getValue().toString().matches("[\\d]+(e)[\\d]+"))
						map.put(c.getName(), c.getValue().asFloat());
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
					case "long":
						if(!objectMember.getValue().isNumber())
							throw new ConfigurationException("Invalid configuration parameter "+c.getName()+" found in "+configurationFile.getAbsolutePath()+". Must be a number.");

						value = objectMember.getValue().asLong();
						break;
					case "binary":
						if(!objectMember.getValue().isString())
							throw new ConfigurationException("Invalid configuration parameter "+c.getName()+" found in "+configurationFile.getAbsolutePath()+". Must be a string enclosed by \"\".");

						String base64String = objectMember.getValue().asString();
						
						if(!base64String.matches("(?:[A-Za-z0-9+/]{4})*(?:[A-Za-z0-9+/]{2}==|[A-Za-z0-9+/]{3}=)?$"))
							throw new ConfigurationException("Invalid configuration parameter "+c.getName()+" found in "+configurationFile.getAbsolutePath()+". Must be a valid Base 64 encoded string enclosed by \"\".");

						value = Base64.getDecoder().decode(base64String);
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
}
