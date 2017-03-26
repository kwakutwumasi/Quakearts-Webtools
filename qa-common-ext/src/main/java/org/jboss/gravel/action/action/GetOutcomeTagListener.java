package org.jboss.gravel.action.action;

import java.io.Serializable;

import javax.el.ValueExpression;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;
import org.jboss.gravel.navigation.GravelNavigationHandler;

public class GetOutcomeTagListener implements ActionListener, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3995904012573893431L;
	private ValueExpression outcomeExpression;

	public GetOutcomeTagListener(ValueExpression outcomeExpression) {
		this.outcomeExpression = outcomeExpression;
	}

	@Override
	public void processAction(ActionEvent arg0) throws AbortProcessingException {
		FacesContext ctx = FacesContext.getCurrentInstance();
		String outcome =(String) ctx.getExternalContext().getRequestMap().get(GravelNavigationHandler.NAV_OUTCOME_KEY);
		if(outcome == null)
			outcomeExpression.setValue(ctx.getELContext(), "default");
		else
			outcomeExpression.setValue(ctx.getELContext(), outcome);			
	}

}
