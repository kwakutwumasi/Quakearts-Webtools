package test.junit;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;

import java.io.InputStream;

import org.junit.Test;

import com.quakearts.tools.test.mockserver.configuration.Configuration;
import com.quakearts.tools.test.mockserver.configuration.impl.ConfigurationBuilder;
import com.quakearts.tools.test.mockserver.exception.ConfigurationException;

public class TestConfiguration {

	private Configuration loadPropertiesFile(String file) throws Exception {
		Configuration configuration;
		try(InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(file)) {
			configuration = ConfigurationBuilder.newConfiguration().fromStream(in);
		}
		
		return configuration;
	}
	
	@Test(expected = ConfigurationException.class)
	public void testWithoutUrlToMock() throws Exception {
		loadPropertiesFile("testmock1.config");
	}

	@Test(expected = ConfigurationException.class)
	public void testWithoutMockingMode() throws Exception {
		loadPropertiesFile("testmock2.config");
	}
	
	@Test
	public void testValidPropertyFileWithoutTLS() throws Exception {
		Configuration configuration = loadPropertiesFile("testmock3.config");
		
		assertThat(configuration.getURLToMock(), is("http://localhost:8080"));
		assertThat(configuration.getMockingMode(), is(Configuration.MockingMode.MOCK));
		assertThat(configuration.getPort(), is(8000));
		assertThat(configuration.getIPInterface(), is("0.0.0.0"));
		assertThat(configuration.getConnectTimeout(), is(60000));
		assertThat(configuration.getReadTimeout(), is(30000));
	}

	@Test(expected = ConfigurationException.class)
	public void testInValidPropertyFileWithTLSButNoKeyStore() throws Exception {
		loadPropertiesFile("testmock4.config");
	}

	@Test(expected = ConfigurationException.class)
	public void testInValidPropertyFileWithTLSButNoKeyPassword() throws Exception {
		loadPropertiesFile("testmock5.config");
	}
	
	@Test(expected = ConfigurationException.class)
	public void testInValidPropertyFileWithTLSButNoKeyStoreType() throws Exception {
		loadPropertiesFile("testmock6.config");
	}
	
	@Test
	public void testValidPropertyFileWithTLS() throws Exception {
		Configuration configuration = loadPropertiesFile("testmock7.config");
		
		assertThat(configuration.getURLToMock(), is("http://localhost:8080"));
		assertThat(configuration.getMockingMode(), is(Configuration.MockingMode.RECORD));
		assertThat(configuration.getPort(), is(4080));
		assertThat(configuration.getConnectTimeout(), is(60000));
		assertThat(configuration.getReadTimeout(), is(30000));
		assertThat(configuration.useTLS(), is(true));
		assertThat(configuration.getKeyStore(), is("conf/tomcat.keystore"));
		assertThat(configuration.getKeyStorePassword(), is("password1"));
		assertThat(configuration.getKeyStoreType(), is("JCEKS"));	
	}

	@Test
	public void testValidPropertyFileWithTLSFalse() throws Exception {
		Configuration configuration = loadPropertiesFile("testmock8.config");
		
		assertThat(configuration.getURLToMock(), is("http://localhost:8080"));
		assertThat(configuration.getMockingMode(), is(Configuration.MockingMode.RECORD));
		assertThat(configuration.getPort(), is(8000));
		assertThat(configuration.getIPInterface(), is("0.0.0.0"));
		assertThat(configuration.getConnectTimeout(), is(60000));
		assertThat(configuration.getReadTimeout(), is(30000));
		assertThat(configuration.useTLS(), is(false));
	}
	
	@Test(expected = ConfigurationException.class)
	public void testInValidPropertyFileWithInvalidMockingMode() throws Exception {
		loadPropertiesFile("testmock9.config");
	}

	@Test(expected = ConfigurationException.class)
	public void testInValidPropertyFileWithInvalidPort() throws Exception {
		loadPropertiesFile("testmock10.config");
	}

	@Test(expected = ConfigurationException.class)
	public void testInValidPropertyFileWithInvalidConnectTimeout() throws Exception {
		loadPropertiesFile("testmock11.config");
	}

	@Test(expected = ConfigurationException.class)
	public void testInValidPropertyFileWithInvalidReadTimeout() throws Exception {
		loadPropertiesFile("testmock12.config");
	}

	@Test
	public void testValidPropertyFileWithMinimalConfig() throws Exception {
		Configuration configuration = loadPropertiesFile("testmock13.config");
		
		assertThat(configuration.getURLToMock(), is("http://localhost:8080"));
		assertThat(configuration.getMockingMode(), is(Configuration.MockingMode.MOCK));
		assertThat(configuration.getPort(), is(0));
		assertThat(configuration.getIPInterface() == null, is(true));
		assertThat(configuration.getConnectTimeout(), is(0));
		assertThat(configuration.getReadTimeout(), is(0));
		assertThat(configuration.useTLS(), is(false));
	}
}
