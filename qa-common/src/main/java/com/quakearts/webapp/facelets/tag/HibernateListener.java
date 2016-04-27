package com.quakearts.webapp.facelets.tag;

import java.io.IOException;

import javax.el.ValueExpression;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.quakearts.webapp.facelets.util.ObjectExtractor;
import com.quakearts.webapp.hibernate.HibernateHelper;

public abstract class HibernateListener extends BaseListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8698951312388732553L;
	protected Session session;
	private ValueExpression domainExpression;
	
	@Override
	public void processAction(ActionEvent event) throws AbortProcessingException {
		FacesContext ctx = FacesContext.getCurrentInstance();
		if(evaluateUnless()){
			if(domainExpression!=null)
				try {
					session = HibernateHelper.getSession(ObjectExtractor.extractString(domainExpression, ctx.getELContext()));
				} catch (HibernateException e) {
					throw new AbortProcessingException(e);
				} catch (IOException e) {
					throw new AbortProcessingException(e);
				}
			else
				session = HibernateHelper.getCurrentSession();
			continueProcessing(event, ctx);
		}
	}

	public void setSession(Session session) {
		if(session == null)
			this.session = session;
	}

	public void setDomainExpression(ValueExpression domainExpression) {
		this.domainExpression = domainExpression;
	}

	public ValueExpression getDomainExpression() {
		return domainExpression;
	}
	
}
