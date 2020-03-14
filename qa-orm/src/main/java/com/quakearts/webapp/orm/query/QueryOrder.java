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
package com.quakearts.webapp.orm.query;

import java.io.Serializable;

/**Used to specify the order in which objects should be returned in the search
 * @author kwakutwumasi-afriyie
 *
 */
public class QueryOrder implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7468698834542995985L;
	private String property;
	private boolean ascending = true;

	public static QueryOrder property(String property){
		return new QueryOrder(property);
	}
		
	private QueryOrder(String property) {
		this.property = property;
	}

	public String getProperty() {
		return property;
	}
	
	public QueryOrder ascending(){
		ascending = true;
		return this;
	}
	
	public QueryOrder descending() {
		ascending = false;
		return this;
	}

	public boolean isAscending() {
		return ascending;
	}

	public boolean isDescending(){
		return !ascending;
	}
}
