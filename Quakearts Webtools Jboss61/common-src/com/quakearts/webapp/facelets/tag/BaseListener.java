package com.quakearts.webapp.facelets.tag;

import java.io.Serializable;
import javax.el.ValueExpression;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;

public abstract class BaseListener extends BaseBean implements ActionListener,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8614908016864040646L;
	private ValueExpression unlessExpression;
	
	protected abstract void continueProcessing(ActionEvent event, FacesContext ctx);

	@Override
	public void processAction(ActionEvent event) throws AbortProcessingException {
		if(evaluateUnless())
			continueProcessing(event, FacesContext.getCurrentInstance());
	}
	
	public void setUnlessExpression(ValueExpression unlessExpression) {
		this.unlessExpression = unlessExpression;
	}

	public ValueExpression getUnlessExpression() {
		return unlessExpression;
	}
	
	public boolean evaluateUnless(){
		FacesContext ctx = FacesContext.getCurrentInstance();
		boolean execute=true;
		if(unlessExpression !=null){
			Object unlessValue = unlessExpression.getValue(ctx.getELContext());
			if(unlessValue instanceof Boolean){
				execute = !(Boolean)unlessValue;
			} else if(unlessValue!=null){
				execute = !Boolean.parseBoolean(unlessValue.toString());
			}
		}
		return execute;
	}
}
