package com.quakearts.security.cryptography.test;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;

import java.io.UnsupportedEncodingException;
import java.security.AllPermission;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Permission;
import java.util.Arrays;

import javax.crypto.spec.SecretKeySpec;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.quakearts.security.cryptography.CryptoResource;
import com.quakearts.security.cryptography.exception.KeyProviderException;
import com.quakearts.security.cryptography.permission.CryptographyOperationPermission;

public class CryptographyOperationPermissionTest {

	@SuppressWarnings("unlikely-arg-type")
	@Test
	public void testImpliesPermissionEqualsObjectAndHashCode() {
		CryptographyOperationPermission permission1 = new CryptographyOperationPermission("Test", "encrypt,decrypt");
		CryptographyOperationPermission permission2 = new CryptographyOperationPermission("Test", "encrypt,decrypt");
		CryptographyOperationPermission permission3 = new CryptographyOperationPermission("Test", "encrypt");
		CryptographyOperationPermission permission4 = new CryptographyOperationPermission("Test2", "encrypt,decrypt");
		CryptographyOperationPermission permission5 = new CryptographyOperationPermission("Test", "all");
		assertTrue(permission1.getActions().equals("encrypt,decrypt"));
		assertTrue(permission1.implies(permission2));
		assertFalse(permission1.implies(permission3));
		assertFalse(permission1.implies(permission4));
		assertTrue(permission5.implies(permission1));
		assertFalse(permission1.implies(permission5));
		assertFalse(permission1.implies(new AllPermission()));
		
		assertTrue(permission1.equals(permission1));
		assertTrue(permission1.equals(permission2));
		assertEquals(permission1.hashCode(), permission2.hashCode());
		assertFalse(permission1.equals(permission3));
		assertNotEquals(permission1.hashCode(), permission3.hashCode());
		assertFalse(permission1.equals(permission4));
		assertNotEquals(permission1.hashCode(), permission4.hashCode());
		assertFalse(permission1.equals(null));
		assertFalse(permission1.equals(""));
	}
	
	@Rule
	public ExpectedException expectedException = ExpectedException.none();
		
	@Test
	public void testAllowEncryptAndDecrypt() throws Exception {
		CryptoResource cryptoResource = new CryptoResource(getAESKey(), "AES/CBC/PKCS5Padding", "SunJCE", "testProfile");
		String cipherText = cryptoResource.doEncrypt("Test");
		String decryptedPlainText = cryptoResource.doDecrypt(cipherText);
		assertEquals("Test", decryptedPlainText);
	}
	
	@Test
	public void testDenyEncrypt() throws Exception {
		try {
			expectedException.expect(SecurityException.class);
			expectedException.expectMessage(is("Not Allowed"));
			System.setSecurityManager(new SecurityManager() {
				@Override
				public void checkPermission(Permission perm) {
					if(perm instanceof CryptographyOperationPermission) {
						assertThat(perm, is(new CryptographyOperationPermission("testProfile", "encrypt")));
						throw new SecurityException("Not Allowed");
					}
				}
			});
			CryptoResource cryptoResource = new CryptoResource(getAESKey(), "AES/CBC/PKCS5Padding", "SunJCE", "testProfile");
			cryptoResource.doEncrypt("Test");
		} finally {
			System.setSecurityManager(null);
		}
	}
	
	@Test
	public void testDenyDecrypt() throws Exception {
		try {
			expectedException.expect(SecurityException.class);
			expectedException.expectMessage(is("Not Allowed"));
			System.setSecurityManager(new SecurityManager() {
				@Override
				public void checkPermission(Permission perm) {
					if(perm instanceof CryptographyOperationPermission) {
						assertThat(perm, is(new CryptographyOperationPermission("testProfile", "decrypt")));
						throw new SecurityException("Not Allowed");
					}
				}
			});
			CryptoResource cryptoResource = new CryptoResource(getAESKey(), "AES/CBC/PKCS5Padding", "SunJCE", "testProfile");
			cryptoResource.doDecrypt("acdab1e4bbbcf359b5a5520d4c1fce88");
		} finally {
			System.setSecurityManager(null);
		}
	}
	
	private Key getAESKey() throws KeyProviderException {
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
		key = Arrays.copyOf(key, 16); // use only first 128 bits

		SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
		return secretKeySpec;
	}
}
