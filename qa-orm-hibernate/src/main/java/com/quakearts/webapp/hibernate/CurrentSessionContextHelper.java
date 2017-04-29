package com.quakearts.webapp.hibernate;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionException;
import org.hibernate.Transaction;
import org.hibernate.context.spi.CurrentSessionContext;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.resource.transaction.spi.TransactionStatus;

public abstract class CurrentSessionContextHelper implements CurrentSessionContext {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3927106770407518460L;
	private static final Logger log = Logger.getLogger(CurrentSessionContextHelper.class.getName());
	public static final String CURRENTSESSION = "com.quakearts.webapp.hibernate.SESSION";
	public static final String DOMAIN = "com.quakearts.webapp.hibernate.DOMAIN";
	
	private static final Map<String, CurrentSessionContextHelper> sessionHelperCache = new ConcurrentHashMap<>(3);
	protected static final String EMPTY = "";
	
	private SessionFactoryImplementor implementor;
	protected String domain;
	
	public CurrentSessionContextHelper(SessionFactoryImplementor implementor) {
		this.implementor = implementor;
		domain = (String) implementor.getProperties().get(DOMAIN);
		if(domain==null)
			domain = EMPTY;
			
		if(sessionHelperCache.containsKey(domain))
			throw new IllegalArgumentException("Domain "+domain+" has already been set up in this application context");
			
		sessionHelperCache.put(domain, this);
	}
	
	public static CurrentSessionContextHelper getInstance(){
		return sessionHelperCache.get(EMPTY);
	}

	public static CurrentSessionContextHelper getInstance(String domain){
		return sessionHelperCache.get(domain);
	}

	@Override
	public Session currentSession() throws HibernateException {
		Session session = getCurrentSessionFromContextAttributes();
		
		if(session==null)
			session = createCurrentSession();
		
		return session;
	}
		
	protected abstract Session getCurrentSessionFromContextAttributes();

	protected abstract void putCurrentSessionInContextAttributes(Session session);
	
	protected abstract void removeCurrentSessionFromContextAttributes();

	protected String getKey(){
		return CURRENTSESSION+(domain!=null?":"+domain:EMPTY);
	}
	
	private Session createCurrentSession(){							
		try {
			Session session = implementor.openSession();
			session.beginTransaction();

			putCurrentSessionInContextAttributes(session);

			return session;
		} catch (HibernateException e) {
			throw new IllegalStateException(e);
		}
	}
	
	public static void closeOpenSessions(){
		
		ArrayList<Exception> exceptions = new ArrayList<>();
				
		for(CurrentSessionContextHelper helper:sessionHelperCache.values()){
			Session session = helper.getCurrentSessionFromContextAttributes();
			
			if(session==null)
				return;
			
			helper.removeCurrentSessionFromContextAttributes();
			
			Transaction tx = session.getTransaction();
			
			if(tx==null)
				throw new IllegalStateException("Transaction cannot be found.");
	
			try {
				if(tx.getStatus()==TransactionStatus.ACTIVE){
					if(tx.getStatus() == TransactionStatus.MARKED_ROLLBACK){
						tx.rollback();
					} else {
						tx.commit();
					}
				} else {
					throw new IllegalStateException("Transaction is not active");
				}
			} catch (HibernateException e) {
				exceptions.add(new Exception("Exception thrown whiles cleaning up domain: "+helper.domain, e));
			} finally {
				try {//Reclose just in case
					session.close();
				} catch (SessionException e) {
					//Ignore
				} catch (Exception e) {
					exceptions.add(new Exception("Exception thrown whiles cleaning up domain: "+helper.domain, e));
				}
			}
		}
		
		if(exceptions.size()>0){
			for(Exception e:exceptions){
				log.log(Level.SEVERE, e.getMessage(), e);
			}
			
			throw new IllegalStateException("Error during session cleanup");
		}
	}

}
