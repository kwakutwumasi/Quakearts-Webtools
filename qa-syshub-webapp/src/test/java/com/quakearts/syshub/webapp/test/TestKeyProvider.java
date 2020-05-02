package com.quakearts.syshub.webapp.test;

import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.crypto.spec.SecretKeySpec;

import com.quakearts.security.cryptography.exception.KeyProviderException;
import com.quakearts.security.cryptography.provider.KeyProvider;

public class TestKeyProvider implements KeyProvider {

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
		key = Arrays.copyOf(key, 16); // use only first 128 bit

		return new SecretKeySpec(key, "AES");
	}

}
