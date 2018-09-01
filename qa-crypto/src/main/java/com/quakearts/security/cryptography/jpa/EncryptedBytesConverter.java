/*******************************************************************************
* Copyright (C) 2016 Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com>.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com> - initial API and implementation
 ******************************************************************************/
package com.quakearts.security.cryptography.jpa;

import javax.persistence.AttributeConverter;

import com.quakearts.security.cryptography.exception.IllegalCryptoActionException;
import com.quakearts.webapp.orm.exception.DataStoreException;

/**A JPA converter for encrypting and decrypting byte fields
 * @author kwakutwumasi-afriyie
 *
 */
public class EncryptedBytesConverter extends EncryptedTypeBase implements AttributeConverter<byte[], byte[]> {

	@Override
	public byte[] convertToDatabaseColumn(byte[] attribute) {
		try {
			if(attribute == null)
				return null;
			
			return getCryptoResource().doEncrypt(attribute);
		} catch (IllegalCryptoActionException e) {
			throw new DataStoreException("Exception " + e.getClass().getName() + ". Message is "
					+ e.getMessage(),e);
		}
	}

	@Override
	public byte[] convertToEntityAttribute(byte[] dbData) {
		try {
			if(dbData == null)
				return null;
			
			return getCryptoResource().doDecrypt(dbData);
		} catch (IllegalCryptoActionException e) {
			throw new DataStoreException("Exception " + e.getClass().getName() + ". Message is "
					+ e.getMessage(),e);
		}
	}

}