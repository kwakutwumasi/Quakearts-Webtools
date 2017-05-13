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