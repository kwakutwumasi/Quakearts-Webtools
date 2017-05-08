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
package com.quakearts.tools.test.generator.primitives;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.quakearts.tools.test.generator.Generator;
import com.quakearts.tools.test.generator.GeneratorBase;
import com.quakearts.tools.test.generator.exception.GeneratorException;
import com.quakearts.tools.test.generator.primitives.configuration.AnnotationPropertyConsumer;
import com.quakearts.tools.test.generator.primitives.configuration.GenerateWith;

public abstract class StringRandomGenerator<T> extends GeneratorBase<T> implements AnnotationPropertyConsumer {

	private static final char[] allSymbols= "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ01234567890!@#$%^&*()_-+={}[]\\|;:'\",.<>/?`~".toCharArray();
	private static final char[] alphaNumeric= "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ01234567890".toCharArray();
	private static final char[] alphabet= "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
	private static final char[] numeric= "01234567890".toCharArray();

	private char[] charset = allSymbols;
	private static final Properties properties = new Properties();
	private static Map<String, String[]> randomStringArrays = new HashMap<>();

	static {
		InputStream in = StringRandomGenerator.class.getClassLoader().getResourceAsStream("random.strings");
		if(in != null){
			try {
				properties.load(in);
			} catch (IOException e) {
				throw new RuntimeException("Unable to load random.strings");
			}
			
			for(String key:properties.stringPropertyNames()){
				if(key.endsWith(".delim"))
					continue;
				String randomStrings = properties.getProperty(key);
				String delimiter = ";";
				if(properties.containsKey(key+".delim")){
					delimiter = properties.getProperty(key+".delim");
				}
				randomStringArrays.put(key, randomStrings.split(delimiter));
			}
		}
	}
	
	private String prefix, suffix;
	private String[] randomStringArray;
	private int length = 10;

	@Override
	public Generator<?> configureFromAnnotations(GenerateWith generatorProperty) {
		if(!generatorProperty.suffix().isEmpty())
			suffix = generatorProperty.suffix();

		if(!generatorProperty.prefix().isEmpty())
			prefix = generatorProperty.prefix();

		if(generatorProperty.length()>0)
			length = generatorProperty.length();
		
		if(generatorProperty.strings().length>0)
			randomStringArray = generatorProperty.strings();
		else
			randomStringArray = null;
		
		if(!generatorProperty.charset().isEmpty())
			changeCharset(generatorProperty.charset());
		
		return this;
	}
	
	public StringRandomGenerator<T> useField(String field){
		super.useField(field);		
		setUp();
		return this;
	}

	public StringRandomGenerator<T> usePrefix(String prefix){
		this.prefix = prefix;
		return this;
	}

	public StringRandomGenerator<T> useSuffix(String suffix){
		this.suffix = suffix;
		return this;
	}

	public StringRandomGenerator<T> useLength(int length){
		this.length = length;
		return this;
	}

	public StringRandomGenerator<T> useCharset(String name){
		changeCharset(name);
		return this;
	}

	protected String generateRandomString() {
		String randomString;
		
		if(randomStringArray != null){			
			randomString = randomStringArray[Math.abs(random.nextInt(randomStringArray.length))];
		} else {
			StringBuilder builder = new StringBuilder();
			for(int count = 0; count < length; count++){
				builder.append(charset[random.nextInt(charset.length)]);
			}
			
			randomString = builder.toString();
		}
		
		return (prefix!=null?prefix:"") + randomString+(suffix!=null?suffix:"");
	}

	private void setUp(){
		if(fieldName!=null)
			randomStringArray = randomStringArrays.get(fieldName);
		else
			randomStringArray = null;
		
		if(exists("length")){
			try {
				length = Integer.parseInt(get("length"));
			} catch (NumberFormatException e) {
				throw new GeneratorException("Invalid number for "+fieldName+".length: "+properties.getProperty(fieldName+".length"), e);
			}
		}
		
		if(exists("suffix")){
			suffix = get("suffix");
		}
		
		if(exists("prefix")){
			prefix = get("prefix");
		}
		
		if(exists("charset")){
			changeCharset(get("charset"));
		}
	}
	
	private void changeCharset(String name) {
		switch (name) {
			case "all":
				charset = allSymbols;
				break;
			case "alphaNumeric":
				charset = alphaNumeric;
				break;
			case "alphabet":
				charset = alphabet;
				break;
			case "numeric":
				charset = numeric;
				break;
			default:
				charset = allSymbols;
				break;
		}
	}

	private boolean exists(String property){
		if(fieldName==null)
			return false;
		
		return properties.containsKey(fieldName+"."+property);
	}

	private String get(String property){
		return properties.getProperty(fieldName+"."+property);
	}

}
