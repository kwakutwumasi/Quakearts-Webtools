/*******************************************************************************
 * Copyright (C) 2018 Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com>
 * 
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 * 
 * SPDX-License-Identifier: EPL-2.0
 ******************************************************************************/
package com.quakearts.webapp.orm.cdi;

import java.io.Serializable;
import java.util.List;
import javax.transaction.RollbackException;
import javax.transaction.Synchronization;
import javax.transaction.SystemException;
import javax.transaction.Transaction;
import javax.transaction.TransactionManager;

import com.quakearts.appbase.spi.factory.JavaTransactionManagerSpiFactory;
import com.quakearts.webapp.orm.DataStore;
import com.quakearts.webapp.orm.DataStoreFactory;
import com.quakearts.webapp.orm.DataStoreFunction;
import com.quakearts.webapp.orm.cdi.exception.NoTransactionException;
import com.quakearts.webapp.orm.cdi.exception.TransactionManagerException;

/**A lazy initialized {@link DataStore}. This is to enable CDI injection of the resource
 * .
 * @author kwakutwumasi-afriyie
 *
 */
public class TransactionalDataStore implements DataStore, Synchronization {
	
	private static TransactionManager transactionManager;
	private DataStore wrappedDataStore;
	private String domain;

	public TransactionalDataStore(String domain) {
		this.domain = domain;
	}

	private static TransactionManager getTransactionManager() {
		if(transactionManager == null)
			transactionManager = JavaTransactionManagerSpiFactory
				.getInstance().getJavaTransactionManagerSpi().getTransactionManager();
		
		return transactionManager;
	}

	private DataStore getDataSource() {
		if(wrappedDataStore == null) {
			Transaction transaction;
			try {
				transaction = getTransactionManager().getTransaction();
			} catch (SystemException e) {
				throw new TransactionManagerException("Unable to get transaction", e);
			}
			
			if(transaction == null) {
				throw new NoTransactionException("Missing transaction instance");
			}
			
			if("".equals(domain)) {
				wrappedDataStore = DataStoreFactory.getInstance().getDataStore();
			} else {
				wrappedDataStore = DataStoreFactory.getInstance().getDataStore(domain);
			}
			
			try {
				transaction.registerSynchronization(this);
			} catch (IllegalStateException | RollbackException | SystemException e) {
				throw new TransactionManagerException("Unable to register synchronization", e);
			}
		}
		
		return wrappedDataStore;
	}
	
	@Override
	public void save(Object object) {
		getDataSource().save(object);
	}

	@Override
	public <E> E get(Class<E> clazz, Serializable id) {
		return getDataSource().get(clazz, id);
	}

	@Override
	public void update(Object object) {
		getDataSource().update(object);
	}

	@Override
	public void delete(Object object) {
		getDataSource().delete(object);
	}

	@Override
	public <E> ListBuilder<E> find(Class<E> clazz) {
		return getDataSource().find(clazz);
	}
	
	@Override
	public <E> List<E> list(Class<E> clazz) {
		return getDataSource().list(clazz);
	}

	@Override
	public void saveOrUpdate(Object object) {
		getDataSource().saveOrUpdate(object);
	}

	@Override
	public <E> E refresh(E object) {
		return getDataSource().refresh(object);
	}

	@Override
	public void flushBuffers() {
		getDataSource().flushBuffers();
	}

	@Override
	public void executeFunction(DataStoreFunction function) {
		getDataSource().executeFunction(function);
	}

	@Override
	public String getConfigurationProperty(String propertyName) {
		return getDataSource().getConfigurationProperty(propertyName);
	}

	@Override
	public void clearBuffers() {
		getDataSource().clearBuffers();
	}

	@Override
	public void beforeCompletion() {
		// Do nothing	
	}

	@Override
	public void afterCompletion(int status) {
		wrappedDataStore = null;
	}
}
