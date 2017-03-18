package com.quakearts.webapp.facelets.tag;

import javax.el.ValueExpression;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import com.quakearts.webapp.facelets.util.ObjectExtractor;
import com.quakearts.webapp.orm.DataStore;
import com.quakearts.webapp.orm.DataStoreFactory;
import com.quakearts.webapp.orm.exception.DataStoreException;

public abstract class HibernateListener extends BaseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8698951312388732553L;
	protected DataStore dataStore;
	private ValueExpression domainExpression;
	
	@Override
	public void processAction(ActionEvent event) throws AbortProcessingException {
		FacesContext ctx = FacesContext.getCurrentInstance();
		if(evaluateUnless()){
			if(domainExpression!=null)
				try {
					dataStore = DataStoreFactory.getInstance().getDataStore(ObjectExtractor.extractString(domainExpression, ctx.getELContext()));
				} catch (DataStoreException e) {
					throw new AbortProcessingException(e);
				}
			else
				dataStore = DataStoreFactory.getInstance().getDataStore();
			continueProcessing(event, ctx);
		}
	}

	public void setDataStore(DataStore dataStore) {
		if(this.dataStore==null)
			this.dataStore = dataStore;
	}

	public void setDomainExpression(ValueExpression domainExpression) {
		this.domainExpression = domainExpression;
	}

	public ValueExpression getDomainExpression() {
		return domainExpression;
	}
	
}
