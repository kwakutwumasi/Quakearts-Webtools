package com.quakearts.webapp.security.jwt.impl;

import com.quakearts.webapp.security.jwt.JWTHeader;
import com.quakearts.webapp.security.jwt.signature.RSASignature;
import com.quakearts.webapp.security.jwt.signature.RSASignature.RSAAlgorithmType;
import com.quakearts.webapp.security.jwt.signature.SignatureBase;

public class RSSigner extends KeyStoreSignerBase {

	private RSAAlgorithmType algorithmType;
	@Override
	public KeyStoreSignerBase setAlgorithim(String algorithm) {
		algorithmType = RSAAlgorithmType.valueOf(algorithm);
		return this;
	}

	@Override
	public KeyStoreSignerBase setParameter(String name, Object parameter) {
		doSetParameter(name, parameter);
		return this;
	}

	@Override
	protected SignatureBase createSignatureInstance() {
		return new RSASignature(algorithmType, alias, file, password, storeType);
	}

	@Override
	protected void setHeaderAlgorithm(JWTHeader header) {
		header.setAlgorithm(algorithmType.toString());
	}

}
