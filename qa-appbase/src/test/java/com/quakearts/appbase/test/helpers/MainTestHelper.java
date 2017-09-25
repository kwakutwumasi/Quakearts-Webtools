package com.quakearts.appbase.test.helpers;

import javax.enterprise.inject.spi.BeanManager;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.InvalidTransactionException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.Status;
import javax.transaction.Synchronization;
import javax.transaction.SystemException;
import javax.transaction.Transaction;
import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;
import javax.transaction.xa.XAResource;

import com.quakearts.appbase.internal.properties.ConfigurationPropertyMap;
import com.quakearts.appbase.spi.ContextDependencySpi;
import com.quakearts.appbase.spi.DataSourceProviderSpi;
import com.quakearts.appbase.spi.EmbeddedWebServerSpi;
import com.quakearts.appbase.spi.JavaNamingDirectorySpi;
import com.quakearts.appbase.spi.JavaTransactionManagerSpi;

public class MainTestHelper
		implements ContextDependencySpi, EmbeddedWebServerSpi, JavaNamingDirectorySpi,
		DataSourceProviderSpi, JavaTransactionManagerSpi, 
		TransactionManager, Transaction, UserTransaction {

	private static boolean mainSingletonIniated;
	private static boolean mainSingletonClassMatches;
	private static boolean contextDependencyInjectionStarted;
	private static boolean embeddedWebServerStarted;
	private static boolean jndiServicesStarted;
	private static boolean datasourceStarted;
	private static boolean javaTransactionManagerInitiated;

	public static void resetChecks() {
		mainSingletonIniated = false;
		mainSingletonClassMatches = false;
		contextDependencyInjectionStarted = false;
		embeddedWebServerStarted = false;
		jndiServicesStarted = false;
		datasourceStarted = false;
		javaTransactionManagerInitiated = false;
	}
	
	public static boolean isJavaTransactionManagerInitiated() {
		return javaTransactionManagerInitiated;
	}
	
	public static boolean isMainSingletonIniated() {
		return mainSingletonIniated;
	}

	public static boolean isMainSingletonClassMatches() {
		return mainSingletonClassMatches;
	}

	public static boolean isContextDependencyInjectionStarted() {
		return contextDependencyInjectionStarted;
	}

	public static boolean isEmbeddedWebServerStarted() {
		return embeddedWebServerStarted;
	}

	public static boolean isJndiServicesStarted() {
		return jndiServicesStarted;
	}

	public static boolean isDatasourceStarted() {
		return datasourceStarted;
	}

	@Override
	public void initiateDataSourceSpi() {
		datasourceStarted = true;
	}

	@Override
	public DataSource getDataSource(ConfigurationPropertyMap configurationParameters) {
		return null;
	}

	@Override
	public DataSource getDataSource(String name) {
		return null;
	}

	@Override
	public void shutDownDataSourceProvider() {
	}

	@Override
	public InitialContext getInitialContext() {
		return null;
	}

	@Override
	public Context createContext(String name) throws NamingException {
		return null;
	}

	@Override
	public void initiateJNDIServices() {
		jndiServicesStarted = true;
	}

	@Override
	public void shutdownJNDIService() {
	}

	@Override
	public void initiateEmbeddedWebServer() {
		embeddedWebServerStarted = true;
	}

	@Override
	public void shutdownEmbeddedWebServer() {
	}

	@Override
	public void initiateContextDependency() {
		contextDependencyInjectionStarted = true;
	}

	@Override
	public void shutDownContextDependency() {
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getMainSingleton(Class<T> mainSingletonClass) {
		mainSingletonClassMatches = this.getClass() == mainSingletonClass;
		if(mainSingletonClassMatches)
			return (T) this;

		throw new RuntimeException("Not matched");
	}

	@Override
	public BeanManager getBeanManager() {
		return null;
	}

	public void init(){
		mainSingletonIniated = true;
	}

	@Override
	public void initiateJavaTransactionManager() {
		javaTransactionManagerInitiated = true;
	}

	@Override
	public void shutdownJavaTransactionManager() {
	}

	@Override
	public TransactionManager getTransactionManager() {
		return this;
	}

	@Override
	public UserTransaction getUserTransaction() {
		return this;
	}

	@Override
	public boolean delistResource(XAResource xaRes, int flag) throws IllegalStateException, SystemException {
		return true;
	}

	@Override
	public boolean enlistResource(XAResource xaRes) throws IllegalStateException, RollbackException, SystemException {
		return true;
	}

	@Override
	public void registerSynchronization(Synchronization synch)
			throws IllegalStateException, RollbackException, SystemException {
	}

	@Override
	public void begin() throws NotSupportedException, SystemException {
	}

	@Override
	public void commit() throws HeuristicMixedException, HeuristicRollbackException, IllegalStateException,
			RollbackException, SecurityException, SystemException {
	}

	@Override
	public int getStatus() throws SystemException {
		return Status.STATUS_NO_TRANSACTION;
	}

	@Override
	public Transaction getTransaction() throws SystemException {
		return this;
	}

	@Override
	public void resume(Transaction tobj) throws IllegalStateException, InvalidTransactionException, SystemException {
	}

	@Override
	public void rollback() throws IllegalStateException, SecurityException, SystemException {
	}

	@Override
	public void setRollbackOnly() throws IllegalStateException, SystemException {
	}

	@Override
	public void setTransactionTimeout(int seconds) throws SystemException {
	}

	@Override
	public Transaction suspend() throws SystemException {
		return this;
	}
}
