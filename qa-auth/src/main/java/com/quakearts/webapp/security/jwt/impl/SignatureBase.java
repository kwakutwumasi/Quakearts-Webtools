package com.quakearts.webapp.security.jwt.impl;

import java.security.InvalidKeyException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;

public abstract class SignatureBase {

	public byte[] sign(byte[] payload) throws InvalidKeyException, SignatureException {
		Signature signature = createSignatureImplementation();
		PrivateKey key = getPrivateKey();
		signature.initSign(key);
		signature.update(payload);
		
		return signature.sign();
	}
	
	protected abstract PrivateKey getPrivateKey();

	protected abstract Signature createSignatureImplementation();

	public void verify(byte[] signatureData) throws InvalidKeyException, SignatureException {
		Signature signature = createSignatureImplementation();
		PublicKey key = getPublicKey();
		signature.initVerify(key);
		signature.verify(signatureData);		
	}

	protected abstract PublicKey getPublicKey();
}
