package com.quakearts.appbase.cdi;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.transaction.UserTransaction;

import com.quakearts.appbase.cdi.annotation.Transaction;
import com.quakearts.appbase.spi.factory.JavaTransactionManagerSpiFactory;

@ApplicationScoped
public class UserTransactionProvider {

	@Produces @Transaction
	public UserTransaction getUserTransaction(){
		return JavaTransactionManagerSpiFactory.getInstance()
			.getTransactionManagerSpi()
			.getUserTransaction();
	}
}
