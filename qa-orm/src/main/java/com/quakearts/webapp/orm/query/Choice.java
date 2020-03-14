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
import java.util.ArrayList;
import java.util.List;

/**Represents a range of selection criteria, one of which the property of the object searched for should match
 * @author kwakutwumasi-afriyie
 *
 */
public class Choice implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 388266923206203510L;
	private List<Serializable> choices = new ArrayList<>();
	
	public Choice() {}

	public Choice(Serializable value){
		choices.add(value);		
	}
	
	public List<Serializable> getChoices() {
		return choices;
	}
	
	public Choice or(Serializable value){
		choices.add(value);
		return this;
	}
	
	public boolean isEmpty(){
		return choices.isEmpty();
	}
}
