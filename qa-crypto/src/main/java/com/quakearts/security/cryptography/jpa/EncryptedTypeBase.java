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
import java.security.GeneralSecurityException;
import com.quakearts.security.cryptography.CryptoResource;
import com.quakearts.security.cryptography.exception.CryptoResourceRuntimeException;
import com.quakearts.security.cryptography.factory.CryptoServiceImpl;
import com.quakearts.webapp.orm.DataStoreFactory;

public abstract class EncryptedTypeBase {

	private CryptoResource resource;

	protected CryptoResource getCryptoResource() {
		if (resource == null) {
			try {
				String resourceName = DataStoreFactory.getInstance().getDataStore()
						.getConfigurationProperty("com.quakearts.cryptoname");

				resource = CryptoServiceImpl.getInstance().getCryptoResource(resourceName);
			} catch (ReflectiveOperationException | GeneralSecurityException
					| IOException e) {
				throw new CryptoResourceRuntimeException(e);
			}
		}
		return resource;
	}
}
