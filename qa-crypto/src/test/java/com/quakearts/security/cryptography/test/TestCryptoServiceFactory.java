package com.quakearts.security.cryptography.test;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;

import org.junit.Test;

import com.quakearts.security.cryptography.CryptoResource;
import com.quakearts.security.cryptography.factory.CrytpoServiceFactory;

public class TestCryptoServiceFactory {

	@Test
	public void testGetAndUseCryptoServiceFactory() throws Exception {
		CryptoResource cryptoResource = CrytpoServiceFactory.getInstance().getCryptoResource("test");
		assertThat(cryptoResource.doEncrypt("test"), is("acdab1e4bbbcf359b5a5520d4c1fce88"));
	}

}
