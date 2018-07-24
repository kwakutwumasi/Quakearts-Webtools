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
package com.quakearts.security.cryptography.provider;

import java.security.Key;
import java.util.Map;

import com.quakearts.security.cryptography.exception.KeyProviderException;

/**The interface for key providers. Implementations should retrieve a cryptographic key
 * for use in the cryptographic resource
 * @author kwakutwumasi-afriyie
 *
 */
public interface KeyProvider {
	/**Get the cryptographic key to use in operations
	 * @return the cryptographic key
	 * @throws KeyProviderException if there is a problem obtaining the key
	 */
	public Key getKey() throws KeyProviderException;
	/**Set the configuration properties of the key provider
	 * @param props a map of the configuration properties
	 */
	@SuppressWarnings("rawtypes")
	public void setProperties(Map props);
}
