package com.quakearts.webapp.security.jwt.impl;

import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public abstract class HMac extends MacBase {

	private byte[] key;
	
	public HMac(byte[] key) {
		this.key = key;
	}

	@Override
	protected SecretKeySpec getKey() {
		return new SecretKeySpec(key, getSignatureName());
	}

	protected abstract String getSignatureName();

	@Override
	protected Mac createMacImplementation() throws NoSuchAlgorithmException {
		return Mac.getInstance(getSignatureName());
	}

}
