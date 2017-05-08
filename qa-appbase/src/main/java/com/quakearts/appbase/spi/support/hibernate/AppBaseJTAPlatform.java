/*******************************************************************************
 * Copyright (C) 2017 Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com>.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com> - initial API and implementation
 ******************************************************************************/
package com.quakearts.appbase.spi.support.hibernate;

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
		return JavaTransactionManagerSpiFactory.getInstance().getTransactionManagerSpi().getTransactionManager();
	}

	@Override
	protected UserTransaction locateUserTransaction() {
		return JavaTransactionManagerSpiFactory.getInstance().getTransactionManagerSpi().getUserTransaction();
	}
	
}
