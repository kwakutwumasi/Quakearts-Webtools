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

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateHelper {
	private static final Map<String, HelperStore> store = new HashMap<String, HelperStore>();
	private static SessionFactory factory;
	private static ServiceRegistry registry;
	private static Configuration configuration;
	private static final Logger log = Logger.getLogger(HibernateHelper.class.getName());

	public synchronized static SessionFactory getCurrentSessionFactory() {
		if(factory == null){
			factory = getCurrentConfiguration().buildSessionFactory(getRegistry());
		}
		
		return factory;
	}
	
	public synchronized static ServiceRegistry getRegistry() {
		if(registry==null){
			registry = new StandardServiceRegistryBuilder().configure().build();
		}
		
		return registry;
	}
	
	public synchronized static Configuration getCurrentConfiguration(){
		if (configuration== null){
			configuration = new Configuration();
		}
		
		return configuration;
	}
	
	public synchronized static Session getCurrentSession(){
		return getCurrentSessionFactory().getCurrentSession();
	}
	
	public synchronized static Configuration getConfiguration(String domain) throws HibernateException {
		if(store.containsKey(domain)){
			return store.get(domain).configuration;
		} else {
			configureDomain(domain);
			return store.get(domain).configuration;
		}
	}
	
	private static void configureDomain(String domain) throws HibernateException{
		Configuration configuration = new Configuration();
		HelperStore helperStore = new HelperStore();
		helperStore.registry = new StandardServiceRegistryBuilder().configure(new StringBuilder(domain)
					.append(".")
					.append(StandardServiceRegistryBuilder.DEFAULT_CFG_RESOURCE_NAME)
					.toString()).applySetting(CurrentSessionContextHelper.DOMAIN, domain).build();
		helperStore.configuration = configuration;
		helperStore.factory = configuration.buildSessionFactory(helperStore.registry);
		store.put(domain, helperStore);
	}
	
	public synchronized static SessionFactory getSessionFactory(String domain) throws HibernateException {
		if(store.containsKey(domain)){
			return store.get(domain).factory;
		} else {
			configureDomain(domain);
			return store.get(domain).factory;
		}
	}
	
	public static Session getSession(String domain) throws HibernateException{
		return getSessionFactory(domain).getCurrentSession();
	}
	
	private static class HelperStore {
		SessionFactory factory;
		Configuration configuration;
		ServiceRegistry registry;
	}
	
	public static Object refresh(Object object, String domain){
		if(domain==null)
			return getCurrentSession().merge(object);
		else
			try {
				return getSession(domain).merge(object);
			} catch (Exception e) {
				log.severe("Exception of type " + e.getClass().getName()
						+ " was thrown. Message is " + e.getMessage());
				return null;
			}
	}

}
