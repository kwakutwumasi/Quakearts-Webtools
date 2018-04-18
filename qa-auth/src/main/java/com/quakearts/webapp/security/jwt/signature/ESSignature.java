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

import com.quakearts.webapp.security.jwt.keyprovider.impl.ECKeystoreKeyPairProvider;
import com.quakearts.webapp.security.jwt.keyprovider.impl.ECKeystoreKeyPairProvider.ECDSAKeySize;

public class ESSignature extends SignatureBase {

	private ECKeystoreKeyPairProvider keyProvider;
	private String alias;
	private ESAlgorithmType algorithmType;
	
	public static enum ESAlgorithmType {
		ES256("SHA256withECDSA", ECDSAKeySize.ES256KEY),
		ES384("SHA384withECDSA", ECDSAKeySize.ES384KEY),
		ES512("SHA512withECDSA", ECDSAKeySize.ES512KEY);
		
		private String algorithm;
		private ECDSAKeySize keySize;
		
		public String getAlgorithm() {
			return algorithm;
		}
		
		public ECDSAKeySize getKeySize() {
			return keySize;
		}

		private ESAlgorithmType(String algorithm, ECDSAKeySize keySize) {
			this.algorithm = algorithm;
			this.keySize = keySize;
		}
	}
	
	public ESSignature(ESAlgorithmType algorithmType, String alias, String file, char[] password, String storeType) {
		keyProvider = new ECKeystoreKeyPairProvider(algorithmType.getKeySize());
		keyProvider.setFile(file);
		keyProvider.setPassword(password);
		keyProvider.setStoreType(storeType);
		this.algorithmType = algorithmType;
		this.alias = alias;
	}
	
	@Override
	protected Signature createSignatureImplementation() throws NoSuchAlgorithmException {
		return Signature.getInstance(algorithmType.getAlgorithm());
	}

	@Override
	protected PrivateKey getPrivateKey() throws InvalidKeyException {
		try {
			return keyProvider.getKeyPair(alias).getPrivate();
		} catch (KeyStoreException | NoSuchAlgorithmException | CertificateException
				| UnrecoverableEntryException e) {
			throw new InvalidKeyException("Unable to load key",e);
		}
	}

	@Override
	protected PublicKey getPublicKey() throws InvalidKeyException {
		try {
			return keyProvider.getKeyPair(alias).getPublic();
		} catch (KeyStoreException | NoSuchAlgorithmException | CertificateException
				| UnrecoverableEntryException e) {
			throw new InvalidKeyException("Unable to load key",e);
		}
	}

}
