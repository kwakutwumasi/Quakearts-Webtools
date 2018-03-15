package com.quakearts.appbase.test.experiments;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;
import static org.hamcrest.core.IsNot.*;

import javax.inject.Inject;
import javax.transaction.Status;
import javax.transaction.UserTransaction;

import com.quakearts.appbase.spi.factory.JavaTransactionManagerSpiFactory;

public class TransactionTestHarness {

	@Inject
	TestTransaction testTransaction;
	
	public void runTransactionTest() throws Exception {
		UserTransaction transaction = JavaTransactionManagerSpiFactory
				.getInstance()
				.getJavaTransactionManagerSpi()
				.getUserTransaction();
		assertThat(transaction.getStatus(), is(Status.STATUS_NO_TRANSACTION));
		testTransaction.beginTransaction();
		assertThat(transaction.getStatus(), is(Status.STATUS_ACTIVE));
		testTransaction.continueTransaction();
		assertThat(transaction.getStatus(), is(Status.STATUS_ACTIVE));
		testTransaction.completeTransaction();
		assertThat(transaction.getStatus(), is(not(Status.STATUS_ACTIVE)));
	}
	
	public void runErrorStateActive() {
		testTransaction.beginTransaction();
	}
	
	public void runErrorStateNotActiveEnd() {
		testTransaction.completeTransaction();
	}
	
	public void runErrorStateNotActiveJoin() {
		testTransaction.continueTransaction();
	}

}
