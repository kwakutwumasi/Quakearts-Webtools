package com.quakearts.security.cryptography.test.provider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Map;

import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import com.quakearts.security.cryptography.CryptoResource;
import com.quakearts.security.cryptography.exception.IllegalCryptoActionException;
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

		SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
		return secretKeySpec;
	}

	@Override
	public void setProperties(Map<Object, Object> props) {
	}
	
	public static void main(String[] args) {
		String inputText, selection;
		try {
			System.out.print("1. Encrypt\n2. Decrypt\n\nSelection:");
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
			selection = bufferedReader.readLine();
			switch (selection) {
			case "1":
				System.out.print("Enter plaintext:");
				inputText = bufferedReader.readLine();				
				break;
			case "2":
				System.out.print("Enter encrypted string:");
				inputText = bufferedReader.readLine();				
				break;
			default:
				System.out.println("Invalid selection");
				return;
			}
		} catch (IOException e1) {
			e1.printStackTrace();
			return;
		}

		try {
			CryptoResource cryptoResource = new CryptoResource(new TestKeyProvider().getKey(),
					"AES");
			switch (selection) {
			case "1":
				System.out.println(cryptoResource.doEncrypt(inputText));				
				break;
			case "2":
				System.out.println(cryptoResource.doDecrypt(inputText));				
				break;
			default:
				break;
			}
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | KeyProviderException
				| IllegalCryptoActionException e) {
			e.printStackTrace();
		}
	}
}
