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
package com.quakearts.syshub.webapp.beans;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.DateTimeConverter;

import com.quakearts.syshub.SysHub;
import com.quakearts.syshub.SysHubMain;
import com.quakearts.syshub.core.runner.AgentRunner;
import com.quakearts.syshub.core.utils.SerializationUtil;
import com.quakearts.syshub.model.AgentConfiguration.RunType;
import com.quakearts.syshub.model.AgentConfigurationParameter.ParameterType;
import com.quakearts.syshub.model.AgentModule.ModuleType;
import com.quakearts.syshub.model.ProcessingLog.LogType;
import com.quakearts.syshub.webapp.helpers.utils.HtmlUtils;
import com.quakearts.webapp.facelets.util.Base64;

@ManagedBean(name="webappmain")
@ViewScoped
public class WebApplicationMain implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1658826860062260843L;
	private static Logger log = Logger.getLogger(WebApplicationMain.class.getName());
	private String mode;
	private Converter converter;
	
	private SysHub sysHub = SysHubMain.getInstance();
	
	public String getMode() {
		return mode;
	}
	
	public void setMode(String mode) {
		this.mode = mode;
	}
	
	public Converter getConverter() {
		if(converter==null){
			converter = new TimeStampConverter();
			((TimeStampConverter)converter).setPattern("dd/MM/yyyy HH:mm:ss");
		}
		return converter;
	}
	
	public static class TimeStampConverter extends DateTimeConverter implements Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = -4704193937507738852L;

		@Override
		public Object getAsObject(FacesContext context, UIComponent component, String dateString) {
			Object result;
			try {
				result = super.getAsObject(context, component, dateString);
				if (result instanceof Date) {
					result = new java.sql.Timestamp(((Date) result).getTime());
				}
			} catch (ConverterException ex) {
				log.severe("Exception of type " + ex.getClass().getName()
						+ " was thrown. Message is " + ex.getMessage());
				return null;
			}
			return result;
		}

		@Override
		public String getAsString(FacesContext context, UIComponent component, Object dateObject) {
			String result = null;
			try {
				result = super.getAsString(context, component, dateObject);
			} catch (ConverterException ex) {
				return null;
			}
			return result;
		}
	}

	public RunType[] getRunTypes() {
		return RunType.values();
	}

	public ParameterType[] getParameterTypes() {
		return ParameterType.values();
	}

	public ModuleType[] getModuleTypes() {
		return ModuleType.values();
	}

	public LogType[] getLogTypes() {
		return LogType.values();
	}
	
	public static class Base64Converter implements Converter {

		@Override
		public Object getAsObject(FacesContext context, UIComponent component, String value) {
			try {
				return Base64.decodeToBytes(value);
			} catch (IOException e) {
				FacesContext.getCurrentInstance().addMessage( component.getClientId(), new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Cannot convert Base64 string to bytes"));
				return null;
			}
		}

		@Override
		public String getAsString(FacesContext context, UIComponent component, Object value) {
			if(value instanceof byte[])
				return Base64.encodeBytes((byte[])value);
			else if(value != null)
				return Base64.encode(value.toString());
			
			return null;
		}
		
	}
	
	private Converter base64Converter = new Base64Converter();

	public Converter getBase64Converter() {
		return base64Converter;
	}

	public static class SerializedObjectConverter implements Converter {

		@Override
		public Object getAsObject(FacesContext context, UIComponent component, String value) {
			// Do nothing. This is a one way conversion
			return null;
		}

		@Override
		public String getAsString(FacesContext context, UIComponent component, Object value) {
			if(value instanceof byte[]){
				try {
					Object object = SerializationUtil.getInstance().toObject((byte[])value);
					String otherData = "";
					if(object instanceof Exception){
						StringWriter otherString = new StringWriter();
						((Exception)object).printStackTrace(new PrintWriter(otherString));
						otherData = "<br />"+otherString.toString();
					}
					return HtmlUtils.escape(object.toString()+otherData).replace("\n", "<br />");
				} catch (ClassNotFoundException | IOException e) {
					FacesContext.getCurrentInstance().addMessage( component.getClientId(), 
							new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error"
							, "Cannot convert bytes to object: "+e.getMessage()));
				}				
			}
			
			return null;
		}
	}
	
	private SerializedObjectConverter serializedObjectConverter = new SerializedObjectConverter();
	
	public SerializedObjectConverter getSerializedObjectConverter() {
		return serializedObjectConverter;
	}
	
	public boolean isInSearchMode(){
		return (FacesContext.getCurrentInstance()
					.getViewRoot().getViewId().endsWith(".list.xhtml"))
				&& (mode == null || mode.trim().isEmpty());
	}
	
	private AgentRunner agentRunner;
		
	public AgentRunner getAgentRunner() {
		return agentRunner;
	}

	public void setAgentRunner(AgentRunner agentRunner) {
		this.agentRunner = agentRunner;
	}

	public Collection<AgentRunner> getAgentRunners(){
		if(sysHub != null)
			return sysHub.listAgentRunners();
		
		return Collections.emptyList();
	}
}
