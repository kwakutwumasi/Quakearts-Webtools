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

import com.quakearts.security.cryptography.provider.KeyProvider;

public class KeyProviderFactory {
	private KeyProviderFactory(){
	}
	
	public static KeyProvider createKeyProvider(String keyProviderClass) throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		Class<?> fileformatterclass = Class.forName(keyProviderClass);
        Object obj = fileformatterclass.newInstance();
        if(obj instanceof KeyProvider){
        	return (KeyProvider)obj;
        } else {
        	throw new ClassNotFoundException(keyProviderClass+" is not a valid "+KeyProvider.class.getName());
        }
	}
}
