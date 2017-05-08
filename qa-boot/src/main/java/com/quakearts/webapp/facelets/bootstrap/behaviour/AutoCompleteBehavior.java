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
package com.quakearts.webapp.facelets.bootstrap.behaviour;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.component.behavior.ClientBehaviorBase;
import javax.faces.component.behavior.ClientBehaviorContext;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.AjaxBehaviorListener;
import javax.faces.event.BehaviorEvent;

import com.quakearts.webapp.facelets.util.ObjectExtractor;

public class AutoCompleteBehavior extends ClientBehaviorBase {
	
	/**
	 * 
	 */
	private String render;
	private String id;
	private String event;
	private String onerror;
	private String onevent;
	private String execute;
	private int delay = 5;
	private int minchars = 3;
	private static final String ATTRIBUTE = "suggestion";
	public static final String SUGGESTIONPRESENT = "com.quakearts.boot.SUGGESTIONPRESENT";
	private boolean hasSuggestion=false;
	private String suggest;
	
	public String getRender() {
		return render;
	}
	
	public void setRender(String render) {
		this.render = render;
	}
	
	public String getOnerror() {
		return onerror;
	}

	public void setOnerror(String onerror) {
		this.onerror = onerror;
	}

	public String getOnevent() {
		return onevent;
	}

	public void setOnevent(String onevent) {
		this.onevent = onevent;
	}

	public String getExecute() {
		return execute;
	}

	public void setExecute(String execute) {
		this.execute = execute;
	}
	
	public int getDelay() {
		return delay;
	}
	
	public void setDelay(int delay) {
		this.delay = delay;
	}

	public int getMinchars() {
		return minchars;
	}
	
	public void setMinchars(int minchars) {
		this.minchars = minchars;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public void setEvent(String event) {
		this.event = event;
	}
	
	public String getEvent() {
		return event;
	}
	
	public boolean hasSuggestion() {
		return hasSuggestion;
	}
	
	public String getSuggest() {
		return suggest;
	}
	
	@Override
	public void decode(FacesContext context, UIComponent component) {
		if (null == context || null == component) {
            throw new NullPointerException();
        }
		
		if(id!=null){
			suggest = context.getExternalContext().getRequestParameterMap().get(id);
			if(suggest == null 	|| suggest.trim().isEmpty()){
				return;
			}
			
			ValueExpression suggestionExpression = component.getValueExpression(ATTRIBUTE);
						
			if(suggestionExpression!=null){
				suggestionExpression.setValue(context.getELContext(), suggest);
				hasSuggestion = true;
			}
			
	        String behaviorEvent = context.getExternalContext().getRequestParameterMap().get("javax.faces.behavior.event");
	        if(getEvent()!=null && getEvent().equals(behaviorEvent)){
	        	context.getAttributes().put(SUGGESTIONPRESENT, Boolean.TRUE);
	        }
		}
		component.queueEvent(new AjaxBehaviorEvent(component, this));
	}
	
	public void loadFromComponent(UIComponent component, FacesContext context){
		render = ObjectExtractor.extractString(component.getValueExpression("render"), context.getELContext());
		if(render==null)
			render = (String) component.getAttributes().get("render");
		
		execute = ObjectExtractor.extractString(component.getValueExpression("execute"), context.getELContext());
		if(execute==null)
			execute = (String) component.getAttributes().get("execute");

		onerror = ObjectExtractor.extractString(component.getValueExpression("onerror"), context.getELContext());
		if(onerror==null)
			onerror = (String) component.getAttributes().get("onerror");

		onevent = ObjectExtractor.extractString(component.getValueExpression("onevent"), context.getELContext());
		if(onevent==null)
			onevent = (String) component.getAttributes().get("onevent");
		
		ValueExpression delayExpression;
		if((delayExpression=component.getValueExpression("delay"))!=null){
			 delay = ObjectExtractor.extractInteger(delayExpression, context.getELContext());
		} else {
			try {
				delay = Integer.parseInt((String)component.getAttributes().get("delay"));
			} catch (Exception e) {
			}
		}
		
		ValueExpression mincharsExpression;
		if((mincharsExpression=component.getValueExpression("minchars"))!=null){
			 minchars = ObjectExtractor.extractInteger(mincharsExpression, context.getELContext());
		} else {
			try {
				minchars = Integer.parseInt((String)component.getAttributes().get("minchars"));
			} catch (Exception e) {
			}
		}
	}
	
	@Override
	public void broadcast(BehaviorEvent event) throws AbortProcessingException {
		if(event instanceof AjaxBehaviorEvent)
			super.broadcast(event);
		else {
			AjaxBehaviorEvent ajaxBehaviorEvent = new AjaxBehaviorEvent(event.getComponent(), null);
			super.broadcast(ajaxBehaviorEvent);
		}
	}
	
	public void addAjaxBehaviour(AjaxBehaviorListener ajaxBehavior){
		addBehaviorListener(ajaxBehavior);
	}
	
	@Override
	public String getScript(ClientBehaviorContext behaviorContext) {	
		String compId = behaviorContext.getComponent().getClientId();

		return "if(this.value.length>="+minchars+") qab.qact(function(){ jsf.ajax.request($('#"
				+compId+"')[0]"
				+", event, {execute:'"+compId+(execute!=null?" "+execute:"")+"', render: '"+compId
				+(render!=null&&render.length()>0? " "+render:"")+"'"
				+(onevent!=null&&onevent.length()>0? ", onevent: "+onevent+"":"")
				+(onerror!=null&&onerror.length()>0?", onerror: "+onerror+"":"")
				+",'javax.faces.behavior.event':'"+event+"'});},"
				+(delay*100)+");";
	}
	
	@Override
	public Object saveState(FacesContext context) {		
		Object superState = super.saveState(context);
		return new Object[]{id,superState};
	}
	
	@Override
	public void restoreState(FacesContext context, Object stateObj) {
		Object[] state = (Object[]) stateObj;
		id = (String) state[0];
		super.restoreState(context, state[1]);
	}
}
