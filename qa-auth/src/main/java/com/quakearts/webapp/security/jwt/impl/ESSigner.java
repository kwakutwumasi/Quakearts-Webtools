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

import com.quakearts.webapp.security.jwt.JWTHeader;
import com.quakearts.webapp.security.jwt.signature.ESSignature;
import com.quakearts.webapp.security.jwt.signature.ESSignature.ESAlgorithmType;
import com.quakearts.webapp.security.jwt.signature.SignatureBase;

public class ESSigner extends KeyStoreSignerBase {

	private ESAlgorithmType algorithmType;
	
	@Override
	public ESSigner setAlgorithim(String algorithm) {
		algorithmType = ESAlgorithmType.valueOf(algorithm);
		return this;
	}

	@Override
	public ESSigner setParameter(String name, Object parameter) {
		doSetParameter(name, parameter);
		return this;
	}

	@Override
	protected SignatureBase createSignatureInstance() {
		return new ESSignature(algorithmType, alias, file, password, storeType, keyType);
	}

	@Override
	protected void setHeaderAlgorithm(JWTHeader header) {
		header.setAlgorithm(algorithmType.toString());
	}

}
