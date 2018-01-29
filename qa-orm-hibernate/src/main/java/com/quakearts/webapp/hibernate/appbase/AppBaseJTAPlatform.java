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
package com.quakearts.webapp.hibernate.appbase;

import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;

import org.hibernate.engine.transaction.jta.platform.internal.AbstractJtaPlatform;

import com.quakearts.appbase.spi.factory.JavaTransactionManagerSpiFactory;

public class AppBaseJTAPlatform extends AbstractJtaPlatform {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6806488231557983385L;

	@Override
	protected TransactionManager locateTransactionManager() {
		return JavaTransactionManagerSpiFactory.getInstance().getJavaTransactionManagerSpi().getTransactionManager();
	}

	@Override
	protected UserTransaction locateUserTransaction() {
		return JavaTransactionManagerSpiFactory.getInstance().getJavaTransactionManagerSpi().getUserTransaction();
	}
	
}
