package com.quakearts.webapp.hibernate;

import java.io.IOException;
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

	public static SessionFactory getCurrentSessionFactory() {
		if(factory == null){
			factory = getCurrentConfiguration().buildSessionFactory(getRegistry());
		}
		
		return factory;
	}
	
	public static ServiceRegistry getRegistry() {
		if(registry==null){
			registry = new StandardServiceRegistryBuilder().configure().build();
		}
		
		return registry;
	}
	
	public static Configuration getCurrentConfiguration(){
		if (configuration== null){
			configuration = new Configuration();
		}
		
		return configuration;
	}
	
	public static Session getCurrentSession(){
		return getCurrentSessionFactory().getCurrentSession();
	}
	
	public static Configuration getConfiguration(String domain) throws IOException, HibernateException {
		if(store.containsKey(domain)){
			return store.get(domain).configuration;
		} else {
			configureDomain(domain);
			return store.get(domain).configuration;
		}
	}
	
	private static void configureDomain(String domain) throws IOException, HibernateException{
		Configuration configuration = new Configuration();
		HelperStore helperStore = new HelperStore();
		helperStore.registry = new StandardServiceRegistryBuilder().configure(new StringBuilder(domain)
					.append(".")
					.append(StandardServiceRegistryBuilder.DEFAULT_CFG_RESOURCE_NAME)
					.toString()).build();
		helperStore.configuration = configuration;
		helperStore.factory = configuration.buildSessionFactory(helperStore.registry);
		store.put(domain, helperStore);
	}
	
	public static SessionFactory getSessionFactory(String domain) throws HibernateException, IOException{
		if(store.containsKey(domain)){
			return store.get(domain).factory;
		} else {
			configureDomain(domain);
			return store.get(domain).factory;
		}
	}
	
	public static Session getSession(String domain) throws HibernateException, IOException{
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
