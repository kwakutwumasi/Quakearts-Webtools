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
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;

public abstract class SignatureBase {

	public byte[] sign(byte[] payload) throws InvalidKeyException, 
		SignatureException, NoSuchAlgorithmException {
		Signature signature = createSignatureImplementation();
		PrivateKey key = getPrivateKey();
		signature.initSign(key);
		signature.update(payload);
		
		return signature.sign();
	}

	protected abstract Signature createSignatureImplementation() throws NoSuchAlgorithmException;
	
	protected abstract PrivateKey getPrivateKey() throws InvalidKeyException;

	public void verify(byte[] payloadData, byte[] signatureData) throws InvalidKeyException, 
		SignatureException, NoSuchAlgorithmException {
		Signature signature = createSignatureImplementation();
		PublicKey key = getPublicKey();
		signature.initVerify(key);
		signature.update(payloadData);
		signature.verify(signatureData);		
	}

	protected abstract PublicKey getPublicKey() throws InvalidKeyException;;
}
