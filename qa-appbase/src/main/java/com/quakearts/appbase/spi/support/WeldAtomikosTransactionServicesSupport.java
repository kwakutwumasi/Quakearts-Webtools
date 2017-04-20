package com.quakearts.appbase.spi.support;

import javax.transaction.RollbackException;
import javax.transaction.Status;
import javax.transaction.Synchronization;
import javax.transaction.SystemException;
import javax.transaction.Transaction;
import javax.transaction.UserTransaction;

import org.jboss.weld.transaction.spi.TransactionServices;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.quakearts.appbase.spi.factory.JavaTransactionManagerSpiFactory;

public class WeldAtomikosTransactionServicesSupport implements TransactionServices {

	@Override
	public void cleanup() {
	}

	@Override
	public void registerSynchronization(Synchronization synchronizedObserver) {
		try {
			Transaction transaction = JavaTransactionManagerSpiFactory
					.getInstance()
					.getTransactionManagerSpi()
					.getTransactionManager()
					.getTransaction();
			if(transaction.getStatus()==Status.STATUS_ACTIVE)
				transaction.registerSynchronization(synchronizedObserver);
		} catch (SystemException | IllegalStateException | RollbackException e) {
		}
	}

	@Override
	public boolean isTransactionActive() {
		try {
			Transaction transaction = JavaTransactionManagerSpiFactory
					.getInstance()
					.getTransactionManagerSpi()
					.getTransactionManager()
					.getTransaction();
			return transaction.getStatus()==Status.STATUS_ACTIVE;
		} catch (SystemException | IllegalStateException e) {
		}
		return false;
	}

	@Override
	public UserTransaction getUserTransaction() {
		return new UserTransactionImp();
	}

}
