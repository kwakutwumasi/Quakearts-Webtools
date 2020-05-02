package com.quakearts.security.cryptography.test;

import static org.junit.Assert.*;

import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;

import static org.hamcrest.core.Is.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import com.quakearts.security.cryptography.CryptoResource;
import com.quakearts.security.cryptography.cdi.CryptoInstance;
import com.quakearts.security.cryptography.cdi.CryptoResourceHandle;
import com.quakearts.security.cryptography.exception.CryptoResourceRuntimeException;
import com.quakearts.security.cryptography.factory.CryptoService;
import com.quakearts.security.cryptography.factory.CryptoServiceImpl;
import com.quakearts.security.cryptography.factory.KeyProviderFactory;
import com.quakearts.security.cryptography.test.injecterror.TestInjectInvalidClass;
import com.quakearts.security.cryptography.test.injecterror.TestInjectInvalidInstance;
import com.quakearts.security.cryptography.test.injecterror.TestInjectMissingHandle;
import com.quakearts.security.cryptography.test.injecterror.TestInjectNonExistent;
import com.quakearts.security.cryptography.test.provider.TestKeyProvider;
import com.quakearts.webtools.test.CDIRunner;

@RunWith(CDIRunner.class)
public class CryptoServiceProducerTest {

	@Inject
	private CryptoService cryptoService;

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Test
	public void testGetAndUseCryptoService() throws Exception {
		CryptoResource cryptoResourceFromService = cryptoService.getCryptoResource("test");
		assertThat(cryptoResourceFromService.doEncrypt("test"), is("acdab1e4bbbcf359b5a5520d4c1fce88"));
	}
	
	@Inject @CryptoResourceHandle @CryptoInstance("test")
	private CryptoResource injectedCryptoResource;

	@Test
	public void testGetAndUseCryptoResource() throws Exception {
		assertThat(injectedCryptoResource.doEncrypt("test"), is("acdab1e4bbbcf359b5a5520d4c1fce88"));
	}

	@Test
	public void testGetAndUseCryptoResourceWithInvalidClassName() {
		expectedException.expect(CryptoResourceRuntimeException.class);
		CDI.current().select(TestInjectInvalidClass.class).get();	
	}

	@Test
	public void testEncryptedTypeBaseInvalidInstance() {
		expectedException.expect(CryptoResourceRuntimeException.class);
		CDI.current().select(TestInjectInvalidInstance.class).get();	
	}
		
	@Test
	public void testGetAndUseCryptoResourceWithNonExistent() {
		expectedException.expect(CryptoResourceRuntimeException.class);
		CDI.current().select(TestInjectNonExistent.class).get();	
	}
	
	@Test
	public void testGetAndUseCryptoResourceWithoutCryptoHandle() {
		expectedException.expect(CryptoResourceRuntimeException.class);
		CDI.current().select(TestInjectMissingHandle.class).get();	
	}
	
	@Test
	public void testCreateKeyProviderWithInvalidClass() throws Exception {
		expectedException.expect(ClassNotFoundException.class);
		KeyProviderFactory.createKeyProvider("java.lang.Object");
	}
	
	@Test
	public void testGetCryptoResourceWithoutProperties() throws Exception {
		CryptoResource resourceWithNoProperties = CryptoServiceImpl.getInstance()
			.getCryptoResource("AES", "SunJCE", TestKeyProvider.class.getName(), null, "Test Null Properties");
		assertThat(resourceWithNoProperties.doEncrypt("test"), is("acdab1e4bbbcf359b5a5520d4c1fce88"));
	}
}
