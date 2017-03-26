package com.quakearts.webapp.facelets.tag.listener;

import java.io.IOException;

import javax.el.ValueExpression;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;
import javax.servlet.http.HttpServletRequest;

import com.quakearts.webapp.facelets.util.ObjectExtractor;

public class LogoutActionListener implements ActionListener {

	private ValueExpression redirectExpression;

	public LogoutActionListener(ValueExpression redirectExpression) {
		this.redirectExpression = redirectExpression;
	}

	@Override
	public void processAction(ActionEvent event) throws AbortProcessingException {
		String redirect;
		FacesContext ctx = FacesContext.getCurrentInstance();
		HttpServletRequest req = (HttpServletRequest) ctx.getExternalContext().getRequest();
		
		try {
			req.getSession().invalidate();
		} catch (Exception e) {
		}
		
		if(redirectExpression!=null){
			redirect = ObjectExtractor.extractString(redirectExpression, ctx.getELContext());
			if(redirect==null)
				throw new AbortProcessingException("Attribute 'redirect' evaluated to null");
		} else {
			redirect = req.getContextPath()+ctx.getViewRoot().getViewId()
					+(req.getQueryString()!=null?"?"+req.getQueryString():"");
		}
		
		ctx.responseComplete();
		try {
			ctx.getExternalContext().redirect(redirect);
		} catch (IOException e) {
			return;
		}
	}

}
