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
package com.quakearts.tools.test.mockserver.configuration;

import com.quakearts.tools.test.mockserver.MockServer;

/**Configuration properties for {@linkplain MockServer}
 * @author kwakutwumasi-afriyie
 *
 */
public interface Configuration {
	/**The mode to place the server in
	 * @author kwakutwumasi-afriyie
	 *
	 */
	enum MockingMode {
		/**Record mode: record and save HTTP request and response cycles
		 * The server acts like a proxy, forwarding calls to the live server
		 */
		RECORD,
		/**Mocking mode: replay recorded request and response cycles
		 */
		MOCK,
		/**Mixed mode: replay recorded request, try to record requests
		 * that have not been handled
		 */
		MIXED
	}
	
	/**Get the URL to record
	 * @return the URL as a string
	 */
	String getURLToRecord();
	/**Get the mocking mode to use
	 * @return the {@linkplain MockingMode}
	 */
	MockingMode getMockingMode();
	/**Get the server port to use
	 * @return the port
	 */
	int getPort();
	/**Get the host interface to listen in for connections
	 * @return the host interface
	 */
	String getIPInterface();
	/**Determine whether to use TLS
	 * @return true if TLS should be used.
	 */
	boolean useTLS();
	/**
	 * 
	 * @return the keytype. Should be one of RSA, DSA, or EC
	 */
	String getKeyType();
	/**Get the location of the key store file to use for the TLS public/private key. Only used when {@link #useTLS()} is true
	 * @return the file location
	 */	
	String getKeyStore();
	/**Get the password for the key store. Only used when {@link #useTLS()} is true
	 * @return the password
	 */
	String getKeyStorePassword();
	/**Get the key store type. Only used when {@link #useTLS()} is true
	 * @return the key store type
	 */
	String getKeyStoreType();
	/**Get the key alias. Only used when {@link #useTLS()} is true
	 * @return the key store alias
	 */
	String getKeyAlias();
	/**Get the read timeout to use when recording from a URL. . Only used when {@link MockingMode} is RECORD
	 * @return the read timeout in milliseconds
	 */	
	int getReadTimeout();
	/**Get the connection timeout to use when recording from a URL. Only used when {@link MockingMode} is RECORD
	 * @return the connection timeout in milliseconds
	 */
	int getConnectTimeout();
	
	/**Determine whether to honor RESTful HTTP contracts. Only used when {@link MockingMode} is RECORD.
	 * <br /><br />
	 * If true, HTTP verbs are not restricted in terms of
	 * inputs and outputs, i.e. it is possible to send any HTTP
	 * verb with input, and expect output from any verb
	 * @return true if REST contracts are to be ignored
	 */
	boolean dishonorRESTContract();
}
