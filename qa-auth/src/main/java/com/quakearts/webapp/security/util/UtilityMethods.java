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
package com.quakearts.webapp.security.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLDecoder;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.quakearts.webapp.security.jwt.internal.Base64;

public class UtilityMethods {

	private static final InitialContext icx = startInitialContext();

	private static InitialContext startInitialContext(){
		InitialContext icx;
		try {
			icx = new InitialContext();
			return icx;
		} catch (NamingException e) {
			return null;
		}
    }
	
	private UtilityMethods() {
	}
	
	public static InputStream getResource(String name){
		return Thread.currentThread().getContextClassLoader().getResourceAsStream(name);
	}
	
	public static OutputStream getOutputstream(String name) throws Exception{
		OutputStream out = null;
		URL url = Thread.currentThread().getContextClassLoader().getResource(name);
		if(url!=null){
			out = new FileOutputStream(URLDecoder.decode(url.getFile(),"UTF-8"));
		}
		return out;
	}

	public static URL getRootDir() throws Exception{
		return Thread.currentThread().getContextClassLoader().getResource(".");
	}

	public static InitialContext getInitialContext() {
		return icx;
	}
	
	public static String base64EncodeWithoutPadding(byte[] toEncode) throws IOException{
		String encoded = Base64.encodeBytes(toEncode, Base64.URL_SAFE);
		if(encoded.endsWith("=") || encoded.endsWith("==")){
			return encoded.substring(0, encoded.indexOf("="));
		}
		
		return encoded;
	}
	
	public static String base64DecodeMissingPadding(String toDecode) throws IOException{
		return Base64.decode(pad(toDecode), Base64.URL_SAFE);
	}

	private static String pad(String toDecode){
		if(toDecode.length() % 4 != 0){
			switch (toDecode.length() % 4) {
			case 3:
				toDecode+="=";
				break;
			case 2:
				toDecode+="==";				
				break;
			default:
				break;
			}
		}
		
		return toDecode;
	}
	
	public static byte[] base64DecodeMissingPaddingToBytes(String toDecode) throws IOException{
		return Base64.decodeToBytes(pad(toDecode), Base64.URL_SAFE);
	}
}

