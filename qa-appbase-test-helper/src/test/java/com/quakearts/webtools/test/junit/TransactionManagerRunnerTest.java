package com.quakearts.webtools.test.junit;

import static org.junit.Assert.*;

import javax.inject.Inject;
import javax.naming.InitialContext;
import javax.transaction.UserTransaction;

import static org.hamcrest.core.Is.*;
import static org.hamcrest.core.IsNull.*;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.quakearts.webtools.test.TransactionManagerRunner;
import com.quakearts.webtools.test.helpers.TestInject;

@RunWith(TransactionManagerRunner.class)
public class TransactionManagerRunnerTest {

	@Inject
	private TestInject inject;
	
	@Test
	public void testCDINotInjecting() throws Exception {
		assertThat(inject, is(nullValue()));
	}

	@Test
	public void testJavaTransactionManager() throws Exception {
		InitialContext context = new InitialContext();
		
		UserTransaction transaction = (UserTransaction) 
				context.lookup("java:comp/UserTransaction");
		
		assertThat(transaction, is(notNullValue()));
		
		transaction.begin();
		transaction.commit();
	}

}
