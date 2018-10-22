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
	
	public enum RSAAlgorithmType {
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
	
	public RSASignature(RSAAlgorithmType algorithmType, String alias, String file, 
			char[] password, String storeType, String keyType) {
		keyProvider = new RSAKeystoreKeyPairProvider();
		keyProvider.setFile(file)
			.setPassword(password)
			.setStoreType(storeType)
			.setKeyType(keyType);
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
