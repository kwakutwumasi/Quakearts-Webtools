package com.quakearts.webapp.facelets.tag.listener;

import javax.el.ValueExpression;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.quakearts.webapp.facelets.phase.GenericPhaseListener;
import com.quakearts.webapp.facelets.tag.BaseListener;

public class GetOutcomeTagListener extends BaseListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3995904012573893431L;
	private ValueExpression outcomeExpression;

	public GetOutcomeTagListener(ValueExpression outcomeExpression) {
		this.outcomeExpression = outcomeExpression;
	}

	@Override
	protected void continueProcessing(ActionEvent event, FacesContext ctx) {
		String outcome =(String) ctx.getExternalContext().getRequestMap().get(GenericPhaseListener.OUTCOME_KEY);
		if(outcome == null)
			outcomeExpression.setValue(ctx.getELContext(), "default");
		else
			outcomeExpression.setValue(ctx.getELContext(), outcome);			
	}

}
