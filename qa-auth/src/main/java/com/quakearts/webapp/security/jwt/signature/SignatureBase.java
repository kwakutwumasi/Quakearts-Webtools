package com.quakearts.webapp.security.jwt.signature;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;

public abstract class SignatureBase {

	public byte[] sign(byte[] payload) throws InvalidKeyException, 
		SignatureException, NoSuchAlgorithmException {
		Signature signature = createSignatureImplementation();
		PrivateKey key = getPrivateKey();
		signature.initSign(key);
		signature.update(payload);
		
		return signature.sign();
	}

	protected abstract Signature createSignatureImplementation() throws NoSuchAlgorithmException;
	
	protected abstract PrivateKey getPrivateKey() throws InvalidKeyException;

	public void verify(byte[] payloadData, byte[] signatureData) throws InvalidKeyException, 
		SignatureException, NoSuchAlgorithmException {
		Signature signature = createSignatureImplementation();
		PublicKey key = getPublicKey();
		signature.initVerify(key);
		signature.update(payloadData);
		signature.verify(signatureData);		
	}

	protected abstract PublicKey getPublicKey() throws InvalidKeyException;;
}
