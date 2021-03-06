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
package com.quakearts.webapp.facelets.hibernate.phase;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.naming.InitialContext;
import javax.transaction.Status;
import javax.transaction.UserTransaction;

import com.quakearts.webapp.hibernate.CurrentSessionContextHelper;

/**A {@linkplain PhaseListener} that auto-closes {@linkplain UserTransaction} (JTA environments) or {@linkplain CurrentSessionContextHelper}
 * @author kwakutwumasi-afriyie
 *
 */
public class HibernatePhaseHelper implements PhaseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2366733998857516126L;
	private static final Logger log = Logger.getLogger(HibernatePhaseHelper.class.getName());
	private Boolean userTransaction;
	
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
		if(isUserTransaction()){
			InitialContext icx = new InitialContext();
			UserTransaction tran= (UserTransaction) icx.lookup("java:comp/UserTransaction");
			if(tran.getStatus() == Status.STATUS_ACTIVE)
				tran.commit();
			else if(tran.getStatus() == Status.STATUS_MARKED_ROLLBACK)
				tran.rollback();
		} else {
			CurrentSessionContextHelper.closeOpenSessions();
		}
	}

	@Override
	public void beforePhase(PhaseEvent event) {
		if(event.getPhaseId() == PhaseId.RESTORE_VIEW){//start a transaction
			if(isUserTransaction()){
				try {
					InitialContext icx = new InitialContext();
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

	private boolean isUserTransaction() {
		if(userTransaction == null){
			userTransaction = ! Boolean.parseBoolean(FacesContext.getCurrentInstance()
					.getExternalContext()
					.getInitParameter("com.quakearts.hibernate.nojta"));
		}
		
		return userTransaction;
	}

}
