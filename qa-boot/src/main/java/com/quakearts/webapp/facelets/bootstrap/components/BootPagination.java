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
package com.quakearts.webapp.facelets.bootstrap.components;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.el.ValueExpression;
import javax.faces.component.UIData;
import javax.faces.component.html.HtmlCommandButton;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.model.DataModel;

import com.quakearts.webapp.facelets.bootstrap.beans.PaginationBean;
import com.quakearts.webapp.facelets.bootstrap.listeners.PaginationActionListener;
import com.quakearts.webapp.facelets.bootstrap.listeners.PaginationEvent;
import com.quakearts.webapp.facelets.util.ObjectExtractor;

public class BootPagination extends HtmlCommandButton {

	private int totalPages, pageSize, limit, currentPage=1;
	public static final String COMPONENT_FAMILY="com.quakearts.bootstrap.pagination";
	public static final String RENDERER_TYPE="com.quakearts.bootstrap.pagination.renderer";
	public static final String PAGINATION_KEY="com.quakearts.bootstrap.pagination.key";

	private List<PaginationActionListener> paginationListeners = new ArrayList<PaginationActionListener>();
	
	@Override
	public String getFamily() {
		return COMPONENT_FAMILY;
	}

	public BootPagination() {
	}

	public int calculatedTotalPages() {
		return totalPages;
	}

	public int calculatedPageSize() {
		return pageSize;
	}

	public int calculatedLimit() {
		return limit;
	}

	public int currentPage() {
		return currentPage;
	}
	
	@SuppressWarnings("rawtypes")
	public void updateDataModel(FacesContext context) {
		if(!isRendered())
			return;
			
		ValueExpression forExpression = getValueExpression("for");
		if(forExpression==null)
			throw new AbortProcessingException("Attribute for is required");
		
		Object forObject = forExpression.getValue(context.getELContext());
		if(!(forObject instanceof PaginationBean)&&(!(forObject instanceof UIData)))
			throw new AbortProcessingException("Attribute for must be of type "+PaginationBean.class.getName()
					+" or "+UIData.class.getName());
		
		ValueExpression pageSizeExpression = getValueExpression("pageSize");
		String pageSizeString;
		
		if(pageSizeExpression!=null)
			pageSize = ObjectExtractor.extractInteger(pageSizeExpression, context.getELContext());
		else {
			pageSizeString = (String) getAttributes().get("pageSize");

			if (pageSizeString != null)
				try {
					pageSize = Integer.parseInt(pageSizeString);
				} catch (Exception e) {
					pageSize = 10;
				}
			else
				pageSize = 10;
		}
		
		String limitString;
		ValueExpression limitExpression = getValueExpression("limit");
		if(limitExpression!=null)
			limit = ObjectExtractor.extractInteger(limitExpression, context.getELContext());
		else {
			limitString = (String) getAttributes().get("limit");

			if (limitString != null)
				try {
					limit = Integer.parseInt(limitString);
				} catch (Exception e) {
					limit = 5;
				}
			else
				limit = 5;
		}

		int totalRows;
		
		if(forObject instanceof UIData){
			Object value = ((UIData)forObject).getValue();
			if(value instanceof DataModel){
				totalRows = ((DataModel)value).getRowCount();
			} else if(value instanceof Collection){
				totalRows = ((Collection)value).size();
			} else if(value!=null && value.getClass().isArray()){
				totalRows = ((Object[])value).length;
			} else {
				totalRows=1;
			}
		} else {
			totalRows = ((PaginationBean)forObject).getSize();
		}
		
		if(totalRows>pageSize){
			totalPages = (totalRows/pageSize) + (totalRows % pageSize!=0?1:0);
		}		
		
		if(ObjectExtractor.extractBoolean(getValueExpression("reset"),
				context.getELContext())){
			currentPage =1;
		} else if(getValue()!=null)
			currentPage = Integer.parseInt(getValue().toString());
		else {
			currentPage = 1;
		}
			
		int first = (currentPage-1) * pageSize;
		int end = first+pageSize;
		if(end>totalRows)
			end=totalRows;
		
		if(forObject instanceof PaginationBean){
			PaginationBean pagination = ((PaginationBean)forObject);
			pagination.setBegin(first);
			pagination.setEnd(end);
			firePaginationEvent(first, end, pageSize, pagination.getValue());
		} else {
			UIData data = (UIData) forObject;			
			data.setFirst(first);
			data.setRows(pageSize);
			firePaginationEvent(first, end, pageSize, data.getValue());
		}
	}
	
	
	
	private void firePaginationEvent(int first, int end, int count, Object value) {
		if(paginationListeners.size()>0){
			PaginationEvent event = new PaginationEvent(first, end, count, value, this);
			
			for(PaginationActionListener listener:paginationListeners){
				listener.handleEvent(event);
			}
		}
	}

	@Override
	public String getRendererType() {
		return RENDERER_TYPE;
	}

	@Override
	public void setRendererType(String rendererType) {
	}
	
	public String get(String attribute) {
		String attributeValue = ObjectExtractor
				.extractString(getValueExpression(attribute), getFacesContext()
						.getELContext());
		if (attributeValue == null)
			attributeValue = (String) getAttributes().get(attribute);

		return attributeValue;
	}
	
	public void addPaginationListener(PaginationActionListener listener){
		paginationListeners.add(listener);
	}
	
	public boolean removePaginationListener(PaginationActionListener listener){
		return paginationListeners.remove(listener);
	}
}
