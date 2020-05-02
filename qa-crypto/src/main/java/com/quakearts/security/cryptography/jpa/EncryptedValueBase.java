package com.quakearts.security.cryptography.jpa;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import com.quakearts.security.cryptography.CryptoResource;
import com.quakearts.security.cryptography.exception.CryptoResourceRuntimeException;
import com.quakearts.security.cryptography.factory.CryptoServiceImpl;
import com.quakearts.webapp.orm.DataStoreFactory;

public class EncryptedValueBase {

	private Map<String, CryptoResource> resources = new ConcurrentHashMap<>();

	public EncryptedValueBase() {
		super();
	}

	protected CryptoResource getCryptoResource(String dataStoreName) {
		CryptoResource resource = resources.get(dataStoreName);
		if(resource == null) {
			try {
				String resourceName = DataStoreFactory.getInstance().getDataStore(dataStoreName)
						.getConfigurationProperty("com.quakearts.cryptoname");
	
				resource = CryptoServiceImpl.getInstance().getCryptoResource(resourceName);
				resources.put(dataStoreName, resource);
			} catch (ReflectiveOperationException | GeneralSecurityException| IOException e) {
				throw new CryptoResourceRuntimeException(e);
			}
		}
		return resource;
	}

}