package com.quakearts.webapp.security.jwt.signature;

import java.security.InvalidKeyException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;

import com.quakearts.webapp.security.jwt.keyprovider.impl.RSAKeystoreKeyPairProvider;

public class RSASignature extends SignatureBase {
	private RSAKeystoreKeyPairProvider keyProvider;
	private String alias;
	private RSAAlgorithmType algorithmType;
	
	public static enum RSAAlgorithmType {
		RS256("SHA256withRSA"),
		RS384("SHA384withRSA"),
		RS512("SHA512withRSA");
		
		private String algorithm;

		private RSAAlgorithmType(String algorithm) {
			this.algorithm = algorithm;
		}
		
		public String getAlgorithm() {
			return algorithm;
		}
	}
	
	public RSASignature(RSAAlgorithmType algorithmType, String alias, String file, char[] password, String storeType) {
		keyProvider = new RSAKeystoreKeyPairProvider();
		keyProvider.setFile(file);
		keyProvider.setPassword(password);
		keyProvider.setStoreType(storeType);
		this.alias = alias;
		this.algorithmType = algorithmType;
	}
	
	@Override
	protected Signature createSignatureImplementation() throws NoSuchAlgorithmException {
		return Signature.getInstance(algorithmType.getAlgorithm());
	}

	@Override
	protected PrivateKey getPrivateKey() throws InvalidKeyException {
		try {
			return keyProvider.getKeyPair(alias).getPrivate();
		} catch (KeyStoreException | NoSuchAlgorithmException | CertificateException | UnrecoverableEntryException e) {
			throw new InvalidKeyException("Unable to load key", e);
		}
	}

	@Override
	protected PublicKey getPublicKey() throws InvalidKeyException {
		try {
			return keyProvider.getKeyPair(alias).getPublic();
		} catch (KeyStoreException | NoSuchAlgorithmException | CertificateException | UnrecoverableEntryException e) {
			throw new InvalidKeyException("Unable to load key", e);
		}
	}

}
