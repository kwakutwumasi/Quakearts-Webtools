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
