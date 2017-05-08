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

public class Range implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7151788060480527063L;
	private Serializable from, to;

	public Serializable getFrom() {
		return from;
	}

	public void setFrom(Serializable from) {
		this.from = from;
	}

	public Serializable getTo() {
		return to;
	}

	public void setTo(Serializable to) {
		this.to = to;
	}
	
	public Range from(Serializable from){
		this.from = from;
		return this;
	}
	
	public Range to(Serializable to){
		this.to = to;
		return this;
	}
	
	public boolean isEmpty(){
		return from == null || to == null;
	}
}
