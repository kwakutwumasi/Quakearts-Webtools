package com.quakearts.webapp.orm.cdi.test;

import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import javax.enterprise.inject.spi.CDI;
import javax.enterprise.util.AnnotationLiteral;
import javax.inject.Inject;
import javax.transaction.SystemException;
import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;

import static org.hamcrest.core.Is.*;
import static org.hamcrest.core.IsNull.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import com.quakearts.appbase.cdi.annotation.Transaction;
import com.quakearts.appbase.cdi.annotation.Transactional;
import com.quakearts.appbase.cdi.annotation.Transactional.TransactionType;
import com.quakearts.tools.test.mocking.proxy.MockingProxyBuilder;
import com.quakearts.webapp.orm.DataStore;
import com.quakearts.webapp.orm.cdi.TransactionalDataStore;
import com.quakearts.webapp.orm.cdi.annotation.DataStoreHandle;
import com.quakearts.webapp.orm.cdi.annotation.RequiresTransaction;
import com.quakearts.webapp.orm.cdi.exception.NoTransactionException;
import com.quakearts.webapp.orm.cdi.exception.TransactionManagerException;
import com.quakearts.webapp.orm.cdi.test.datastore.TestDataStoreFactory;
import com.quakearts.webapp.orm.cdi.test.model.TestObject;
import com.quakearts.webapp.orm.cdi.test.runner.CDIAndDataSourceRunner;

@RunWith(CDIAndDataSourceRunner.class)
public class TransactionalDataStoreTest extends TestBase {

	@Inject @Transaction
	private UserTransaction transaction;
	
	@Inject @DataStoreHandle @RequiresTransaction
	private DataStore dataStore;

	@Inject @DataStoreHandle("alternate") @RequiresTransaction
	private DataStore alternateDataStore;
	
	@Test
	public void testMultipleTransactions() throws Exception {
		abstract class DataStoreHandleLiteral extends AnnotationLiteral<DataStoreHandle>
			implements DataStoreHandle {

			/**
			 * 
			 */
			private static final long serialVersionUID = -8176957488399078777L;
			
		};
		
		DataStoreHandle dataStoreHandleLiteral = new DataStoreHandleLiteral(){
			/**
			 * 
			 */
			private static final long serialVersionUID = 7059398371660703981L;

			@Override
			public String value() {
				return "";
			}	
		};
		
		AnnotationLiteral<RequiresTransaction> requiresTransactionLiteral = new AnnotationLiteral<RequiresTransaction>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = -937825423946433888L;
			
		};
		
		transaction.begin();
		
		DataStore dataStore1 = CDI.current().select(DataStore.class, requiresTransactionLiteral, dataStoreHandleLiteral).get();
		TestObject testObject = new TestObject();
		testObject.setName("Test1");
		dataStore1.save(testObject);

		transaction.commit();
		
		transaction.begin();

		DataStore dataStore2 = CDI.current().select(DataStore.class, requiresTransactionLiteral, dataStoreHandleLiteral).get();
		testObject = new TestObject();
		testObject.setName("Test1");
		dataStore2.save(testObject);
		
		transaction.commit();
		
		assertNotEquals(dataStore1, dataStore2);
	}
	
	@Test @Transactional(TransactionType.SINGLETON)
	public void testDataStoreInjection() throws Exception {
		try {
			runTest(dataStore);
		} catch (Exception e) {
			fail("Data Store test failed: "+e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test @Transactional(TransactionType.SINGLETON)
	public void testDataStoreInjectionWithDomain() throws Exception {
		try {
			runTest(alternateDataStore);
			
			assertThat(alternateDataStore.getConfigurationProperty("com.test.alternate"), is("true"));
		} catch (Exception e) {
			fail("Data Store test failed: "+e.getMessage());
			e.printStackTrace();
		}
	}

	private static AtomicInteger count = new AtomicInteger();
	
	private void runTest(DataStore dataStore){
		String name = "Test"+count.getAndIncrement();
		TestObject testObject = new TestObject();
		testObject.setName(name);
		
		dataStore.save(testObject);
		dataStore.flushBuffers();
		
		TestObject testObject2 = dataStore.get(TestObject.class, testObject.getId());
		assertThat(testObject2.getId(), is(testObject.getId()));
		
		testObject2.setName(name+"_Updated");
		
		dataStore.update(testObject2);
		dataStore.flushBuffers();

		Optional<TestObject> testObjectOptional = dataStore.find(TestObject.class).filterBy("name")
			.withAValueEqualTo(name+"_Updated").thenGetFirst();
		
		assertTrue(testObjectOptional.isPresent());
		
		List<TestObject> testObjects = dataStore.list(TestObject.class);
		assertThat(testObjects.size(), is(1));

		TestObject testObject3 = testObjects.get(0);
		testObject3.setName(name+"_Update2");
		
		dataStore.saveOrUpdate(testObject3);
		dataStore.flushBuffers();
		
		testObjectOptional = dataStore.find(TestObject.class).filterBy("name")
				.withAValueEqualTo(name+"_Update2").thenGetFirst();
		
		assertTrue(testObjectOptional.isPresent());
		testObject = dataStore.refresh(testObject);
		
		assertThat(testObject.getName(), is(testObject3.getName()));
		
		dataStore.executeFunction(dc->{
			assertThat(dc.getConnection(Connection.class), is(notNullValue()));
		});
		
		dataStore.delete(testObject);
		
		assertThat(dataStore.get(TestObject.class, testObject.getId()), is(nullValue()));
		
		dataStore.clearBuffers();
	}
	
	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	@Test
	public void testTransactionalDataStoreWithSystemException() throws Exception {
		replaceTransactionManager(MockingProxyBuilder
				.createMockingInvocationHandlerFor(TransactionManager.class)
				.mock("getTransaction").withVoidEmptyMethod(()->{
					throw new SystemException("TestException");
				}).thenBuild());
		try {
			expectedException.expect(TransactionManagerException.class);
			expectedException.expectMessage("Unable to get transaction");
			expectedException.expectCause(isA(SystemException.class));
			new TransactionalDataStore(null).save(new TestObject());
		} finally {
			replaceTransactionManager(null);
		}
	}
	
	@Test
	public void testTransactionalDataStoreWithNullTransaction() throws Exception {
		replaceTransactionManager(MockingProxyBuilder
				.createMockingInvocationHandlerFor(TransactionManager.class)
				.mock("getTransaction").withEmptyMethod(()->{
					return null;
				}).thenBuild());
		try {
			expectedException.expect(NoTransactionException.class);
			expectedException.expectMessage("Missing transaction instance");
			new TransactionalDataStore(null).save(new TestObject());
		} finally {
			replaceTransactionManager(null);
		}
	}

	@Test
	public void testTransactionalDataStoreWithRegisterSynchronizationException() throws Exception {
		TestDataStoreFactory.replaceCurrentFactory();
		replaceTransactionManager(MockingProxyBuilder
				.createMockingInvocationHandlerFor(TransactionManager.class)
				.mock("getTransaction").withEmptyMethod(()->{
					return MockingProxyBuilder.createMockingInvocationHandlerFor(javax.transaction.Transaction.class)
							.mock("registerSynchronization").withVoidMethod(args->{
								assertTrue(args.get(0) instanceof TransactionalDataStore);
								throw new IllegalStateException("TestException2");
							}).thenBuild();
				}).thenBuild());
		try {
			expectedException.expect(TransactionManagerException.class);
			expectedException.expectMessage("Unable to register synchronization");
			expectedException.expectCause(isA(IllegalStateException.class));
			new TransactionalDataStore(null).save(new TestObject());
		} finally {
			replaceTransactionManager(null);
			TestDataStoreFactory.restoreOldFactory();
		}
	}

	
	private void replaceTransactionManager(TransactionManager manager) throws Exception {
		Field transactionManager = TransactionalDataStore.class.getDeclaredField("transactionManager");
		transactionManager.setAccessible(true);
		transactionManager.set(null, manager);
	}
}
