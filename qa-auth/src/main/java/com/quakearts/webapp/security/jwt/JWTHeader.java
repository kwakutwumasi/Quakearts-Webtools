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

public interface JWTHeader {
	public static final String HS256ALGORITHM = "HS256";
	public static final String HS384ALGORITHM = "HS384";
	public static final String HS512ALGORITHM = "HS512";
	public static final String RS256ALGORITHM = "RS256";
	public static final String RS384ALGORITHM = "RS384";
	public static final String RS512ALGORITHM = "RS512";
	public static final String ES256ALGORITHM = "ES256";
	public static final String ES384ALGORITHM = "ES384";
	public static final String ES512ALGORITHM = "ES512";
	public static final String PS256ALGORITHM = "PS256";
	public static final String PS384ALGORITHM = "PS384";
	public static final String PS512ALGORITHM = "PS512";

	JWTHeader setAlgorithm(String algorithm);

	String getAlgorithm();

	JWTHeader setType(String type);

	String getType();
	
	String compact();
	
	void unCompact(String compacted);
}
