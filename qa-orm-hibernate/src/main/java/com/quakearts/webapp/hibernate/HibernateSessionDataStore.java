package com.quakearts.webapp.hibernate;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.quakearts.webapp.orm.DataStore;
import com.quakearts.webapp.orm.exception.DataStoreException;
import com.quakearts.webapp.orm.query.QueryOrder;

public class HibernateSessionDataStore extends HibernateBean implements DataStore {

	private Session session;
	private String domain;
	
	public HibernateSessionDataStore() {
		session = HibernateHelper.getCurrentSession();
	}
	
	public HibernateSessionDataStore(String domain) {
		try {
			this.domain = domain;
			session = HibernateHelper.getSession(domain);
		} catch (HibernateException e) {
			throw new DataStoreException(e);
		}
	}
	
	@Override
	public void save(Object object) {
		try {
			session.save(object);			
		} catch (HibernateException e) {
			throw new DataStoreException(e);
		}
	}

	@Override
	public <T> T get(Class<T> clazz, Serializable id) {
		try {
			return session.get(clazz, id);
		} catch (HibernateException e) {
			throw new DataStoreException(e);
		}
	}

	@Override
	public void update(Object object) {
		try {
			session.update(object);
		} catch (HibernateException e) {
			throw new DataStoreException(e);
		}
	}

	@Override
	public void delete(Object object) {
		try {
			session.delete(object);
		} catch (HibernateException e) {
			throw new DataStoreException(e);
		}
	}

	@Override
	public <T> List<T> list(Class<T> clazz, Map<String, Serializable> parameters, QueryOrder...orders) {
		try {
			return findObjects(clazz, parameters, session, orders);
		} catch (HibernateException e) {
			throw new DataStoreException(e);
		}
	}

	@Override
	public void saveOrUpdate(Object object) {
		try {
			session.saveOrUpdate(object);
		} catch (HibernateException e) {
			throw new DataStoreException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T refresh(T object) {
		try {
			return (T) session.merge(object);
		} catch (HibernateException e) {
			throw new DataStoreException(e);
		}
	}

	public Session getSession() {
		return session;
	}
	
	public String getDomain() {
		return domain;
	}

	@Override
	public void flushBuffers() {
		session.flush();
	}

	@Override
	public String getConfigurationProperty(String propertyName) {
		if(domain!=null)
			return HibernateHelper.getConfiguration(domain).getProperty(propertyName);
		else
			return HibernateHelper.getCurrentConfiguration().getProperty(propertyName);			
	}
}
