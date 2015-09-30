package com.quakearts.webapp.facelets.phase;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.naming.InitialContext;
import javax.transaction.Status;
import javax.transaction.UserTransaction;

import org.apache.log4j.Logger;

import com.quakearts.webapp.facelets.util.UtilityMethods;

public class HibernateTransactionPhaseListener implements PhaseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2366733998857516126L;
	private static final Logger log = Logger.getLogger(HibernateTransactionPhaseListener.class);
		
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
			log.error("Exception of type " + e.getClass().getName()
					+ " was thrown. Message is " + e.getMessage()
					+ ". Exception occured whiles commiting transaction", e);
			return;
		}
	}

	private void commitTrx() throws Exception{
		InitialContext icx = UtilityMethods.getInitialContext();
		UserTransaction tran= (UserTransaction) icx.lookup("java:comp/UserTransaction");
		if(tran.getStatus() == Status.STATUS_ACTIVE)
			tran.commit();
		else if(tran.getStatus() == Status.STATUS_MARKED_ROLLBACK)
			tran.rollback();
	}

	@Override
	public void beforePhase(PhaseEvent event) {
		if(event.getPhaseId() == PhaseId.RESTORE_VIEW){//start a transaction
			try {
				InitialContext icx = UtilityMethods.getInitialContext();
				UserTransaction tran= (UserTransaction) icx.lookup("java:comp/UserTransaction");
				if(tran.getStatus() == Status.STATUS_NO_TRANSACTION)
					tran.begin();
			} catch (Exception e) {
				log.error("Exception of type " + e.getClass().getName()
						+ " was thrown. Message is " + e.getMessage()
						+ ". Exception occured whiles beginning transaction", e);
				return;
			}
		} 
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.ANY_PHASE;
	}
}
