package com.quakearts.webapp.security.jwt.impl;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;

import com.quakearts.webapp.security.jwt.signature.SignatureBase;

public abstract class KeyStoreSignerBase extends SignerBase {

	public static final String STORE_TYPEPARAMETER = "storeType";
	public static final String PASSWORDPARAMETER = "password";
	public static final String FILEPARAMETER = "file";
	public static final String ALIASPARAMETER = "alias";
	protected SignatureBase signature;
	protected String alias;
	protected String file;
	protected char[] password;
	protected String storeType;

	protected void doSetParameter(String name, Object parameter) {
		switch (name) {
		case ALIASPARAMETER:
			alias = parameter.toString();
			break;
		case FILEPARAMETER:
			file = parameter.toString();
			break;
		case PASSWORDPARAMETER:
			password = parameter.toString().toCharArray();
			break;
		case STORE_TYPEPARAMETER:
			storeType = parameter.toString();
			break;
		default:
		}
	}

	protected String getNullParameter() {
		return (alias == null? "alias;" : "") 
				+ (file == null? "file;" : "") 
				+ (password == null? "password;" : "")
				+ (storeType == null? "storeType;" : "");
	}

	@Override
	protected byte[] doSigning(String prepare) throws InvalidKeyException, NoSuchAlgorithmException, SignatureException {
		return getSignature().sign(prepare.getBytes());
	}

	private SignatureBase getSignature() throws SignatureException {
		if(signature == null){
			if(alias == null || file == null || password == null || storeType == null)
				throw new SignatureException("Incomplete setup. Missing: "+getNullParameter());
			
			signature = createSignatureInstance();
		}
		
		return signature;
	}

	protected abstract SignatureBase createSignatureInstance();
	
	@Override
	protected void doVerification(byte[] payload, byte[] signatureDecoded) throws InvalidKeyException, 
		SignatureException, NoSuchAlgorithmException {
		getSignature().verify(payload, signatureDecoded);
	}

}
