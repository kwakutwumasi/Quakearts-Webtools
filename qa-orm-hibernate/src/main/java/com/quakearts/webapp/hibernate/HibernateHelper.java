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
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateHelper {
	private static final Map<String, HelperStore> store = new HashMap<>();
	private static SessionFactory factory;
	private static Configuration configuration;

	private HibernateHelper() {}
	
	public static synchronized SessionFactory getCurrentSessionFactory() {
		if(factory == null){
			factory = getCurrentConfiguration().buildSessionFactory();
		}
		
		return factory;
	}
		
	public static synchronized Configuration getCurrentConfiguration(){
		if (configuration== null){
			configuration = new Configuration().configure(
					Thread.currentThread().getContextClassLoader()
					.getResource(StandardServiceRegistryBuilder.DEFAULT_CFG_RESOURCE_NAME));
		}
		
		return configuration;
	}
	
	public static synchronized Session getCurrentSession(){
		return getCurrentSessionFactory().getCurrentSession();
	}
	
	public static synchronized Configuration getConfiguration(String domain) {
		return getStore(domain).configuration;
	}
	
	private static void configureDomain(String domain) {
		Configuration configuration = new Configuration().configure(
				Thread.currentThread().getContextClassLoader()
				.getResource(new StringBuilder(domain)
					.append(".")
					.append(StandardServiceRegistryBuilder.DEFAULT_CFG_RESOURCE_NAME)
					.toString()));
		configuration.getProperties().put(CurrentSessionContextHelper.DOMAIN_KEY, domain);
		HelperStore helperStore = new HelperStore();
		helperStore.configuration = configuration;
		helperStore.factory = configuration.buildSessionFactory();
		store.put(domain, helperStore);
	}
	
	public static synchronized SessionFactory getSessionFactory(String domain) {
		return getStore(domain).factory;
	}

	
	public static Session getSession(String domain) {
		return getSessionFactory(domain).getCurrentSession();
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
	}
}
