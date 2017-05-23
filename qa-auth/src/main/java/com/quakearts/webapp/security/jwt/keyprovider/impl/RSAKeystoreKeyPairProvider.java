package com.quakearts.webapp.security.jwt.keyprovider.impl;

import java.security.InvalidKeyException;
import java.security.KeyStore.PrivateKeyEntry;
import java.security.interfaces.RSAPublicKey;

import com.quakearts.webapp.security.jwt.keyprovider.KeystoreKeyPairProvider;

public class RSAKeystoreKeyPairProvider extends KeystoreKeyPairProvider {

	private static final String RSAALGORITHM = "RSA";
	private static final int MINIMUMKEYSIZE = 2048;
	
	@Override
	protected void validateKeyType(PrivateKeyEntry privateKeyEntry) throws InvalidKeyException {
		validatePrivateKeyEntry(RSAALGORITHM, privateKeyEntry);
		
		if(!(privateKeyEntry.getCertificate().getPublicKey() instanceof RSAPublicKey)
				||((RSAPublicKey)privateKeyEntry.getCertificate().getPublicKey()).getModulus().bitLength()<MINIMUMKEYSIZE)
			throw new InvalidKeyException("Key is not a valid RSA key of length greater than "+MINIMUMKEYSIZE);
	}

}
