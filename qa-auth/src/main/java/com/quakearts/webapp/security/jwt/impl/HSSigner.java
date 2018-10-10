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

import com.quakearts.webapp.security.jwt.JWTHeader;
import com.quakearts.webapp.security.jwt.signature.HMac;
import com.quakearts.webapp.security.jwt.signature.HMac.HSAlgorithmType;
import com.quakearts.webapp.security.jwt.signature.MacBase;

public class HSSigner extends SignerBase {

	public static final String SECRETPARAMETER = "secret";
	private HMac mac;
	private HSAlgorithmType algorithmType;
	private byte[] key;
		
	@Override
	protected byte[] doSigning(String prepare) throws InvalidKeyException, NoSuchAlgorithmException {	
		return getMac().sign(prepare.getBytes());
	}

	private MacBase getMac() throws InvalidKeyException {
		if(algorithmType == null || key == null)
			throw new InvalidKeyException("Incomplete setup. Missing: "
					+(algorithmType==null?"algorithmType;":"")
					+(key==null?"key;":""));
		
		if(mac == null)
			mac = new HMac(algorithmType, key);
		
		return mac;
	}

	@Override
	public HSSigner setAlgorithim(String algorithm) {
		algorithmType = HSAlgorithmType.valueOf(algorithm.toString());
		return this;
	}
	
	@Override
	public HSSigner setParameter(String name, Object parameter) {
		if(SECRETPARAMETER.equals(name))
			key = parameter.toString().getBytes();
		
		return this;
	}

	@Override
	protected void setHeaderAlgorithm(JWTHeader header) {
		header.setAlgorithm(algorithmType.toString());
	}

	@Override
	protected void doVerification(byte[] payload, byte[] signatureDecoded)
			throws InvalidKeyException, SignatureException, NoSuchAlgorithmException {
		byte[] comparedSignature = getMac().sign(payload);
		
		if(! new String(signatureDecoded).equals(new String(comparedSignature)))
			throw new SignatureException("Invalid signature");
	}
}
