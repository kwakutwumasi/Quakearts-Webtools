package com.quakearts.security.cryptography.test.injecterror;

import javax.inject.Inject;

import com.quakearts.security.cryptography.CryptoResource;
import com.quakearts.security.cryptography.cdi.CryptoInstance;
import com.quakearts.security.cryptography.cdi.CryptoResourceHandle;

public class TestInjectInvalidKeyStoreFile {
	@Inject @CryptoResourceHandle @CryptoInstance("test-invalid-keystorefile")
	private CryptoResource cryptoResource;

	public CryptoResource getCryptoResource() {
		return cryptoResource;
	}
}
