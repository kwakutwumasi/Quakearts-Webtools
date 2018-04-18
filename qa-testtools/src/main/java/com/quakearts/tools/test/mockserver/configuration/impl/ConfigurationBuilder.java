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
import java.util.Properties;
import com.quakearts.tools.test.mockserver.configuration.Configuration;
import com.quakearts.tools.test.mockserver.configuration.Configuration.MockingMode;
import com.quakearts.tools.test.mockserver.exception.ConfigurationException;

public class ConfigurationBuilder {
	
	private ConfigurationBuilder() {
		configuration = new DefaultConfiguration();
	}
	
	public static ConfigurationBuilder newConfiguration() {
		return new ConfigurationBuilder();
	}
	
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
			}
		}
		return thenBuild();
	}
	
	private DefaultConfiguration configuration;
	
	public ConfigurationBuilder setURLToRecord(String urlToRecord) {
		if(urlToRecord == null || (!urlToRecord.startsWith("http://")
				&& !urlToRecord.startsWith("https://")))
			throw new ConfigurationException("Property is not valid. Must be a string of the form http://serveraddress[:port]");
		configuration.urlToRecord = urlToRecord;
		return this;
	}

	public ConfigurationBuilder setMockingModeAs(MockingMode mode) {
		configuration.mode = mode;
		return this;
	}

	public ConfigurationBuilder setPortAs(int port) {
		configuration.port = port;
		return this;
	}

	public ConfigurationBuilder setIPInterfaceAs(String ipInterface) {
		configuration.ipInterface = ipInterface;
		return this;
	}

	public ConfigurationBuilder setUseTLSAs(boolean useTLS) {
		configuration.useTLS = useTLS;
		return this;
	}

	public ConfigurationBuilder setKeyStoreAs(String keyStore) {
		configuration.keyStore = keyStore;
		return this;
	}

	public ConfigurationBuilder setKeyStorePasswordAs(String keyPassword) {
		configuration.keyStorePassword = keyPassword;
		return this;
	}

	public ConfigurationBuilder setKeyStoreTypeAs(String keyStoreType) {
		configuration.keyStoreType = keyStoreType;
		return this;
	}
	
	public ConfigurationBuilder setConnectTimeoutAs(int timeInMills) {
		configuration.connectTimeout = timeInMills;
		return this;
	}
	
	public ConfigurationBuilder setReadTimeoutAs(int timeInMills) {
		configuration.readTimeout = timeInMills;
		return this;
	}
	
	public Configuration thenBuild() {
		if(configuration.mode == null)
			throw new ConfigurationException("Property mode is required");
		
		if(configuration.mode == MockingMode.RECORD 
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
	
	public class DefaultConfiguration implements Configuration {
		String urlToRecord;
		MockingMode mode;
		int port;
		String ipInterface;
		boolean useTLS;
		String keyStore;
		String keyStorePassword;
		String keyStoreType;
		int connectTimeout;
		int readTimeout;
		
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
		public int getConnectTimeout() {
			return connectTimeout;
		}
		
		@Override
		public int getReadTimeout() {
			return readTimeout;
		}
	}
}
