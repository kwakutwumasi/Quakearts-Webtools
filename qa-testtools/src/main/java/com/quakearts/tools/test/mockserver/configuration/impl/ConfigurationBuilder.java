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
package com.quakearts.tools.test.mockserver.configuration.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Properties;
import com.quakearts.tools.test.mockserver.configuration.Configuration;
import com.quakearts.tools.test.mockserver.configuration.Configuration.MockingMode;
import com.quakearts.tools.test.mockserver.exception.ConfigurationException;

/**Builder for {@linkplain Configuration} instances
 * @author kwakutwumasi-afriyie
 *
 */
public class ConfigurationBuilder {
	
	private ConfigurationBuilder() {
		configuration = new DefaultConfiguration();
	}
	
	/**Create a new {@linkplain Configuration}
	 * @return
	 */
	public static ConfigurationBuilder newConfiguration() {
		return new ConfigurationBuilder();
	}
	
	/**Load a configuration file from an {@linkplain InputStream}
	 * @param in the {@linkplain InputStream}
	 * @return the Configuration
	 * @throws IOException if there is an error reading from the stream
	 */
	public Configuration fromStream(InputStream in) throws IOException {
		
		Properties props = new Properties();
		props.load(in);
		
		if (props.containsKey("url.to.record"))
			setURLToRecord(props.getProperty("url.to.record"));
		else
			throw new ConfigurationException("url.to.record property required");
		
		if (props.containsKey("mocking.mode"))
			try {
				setMockingModeAs(MockingMode.valueOf(props.getProperty("mocking.mode")));				
			} catch (IllegalArgumentException e) {
				throw new ConfigurationException(e);
			}
		else
			throw new ConfigurationException("mocking.mode property required");
				
		if (props.containsKey("port"))
			try {
				setPortAs(Integer.parseInt(props.getProperty("port")));
			} catch (NumberFormatException e) {
				throw new ConfigurationException("port property must be a valid integer");
			}
		
		if (props.containsKey("ip.interface"))
			setIPInterfaceAs(props.getProperty("ip.interface"));
		
		if (props.containsKey("connect.timeout"))
			try {
				setConnectTimeoutAs(Integer.parseInt(props.getProperty("connect.timeout")));
			} catch (NumberFormatException e) {
				throw new ConfigurationException("connect.timeout property must be a valid integer");
			}

		if (props.containsKey("read.timeout"))
			try {
				setReadTimeoutAs(Integer.parseInt(props.getProperty("read.timeout")));
			} catch (NumberFormatException e) {
				throw new ConfigurationException("read.timeout property must be a valid integer");
			}

		if (props.containsKey("use.tls")) {
			setUseTLSAs(Boolean.parseBoolean(props.getProperty("use.tls")));
		
			if(configuration.useTLS) {
				if (props.containsKey("keystore"))
					setKeyStoreAs(props.getProperty("keystore"));
				else
					throw new ConfigurationException("keystore property required");
				
				if (props.containsKey("keystore.password"))
					setKeyStorePasswordAs(props.getProperty("keystore.password"));
				else
					throw new ConfigurationException("key.password property required");
				
				if (props.containsKey("keystore.type"))
					setKeyStoreTypeAs(props.getProperty("keystore.type"));
				else
					throw new ConfigurationException("keystore.type property required");
				
				if(props.containsKey("key.alias"))
					setKeyAliasAs(props.getProperty("key.alias"));
			}
		}
		return thenBuild();
	}
	
	private DefaultConfiguration configuration;
	
	/**Set the URL to record
	 * @param urlToRecord the URL as a string
	 * @return this object for method chaining
	 */
	public ConfigurationBuilder setURLToRecord(String urlToRecord) {
		if(urlToRecord == null || (!urlToRecord.startsWith("http://")
				&& !urlToRecord.startsWith("https://")))
			throw new ConfigurationException("Property is not valid. Must be a string of the form http://serveraddress[:port]");
		configuration.urlToRecord = urlToRecord;
		return this;
	}

	/**Set the mocking mode to use
	 * @param mode the mocking mode
	 * @return this object for method chaining
	 */
	public ConfigurationBuilder setMockingModeAs(MockingMode mode) {
		configuration.mode = mode;
		return this;
	}

	/**Set the server port to use
	 * @param port the server port
	 * @return this object for method chaining
	 */
	public ConfigurationBuilder setPortAs(int port) {
		configuration.port = port;
		return this;
	}

	/**Set the host interface to listen to for connections
	 * @param ipInterface the host interface
	 * @return this object for method chaining
	 */
	public ConfigurationBuilder setIPInterfaceAs(String ipInterface) {
		configuration.ipInterface = ipInterface;
		return this;
	}

	/**Set whether to use TLS
	 * @param useTLS true if TLS should be used
	 * @return this object for method chaining
	 */
	public ConfigurationBuilder setUseTLSAs(boolean useTLS) {
		configuration.useTLS = useTLS;
		return this;
	}

	/**Set the location of the key store file to use for the TLS public/private key
	 * @param keyStore the file location
	 * @return this object for method chaining
	 */
	public ConfigurationBuilder setKeyStoreAs(String keyStore) {
		configuration.keyStore = keyStore;
		return this;
	}

	/**Set the password for the key store
	 * @param keyPassword the password
	 * @return this object for method chaining
	 */
	public ConfigurationBuilder setKeyStorePasswordAs(String keyPassword) {
		configuration.keyStorePassword = keyPassword;
		return this;
	}

	/**Set the key store type
	 * @param keyStoreType
	 * @return this object for method chaining
	 */
	public ConfigurationBuilder setKeyStoreTypeAs(String keyStoreType) {
		configuration.keyStoreType = keyStoreType;
		return this;
	}
	
	/**Set the key alias
	 * @param keyAlias
	 * @return this object for method chaining
	 */
	public ConfigurationBuilder setKeyAliasAs(String keyAlias) {
		configuration.keyAlias = keyAlias;
		return this;
	}

	private static final HashSet<String> TYPES = new HashSet<>(Arrays.asList("RSA","DSA","EC"));

	/**Set the key type
	 * @param keyType
	 * @return this object for method chaining
	 */
	public ConfigurationBuilder setKeyTypeAs(String keyType) {
		if(!TYPES.contains(keyType))
			throw new ConfigurationException(keyType + " is not supported");

		configuration.keyType = keyType;
		return this;
	}
	
	/**Set the connection timeout to use when recording from a URL
	 * @param timeInMills the connection timeout in milliseconds
	 * @return this object for method chaining
	 */
	public ConfigurationBuilder setConnectTimeoutAs(int timeInMills) {
		configuration.connectTimeout = timeInMills;
		return this;
	}
	
	/**Set the read timeout to use when recording from a URL
	 * @param timeInMills the read timeout in milliseconds
	 * @return this object for method chaining
	 */
	public ConfigurationBuilder setReadTimeoutAs(int timeInMills) {
		configuration.readTimeout = timeInMills;
		return this;
	}
	
	/**Determine whether to honor RESTful HTTP contracts.
	 * If true, HTTP verbs are not restricted in terms of
	 * inputs and outputs, i.e. it is possible to send any HTTP
	 * verb with input, and expect output from any verb
	 * @param disHonorRESTContract
	 * @return true if REST contracts are to be ignored
	 */
	public ConfigurationBuilder setDisHonorRESTContractAs(boolean disHonorRESTContract) {
		configuration.disHonorRESTContract = disHonorRESTContract;
		return this;
	}
	
	/**Return the configuration. This is a terminal method in the fluid API chain
	 * @return the {@linkplain Configuration}
	 */
	public Configuration thenBuild() {
		if(configuration.mode == null)
			throw new ConfigurationException("Property mode is required");
		
		if(configuration.mode != MockingMode.MOCK
				&& configuration.urlToRecord == null) 
			throw new ConfigurationException("Property urlToRecord is required when mocking mode is RECORD");

		if(configuration.useTLS) {
			if(configuration.keyStore == null)
				throw new ConfigurationException("Property mode is required");
			
			if(configuration.keyStorePassword == null)
				throw new ConfigurationException("Property keyPassword is required");

			if(configuration.keyStoreType == null)
				throw new ConfigurationException("Property keyStoreType is required");
		}
		
		return configuration;
	}
	
	/**Default configuration class
	 * @author kwakutwumasi-afriyie
	 *
	 */
	class DefaultConfiguration implements Configuration {
		String urlToRecord;
		MockingMode mode;
		int port;
		String ipInterface;
		boolean useTLS;
		String keyStore;
		String keyStorePassword;
		String keyStoreType;
		String keyAlias;
		String keyType;
		int connectTimeout;
		int readTimeout;
		boolean disHonorRESTContract;
		
		@Override
		public String getURLToRecord() {
			return urlToRecord;
		}

		@Override
		public MockingMode getMockingMode() {
			return mode;
		}

		@Override
		public int getPort() {
			return port;
		}

		@Override
		public String getIPInterface() {
			return ipInterface;
		}

		@Override
		public boolean useTLS() {
			return useTLS;
		}

		@Override
		public String getKeyStore() {
			return keyStore;
		}

		@Override
		public String getKeyStorePassword() {
			return keyStorePassword;
		}

		@Override
		public String getKeyStoreType() {
			return keyStoreType;
		}

		@Override
		public String getKeyAlias() {
			return keyAlias;
		}
		
		@Override
		public String getKeyType() {
			return keyType;
		}

		@Override
		public int getConnectTimeout() {
			return connectTimeout;
		}
		
		@Override
		public int getReadTimeout() {
			return readTimeout;
		}
		
		@Override
		public boolean dishonorRESTContract() {
			return disHonorRESTContract;
		}
	}
}
