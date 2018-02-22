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
		
		if (props.contains("url.to.mock"))
			setURLToMock(props.getProperty("url.to.mock"));
		else
			throw new ConfigurationException("url.to.mock property required");
		
		if (props.contains("mocking.mode"))
			setMockingModeAs(MockingMode.valueOf(props.getProperty("mocking.mode")));
		else
			throw new ConfigurationException("mocking.mode property required");
				
		if (props.contains("port"))
			try {
				setPortAs(Integer.parseInt(props.getProperty("port")));
			} catch (NumberFormatException e) {
				throw new ConfigurationException("port property must be a valid integer");
			}
		
		if (props.contains("ip.interface"))
			setIPInterfaceAs(props.getProperty("ip.interface"));
		
		if (props.contains("connect.timeout"))
			try {
				setConnectTimeoutAs(Integer.parseInt(props.getProperty("connect.timeout")));
			} catch (NumberFormatException e) {
				throw new ConfigurationException("connect.timeout property must be a valid integer");
			}

		if (props.contains("read.timeout"))
			try {
				setReadTimeoutAs(Integer.parseInt(props.getProperty("read.timeout")));
			} catch (NumberFormatException e) {
				throw new ConfigurationException("read.timeout property must be a valid integer");
			}

		if (props.contains("use.tls")) {
			useTLS(Boolean.getBoolean(props.getProperty("use.tls")));
		
			if(configuration.useTLS) {
				if (props.contains("keystore"))
					setKeyStoreAs(props.getProperty("keystore"));
				else
					throw new ConfigurationException("keystore property required");
				
				if (props.contains("key.password"))
					setKeyPasswordAs(props.getProperty("key.password"));
				else
					throw new ConfigurationException("key.password property required");
				
				if (props.contains("keystore.type"))
					setKeyStoreTypeAs(props.getProperty("keystore.type"));
				else
					throw new ConfigurationException("keystore.type property required");
			}
		}
		return thenBuild();
	}
	
	private DefaultConfiguration configuration;
	
	public ConfigurationBuilder setURLToMock(String urlToMock) {
		if(urlToMock == null || (!urlToMock.startsWith("http://")
				&& !urlToMock.startsWith("https://")))
			throw new ConfigurationException("Property is not valid. Must be a string of the form http://serveraddress[:port]");
		configuration.urlToMock = urlToMock;
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

	public ConfigurationBuilder useTLS(boolean useTLS) {
		configuration.useTLS = useTLS;
		return this;
	}

	public ConfigurationBuilder setKeyStoreAs(String keyStore) {
		configuration.keyStore = keyStore;
		return this;
	}

	public ConfigurationBuilder setKeyPasswordAs(String keyPassword) {
		configuration.keyPassword = keyPassword;
		return this;
	}

	public ConfigurationBuilder setKeyStoreTypeAs(String keyStoreType) {
		configuration.keyStoreType = keyStoreType;
		return this;
	}

	public ConfigurationBuilder setVerifyClientCertifcateAs(boolean verifyClientCertifcate) {
		configuration.verifyClientCertifcate = verifyClientCertifcate;
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
		if(configuration.urlToMock == null) 
			throw new ConfigurationException("Property urlToMock is required");

		if(configuration.mode == null)
			throw new ConfigurationException("Property mode is required");
		
		if(configuration.useTLS) {
			if(configuration.keyStore == null)
				throw new ConfigurationException("Property mode is required");
			
			if(configuration.keyPassword == null)
				throw new ConfigurationException("Property keyPassword is required");

			if(configuration.keyStoreType == null)
				throw new ConfigurationException("Property keyStoreType is required");
		}
		
		return configuration;
	}
	
	public class DefaultConfiguration implements Configuration {
		String urlToMock;
		MockingMode mode;
		int port;
		String ipInterface;
		boolean useTLS;
		String keyStore;
		String keyPassword;
		String keyStoreType;
		boolean verifyClientCertifcate;
		int connectTimeout;
		int readTimeout;
		
		@Override
		public String getURLToMock() {
			return urlToMock;
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
		public String getKeyPassword() {
			return keyPassword;
		}

		@Override
		public String getKeyStoreType() {
			return keyStoreType;
		}

		@Override
		public boolean verifyClientCertificate() {
			return verifyClientCertifcate;
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
