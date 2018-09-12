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
package com.quakearts.appbase.internal.properties.impl;

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

import com.quakearts.appbase.Main;
import com.quakearts.appbase.exception.ConfigurationException;
import com.quakearts.appbase.internal.json.Json;
import com.quakearts.appbase.internal.json.JsonObject;
import com.quakearts.appbase.internal.json.JsonValue;
import com.quakearts.appbase.internal.json.ParseException;
import com.quakearts.appbase.internal.json.JsonObject.Member;
import com.quakearts.appbase.internal.properties.AppBasePropertiesLoader;
import com.quakearts.appbase.internal.properties.ConfigurationPropertyMap;

/**A JSON property loader
 * @author kwakutwumasi-afriyie
 *
 */
public class AppBasePropertiesLoaderImpl implements AppBasePropertiesLoader {
	
	public AppBasePropertiesLoaderImpl() {
	}
	
	/**Retrieve a list of {@linkplain ConfigurationPropertyMap} objects within the given folder
	 * @param fileLocation the name of the search folder relative to the application root
	 * @param fileSuffix the suffix of files to scan and load
	 * @param appName the name of the module to be displayed when displaying any processing error
	 * @return a list of {@linkplain ConfigurationPropertyMap} objects
	 */
	@Override
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
	@Override
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
	
	/**Convert environment variables into a {@linkplain ConfigurationPropertyMap}.
	 * <br /><br />
	 * <b>Note</b>: Windows OS's and some other environments are not capable of storing variables in 
	 * a case sensitive manner. Implementations may return the variables in upper case format. This 
	 * leads to a situation where CamelCase variables cannot be represented. The solution is to split
	 * CamelCase variables by the '_' character. Ex.: camelCase becomes CAMEL_CASE. Such variables 
	 * are auto detected and changed to CamelCase.
	 * @param prefix the variable prefix
	 * @return a {@linkplain ConfigurationPropertyMap} object
	 */
	@Override
	public ConfigurationPropertyMap loadParametersFromEnvironment(String prefix) {
		StringBuilder builder = new StringBuilder("{");
		System.getenv().forEach((key,value)->{
			if(key.toLowerCase().startsWith(prefix.toLowerCase())) {
				builder.append(builder.length()==1?"":",").append("\"")
					.append(convertKey(key, prefix))
					.append("\"")
					.append(convertValue(value));
			}
		});
		builder.append("}");
		
		JsonValue datasourceValue = Json.parse(builder.toString());
		ConfigurationPropertyMap map = new ConfigurationPropertyMap();
		
		extractProperties("environment variables", map, datasourceValue.asObject(), null);
		return map;
	}
	
	private String convertKey(String key, String prefix) {
		if(key.length()==prefix.length())
			throw new ConfigurationException("Invalid environment variable "
					+key+". Missing variable after prefix");
		
		key = key.substring(prefix.length());
		char prefixDivider = key.charAt(0);
		key = key.substring(1);
		
		if(prefixDivider == '_') {
			key = convertWindowsTypeVariable(key);
		}
		
		return key;
	}

	enum ParseState {
		LOWER,
		UPPER
	}
	
	private String convertWindowsTypeVariable(String key) {
		ParseState parseState=ParseState.LOWER;
		StringBuilder builder = new StringBuilder();
		for(char c:key.toCharArray()) {
			if(c == '_') {
				if(parseState == ParseState.LOWER) {
					parseState=ParseState.UPPER;
				} else if(parseState == ParseState.UPPER) {
					builder.append(".");
					parseState = ParseState.LOWER;
				}
			} else {
				if(parseState == ParseState.UPPER && Character.isLowerCase(c)) {
					c = Character.toUpperCase(c);
				} else if(parseState == ParseState.LOWER && Character.isUpperCase(c)) {
					c = Character.toLowerCase(c);
				}
				parseState=ParseState.LOWER;
				builder.append(c);
			}
		}
		return builder.toString();
	}

	private String convertValue(String value) {
		if(value.startsWith(":"))
			return value;

		if(value.startsWith("{"))
			return ":"+value;

		return new StringBuilder(":\"")
				.append(value).append("\"").toString();
	}

	/**Load a {@linkplain ConfigurationPropertyMap} from a file
	 * @param configurationFile the {@linkplain File} to return
	 * @return a {@linkplain ConfigurationPropertyMap} object
	 */
	@Override
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
	@Override
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
			ConfigurationPropertyMap map = new ConfigurationPropertyMap();
			extractProperties(ctx.getFilePath(), map, object, ctx.getPath());
			value = map;
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
