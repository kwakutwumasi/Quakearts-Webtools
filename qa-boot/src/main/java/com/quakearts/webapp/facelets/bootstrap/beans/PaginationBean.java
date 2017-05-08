/*******************************************************************************
* Copyright (C) 2016 Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com>.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com> - initial API and implementation
 ******************************************************************************/
package com.quakearts.webapp.facelets.bootstrap.beans;
import java.io.Serializable;
import java.util.Collection;

import javax.faces.component.UIComponent;

public class PaginationBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6438714134708931420L;
	private int begin, end;
	private Object value;
	private UIComponent dataComponent;
	
	public UIComponent getDataComponent() {
		return dataComponent;
	}
	
	public void setDataComponent(UIComponent dataComponent) {
		this.dataComponent = dataComponent;
	}
	
	public int getBegin() {
		return begin;
	}

	public void setBegin(int begin) {
		this.begin = begin;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public int getSize(){
		if(value==null)
			return 0;
		Class<?> clazz = value.getClass();
		if(clazz.isArray()){
			return ((Object[])value).length;
		} else if(Collection.class.isAssignableFrom(clazz)){
			return ((Collection<?>)value).size();
		}
		return 1;
	}
}
