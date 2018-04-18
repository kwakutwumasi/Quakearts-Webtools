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

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public abstract class MacBase {
	
	public byte[] sign(byte[] payload) throws InvalidKeyException, NoSuchAlgorithmException{
		Mac mac = createMacImplementation();
		SecretKeySpec key = getKey();
		
		mac.init(key);
		return mac.doFinal(payload);
	}

	protected abstract SecretKeySpec getKey();
	protected abstract Mac createMacImplementation() throws NoSuchAlgorithmException;
}
