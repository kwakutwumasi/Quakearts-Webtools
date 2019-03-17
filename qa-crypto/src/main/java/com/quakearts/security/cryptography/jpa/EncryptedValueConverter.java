package com.quakearts.security.cryptography.jpa;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

import javax.persistence.AttributeConverter;

import com.quakearts.security.cryptography.CryptoResource;
import com.quakearts.security.cryptography.exception.CryptoResourceRuntimeException;
import com.quakearts.security.cryptography.exception.IllegalCryptoActionException;
import com.quakearts.security.cryptography.exception.KeyProviderException;
import com.quakearts.security.cryptography.factory.CryptoServiceImpl;
import com.quakearts.webapp.orm.DataStoreFactory;
import com.quakearts.webapp.orm.exception.DataStoreException;

public class EncryptedValueConverter implements AttributeConverter<EncryptedValue, byte[]> {
	private Map<String, CryptoResource> resources = new ConcurrentHashMap<>();
	private static final Logger log = Logger.getLogger(EncryptedValueConverter.class.getName());
		
	@Override
	public byte[] convertToDatabaseColumn(EncryptedValue attribute) {
		try {
			if(attribute == null)
				return null;
		
			if(attribute.getDataStoreName()==null || attribute.getValue() == null
					|| attribute.getValue().length==0)
				throw new DataStoreException("The dataStoreName and value are required for EncryptedValue");
			
			byte[] encrypted = getCryptoResource(attribute.getDataStoreName()).doEncrypt(attribute.getValue());
			byte[] storedValue = new byte[attribute.getDataStoreName().length()+1+encrypted.length];
			System.arraycopy(attribute.getDataStoreName().getBytes(), 0, storedValue, 0, attribute.getDataStoreName().length());
			System.arraycopy("|".getBytes(), 0, storedValue, attribute.getDataStoreName().length(), 1);
			System.arraycopy(encrypted, 0, storedValue, attribute.getDataStoreName().length()+1, encrypted.length);
			return storedValue;
		} catch (IllegalCryptoActionException e) {
			throw new DataStoreException("Exception " + e.getClass().getName() + ". Message is "
					+ e.getMessage(),e);
		}
	}

	@Override
	public EncryptedValue convertToEntityAttribute(byte[] dbData) {
		if(dbData==null)
			return null;
		
		StringBuilder builder = new StringBuilder();
		int index=0;
		for(; index<dbData.length-1;index++) {
			char c = (char) dbData[index];
			if(c != '|') {
				builder.append(c);
			} else {
				break;
			}
		}
		
		if(index == dbData.length-1) {
			throw new DataStoreException("The value returned from the database was tampered with. The dataStoreName could not be found");
		}
		
		EncryptedValue value = new EncryptedValue();
		value.setDataStoreName(builder.toString());
		byte[] encrypted = new byte[dbData.length-index-1];
		System.arraycopy(dbData, index+1, encrypted, 0, encrypted.length);
		try {
			value.setValue(getCryptoResource(value.getDataStoreName()).doDecrypt(encrypted));
			return value;
		} catch (IllegalCryptoActionException e) {
			throw new DataStoreException("Exception " + e.getClass().getName() + ". Message is "
					+ e.getMessage(),e);
		}
	}

	protected CryptoResource getCryptoResource(String dataStoreName) {
		CryptoResource resource = resources.get(dataStoreName);
		if(resource == null) {
			try {
				String resourceName = DataStoreFactory.getInstance().getDataStore(dataStoreName)
						.getConfigurationProperty("com.quakearts.cryptoname");

				resource = CryptoServiceImpl.getInstance().getCryptoResource(resourceName);
				resources.put(dataStoreName, resource);
			} catch (ReflectiveOperationException | GeneralSecurityException
					| IOException | KeyProviderException e) {
				log.severe("Cannot perform cryptography: " + e.getMessage() + ". " + e.getClass().getName());
				throw new CryptoResourceRuntimeException(e);
			}
		}
		return resource;
	}
}
