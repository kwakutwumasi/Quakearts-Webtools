package com.quakearts.appbase.cdi;

import javax.enterprise.inject.Produces;
import javax.transaction.UserTransaction;

import com.quakearts.appbase.cdi.annotation.TransactionHandle;
import com.quakearts.appbase.spi.factory.JavaTransactionManagerSpiFactory;

public class UserTransactionProvider {

	@Produces
	public @TransactionHandle UserTransaction getUserTransaction(){
		return JavaTransactionManagerSpiFactory.getInstance()
			.getTransactionManagerSpi()
			.getUserTransaction();
	}
}
