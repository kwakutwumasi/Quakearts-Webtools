package com.quakearts.webapp.hibernate.phase;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.naming.InitialContext;
import javax.transaction.Status;
import javax.transaction.UserTransaction;

import com.quakearts.webapp.facelets.util.UtilityMethods;
import com.quakearts.webapp.hibernate.ServletSessionHelper;

public class HibernatePhaseHelper implements PhaseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2366733998857516126L;
	private static final Logger log = Logger.getLogger(HibernatePhaseHelper.class.getName());
	private boolean userTransaction = true;
	
	public HibernatePhaseHelper() {
		String nojtaparameter = FacesContext.getCurrentInstance().getExternalContext().getInitParameter("quakearts.hibernate.nojta");

		if(nojtaparameter==null)
			try {
				Class.forName("javax.transaction.UserTransaction");
			} catch (ClassNotFoundException e) {
				userTransaction = false;
			}
		else
			userTransaction = Boolean.parseBoolean(nojtaparameter);
	}
		
	@Override
	public void afterPhase(PhaseEvent event) {
		FacesContext ctx = FacesContext.getCurrentInstance();

		try {
			if(ctx.getResponseComplete()){
				commitTrx();
			} else if(event.getPhaseId() == PhaseId.RENDER_RESPONSE){//response complete has yet to be called
				commitTrx();				
			}
		} catch (Exception e) {
			log.log(Level.SEVERE,"Exception of type " + e.getClass().getName()
					+ " was thrown. Message is " + e.getMessage()
					+ ". Exception occured whiles commiting transaction", e);
			return;
		}
	}

	private void commitTrx() throws Exception{
		if(userTransaction){
			InitialContext icx = UtilityMethods.getInitialContext();
			UserTransaction tran= (UserTransaction) icx.lookup("java:comp/UserTransaction");
			if(tran.getStatus() == Status.STATUS_ACTIVE)
				tran.commit();
			else if(tran.getStatus() == Status.STATUS_MARKED_ROLLBACK)
				tran.rollback();
		} else {
			ServletSessionHelper.closeOpenSessions();
		}
	}

	@Override
	public void beforePhase(PhaseEvent event) {
		if(event.getPhaseId() == PhaseId.RESTORE_VIEW){//start a transaction
			if(userTransaction){
				try {
					InitialContext icx = UtilityMethods.getInitialContext();
					UserTransaction tran= (UserTransaction) icx.lookup("java:comp/UserTransaction");
					if(tran.getStatus() == Status.STATUS_NO_TRANSACTION)
						tran.begin();
				} catch (NullPointerException e) {
					log.warning("JTA has not been setup for this application. If JTA is required, check server settings and restart the application. Using FaceletCurrentSessionContext");
					userTransaction = false;
					beforePhase(event);
				} catch (Exception e) {
					log.log(Level.WARNING, "Exception of type " + e.getClass().getName()
							+ " was thrown. Message is " + e.getMessage()
							+ ". Exception occured whiles beginning transaction. JTA has not been setup for this application. If JTA is required, check server settings and restart the application. Using FaceletCurrentSessionContext", e);
					userTransaction = false;
					beforePhase(event);
				}
			}
		} 
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.ANY_PHASE;
	}
}
