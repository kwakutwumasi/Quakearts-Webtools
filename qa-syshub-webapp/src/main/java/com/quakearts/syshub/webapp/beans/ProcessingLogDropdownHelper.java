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

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import javax.faces.event.AjaxBehaviorEvent;
import com.quakearts.syshub.model.ProcessingLog;

public class ProcessingLogDropdownHelper implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6389954004294133708L;

	private transient ProcessingLogFinder finder = new ProcessingLogFinder();
	
	private List<ProcessingLog> foundItems;
	public List<ProcessingLog> getFoundItems() {
    	return foundItems;
    }
    	
	private ProcessingLog processingLog;
	
	public ProcessingLog getProcessingLog(){
		if(processingLog == null){
			processingLog = new ProcessingLog();
			addToFoundItemsList(processingLog);
		}
		
		return processingLog;
	}
	
	private boolean inCreateMode;
	
	public boolean isInCreateMode() {
		return inCreateMode;
	}

	public void setInCreateMode(boolean inCreateMode) {
		this.inCreateMode = inCreateMode;
	}

	public void addToFoundItemsList(ProcessingLog processingLog) {
		if(foundItems==null)
			foundItems = new ArrayList<>();
		
		foundItems.add(processingLog);
	}
	
	private String searchText;
	
	public String getSearchText() {
		return searchText;
	}
	
	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}
	
	public void filterItems(AjaxBehaviorEvent event){
		if(searchText !=null && !searchText.trim().isEmpty()){
			foundItems = finder.filterByText(searchText);
		}
	}
}
