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
package com.quakearts.webapp.security.jwt.impl;

import com.quakearts.webapp.security.jwt.JWTHeader;

public class JWTHeaderImpl extends JWTJsonObjectBase implements JWTHeader {
	private static final String JWT = "JWT";
	private static final String TYP = "typ";
	private static final String ALG = "alg";

	public JWTHeaderImpl() {
		jsonObject.set(TYP,JWT);
	}
	
	@Override
	public JWTHeader setAlgorithm(String algorithm) {
		jsonObject.add(ALG, algorithm);
		return this;
	}

	@Override
	public String getAlgorithm() {
		try {
			if (jsonObject.get(ALG) != null)
				return jsonObject.get(ALG).asString();
		} catch (UnsupportedOperationException e) {
		}

		return null;
	}

	@Override
	public JWTHeader setType(String type) {
		jsonObject.add(TYP, type);
		return this;
	}

	@Override
	public String getType() {
		try {
			if (jsonObject.get(TYP) != null)
				return jsonObject.get(TYP).asString();
		} catch (UnsupportedOperationException e) {
		}

		return null;
	}
}
