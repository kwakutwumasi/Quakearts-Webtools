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
import com.quakearts.syshub.model.ResultExceptionLog;

public class ResultExceptionLogDropdownHelper implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5036785675708635627L;

	private transient ResultExceptionLogFinder finder = new ResultExceptionLogFinder();
	
	private List<ResultExceptionLog> foundItems;
	public List<ResultExceptionLog> getFoundItems() {
    	return foundItems;
    }
    	
	private ResultExceptionLog resultExceptionLog;
	
	public ResultExceptionLog getResultExceptionLog(){
		if(resultExceptionLog == null){
			resultExceptionLog = new ResultExceptionLog();
			addToFoundItemsList(resultExceptionLog);
		}
		
		return resultExceptionLog;
	}
	
	private boolean inCreateMode;
	
	public boolean isInCreateMode() {
		return inCreateMode;
	}

	public void setInCreateMode(boolean inCreateMode) {
		this.inCreateMode = inCreateMode;
	}

	public void addToFoundItemsList(ResultExceptionLog resultExceptionLog) {
		if(foundItems==null)
			foundItems = new ArrayList<>();
		
		if(!foundItems.contains(resultExceptionLog))
			foundItems.add(resultExceptionLog);
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
