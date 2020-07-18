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

import java.util.Base64;
import java.util.List;
import java.util.logging.Logger;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.quakearts.webapp.facelets.base.BaseBean;
import com.quakearts.webapp.orm.query.criteria.CriteriaMapBuilder;
import com.quakearts.webapp.orm.exception.DataStoreException;
import com.quakearts.syshub.model.VariableCache;

@Named("variableCachePage")
@ViewScoped
public class VariableCachePage extends BaseBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2986685158076919558L;

	private static Logger log = Logger.getLogger(VariableCachePage.class.getName());

	private VariableCache variableCache;
	@Inject @Named("webappmain")
	private WebApplicationMain webappmain;
	private transient VariableCacheFinder finder = new VariableCacheFinder();
			
	public WebApplicationMain getWebappmain(){
		return webappmain;
	}
	
	public VariableCache getVariableCache() {
		if(variableCache==null){
			if(hasParameter("variableCache")){
				setVariableCache(finder.getById(getParameter("variableCache")));
				webappmain.setMode("read");
			} else {
    			variableCache = new VariableCache();
			}
		}
		
		return variableCache;
	}
    	
	public void setVariableCache(VariableCache variableCache) {
		this.variableCache = variableCache;
	}
	
	
	private List<VariableCache> variableCacheList;
	
	public List<VariableCache> getVariableCacheList(){
		return variableCacheList;
	}
	
	public void findVariableCache(ActionEvent event){
		CriteriaMapBuilder criteriaMapBuilder = CriteriaMapBuilder.createCriteria();
		
		if(variableCache.getAppKey() != null && ! variableCache.getAppKey().trim().isEmpty()){
			criteriaMapBuilder.property("appKey").mustBeLike(variableCache.getAppKey());
		}
    		
		try {
			variableCacheList = finder.findObjects(criteriaMapBuilder.finish());
		} catch (DataStoreException e) {
			addError("Search error", "An error occured while searching for Variable Cache", FacesContext.getCurrentInstance());
			log.severe("Exception of type " + e.getClass().getName() + " was thrown. Message is " + e.getMessage()
					+ ". Exception occured whiles searching for VariableCache");
		}		
	}
    	
	public void removeVariableCache(ActionEvent event){
		if(variableCache!=null && variableCacheList!=null){
			variableCacheList.remove(variableCache);
		}
	}
		
	public boolean isInCreateOrEditMode(){
		return FacesContext.getCurrentInstance().getViewRoot().getViewId().endsWith("create.xhtml") || "edit".equals(webappmain.getMode());
	}
	
	private transient Converter base64Converter = new Base64Converter();
	
	public Converter getBase64Converter() {
		return base64Converter;
	}
	
	private class Base64Converter implements Converter {

		@Override
		public Object getAsObject(FacesContext context, UIComponent component, String value) {
			if(value!=null)
				return Base64.getEncoder().encode(value.getBytes());
			
			return value;
		}

		@Override
		public String getAsString(FacesContext context, UIComponent component, Object value) {
			if(value instanceof byte[])
				return new String(Base64.getDecoder().decode((byte[])value));
			
			return "";
		}
	}
}
