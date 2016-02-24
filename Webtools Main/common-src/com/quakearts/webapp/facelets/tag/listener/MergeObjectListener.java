package com.quakearts.webapp.facelets.tag.listener;

import javax.el.ValueExpression;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.quakearts.webapp.facelets.tag.HibernateListener;
import com.quakearts.webapp.facelets.util.ObjectExtractor;

public class MergeObjectListener extends HibernateListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -492106451127617568L;
	private ValueExpression objectExpression,deleteExpression;
	private ValueExpression messageExpression;

	public MergeObjectListener(ValueExpression objectExpression,ValueExpression deleteExpression, ValueExpression messageExpression) {
		this.objectExpression = objectExpression;
		this.deleteExpression = deleteExpression;
		this.messageExpression = messageExpression;
	}

	@Override
	protected void continueProcessing(ActionEvent event, FacesContext ctx) {
		boolean delete = ObjectExtractor.extractBoolean(deleteExpression, ctx.getELContext());
		Object obj = objectExpression.getValue(ctx.getELContext());
		String message = ObjectExtractor.extractString(messageExpression, ctx.getELContext());
		
		try {
			if(obj !=null){
				obj = session.merge(obj);
				
				if(delete){
					session.delete(obj);
					setOutcome("success");
					addMessage("Deleted",message==null?(obj.getClass().getSimpleName()+" has been successfully deleted"):message, ctx);
				} else {
					setOutcome("success");
					addMessage("Modified",message==null?(obj.getClass().getSimpleName()+" has been successfully updated"):message, ctx);
					objectExpression.setValue(ctx.getELContext(), obj);
				}
			} else {
				setOutcome("error");
				addError("Invalid object", "Object attribute evaluated to null", ctx);
			}
		} catch (Exception e) {
			addError("Exception merging object", e.getClass().getSimpleName()+". "+e.getMessage(), ctx);
		}
	}

}
