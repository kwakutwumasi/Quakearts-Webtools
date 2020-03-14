/*******************************************************************************
* Copyright (C) 2016 Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com>.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com> - initial API and implementation
 ******************************************************************************/
package com.quakearts.webapp.hibernate;

import java.io.Serializable;
import java.sql.Connection;
import java.util.List;
import javax.persistence.PersistenceException;
import org.hibernate.Session;
import com.quakearts.webapp.orm.DataStore;
import com.quakearts.webapp.orm.DataStoreConnection;
import com.quakearts.webapp.orm.DataStoreFunction;
import com.quakearts.webapp.orm.exception.DataStoreException;
import com.quakearts.webapp.orm.query.criteria.CriteriaMap;
import com.quakearts.webapp.orm.stringconcat.OrmStringConcatUtil;

public class HibernateSessionDataStore extends HibernateBean implements DataStore {

	private Session session;
	private String domain;
	
	private Boolean skipConcatenation;
	
	private boolean shouldSkipConcatenation(){
		if(skipConcatenation==null){
			skipConcatenation = Boolean.parseBoolean(getConfigurationProperty("com.quakearts.orm.skip.string.concatenation"));
		}
		
		return skipConcatenation;
	}
	
	public HibernateSessionDataStore() {
		try {
			session = HibernateHelper.getCurrentSession();
		} catch (PersistenceException e) {
			throw new DataStoreException(e);
		}
	}
	
	public HibernateSessionDataStore(String domain) {
		try {
			this.domain = domain;
			session = HibernateHelper.getSession(domain);
		} catch (PersistenceException e) {
			throw new DataStoreException(e);
		}
	}
	
	@Override
	public <E> List<E> list(Class<E> clazz) {
		try {
			return findObjects(clazz, null, session);
		} catch (PersistenceException e) {
			throw new DataStoreException(e);
		}
	}

	@Override
	public <E> ListBuilder<E> find(Class<E> clazz) {
		return new ListBuilder<>(clazz, this::findObjectsFunction);
	}
	
	private <E> List<E> findObjectsFunction(Class<E> clazz, CriteriaMap criteriaMap){
		try {
			return findObjects(clazz, criteriaMap, session);
		} catch (PersistenceException e) {
			throw new DataStoreException(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.quakearts.webapp.orm.DataStore#save(java.lang.Object)
	 */
	@Override
	public void save(Object object) {
		try {
			if(!shouldSkipConcatenation())
				OrmStringConcatUtil.trimStrings(object);
			session.save(object);			
		} catch (PersistenceException e) {
			throw new DataStoreException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.quakearts.webapp.orm.DataStore#get(java.lang.Class, java.io.Serializable)
	 */
	@Override
	public <T> T get(Class<T> clazz, Serializable id) {
		try {
			return session.get(clazz, id);
		} catch (PersistenceException e) {
			throw new DataStoreException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.quakearts.webapp.orm.DataStore#update(java.lang.Object)
	 */
	@Override
	public void update(Object object) {
		try {
			if(!shouldSkipConcatenation())
				OrmStringConcatUtil.trimStrings(object);
			session.update(object);
		} catch (PersistenceException e) {
			throw new DataStoreException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.quakearts.webapp.orm.DataStore#delete(java.lang.Object)
	 */
	@Override
	public void delete(Object object) {
		try {
			session.delete(object);
		} catch (PersistenceException e) {
			throw new DataStoreException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.quakearts.webapp.orm.DataStore#saveOrUpdate(java.lang.Object)
	 */
	@Override
	public void saveOrUpdate(Object object) {
		try {
			if(!shouldSkipConcatenation())
				OrmStringConcatUtil.trimStrings(object);
			session.saveOrUpdate(object);
		} catch (PersistenceException e) {
			throw new DataStoreException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.quakearts.webapp.orm.DataStore#refresh(java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> T refresh(T object) {
		try {
			return (T) session.merge(object);
		} catch (PersistenceException e) {
			throw new DataStoreException(e);
		}
	}

	/**Get the Hibernate {@link Session}
	 * @return 
	 */
	public Session getSession() {
		return session;
	}
	
	/**Get the domain
	 * @return The domain
	 */
	public String getDomain() {
		return domain;
	}

	/* (non-Javadoc)
	 * @see com.quakearts.webapp.orm.DataStore#flushBuffers()
	 */
	@Override
	public void flushBuffers() {
		try {
			session.flush();
		} catch (PersistenceException e) {
			throw new DataStoreException(e);
		} 
	}

	/* (non-Javadoc)
	 * @see com.quakearts.webapp.orm.DataStore#executeFunction(com.quakearts.webapp.orm.DataStoreFunction)
	 */
	@Override
	public void executeFunction(DataStoreFunction function) {
		try {
			session.doWork(connection->function.execute(new SessionDataStoreConnection(connection, session)));			
		} catch (PersistenceException e) {
			throw new DataStoreException(e);
		}
	}
	
	class SessionDataStoreConnection implements DataStoreConnection{

		Connection connection;
		Session session;
		
		SessionDataStoreConnection(Connection connection, Session session) {
			this.connection = connection;
			this.session = session;
		}

		@Override
		public <T> T getConnection(Class<T> expectedConnection) {
			if(expectedConnection.isAssignableFrom(connection.getClass()))
				return expectedConnection.cast(connection);
			else if(expectedConnection.isAssignableFrom(session.getClass()))
				return expectedConnection.cast(session);
			else
				throw new DataStoreException("Unsupported connection class: "+expectedConnection.getName());
		}
	}
	
	@Override
	public String getConfigurationProperty(String propertyName) {
		if(domain!=null)
			return HibernateHelper.getConfiguration(domain).getProperty(propertyName);
		else
			return HibernateHelper.getCurrentConfiguration().getProperty(propertyName);			
	}

	@Override
	public void clearBuffers() {
		try {
			session.getTransaction().markRollbackOnly();
		} catch (PersistenceException e) {
			throw new DataStoreException(e);
		}
	}
}
