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
package com.quakearts.webapp.security.jwt;

public interface JWTClaims extends Iterable<JWTClaims.Claim> {
	JWTClaims setSubject(String subject);
	String getSubject();
	JWTClaims setIssuer(String issuer);
	String getIssuer();
	JWTClaims setAudience(String audience);
	String getAudience();
	JWTClaims setExpiry(long time);
	long getExpiry();
	JWTClaims setNotBefore(long time);
	long getNotBefore();
	long getIssuedAt();
	JWTClaims setIssuedAt(long time);
	JWTClaims addPrivateClaim(String name, String value);
	String getPrivateClaim(String name);
	String compact();
	void unCompact(String compacted);
	
	public static class Claim {
		private String name;
		private String value;
		
		public Claim(String name, String value) {
			this.name = name;
			this.value = value;
		}
		
		public String getName() {
			return name;
		}
		public String getValue() {
			return value;
		}
	}
}
