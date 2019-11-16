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
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateHelper {
	private static final Map<String, HelperStore> store = new HashMap<>();
	private static SessionFactory factory;
	private static ServiceRegistry registry;
	private static StandardServiceRegistryBuilder registryBuilder;
	private static Configuration configuration;

	private HibernateHelper() {}
	
	public static synchronized SessionFactory getCurrentSessionFactory() {
		if(factory == null){
			factory = getCurrentConfiguration().buildSessionFactory(getRegistry());
		}
		
		return factory;
	}
	
	public static synchronized ServiceRegistry getRegistry() {
		if(registry==null) {
			registry = getRegistryBuilder().build();
		}
		
		return registry;
	}
	
	public static synchronized Configuration getCurrentConfiguration(){
		if (configuration== null){
			configuration = new Configuration();
		}
		
		return configuration;
	}
	
	public static synchronized Session getCurrentSession(){
		return getCurrentSessionFactory().getCurrentSession();
	}
	
	public static synchronized StandardServiceRegistryBuilder getRegistryBuilder() {
		if(registryBuilder == null)
			registryBuilder = new StandardServiceRegistryBuilder().configure();
		
		return registryBuilder;
	}
	
	public static synchronized Configuration getConfiguration(String domain) throws HibernateException {
		return getStore(domain).configuration;
	}
	
	private static void configureDomain(String domain) throws HibernateException{
		Configuration configuration = new Configuration();
		HelperStore helperStore = new HelperStore();
		helperStore.registryBuilder = new StandardServiceRegistryBuilder().configure(new StringBuilder(domain)
					.append(".")
					.append(StandardServiceRegistryBuilder.DEFAULT_CFG_RESOURCE_NAME)
					.toString()).applySetting(CurrentSessionContextHelper.DOMAIN, domain);
				
		helperStore.registry = helperStore.registryBuilder.build();
		helperStore.configuration = configuration;
		helperStore.factory = configuration.buildSessionFactory(helperStore.registry);
		store.put(domain, helperStore);
	}
	
	public static synchronized SessionFactory getSessionFactory(String domain) throws HibernateException {
		return getStore(domain).factory;
	}

	
	public static Session getSession(String domain) throws HibernateException{
		return getSessionFactory(domain).getCurrentSession();
	}
	
	public static StandardServiceRegistryBuilder getRegistryBuilder(String domain) {
		return getStore(domain).registryBuilder;
	}

	
	private static HelperStore getStore(String domain){
		if(!store.containsKey(domain)){
			configureDomain(domain);
		} 			
		
		return store.get(domain);
	}
	
	private static class HelperStore {
		SessionFactory factory;
		Configuration configuration;
		ServiceRegistry registry;
		StandardServiceRegistryBuilder registryBuilder;
	}
}
