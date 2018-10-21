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
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class KeystoreKeyPairProvider {
	private static Map<String, PrivateKeyEntry> loadedKeyPairs = new ConcurrentHashMap<>();
	private String file;
	private String storeType;
	private char[] password;

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

	public KeyPair getKeyPair(String alias) throws InvalidKeyException, KeyStoreException, NoSuchAlgorithmException,
			CertificateException, UnrecoverableEntryException {
		PrivateKeyEntry entry;
		if (loadedKeyPairs.containsKey(file + ":" + alias)) {
			entry = loadedKeyPairs.get(file + ":" + alias);
		} else {
			entry = loadKeyPair(file, storeType, alias, password);
			loadedKeyPairs.put(file + ":" + alias, entry);
		}
		validateKeyType(entry);
		return new KeyPair(entry.getCertificate().getPublicKey(), entry.getPrivateKey());
	}

	private PrivateKeyEntry loadKeyPair(String file, String storeType, String alias, char[] password)
			throws InvalidKeyException, KeyStoreException, NoSuchAlgorithmException, CertificateException,
			UnrecoverableEntryException {
		try (InputStream in = getFile(file)) {
			KeyStore keyStore = KeyStore.getInstance(storeType);
			keyStore.load(in, password);

			Entry entry = keyStore.getEntry(alias, new KeyStore.PasswordProtection(password));
			if (entry instanceof PrivateKeyEntry) {
				return (PrivateKeyEntry) entry;
			} else {
				throw new InvalidKeyException(
						"Alias " + alias + " in file " + file + " is not a valid PrivateKeyEntry");
			}
		} catch (IOException e) {
			throw new InvalidKeyException("Unable to load file: " + file, e);
		}
	}

	protected abstract void validateKeyType(PrivateKeyEntry privateKeyEntry) throws InvalidKeyException;

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

	protected void validatePrivateKeyEntry(String algorithm, PrivateKeyEntry privateKeyEntry)
			throws InvalidKeyException {
		if (privateKeyEntry == null)
			throw new InvalidKeyException("PrivateKeyEntry is null");

		if (privateKeyEntry.getCertificate() == null)
			throw new InvalidKeyException("PrivateKeyEntry.getCertificate() is null");

		if (privateKeyEntry.getCertificate().getPublicKey() == null)
			throw new InvalidKeyException("PrivateKeyEntry.getCertificate().getPublicKey() is null");

		if (!algorithm.equals(privateKeyEntry.getCertificate().getPublicKey().getAlgorithm()))
			throw new InvalidKeyException("Key is not a valid " + algorithm + " key");
	}
}
