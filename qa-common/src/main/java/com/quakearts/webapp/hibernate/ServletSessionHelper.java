package com.quakearts.webapp.hibernate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.context.FacesContext;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.context.spi.CurrentSessionContext;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.resource.transaction.spi.TransactionStatus;

public class ServletSessionHelper implements CurrentSessionContext {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3927106770407518460L;
	public static final String CURRENTSESSION = "com.quakearts.webapp.hibernate.SESSION",
			DOMAIN = "com.quakearts.webapp.hibernate.DOMAIN",
			OPENSESSIONS = "com.quakearts.webapp.hibernate.SESSIONS";
	public static boolean nofacelets = false;
	
	private static final ThreadLocal<Map<Object, Object>> threadLocalContext = new ThreadLocal<Map<Object, Object>>(){
		protected Map<Object,Object> initialValue() {
			return new HashMap<>();
		};
	};

	private static final Logger log = Logger.getLogger(ServletSessionHelper.class.getName());
	
	public SessionFactoryImplementor implementor;
	public String domain;
	
	public ServletSessionHelper(SessionFactoryImplementor implementor) {
		this.implementor = implementor;
		domain = implementor.getProperties().getProperty(DOMAIN);		
	}
	
	@Override
	public Session currentSession() throws HibernateException {
		Session session = getCurrentSession(domain);
		
		if(session==null)
			session = createCurrentSession();
		
		return session;
	}
		
	public static Session getCurrentSession(){
		return getCurrentSession(null);
	}
	
	public static Session getCurrentSession(String domain){
		return (Session) getContextAttributes().get(CURRENTSESSION+(domain!=null?":"+domain:""));
	}
	
	public static List<String> getOpenSessions(){
		@SuppressWarnings("unchecked")
		List<String> openSessions = (List<String>) getContextAttributes().get(OPENSESSIONS);
		
		if(openSessions==null){
			openSessions = new ArrayList<>();
			getContextAttributes().put(OPENSESSIONS, openSessions);
		}
		
		return openSessions;
	}
	
	private static Map<Object, Object> getContextAttributes(){
		try {
			Class.forName("javax.faces.context.FacesContext");
			nofacelets = FacesContext.getCurrentInstance()==null;
		} catch (ClassNotFoundException e){
			nofacelets = true;
		}
		
		if(nofacelets){
			return threadLocalContext.get();
		} else {
			return FacesContext.getCurrentInstance().getAttributes();
		}
	}
	
	private Session createCurrentSession(){							
		try {
			Session session = implementor.openSession();
			session.beginTransaction();
			
			getContextAttributes().put(CURRENTSESSION+(domain!=null?":"+domain:""), session);
			
			getOpenSessions().add(domain!=null?":"+domain:"");
			
			return session;
		} catch (HibernateException e) {
			throw new IllegalStateException(e);
		}
	}
	
	public static void closeOpenSessions(){
		
		ArrayList<Exception> exceptions = new ArrayList<>();
		
		for(String domain:getOpenSessions()){
			Session session = getCurrentSession(domain.equals("")?null:domain);
			
			if(session==null)
				return;
			
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
				exceptions.add(new Exception("Exception thrown whiles cleaning up domain: "+domain, e));
			} finally {
				try {
					session.close();
				} catch (Exception e) {
					exceptions.add(new Exception("Exception thrown whiles cleaning up domain: "+domain, e));
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
