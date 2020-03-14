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

import com.quakearts.webapp.orm.exception.DataStoreException;

/**Base class for implementations of {@linkplain CurrentSessionContext}. This provides support for
 * {@link org.hibernate.SessionFactory SessionFactory}'s {@link org.hibernate.SessionFactory#getCurrentSession() getCurrentSession()}
 * method in non-JTA environments
 * @author kwakutwumasi-afriyie
 *
 */
public abstract class CurrentSessionContextHelper implements CurrentSessionContext {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3927106770407518460L;
	private static final Logger log = Logger.getLogger(CurrentSessionContextHelper.class.getName());
	public static final String CURRENTSESSION_KEY = "com.quakearts.webapp.hibernate.SESSION";
	public static final String DOMAIN_KEY = "com.quakearts.webapp.hibernate.DOMAIN";
	
	private static final Map<String, CurrentSessionContextHelper> sessionHelperCache = new ConcurrentHashMap<>(3);
	protected static final String EMPTY = "";
	
	private SessionFactoryImplementor implementor;
	protected String domain;
	
	public CurrentSessionContextHelper(SessionFactoryImplementor implementor) {
		this.implementor = implementor;
		domain = (String) implementor.getProperties().get(DOMAIN_KEY);
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
	public Session currentSession() {
		Session session = getCurrentSessionFromContextAttributes();
		
		if(session==null)
			session = createCurrentSession();
		
		return session;
	}
		
	protected abstract Session getCurrentSessionFromContextAttributes();

	protected abstract void putCurrentSessionInContextAttributes(Session session);
	
	protected abstract void removeCurrentSessionFromContextAttributes();

	protected String getKey(){
		return CURRENTSESSION_KEY+(domain!=null?":"+domain:EMPTY);
	}
	
	private Session createCurrentSession(){							
		Session session = implementor.openSession();
		session.beginTransaction();

		putCurrentSessionInContextAttributes(session);

		return session;
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
				if(tx.getStatus() == TransactionStatus.ACTIVE){
					tx.commit();
				} else if(tx.getStatus() == TransactionStatus.MARKED_ROLLBACK){
					tx.rollback();
				} else {
					throw new IllegalStateException("Transaction is not in an epected state");
				}
			} catch (HibernateException e) {
				exceptions.add(new Exception("Exception thrown whiles cleaning up domain: "+helper.domain, e));
			} finally {
				doFinal(exceptions, helper, session);
			}
		}
		
		if(!exceptions.isEmpty()){
			for(Exception e:exceptions){
				log.log(Level.SEVERE, e.getMessage(), e);
			}
			
			throw new DataStoreException("Error during session cleanup");
		}
	}

	protected static void doFinal(ArrayList<Exception> exceptions, CurrentSessionContextHelper helper,
			Session session) {
		try {//Reclose just in case
			session.close();
		} catch (SessionException e) {
			//Ignore
		} catch (Exception e) {
			exceptions.add(new Exception("Exception thrown whiles cleaning up domain: "+helper.domain, e));
		}
	}

}
