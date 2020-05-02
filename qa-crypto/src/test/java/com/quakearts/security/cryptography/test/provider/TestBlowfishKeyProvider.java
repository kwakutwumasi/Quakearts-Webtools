package com.quakearts.security.cryptography.test.provider;

import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Map;

import javax.crypto.spec.SecretKeySpec;

import com.quakearts.security.cryptography.exception.KeyProviderException;
import com.quakearts.security.cryptography.provider.KeyProvider;

public class TestBlowfishKeyProvider implements KeyProvider {

	@Override
	public Key getKey() throws KeyProviderException {
		byte[] key;
		try {
			key = "testkey".getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new KeyProviderException(e);
		}

		MessageDigest sha;
		try {
			sha = MessageDigest.getInstance("SHA-1");
		} catch (NoSuchAlgorithmException e) {
			throw new KeyProviderException(e);
		}

		key = sha.digest(key);
		key = Arrays.copyOf(key, 4); // use only first 32 bits

		return new SecretKeySpec(key, "Blowfish");
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void setProperties(Map props) {
	}
}
