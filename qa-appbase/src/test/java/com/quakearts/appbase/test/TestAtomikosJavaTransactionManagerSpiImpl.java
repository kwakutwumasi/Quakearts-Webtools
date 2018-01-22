package com.quakearts.appbase.test;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;

import javax.naming.InitialContext;
import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;

import org.junit.Test;

import com.quakearts.appbase.spi.JavaTransactionManagerSpi;
import com.quakearts.appbase.spi.factory.JavaNamingDirectorySpiFactory;
import com.quakearts.appbase.spi.factory.JavaTransactionManagerSpiFactory;
import com.quakearts.appbase.spi.impl.AtomikosJavaTransactionManagerSpiImpl;
import com.quakearts.appbase.spi.impl.JavaNamingDirectorySpiImpl;

public class TestAtomikosJavaTransactionManagerSpiImpl {

	@Test
	public void testAtomikosTransactionManagerSpiImpl() throws Exception {
		JavaNamingDirectorySpiFactory.getInstance()
			.createJavaNamingDirectorySpi(JavaNamingDirectorySpiImpl.class.getName())
			.initiateJNDIServices();
		
		JavaTransactionManagerSpiFactory.getInstance().createJavaTransactionManagerSpi(AtomikosJavaTransactionManagerSpiImpl.class.getName());		
		JavaTransactionManagerSpi transactionManagerSpi = JavaTransactionManagerSpiFactory.getInstance().getJavaTransactionManagerSpi();		
		assertThat(transactionManagerSpi == null, is(false));
		transactionManagerSpi.initiateJavaTransactionManager();
		TransactionManager manager = transactionManagerSpi.getTransactionManager();
		assertThat(manager != null, is(true));
		UserTransaction transaction = transactionManagerSpi.getUserTransaction();
		assertThat(transaction != null, is(true));
		
		InitialContext initialContext;
		initialContext = new InitialContext();
		
		transaction = (UserTransaction) initialContext.lookup("java:comp/UserTransaction");
		assertThat(transaction != null, is(true));

		transactionManagerSpi.shutdownJavaTransactionManager();
		transactionManagerSpi.shutdownJavaTransactionManager();
		JavaNamingDirectorySpiFactory.getInstance()
		.getJavaNamingDirectorySpi()
		.shutdownJNDIService();
		new TestAppBaseMainStartup().clearInstanceVariables();
	}

}
