package com.quakearts.security.cryptography.jpa;

import javax.persistence.AttributeConverter;

import com.quakearts.security.cryptography.exception.IllegalCryptoActionException;
import com.quakearts.webapp.orm.exception.DataStoreException;

public class EncryptedValueStringConverter extends EncryptedValueBase implements AttributeConverter<EncryptedValue, String> {
	@Override
	public String convertToDatabaseColumn(EncryptedValue attribute) {
		try {
			if(attribute == null)
				return null;
		
			if(attribute.getDataStoreName()==null || attribute.getValue() == null
					|| attribute.getValue().length==0)
				throw new DataStoreException("The dataStoreName and value are required for EncryptedValue");
			
			String encrypted = getCryptoResource(attribute.getDataStoreName()).doEncrypt(attribute.getStringValue());
			
			return attribute.getDataStoreName()+"|"+encrypted;
		} catch (IllegalCryptoActionException e) {
			throw new DataStoreException("Exception " + e.getClass().getName() + ". Message is "
					+ e.getMessage(),e);
		}
	}

	@Override
	public EncryptedValue convertToEntityAttribute(String dbData) {
		if(dbData==null)
			return null;
		
		StringBuilder builder = new StringBuilder();
		byte[] dbDataBytes = dbData.getBytes();
		int index=0;
		for(; index<dbDataBytes.length-1;index++) {
			char c = (char) dbDataBytes[index];
			if(c != '|') {
				builder.append(c);
			} else {
				break;
			}
		}
		
		if(index == dbDataBytes.length-1) {
			throw new DataStoreException("The value returned from the database was tampered with. The dataStoreName could not be found");
		}
		
		EncryptedValue value = new EncryptedValue();
		value.setDataStoreName(builder.toString());
		String encrypted = dbData.substring(index+1);
		try {
			value.setStringValue(getCryptoResource(value.getDataStoreName()).doDecrypt(encrypted));
			return value;
		} catch (IllegalCryptoActionException e) {
			throw new DataStoreException("Exception " + e.getClass().getName() + ". Message is "
					+ e.getMessage(),e);
		}
	}
}
