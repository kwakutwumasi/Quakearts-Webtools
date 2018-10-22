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
package com.quakearts.webapp.security.jwt.keyprovider;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.KeyStore.Entry;
import java.security.KeyStore.PrivateKeyEntry;
import java.security.KeyStore.SecretKeyEntry;
import java.security.KeyStore.TrustedCertificateEntry;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class KeystoreKeyPairProvider {
	private static Map<String, Entry> loadedKeyPairs = new ConcurrentHashMap<>();
	private static final PrivateKey PRIVATEKEY = new NullPrivateKey();
	private String file;
	private String storeType;
	private char[] password;
	private KeyType keyType = KeyType.PUBLICPRIVATEPAIR;
	public enum KeyType {
		PUBLICONLY,
		PUBLICPRIVATEPAIR,
	}

	public KeystoreKeyPairProvider setFile(String file) {
		this.file = file;
		return this;
	}

	public KeystoreKeyPairProvider setStoreType(String storeType) {
		this.storeType = storeType;
		return this;
	}

	public KeystoreKeyPairProvider setPassword(char[] password) {
		this.password = password;
		return this;
	}
	
	public KeystoreKeyPairProvider setKeyType(String keyType) {
		if(keyType!=null)
			this.keyType = KeyType.valueOf(keyType.toUpperCase());
		
		return this;
	}

	public KeyPair getKeyPair(String alias) throws InvalidKeyException, KeyStoreException, NoSuchAlgorithmException,
			CertificateException, UnrecoverableEntryException {
		Entry entry;
		if (loadedKeyPairs.containsKey(file + ":" + alias)) {
			entry = loadedKeyPairs.get(file + ":" + alias);
		} else {
			entry = loadKeyPair(file, storeType, alias, password);
			loadedKeyPairs.put(file + ":" + alias, entry);
		}
		validateKeyType(entry);
		if(entry instanceof PrivateKeyEntry) {
			PrivateKeyEntry privateKeyEntry = (PrivateKeyEntry) entry;
			return new KeyPair(privateKeyEntry.getCertificate().getPublicKey(), privateKeyEntry.getPrivateKey());			
		} else {
			TrustedCertificateEntry trustedCertificateEntry = (TrustedCertificateEntry) entry;
			return new KeyPair(trustedCertificateEntry.getTrustedCertificate().getPublicKey(), PRIVATEKEY);			
		}
	}

	private Entry loadKeyPair(String file, String storeType, String alias, char[] password)
			throws InvalidKeyException, KeyStoreException, NoSuchAlgorithmException, CertificateException,
			UnrecoverableEntryException {
		try (InputStream in = getFile(file)) {
			KeyStore keyStore = KeyStore.getInstance(storeType);
			keyStore.load(in, password);

			Entry entry;
			if(keyType == KeyType.PUBLICPRIVATEPAIR) {
				entry = keyStore.getEntry(alias, new KeyStore.PasswordProtection(password));
			} else {
				entry = keyStore.getEntry(alias, null);
			}

			if (entry !=null && !(entry instanceof SecretKeyEntry)) {
				return entry;
			} else {
				throw new InvalidKeyException(
						"Alias " + alias + " in file " + file + " is not a valid key store entry");
			}
		} catch (IOException e) {
			throw new InvalidKeyException("Unable to load file: " + file, e);
		}
	}

	protected abstract void validateKeyType(Entry privateKeyEntry) throws InvalidKeyException;

	private InputStream getFile(String file) throws InvalidKeyException {
		try {
			return new FileInputStream(file);
		} catch (FileNotFoundException e) {
			InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(file);
			if (in == null)
				throw new InvalidKeyException("Unable to load file: " + file, e);
			return in;
		}
	}

	protected void validatePrivateKeyEntry(String algorithm, Entry entry)
			throws InvalidKeyException {
		if (entry == null)
			throw new InvalidKeyException("PrivateKeyEntry is null");
		if(entry instanceof PrivateKeyEntry) {
			PrivateKeyEntry privateKeyEntry = (PrivateKeyEntry) entry;
			if (privateKeyEntry.getCertificate() == null)
				throw new InvalidKeyException("PrivateKeyEntry.getCertificate() is null");
	
			if (privateKeyEntry.getCertificate().getPublicKey() == null)
				throw new InvalidKeyException("PrivateKeyEntry.getCertificate().getPublicKey() is null");
	
			if (!algorithm.equals(privateKeyEntry.getCertificate().getPublicKey().getAlgorithm()))
				throw new InvalidKeyException("Key is not a valid " + algorithm + " key");
		} else {
			TrustedCertificateEntry trustedCertificateEntry = (TrustedCertificateEntry) entry;
			if (trustedCertificateEntry.getTrustedCertificate().getPublicKey() == null)
				throw new InvalidKeyException("TrustedCertificateEntry.getTrustedCertificate().getPublicKey() is null");
			
			if (!algorithm.equals(trustedCertificateEntry.getTrustedCertificate().getPublicKey().getAlgorithm()))
				throw new InvalidKeyException("Key is not a valid " + algorithm + " key");
		}
	}
	
	protected PublicKey getPublicKey(Entry entry) {
		if(entry instanceof PrivateKeyEntry) {
			return ((PrivateKeyEntry) entry).getCertificate().getPublicKey();
		} else {
			return ((TrustedCertificateEntry) entry).getTrustedCertificate().getPublicKey();
		}
	}
	
	@SuppressWarnings("serial")
	public static class NullPrivateKey implements PrivateKey {

		private static final String NOT_SUPPORTED = "Key operations not supported";

		@Override
		public String getAlgorithm() {
			throw new UnsupportedOperationException(NOT_SUPPORTED);
		}

		@Override
		public String getFormat() {
			throw new UnsupportedOperationException(NOT_SUPPORTED);
		}

		@Override
		public byte[] getEncoded() {
			throw new UnsupportedOperationException(NOT_SUPPORTED);
		}
		
	}
}
