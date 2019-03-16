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
package com.quakearts.security.cryptography.factory;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Map;

import javax.crypto.NoSuchPaddingException;

import com.quakearts.security.cryptography.CryptoResource;
import com.quakearts.security.cryptography.exception.KeyProviderException;

public interface CryptoService {

	/**Obtain a cryptographic resource using the supplied parameters
	 * @param instance the cryptographic algorithm
	 * @param provider the JCE provider to use
	 * @param keyProviderClass the key provider class
	 * @param properties properties to pass to the key provider class
	 * @param name the name to give the resource
	 * @return a cryptographic resource
	 * @throws ClassNotFoundException the key provider class could not be found
	 * @throws InstantiationException the key provider class could not be instantiated
	 * @throws IllegalAccessException the key provider class could not be instantiated
	 * @throws KeyProviderException the key provider class could not be instantiated
	 * @throws NoSuchAlgorithmException the cryptographic algorithm is invalid
	 * @throws NoSuchPaddingException the cryptographic algorithm is invalid
	 * @throws NoSuchProviderException the cryptographic provider name is invalid
	 */
	CryptoResource getCryptoResource(String instance, String provider, String keyProviderClass, Map<Object, Object> properties,
			String name) throws ClassNotFoundException, InstantiationException, IllegalAccessException,
			KeyProviderException, NoSuchAlgorithmException, NoSuchPaddingException, NoSuchProviderException;

	/**Obtain a cryptographic resource with the given name
	 * @param name the name to give the resource
	 * @return a cryptographic resource
	 * @throws IOException the key provider properties could not be loaded
	 * @throws ClassNotFoundException the key provider class could not be found
	 * @throws InstantiationException the key provider class could not be instantiated
	 * @throws IllegalAccessException the key provider class could not be instantiated
	 * @throws KeyProviderException the key provider class could not be instantiated
	 * @throws NoSuchAlgorithmException the cryptographic algorithm is invalid
	 * @throws NoSuchPaddingException the cryptographic algorithm is invalid
	 * @throws NoSuchProviderException the cryptographic provider name is invalid
	 */
	CryptoResource getCryptoResource(String name) throws IOException, ClassNotFoundException, InstantiationException,
			IllegalAccessException, NoSuchAlgorithmException, NoSuchPaddingException, KeyProviderException, NoSuchProviderException;

}