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
package com.quakearts.appbase.spi.impl;

import javax.enterprise.inject.Vetoed;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.transaction.SystemException;
import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;

import com.atomikos.icatch.jta.UserTransactionManager;
import com.quakearts.appbase.exception.ConfigurationException;
import com.quakearts.appbase.spi.JavaTransactionManagerSpi;
import com.quakearts.appbase.spi.factory.JavaNamingDirectorySpiFactory;

@Vetoed
public class AtomikosJavaTransactionManagerSpiImpl implements JavaTransactionManagerSpi {

	private static final String JAVA_COMP_USER_TRANSACTION = "java:comp/UserTransaction";
	private static final String JAVA_COMP = "java:comp";
	private UserTransactionManager tm;

	public TransactionManager getTransactionManager() {
		return getTm();
	}

	private UserTransactionManager getTm() {
		return tm;
	}

	@Override
	public UserTransaction getUserTransaction() {
		return getTm();
	}

	@Override
	public void initiateJavaTransactionManager() {
		if (tm == null) {
			tm = new UserTransactionManager();
			try {
				tm.init();
			} catch (SystemException e) {
				throw new ConfigurationException("Unable to create Transaction Manager", e);
			}
			try {
				JavaNamingDirectorySpiFactory.getInstance().getJavaNamingDirectorySpi().createContext(JAVA_COMP);

				JavaNamingDirectorySpiFactory.getInstance().getJavaNamingDirectorySpi().getInitialContext()
						.bind(JAVA_COMP_USER_TRANSACTION, tm);
			} catch (NamingException e) {
				throw new ConfigurationException(e.getMessage(), e);
			}

		}
	}

	@Override
	public void shutdownJavaTransactionManager() {
		if (tm != null) {
			InitialContext initialContext =
					JavaNamingDirectorySpiFactory.getInstance()
					.getJavaNamingDirectorySpi().getInitialContext();
			try {
				initialContext.unbind(JAVA_COMP_USER_TRANSACTION);
			} catch (NamingException e) {
				//Do nothing
			}
			try {
				initialContext.unbind(JAVA_COMP);
			} catch (NamingException e) {
				//Do nothing
			}
			tm.close();
			tm = null;
		}
	}
}
