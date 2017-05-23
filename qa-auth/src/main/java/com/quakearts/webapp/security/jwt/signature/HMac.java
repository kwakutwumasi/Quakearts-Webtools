package com.quakearts.webapp.security.jwt.signature;

import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class HMac extends MacBase {

	private byte[] key;
	public HSAlgorithmType algorithmType;
	
	public static enum HSAlgorithmType {
		HS256("HmacSHA256"),
		HS384("HmacSHA384"),
		HS512("HmacSHA512");
		
		private String algorithm;
		
		private HSAlgorithmType(String algorithm) {
			this.algorithm = algorithm;
		}

		public String getAlgorithm() {
			return algorithm;
		}
	}
	
	public HMac(HSAlgorithmType algorithmType, byte[] key) {
		this.key = key;
		this.algorithmType = algorithmType;
	}

	@Override
	protected SecretKeySpec getKey() {
		return new SecretKeySpec(key, algorithmType.getAlgorithm());
	}

	@Override
	protected Mac createMacImplementation() throws NoSuchAlgorithmException {
		return Mac.getInstance(algorithmType.getAlgorithm());
	}

}
