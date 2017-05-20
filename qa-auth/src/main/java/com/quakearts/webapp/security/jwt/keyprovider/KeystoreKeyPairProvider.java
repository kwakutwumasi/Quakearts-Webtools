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
	private static Map<String, KeyPair> loadedKeyPairs = new ConcurrentHashMap<>();
	private String file;
	private String storeType;
	private char[] password;
	
	public String getFile() {
		return file;
	}

	public KeystoreKeyPairProvider setFile(String file) {
		this.file = file;
		return this;
	}

	public String getStoreType() {
		return storeType;
	}

	public KeystoreKeyPairProvider setStoreType(String storeType) {
		this.storeType = storeType;
		return this;
	}

	public char[] getPassword() {
		return password;
	}

	public KeystoreKeyPairProvider setPassword(char[] password) {
		this.password = password;
		return this;
	}

	public KeyPair getKeyPair(String alias) throws InvalidKeyException, 
		KeyStoreException, NoSuchAlgorithmException,
		CertificateException, UnrecoverableEntryException {
		if(loadedKeyPairs.containsKey(alias)){
			return loadedKeyPairs.get(alias);
		} else {
			KeyPair keyPair = loadKeyPair(file, storeType, alias, password);
			loadedKeyPairs.put(alias, keyPair);
			return keyPair;
		}
	}
	
	private KeyPair loadKeyPair(String file, String storeType, String alias, char[] password)
			throws InvalidKeyException, KeyStoreException, NoSuchAlgorithmException, CertificateException, 
			UnrecoverableEntryException {
		try (InputStream in = getFile(file)) {
			KeyStore keyStore = KeyStore.getInstance(storeType);
			keyStore.load(in, password);
			
			Entry entry = keyStore.getEntry(alias, new KeyStore.PasswordProtection(password));
			if(entry instanceof PrivateKeyEntry){
				PrivateKeyEntry privateKeyEntry  = (PrivateKeyEntry) entry;
				validateKeyType(privateKeyEntry);
				return new KeyPair(privateKeyEntry.getCertificate().getPublicKey(), privateKeyEntry.getPrivateKey());
			} else {
				throw new InvalidKeyException("Alias "+alias+" in file " + file+" is not a valid PrivateKeyEntry");
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
}
