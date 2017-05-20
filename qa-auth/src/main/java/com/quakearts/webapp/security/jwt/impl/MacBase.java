package com.quakearts.webapp.security.jwt.impl;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public abstract class MacBase {
	
	public byte[] sign(byte[] payload) throws InvalidKeyException, NoSuchAlgorithmException{
		Mac mac = createMacImplementation();
		SecretKeySpec key = getKey();
		
		mac.init(key);
		return mac.doFinal(payload);
	}

	protected abstract SecretKeySpec getKey();
	protected abstract Mac createMacImplementation() throws NoSuchAlgorithmException;
}
