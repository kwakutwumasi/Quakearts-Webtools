/*******************************************************************************
* Copyright (C) 2016 Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com>.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com> - initial API and implementation
 ******************************************************************************/
package com.quakearts.appbase.internal.properties;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.enterprise.inject.Vetoed;

import com.quakearts.appbase.Main;
import com.quakearts.appbase.exception.ConfigurationException;
import com.quakearts.appbase.internal.json.Json;
import com.quakearts.appbase.internal.json.JsonObject;
import com.quakearts.appbase.internal.json.JsonValue;
import com.quakearts.appbase.internal.json.ParseException;
import com.quakearts.appbase.internal.json.JsonObject.Member;

/**A JSON property loader
 * @author kwakutwumasi-afriyie
 *
 */
@Vetoed
public class AppBasePropertiesLoader {
	
	/**Retrieve a list of {@linkplain ConfigurationPropertyMap} objects within the given folder
	 * @param fileLocation the name of the search folder relative to the application root
	 * @param fileSuffix the suffix of files to scan and load
	 * @param appName the name of the module to be displayed when displaying any processing error
	 * @return a list of {@linkplain ConfigurationPropertyMap} objects
	 */
	public List<ConfigurationPropertyMap> getAllConfigurationProperties(String fileLocation, String fileSuffix, String appName){
		List<ConfigurationPropertyMap> configurationProperties = new ArrayList<>();
		
		List<File> configurationFiles = listConfigurationFiles(fileLocation, fileSuffix, appName);
		for(File configurationFile:configurationFiles){
			configurationProperties.add(loadParametersFromFile(configurationFile));
		}
		
		return configurationProperties;
	}
	
	/**Retrieve a list of {@linkplain File} objects within the given folder
	 * @param fileLocation the name of the search folder relative to the application root
	 * @param fileSuffix the suffix of files to scan and load
	 * @param appName the name of the module to be displayed when displaying any processing error
	 * @return a list of {@linkplain File} objects
	 */
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
		} else if(!configurationFilesLocation.exists()){
			configurationFilesLocation.mkdirs();
			Main.log.info("Created "+configurationFilesLocation.getAbsolutePath()
				+" to store "+appName+" files (*"+fileSuffix+")");
		} else {
			throw new ConfigurationException("File "+configurationFilesLocation.getAbsolutePath()+" is not a directory. "
					+ "This location must be a folder to hold configuration files");
		}
		
		return configurationFiles;
	}
	
	/**Load a {@linkplain ConfigurationPropertyMap} from a file
	 * @param configurationFile the {@linkplain File} to return
	 * @return a {@linkplain ConfigurationPropertyMap} object
	 */
	public ConfigurationPropertyMap loadParametersFromFile(File configurationFile){
		try(FileReader reader = new FileReader(configurationFile)) {
			return loadParametersFromReader(configurationFile.getAbsolutePath(), reader);
		} catch (IOException | ParseException e) {
			throw new ConfigurationException("Exception of type " 
					+ e.getClass().getName() 
					+ " was thrown. Message is " 
					+ e.getMessage()
					+ ". Exception occured whiles reading configuration file "
					+ configurationFile.getAbsolutePath(), e);
		}	
	}

	/**Load a {@linkplain ConfigurationPropertyMap} from a {@linkplain Reader}
	 * @param filePath the name of the file to be displayed when displaying any processing error
	 * @param reader a {@linkplain Reader}
	 * @return a {@linkplain ConfigurationPropertyMap} object
	 * @throws IOException
	 */
	public ConfigurationPropertyMap loadParametersFromReader(String filePath, Reader reader)
			throws IOException {
		ConfigurationPropertyMap map = new ConfigurationPropertyMap();
		JsonValue datasourceValue = Json.parse(reader);
		
		if(!datasourceValue.isObject()){
			throw new ConfigurationException("Invalid configuration file "+filePath+". Must be a single json object with name:value pairs.");
		}
		
		extractProperties(filePath, map, datasourceValue.asObject(), null);
		return map;
	}

	private class ParseContext {
		String filePath;
		String path;
		
		ParseContext(String filePath, String path) {
			this.filePath = filePath;
			this.path = path;
		}
		
		public String getFilePath() {
			return filePath;
		}
		public String getPath() {
			return path;
		}
	}
	
	private void extractProperties(String filePath, Map<String, Serializable> map, JsonObject jsonObject, String path) {
		jsonObject.forEach((c)->{
			map.put(c.getName(), extractValue(new ParseContext(filePath, (path!=null?path+".":"")+c.getName()), c.getValue()));
		});
	}

	private Serializable extractValue(ParseContext ctx, JsonValue jsonValue) {
		Serializable value;
		if(jsonValue.isBoolean()){
			value = jsonValue.asBoolean();
		} else if(jsonValue.isNumber()){
			if(jsonValue.toString().matches("[\\d]*\\.[\\d]+"))
				value =  jsonValue.asDouble();
			else if(jsonValue.toString().matches("[\\d]+(e)[\\d]+"))
				value =  jsonValue.asFloat();
			else
				value = jsonValue.asInt();
		} else if(jsonValue.isString()){
			String stringValue = jsonValue.asString();
			if(stringValue.matches("[\\d]+(l)")) {
				value = Long.parseLong(stringValue.substring(0, stringValue.length()-1));
			} else { 						
				value = stringValue;
			}
		} else if(jsonValue.isObject()){
			value = handleSpecialObject(ctx, jsonValue.asObject());
		} else {
			throw new ConfigurationException("Invalid configuration parameter "+ctx.getPath()+" found in "
					+ctx.getFilePath()+". Json type "+jsonValue.getClass().getName()+" is handled.");
		}
		
		return value;
	}

	private Serializable handleSpecialObject(ParseContext ctx, JsonObject object) {
		if(object.isEmpty())
			throw new ConfigurationException("Invalid configuration parameter "+ctx.getPath()+" found in "
					+ctx.getFilePath()+". Must not be empty.");
			
		Serializable value;
		Member objectMember = object.iterator().next();
		JsonValue objectValue = objectMember.getValue();
		String objectName = objectMember.getName();
		String memberPath = ctx.getPath()+"."+objectName;

		switch (objectMember.getName()) {
		case "set":
			value = extractSpecialObjectSet(new ParseContext(ctx.getFilePath(), memberPath), objectValue);
			break;
		case "list":
			value = extractSpecialObjectList(new ParseContext(ctx.getFilePath(), memberPath), objectValue);
			break;
		case "map":
			value = extractSpecialObjectMap(new ParseContext(ctx.getFilePath(), memberPath), objectValue);
			break;
		case "binary":
			value = extractSpecialObjectBinary(new ParseContext(ctx.getFilePath(), memberPath), objectValue);
			break;
		default:
			throw new ConfigurationException("Invalid configuration parameter "+ctx.getPath()+" found in "
					+ctx.getFilePath()+". Conversion not understood: "+objectName);
		}
		
		return value;
	}

	private Serializable extractSpecialObjectList(ParseContext ctx, JsonValue jsonValue) {
		Serializable value;
		if(!jsonValue.isArray())
			throw new ConfigurationException("Invalid configuration parameter "+ctx.getPath()+" found in "
					+ctx.getFilePath()+". Must be a json object.");

		ArrayList<Serializable> arrayList = new ArrayList<>();
		jsonValue.asArray().forEach((v)->{
			arrayList.add(extractValue(ctx, v));
		});
		value = arrayList;
		return value;
	}

	private Serializable extractSpecialObjectSet(ParseContext ctx, JsonValue jsonValue) {
		Serializable value;
		if(!jsonValue.isArray())
			throw new ConfigurationException("Invalid configuration parameter "+ctx.getPath()+" found in "
					+ctx.getFilePath()+". Must be a json object.");
		
		HashSet<Serializable> hashSet = new HashSet<>();
		jsonValue.asArray().forEach((v)->{
			hashSet.add(extractValue(ctx, v));
		});
		value = hashSet;
		return value;
	}

	private Serializable extractSpecialObjectBinary(ParseContext ctx, JsonValue jsonValue) {
		if(!jsonValue.isString())
			throw new ConfigurationException("Invalid configuration parameter "+ctx.getPath()+" found in "
					+ctx.getFilePath()+". Must be a string enclosed by \"\".");

		String base64String = jsonValue.asString();
		
		if(!base64String.matches("(?:[A-Za-z0-9+/]{4})*(?:[A-Za-z0-9+/]{2}==|[A-Za-z0-9+/]{3}=)?$"))
			throw new ConfigurationException("Invalid configuration parameter "+ctx.getPath()+" found in "
					+ctx.getFilePath()+". Must be a valid Base 64 encoded string enclosed by \"\".");

		return Base64.getDecoder().decode(base64String);
	}

	private Serializable extractSpecialObjectMap(ParseContext ctx, JsonValue jsonValue) {
		if(!jsonValue.isObject())
			throw new ConfigurationException("Invalid configuration parameter "+ctx.getPath()
					+" found in "+ctx.getFilePath()+". Must be a json object.");

		HashMap<String, Serializable> subMap = new HashMap<>();
		extractProperties(ctx.getFilePath(), subMap, jsonValue.asObject(), ctx.getPath());
		return subMap;
	}	
}
