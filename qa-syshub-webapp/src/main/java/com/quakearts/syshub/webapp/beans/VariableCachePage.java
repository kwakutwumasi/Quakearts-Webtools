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

import java.util.List;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import com.quakearts.webapp.facelets.base.BaseBean;
import com.quakearts.webapp.orm.query.helper.ParameterMapBuilder;
import com.quakearts.webapp.orm.exception.DataStoreException;
import com.quakearts.syshub.model.VariableCache;

@ManagedBean(name="variableCachePage")
@ViewScoped
public class VariableCachePage extends BaseBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2986685158076919558L;

	private static Logger log = Logger.getLogger(VariableCachePage.class.getName());

	private VariableCache variableCache;
	private WebApplicationMain webappmain;
	private transient VariableCacheFinder finder = new VariableCacheFinder();
		
	public VariableCachePage(){
		webappmain = new WebApplicationMain();
	}
	
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
		if(variableCache!=null){
		}
	}
	
	
	private List<VariableCache> variableCacheList;
	
	public List<VariableCache> getVariableCacheList(){
		return variableCacheList;
	}
	
	public void findVariableCache(ActionEvent event){
		ParameterMapBuilder parameterBuilder = new ParameterMapBuilder();
		
		if(variableCache.getAppKey() != null && ! variableCache.getAppKey().trim().isEmpty()){
			parameterBuilder.addVariableString("appKey", variableCache.getAppKey());
		}
    		
		try {
			variableCacheList = finder.findObjects(parameterBuilder.build());
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
}
