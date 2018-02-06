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

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Logger;

import javax.crypto.NoSuchPaddingException;
import com.quakearts.security.cryptography.CryptoResource;
import com.quakearts.security.cryptography.exception.CryptoResourceRuntimeException;
import com.quakearts.security.cryptography.exception.KeyProviderException;
import com.quakearts.security.cryptography.factory.CrytpoServiceFactory;
import com.quakearts.webapp.orm.DataStoreFactory;

public abstract class EncryptedTypeBase {

	private transient CryptoResource resource;
	private static final Logger log = Logger.getLogger(EncryptedStringConverter.class.getName());

	protected CryptoResource getCryptoResource() {
		if (resource == null) {
			try {
				String resourceName = DataStoreFactory.getInstance().getDataStore()
						.getConfigurationProperty("com.quakearts.cryptoname");

				resource = CrytpoServiceFactory.getInstance().getCryptoResource(resourceName);
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchAlgorithmException
					| NoSuchPaddingException | IOException | KeyProviderException e) {
				log.severe("Cannot perform cryptography: " + e.getMessage() + ". " + e.getClass().getName());
				throw new CryptoResourceRuntimeException(e);
			}
		}
		return resource;
	}
}
