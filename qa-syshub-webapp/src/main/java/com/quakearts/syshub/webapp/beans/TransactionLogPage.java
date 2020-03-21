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
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.quakearts.webapp.facelets.base.BaseBean;
import com.quakearts.webapp.orm.query.Range;
import com.quakearts.webapp.orm.query.criteria.CriteriaMapBuilder;
import com.quakearts.webapp.orm.exception.DataStoreException;
import com.quakearts.syshub.model.TransactionLog;
import com.quakearts.syshub.model.ProcessingLog;

@Named("transactionLogPage")
@ViewScoped
public class TransactionLogPage extends BaseBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7173268664733723334L;

	private static Logger log = Logger.getLogger(TransactionLogPage.class.getName());

	private TransactionLog transactionLog;
	@Inject @Named("webappmain")
	private WebApplicationMain webappmain;
	private transient TransactionLogFinder finder = new TransactionLogFinder();
			
	public WebApplicationMain getWebappmain(){
		return webappmain;
	}
	
	public TransactionLog getTransactionLog() {
		if(transactionLog==null){
			if(hasParameter("transactionLog")){
				setTransactionLog(finder.getById(getParameterLong("transactionLog")));
				webappmain.setMode("read");
			} else {
    			transactionLog = new TransactionLog();
			}
		}
		
		return transactionLog;
	}
    	
	public void setTransactionLog(TransactionLog transactionLog) {
		this.transactionLog = transactionLog;
		if(transactionLog!=null){
			ProcessingLog processingLog = transactionLog.getProcessingLog();
			if(processingLog!=null){
				getProcessingLogDropdownHelper().addToFoundItemsList(processingLog);
			}
		}
	}
	
	private ProcessingLogDropdownHelper processingLogDropdownHelper;

	public ProcessingLogDropdownHelper getProcessingLogDropdownHelper(){
		if(processingLogDropdownHelper == null)
			processingLogDropdownHelper = new ProcessingLogDropdownHelper();
			
		return processingLogDropdownHelper;
	}
	
	private List<TransactionLog> transactionLogList;
	
	public List<TransactionLog> getTransactionLogList(){
		return transactionLogList;
	}
	
	private Range tranDtRange = new Range();
	
	public Range getTranDtRange() {
		return tranDtRange;
	}
	
	public void findTransactionLog(ActionEvent event){
		CriteriaMapBuilder criteriaMapBuilder = CriteriaMapBuilder.createCriteria();
		if(transactionLog.getAction() != null && ! transactionLog.getAction().trim().isEmpty()){
			criteriaMapBuilder.property("action").mustBeEqualTo(transactionLog.getAction());
		}
		
		if(transactionLog.getProcessingLog() != null){
			criteriaMapBuilder.property("processingLog").mustBeEqualTo(transactionLog.getProcessingLog());
		}
		
		if(!tranDtRange.isEmpty()){
			criteriaMapBuilder.property("tranDt").mustBeEqualTo(tranDtRange);
		}
		
		if(transactionLog.getUsername() != null && ! transactionLog.getUsername().trim().isEmpty()){
			criteriaMapBuilder.property("username").mustBeLike(transactionLog.getUsername());
		}
    		
		try {
			transactionLogList = finder.findObjects(criteriaMapBuilder.finish());
		} catch (DataStoreException e) {
			addError("Search error", "An error occured while searching for Transaction Log", FacesContext.getCurrentInstance());
			log.severe("Exception of type " + e.getClass().getName() + " was thrown. Message is " + e.getMessage()
					+ ". Exception occured whiles searching for TransactionLog");
		}		
	}
    	
	public void removeTransactionLog(ActionEvent event){
		if(transactionLog!=null && transactionLogList!=null){
			transactionLogList.remove(transactionLog);
		}
	}
	
	public boolean isInCreateOrEditMode(){
		return FacesContext.getCurrentInstance().getViewRoot().getViewId().endsWith("create.xhtml") || "edit".equals(webappmain.getMode());
	}
}
