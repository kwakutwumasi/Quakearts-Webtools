package com.quakearts.security.cryptography.test;

import static org.junit.Assert.*;

import javax.enterprise.inject.spi.CDI;
import javax.enterprise.util.AnnotationLiteral;

import static org.hamcrest.core.Is.*;

import org.junit.Test;

import com.quakearts.security.cryptography.CryptoResource;
import com.quakearts.security.cryptography.cdi.CryptoServiceHandle;
import com.quakearts.security.cryptography.factory.CryptoService;
import com.quakearts.webtools.test.AppBaseCDIBaseTest;

public class TestCryptoServiceFactory extends AppBaseCDIBaseTest {

	@SuppressWarnings("serial")
	@Test
	public void testGetAndUseCryptoServiceFactory() throws Exception {
		CryptoService cryptoService = CDI.current().select(CryptoService.class, new AnnotationLiteral<CryptoServiceHandle>() {}).get();
		CryptoResource cryptoResource = cryptoService.getCryptoResource("test");
		assertThat(cryptoResource.doEncrypt("test"), is("acdab1e4bbbcf359b5a5520d4c1fce88"));
	}

}
