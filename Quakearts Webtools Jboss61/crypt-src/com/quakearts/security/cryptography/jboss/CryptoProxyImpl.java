package com.quakearts.security.cryptography.jboss;

import com.quakearts.security.cryptography.CryptoProxy;
import com.quakearts.security.cryptography.CryptoResource;

public class CryptoProxyImpl implements CryptoProxy {
	
	private CryptoService service;
	
	protected CryptoProxyImpl(CryptoService service){
		this.service = service;
	}
	@SuppressWarnings("unused")
	private CryptoProxyImpl(){		
	}
	
	public CryptoResource getResource() throws Exception {
		return service.getResource();
	}
}
