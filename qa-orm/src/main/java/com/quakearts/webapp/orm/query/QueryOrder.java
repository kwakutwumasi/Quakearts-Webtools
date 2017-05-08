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
package com.quakearts.webapp.orm.query;

import java.io.Serializable;

public class QueryOrder implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7468698834542995985L;
	private String property;
	private boolean ascending;
	
	public QueryOrder(String property, boolean ascending) {
		this.property = property;
		this.ascending = ascending;
	}

	public String getProperty() {
		return property;
	}
	
	public void setProperty(String property) {
		this.property = property;
	}
	
	public boolean isAscending() {
		return ascending;
	}
	
	public void setAscending(boolean ascending) {
		this.ascending = ascending;
	}
}
