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
package com.quakearts.security.cryptography.factory;

import java.io.IOException;
import java.security.GeneralSecurityException;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

import com.quakearts.security.cryptography.CryptoResource;
import com.quakearts.security.cryptography.cdi.CryptoInstance;
import com.quakearts.security.cryptography.cdi.CryptoResourceHandle;
import com.quakearts.security.cryptography.exception.CryptoResourceRuntimeException;

public class CryptoServiceProducer {
	private CryptoServiceProducer() {}
	
	@Produces
	public static CryptoService getCryptoService() {
		return CryptoServiceImpl.getInstance();
	}
	
	@Produces @CryptoResourceHandle
	public static CryptoResource getCryptoService(InjectionPoint injectionPoint) {
		CryptoInstance instanceHandle = injectionPoint.getAnnotated().getAnnotation(CryptoInstance.class);
		if(instanceHandle!=null) {
			try {
				return CryptoServiceImpl.getInstance().getCryptoResource(instanceHandle.value());
			} catch (ReflectiveOperationException | GeneralSecurityException | IOException e) {
				throw new CryptoResourceRuntimeException(e);
			}
		} else {
			throw new CryptoResourceRuntimeException("Missing CryptoInstance handle");
		}
	}
}
