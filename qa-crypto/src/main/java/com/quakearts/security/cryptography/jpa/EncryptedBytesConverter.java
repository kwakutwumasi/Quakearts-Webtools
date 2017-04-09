package com.quakearts.security.cryptography.jpa;

import javax.persistence.AttributeConverter;

import com.quakearts.security.cryptography.exception.IllegalCryptoActionException;
import com.quakearts.webapp.orm.exception.DataStoreException;

public class EncryptedBytesConverter extends EncryptedTypeBase implements AttributeConverter<byte[], byte[]> {

	@Override
	public byte[] convertToDatabaseColumn(byte[] attribute) {
		try {
			return getCryptoResource().doEncrypt(attribute);
		} catch (IllegalCryptoActionException e) {
			throw new DataStoreException("Exception " + e.getClass().getName() + ". Message is "
					+ e.getMessage(),e);
		}
	}

	@Override
	public byte[] convertToEntityAttribute(byte[] dbData) {
		try {
			return getCryptoResource().doDecrypt(dbData);
		} catch (IllegalCryptoActionException e) {
			throw new DataStoreException("Exception " + e.getClass().getName() + ". Message is "
					+ e.getMessage(),e);
		}
	}

}
