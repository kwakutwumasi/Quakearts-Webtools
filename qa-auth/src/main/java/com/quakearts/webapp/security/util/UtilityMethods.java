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

import java.time.Duration;
import java.util.Base64;

import javax.naming.InitialContext;
import javax.naming.NamingException;

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
	
	private UtilityMethods() {}
	
	public static InitialContext getInitialContext() {
		return icx;
	}
	
	public static String base64EncodeWithoutPadding(byte[] toEncode){
		String encoded = Base64.getUrlEncoder().encodeToString(toEncode);
		if(encoded.endsWith("=")){
			return encoded.substring(0, encoded.indexOf('='));
		}
		
		return encoded;
	}
	
	public static String base64DecodeMissingPadding(String toDecode){
		return new String(base64DecodeMissingPaddingToBytes(toDecode));
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
	
	public static byte[] base64DecodeMissingPaddingToBytes(String toDecode){
		return Base64.getUrlDecoder().decode(pad(toDecode));
	}
	
	public static long parseDuration(String durationString) {
		String[] durationStringParts = durationString.split("[\\s]+", 2);

		if (durationStringParts.length == 2 
				&& !durationStringParts[0].trim().isEmpty()
				&& !durationStringParts[1].trim().isEmpty()) {
			int periodAmount = Integer.parseInt(durationStringParts[0].trim());
			String prefix = durationStringParts[1].trim().substring(0, 1).toUpperCase();
			Duration duration = Duration
					.parse("P" + (prefix.equals("H") || prefix.equals("M") || prefix.equals("S") ? "T" : "")
							+ periodAmount + prefix);

			return duration.getSeconds();
		}
		return 0;
	}

}

