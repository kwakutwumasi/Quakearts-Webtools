package com.quakearts.webapp.hibernate;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateHelper {
	private static final Map<String, HelperStore> store = new HashMap<String, HelperStore>();
	private static SessionFactory factory;
	private static Configuration configuration;
	private static final Logger log = Logger.getLogger(HibernateHelper.class);

	public static SessionFactory getCurrentSessionFactory() {
		if(factory == null){
			factory = getCurrentConfiguration().buildSessionFactory();
		}
		
		return factory;
	}
	
	public static Configuration getCurrentConfiguration(){
		if (configuration== null)
			configuration = new Configuration().configure();
		
		return configuration;
	}
	
	public static Session getCurrentSession(){
		return getCurrentSessionFactory().getCurrentSession();
	}
	
	public static Configuration getConfiguration(String domain) throws IOException, HibernateException {
		if(store.containsKey(domain)){
			return store.get(domain).getConfiguration();
		} else {
			configureDomain(domain);
			return store.get(domain).getConfiguration();
		}
	}
	
	private static void configureDomain(String domain) throws IOException, HibernateException{
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		URL url = loader.getResource(domain+".hibernate.cfg.xml");
		Configuration configuration = new Configuration().configure(url);
		HelperStore helperStore = new HelperStore();
		helperStore.setConfiguration(configuration);
		helperStore.setFactory(configuration.buildSessionFactory());
		store.put(domain, helperStore);
	}
	
	public static SessionFactory getSessionFactory(String domain) throws HibernateException, IOException{
		if(store.containsKey(domain)){
			return store.get(domain).getFactory();
		} else {
			configureDomain(domain);
			return store.get(domain).getFactory();
		}
	}
	
	public static Session getSession(String domain) throws HibernateException, IOException{
		return getSessionFactory(domain).getCurrentSession();
	}
	
	private static class HelperStore {
		private SessionFactory factory;
		private Configuration configuration;
		public void setFactory(SessionFactory factory) {
			this.factory = factory;
		}
		
		public SessionFactory getFactory() {
			return factory;
		}
		
		public void setConfiguration(Configuration configuration) {
			this.configuration = configuration;
		}
		
		public Configuration getConfiguration() {
			return configuration;
		}
	}
	
	public static Object refresh(Object object, String domain){
		
		if(domain==null)
			return getCurrentSession().merge(object);
		else
			try {
				return getSession(domain).merge(object);
			} catch (Exception e) {
				log.error("Exception of type " + e.getClass().getName()
						+ " was thrown. Message is " + e.getMessage());
				return null;
			}
	}

}
