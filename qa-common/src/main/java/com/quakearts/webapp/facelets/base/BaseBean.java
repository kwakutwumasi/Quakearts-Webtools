/*******************************************************************************
 * Copyright (C) 2017 Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com>.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com> - initial API and implementation
 ******************************************************************************/
package com.quakearts.webapp.facelets.base;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import com.quakearts.webapp.facelets.tag.NavigationResolver;

public abstract class BaseBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private NavigationResolver resolver;
	private static final NavigationResolver DEFAULT_RESOLVER = new NavigationResolver() {		
		@Override
		public void setOutcome(String outCome) {
		}
	};
	
	protected void storeInView(String key, Object value){
		FacesContext.getCurrentInstance().getViewRoot().getViewMap().put(key, value);
	}
	
	protected Object retrieveFromView(String key){
		return FacesContext.getCurrentInstance().getViewRoot().getViewMap().get(key);
	}
	
	protected void setOutcome(String outcome){
		if(resolver==null){
			FacesContext context = FacesContext.getCurrentInstance();
			ServletContext servletContext = (ServletContext) context.getExternalContext().getContext();
			
			resolver = (NavigationResolver) servletContext.getAttribute("com.quakearts.navigation.resolver");
			if(resolver==null) {
				String string = servletContext.getInitParameter("navigation.resolver");
				if(string == null){
					resolver = DEFAULT_RESOLVER;
				} else {
					try {
						resolver =(NavigationResolver) Class.forName(string).newInstance();
					} catch (Exception e) {
						System.err.println("[com.quakearts.navigation] Cannot instantiate "+string+". Exception of type " + e.getClass().getName()
								+ " was thrown. Message is " + e.getMessage());
						resolver = DEFAULT_RESOLVER;
					}
				}
				servletContext.setAttribute("com.quakearts.navigation.resolver", resolver);
			}
		}
		
		resolver.setOutcome(outcome);
	}
	
	protected void addError(String summary,String detail,FacesContext ctx){
		addError(summary, detail, ctx, null);
	}

	protected void addError(String summary,String detail,FacesContext ctx,String clientID){
		FacesMessage message = new FacesMessage();
		message.setSeverity(FacesMessage.SEVERITY_ERROR);
		message.setDetail(detail);
		message.setSummary(summary);
		ctx.addMessage(clientID, message);
	}

	
	protected void addMessage(String summary,String detail, FacesContext ctx){
		addMessage(summary, detail, ctx, null);
	}

	protected void addMessage(String summary,String detail, FacesContext ctx, String clientID){
		FacesMessage message = new FacesMessage();
		message.setSeverity(FacesMessage.SEVERITY_INFO);
		message.setDetail(detail);
		message.setSummary(summary);
		ctx.addMessage(clientID, message);		
	}

	
	protected void addWarning(String summary,String detail, FacesContext ctx){
		addWarning(summary, detail, ctx, null);
	}
	
	protected void addWarning(String summary,String detail, FacesContext ctx, String clientID){
		FacesMessage message = new FacesMessage();
		message.setSeverity(FacesMessage.SEVERITY_WARN);
		message.setDetail(detail);
		message.setSummary(summary);
		ctx.addMessage(clientID, message);		
	}

	protected String getParameter(String name){
		return FacesContext.getCurrentInstance()
				.getExternalContext()
				.getRequestParameterMap()
				.get(name);
	}

	protected int getParameterInt(String name){
		String value = getParameter(name);
		if(value==null)
			return 0;
		return Integer.parseInt(value);
	}
	
	protected long getParameterLong(String name){
		String value = getParameter(name);
		if(value==null)
			return 0;
		return Long.parseLong(value);
	}
	
	protected boolean hasParameter(String name) {
		return FacesContext.getCurrentInstance()
		.getExternalContext()
		.getRequestParameterMap().containsKey(name);
	}
}
