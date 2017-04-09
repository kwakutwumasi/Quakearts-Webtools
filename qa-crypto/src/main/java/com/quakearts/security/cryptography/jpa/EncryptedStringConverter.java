package com.quakearts.security.cryptography.jpa;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.quakearts.security.cryptography.exception.IllegalCryptoActionException;
import com.quakearts.webapp.orm.exception.DataStoreException;

@Converter
public class EncryptedStringConverter extends EncryptedTypeBase implements AttributeConverter<String, String> {

	@Override
	public String convertToDatabaseColumn(String attribute) {
		try {
			return getCryptoResource()
					.doEncrypt(attribute.toString());
		} catch (IllegalCryptoActionException e) {
			throw new DataStoreException("Exception " + e.getClass().getName() + ". Message is "
					+ e.getMessage(),e);
		}
	}

	@Override
	public String convertToEntityAttribute(String dbData) {
		try {
			return getCryptoResource().doDecrypt(dbData);
		} catch (IllegalCryptoActionException e) {
			throw new DataStoreException("Exception " + e.getClass().getName() + ". Message is "
					+ e.getMessage(),e);
		}
	}
}
