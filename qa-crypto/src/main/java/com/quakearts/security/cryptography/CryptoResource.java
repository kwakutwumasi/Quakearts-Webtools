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
package com.quakearts.security.cryptography;

import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.regex.Pattern;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.security.auth.DestroyFailedException;
import javax.security.auth.Destroyable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.quakearts.security.cryptography.exception.IllegalCryptoActionException;
import com.quakearts.security.cryptography.exception.KeyProviderException;
import com.quakearts.security.cryptography.permission.CryptographyOperationPermission;
import com.quakearts.security.cryptography.provider.KeyProvider;

/**A Cryptographic resource that can be used for symetric encryptions.
 * If the algorithm requires an initialization vector, the resource automatically
 * generates one and appends it to the encrypted value. This makes it easy to decrypt the 
 * value without having to store the initialization vector separately.
 * 
 * This class is thread safe
 * @author kwakutwumasi-afriyie
 *
 */
public class CryptoResource {
	private static final String INVALID_HEXIDECIMAL_STRING = "Invalid hexidecimal string";
	private KeyProvider keyProvider;
	private CryptographyOperationPermission decryptPermission;
	private CryptographyOperationPermission encryptPermission;
	private boolean generatesIv;
	
	private static final Logger log = LoggerFactory.getLogger(CryptoResource.class);

	private static final Pattern ivPatterns = Pattern.compile(".+/((CBC)|(CFB)|(OFB)|(CTR))/?(.*)?");
	private Cipher cipher;
	
	/**Simple constructor, taking the key and the algorithm instance
	 * @param keyProvider
	 * @param instance
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws NoSuchProviderException 
	 */
	public CryptoResource(KeyProvider keyProvider, String instance, String provider) 
			throws NoSuchAlgorithmException, NoSuchPaddingException, NoSuchProviderException {
		this(keyProvider, instance, provider, null);
	}
	
	/**Constructor for use in environments with a security manager. The profile
	 * is used to load the {@link java.security.Permission Permission} object. It points
	 * to the profile name in the security properties file to load.
	 * @param keyProvider
	 * @param instance
	 * @param securityProfile
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws NoSuchProviderException 
	 */
	public CryptoResource(KeyProvider keyProvider, String instance, String provider, String securityProfile)
			throws NoSuchAlgorithmException, NoSuchPaddingException, NoSuchProviderException {
		this.keyProvider = keyProvider;
		cipher = Cipher.getInstance(instance, provider);
		generatesIv = ivPatterns.matcher(instance).matches();
		if (System.getSecurityManager() != null && securityProfile != null) {
			decryptPermission = new CryptographyOperationPermission(securityProfile, "decrypt");
			encryptPermission = new CryptographyOperationPermission(securityProfile, "encrypt");
		}
	}

	/**Convert a byte array to hexadecimal string
	 * @param buf the byte array
	 * @return a {@linkplain String} of the byte array in hexadecimal form
	 */
	public static String byteAsHex(byte[] buf) {
		if (buf == null)
			return "";

		StringBuilder strbuf = new StringBuilder(buf.length * 2);
		int i;

		for (i = 0; i < buf.length; i++) {
			if (((int) buf[i] & 0xff) < 0x10)
				strbuf.append("0");

			strbuf.append(Long.toString((int) buf[i] & 0xff, 16));
		}

		return strbuf.toString();
	}

	/**Convert a hexadecimal string to byte array
	 * @param hexstring the hexadecimal string
	 * @return a byte array
	 */
	public static byte[] hexAsByte(String hexstring) throws IllegalCryptoActionException {
		if (hexstring == null || hexstring.isEmpty())
			return new byte[0];

		if (hexstring.length() % 2 != 0) {
			throw new IllegalCryptoActionException(INVALID_HEXIDECIMAL_STRING);
		}
		byte[] results = new byte[hexstring.length() / 2];
		try {
			for (int i = 0; i < hexstring.length() - 1; i += 2) {
				results[i / 2] = ((byte) Long.parseLong(hexstring.substring(i, i + 2), 16));
			}
		} catch (NumberFormatException e) {
			throw new IllegalCryptoActionException(INVALID_HEXIDECIMAL_STRING, e);
		}

		return results;
	}

	/**Decrypt the ciphered text
	 * @param cipheredtext the text to decrypt
	 * @return the decrypted cipher text
	 * @throws IllegalCryptoActionException if decryption failed
	 * @throws KeyProviderException 
	 */
	public String doDecrypt(String cipheredtext) throws IllegalCryptoActionException, KeyProviderException {
		if (cipheredtext == null || cipheredtext.trim().isEmpty())
			return "";

		String plainString = null;
		plainString = new String(doDecrypt(hexAsByte(cipheredtext)));
		return plainString;
	}

	/**Encrypt the plain text
	 * @param plaintext the text to encrypt
	 * @return the encrypted plain text
	 * @throws IllegalCryptoActionException if encryption failed
	 * @throws KeyProviderException if there is an exception loading the encryption keys
	 */
	public String doEncrypt(String plaintext) throws IllegalCryptoActionException, KeyProviderException {
		if (plaintext == null || plaintext.trim().isEmpty())
			return "";

		return byteAsHex(doEncrypt(plaintext.getBytes()));
	}

	/**Decrypt the ciphered text
	 * @param cipheredtext the text to decrypt
	 * @return the decrypted cipher text
	 * @throws IllegalCryptoActionException if decryption failed
	 * @throws KeyProviderException 
	 */
	public byte[] doDecrypt(byte[] cipheredtext) throws IllegalCryptoActionException, KeyProviderException {
		SecurityManager manager = System.getSecurityManager();
		if (manager != null) {
			manager.checkPermission(decryptPermission);
		}

		if (cipheredtext == null || cipheredtext.length == 0)
			return new byte[0];

		Key key = keyProvider.getKey();
		
		try {
			byte[] ivPart = null;
			byte[] cipheredPart = null;
			if(generatesIv) {
				ivPart = new byte[cipher.getBlockSize()];
				cipheredPart = new byte[cipheredtext.length - ivPart.length];
				System.arraycopy(cipheredtext, 0, cipheredPart, 0, cipheredPart.length);
				System.arraycopy(cipheredtext, cipheredPart.length, ivPart, 0, ivPart.length);
			}
			
			synchronized(this) {
				if(generatesIv) {
					cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(ivPart));
					return cipher.doFinal(cipheredPart);
				} else {
					cipher.init(Cipher.DECRYPT_MODE, key);
					return cipher.doFinal(cipheredtext);
				}
			}
		} catch (GeneralSecurityException e) {
			throw new IllegalCryptoActionException(
					"Error decrypting text.\nException " + e.getClass().getName() + ". Message is " + e.getMessage(),e);
		} finally {
			destroyKey(key);
		}
	}

	/**Encrypt the plain text
	 * @param plaintext the text to encrypt
	 * @return the encrypted plain text
	 * @throws IllegalCryptoActionException if encryption failed
	 * @throws KeyProviderException 
	 */
	public byte[] doEncrypt(byte[] plaintext) throws IllegalCryptoActionException, KeyProviderException {
		SecurityManager manager = System.getSecurityManager();
		if (manager != null) {
			manager.checkPermission(encryptPermission);
		}

		if (plaintext == null || plaintext.length == 0)
			return new byte[0];

		Key key = keyProvider.getKey();
		try {
			byte[] ciphertext;
			byte[] iv = null;
			synchronized(this) {
				cipher.init(Cipher.ENCRYPT_MODE, key);
				ciphertext = cipher.doFinal(plaintext);
				if(generatesIv)
					iv = cipher.getIV();
			}
			
			if(iv!=null) {
				byte[] enhancedCiphertext = new byte[ciphertext.length+iv.length];
				System.arraycopy(ciphertext, 0, enhancedCiphertext, 0, ciphertext.length);
				System.arraycopy(iv, 0, enhancedCiphertext, ciphertext.length, iv.length);
				return enhancedCiphertext;
			} else {
				return ciphertext;
			}
			
		} catch (GeneralSecurityException e) {
			throw new IllegalCryptoActionException("Cryptographic service failure.\n" + e.getMessage(), e.getCause());
		} finally {
			destroyKey(key);
		}
	}

	private void destroyKey(Key key) {
		if(key instanceof Destroyable) {
			try {
				((Destroyable)key).destroy();
			} catch (DestroyFailedException e) {
				//The default implementation simply throws DestroyFailedException
				//Simply ignore if so. Otherwise, show warning
				if(e.getMessage()!=null)
					log.warn("The key was not destroyed. {}", e.getMessage());
				// Just log this, since the default implementation of destroyable throws DestroyFailedException
			}
		}
	}
}
