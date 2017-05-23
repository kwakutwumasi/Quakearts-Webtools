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
