package com.quakearts.webtools.test.junit;

import static org.hamcrest.core.Is.*;
import static org.hamcrest.core.IsNull.*;
import static org.junit.Assert.*;

import java.sql.Connection;

import javax.inject.Inject;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import javax.transaction.UserTransaction;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.quakearts.webtools.test.AllServicesRunner;
import com.quakearts.webtools.test.helpers.TestClient;
import com.quakearts.webtools.test.helpers.TestClientBuilder;
import com.quakearts.webtools.test.helpers.TestInject;

@RunWith(AllServicesRunner.class)
public class AllServicesTest {

	@Inject
	private TestInject inject;
	
	@Test
	public void testInject() throws Exception{
		assertThat(inject, is(notNullValue()));
		assertThat(inject.sayHello(), is("Hi! Runner is working!"));
	}

	@Test
	public void testWebServer() throws Exception {
		TestClient client = new TestClientBuilder()
				.setURLAs("http://localhost:8280")
				.thenBuild();
		
		assertThat(inject.sayHello(), is(client.makeCall()));
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

	@Test
	public void testGetDataSource() throws Exception {
		InitialContext context = new InitialContext();
		
		DataSource dataSource = (DataSource) context.lookup("java:/jdbc/DefaultDS");
		assertThat(dataSource, is(notNullValue()));
		try(Connection connection = dataSource.getConnection()) {
			assertThat(connection, is(notNullValue()));			
		}
	}
}
