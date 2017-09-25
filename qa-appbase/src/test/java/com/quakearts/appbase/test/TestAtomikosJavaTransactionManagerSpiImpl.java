package com.quakearts.appbase.test;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;

import org.junit.Test;

import com.quakearts.appbase.spi.JavaNamingDirectorySpi;
import com.quakearts.appbase.spi.JavaTransactionManagerSpi;
import com.quakearts.appbase.spi.factory.JavaNamingDirectorySpiFactory;
import com.quakearts.appbase.spi.impl.AtomikosJavaTransactionManagerSpiImpl;
import com.quakearts.appbase.spi.impl.JavaNamingDirectorySpiImpl;

public class TestAtomikosJavaTransactionManagerSpiImpl {

	@Test
	public void testAtomikosTransactionManagerSpiImpl() {
		JavaNamingDirectorySpi directorySpi = JavaNamingDirectorySpiFactory.getInstance().createJavaNamingDirectorySpi(JavaNamingDirectorySpiImpl.class.getName());
		
		directorySpi.initiateJNDIServices();

		JavaTransactionManagerSpi transactionManagerSpi = new AtomikosJavaTransactionManagerSpiImpl();
		transactionManagerSpi.initiateJavaTransactionManager();
		
		TransactionManager manager = transactionManagerSpi.getTransactionManager();
		
		assertThat(manager != null, is(true));
		
		UserTransaction transaction = transactionManagerSpi.getUserTransaction();
		
		assertThat(transaction != null, is(true));
		
		InitialContext initialContext;
		try {
			initialContext = new InitialContext();
			
			transaction = (UserTransaction) initialContext.lookup("java:comp/UserTransaction");
			assertThat(transaction != null, is(true));
		} catch (NamingException | ClassCastException e) {
			fail("unable to obtain a connection: "+e.getMessage());
		}

		transactionManagerSpi.shutdownJavaTransactionManager();
	}

}
