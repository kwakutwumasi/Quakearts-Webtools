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
package com.quakearts.security.cryptography.jboss;

import com.quakearts.security.cryptography.CryptoProxy;
import com.quakearts.security.cryptography.CryptoResource;

public class CryptoProxyImpl implements CryptoProxy {
	
	private CryptoService service;
	
	protected CryptoProxyImpl(CryptoService service){
		this.service = service;
	}
	@SuppressWarnings("unused")
	private CryptoProxyImpl(){		
	}
	
	public CryptoResource getResource() throws Exception {
		return service.getResource();
	}
}
