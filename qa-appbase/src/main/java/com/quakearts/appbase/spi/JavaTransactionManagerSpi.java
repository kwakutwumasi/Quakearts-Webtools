package com.quakearts.appbase.spi;

import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;

public interface JavaTransactionManagerSpi {
	void initiateJavaTransactionManager();
	void shutdownJavaTransactionManager();
	TransactionManager getTransactionManager();
	UserTransaction getUserTransaction();
}
