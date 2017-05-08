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
package com.quakearts.appbase.spi.factory;

import com.quakearts.appbase.Main;
import com.quakearts.appbase.exception.ConfigurationException;
import com.quakearts.appbase.spi.JavaTransactionManagerSpi;

public class JavaTransactionManagerSpiFactory {

	private JavaTransactionManagerSpiFactory() {
	}
	
	private static JavaTransactionManagerSpiFactory instance = new JavaTransactionManagerSpiFactory();
	
	public static JavaTransactionManagerSpiFactory getInstance() {
		return instance;
	}
	
	private JavaTransactionManagerSpi transactionManagerSpi;
	
	public JavaTransactionManagerSpi getTransactionManagerSpi() {
		return transactionManagerSpi;
	}
	
	public JavaTransactionManagerSpi createJavaTransactionManagerSpi(String javaTmSpiClassname) {
		try {
			Class<?> javaTmSpiClass = Class.forName(javaTmSpiClassname);
			Main.log.info("JavaTransactionManagerSpi class: "+javaTmSpiClassname+" loaded");
			transactionManagerSpi = (JavaTransactionManagerSpi) javaTmSpiClass.newInstance();
			return transactionManagerSpi;
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException| ClassCastException e) {
			throw new ConfigurationException("Unable to instantiate class "+javaTmSpiClassname, e);
		}
	}

}
