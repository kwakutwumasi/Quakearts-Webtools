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
package com.quakearts.webapp.security.jwt.impl;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;

import com.quakearts.webapp.security.jwt.signature.SignatureBase;

public abstract class KeyStoreSignerBase extends SignerBase {

	public static final String STORE_TYPEPARAMETER = "storeType";
	public static final String STORECREDENTIALSPARAMETER = "password";
	public static final String FILEPARAMETER = "file";
	public static final String ALIASPARAMETER = "alias";
	protected SignatureBase signature;
	protected String alias;
	protected String file;
	protected char[] password;
	protected String storeType;

	protected void doSetParameter(String name, Object parameter) {
		switch (name) {
		case ALIASPARAMETER:
			alias = parameter.toString();
			break;
		case FILEPARAMETER:
			file = parameter.toString();
			break;
		case STORECREDENTIALSPARAMETER:
			password = parameter.toString().toCharArray();
			break;
		case STORE_TYPEPARAMETER:
			storeType = parameter.toString();
			break;
		default:
		}
	}

	protected String getNullParameter() {
		return (alias == null? "alias;" : "") 
				+ (file == null? "file;" : "") 
				+ (password == null? "password;" : "")
				+ (storeType == null? "storeType;" : "");
	}

	@Override
	protected byte[] doSigning(String prepare) throws InvalidKeyException, NoSuchAlgorithmException, SignatureException {
		return getSignature().sign(prepare.getBytes());
	}

	private SignatureBase getSignature() throws SignatureException {	
		if(signature == null){
			if(alias == null 
					|| file == null 
					|| password == null 
					|| storeType == null)
				throw new SignatureException("Incomplete setup. Missing: "+getNullParameter());
			
			signature = createSignatureInstance();
		}
		
		return signature;
	}

	protected abstract SignatureBase createSignatureInstance();
	
	@Override
	protected void doVerification(byte[] payload, byte[] signatureDecoded) throws InvalidKeyException, 
		SignatureException, NoSuchAlgorithmException {
		getSignature().verify(payload, signatureDecoded);
	}

}
