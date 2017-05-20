package com.quakearts.webapp.security.jwt.keyprovider.impl;

import java.security.InvalidKeyException;
import java.security.KeyStore.PrivateKeyEntry;
import java.security.interfaces.ECPrivateKey;

import com.quakearts.webapp.security.jwt.keyprovider.KeystoreKeyPairProvider;

public class ECKeystoreKeyPairProvider extends KeystoreKeyPairProvider {

	private int keySize;
	
	public ECKeystoreKeyPairProvider(int keySize) {
		this.keySize = keySize;
	}

	@Override
	protected void validateKeyType(PrivateKeyEntry privateKeyEntry) throws InvalidKeyException {
		if(!"EC".equals(privateKeyEntry.getPrivateKey().getAlgorithm()))
			throw new InvalidKeyException("Key is not a valid EC key");
		ECPrivateKey key;
		//key.getParams().
	}

}
