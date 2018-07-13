package com.quakearts.security.cryptography.factory;

import javax.enterprise.inject.Produces;

import com.quakearts.security.cryptography.cdi.CryptoServiceHandle;

public class CryptoServiceProducer {
	private CryptoServiceProducer() {
	}
	
	@Produces @CryptoServiceHandle
	public static CryptoService getCryptoService() {
		return CryptoServiceImpl.getInstance();
	}
}
