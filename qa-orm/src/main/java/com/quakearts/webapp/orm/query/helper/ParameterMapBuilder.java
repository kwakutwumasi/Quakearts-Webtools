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
package com.quakearts.webapp.orm.query.helper;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import com.quakearts.webapp.orm.query.Choice;
import com.quakearts.webapp.orm.query.Range;
import com.quakearts.webapp.orm.query.VariableString;

public class ParameterMapBuilder {
	private HashMap<String, Serializable> parameters;
	private Stack<HashMap<String, Serializable>> stack = new Stack<>();
	public static final String DISJUNCTION = "com.quakearts.orm.searchparams.DISJUNCTION",
			CONJUNCTION = "com.quakearts.orm.searchparams.CONJUNCTION",
			MAXRESULTS = "com.quakearts.orm.searchparams.MAXRESULTS";
		
	public ParameterMapBuilder() {
		parameters = new HashMap<>();
	}
	
	public Map<String, Serializable> build() {
		if(!stack.isEmpty()){
			parameters = stack.firstElement();
			stack.clear();
		}
		
		return parameters;
	}
	
	public ParameterMapBuilder add(String propertyName, Serializable value){
		parameters.put(propertyName, value);
		return this;
	}
	
	public ParameterMapBuilder addVariableString(String propertyName, String value){
		return add(propertyName, new VariableString(value));
	}
	
	public ParameterMapBuilder addRange(String propertyName, Serializable from, Serializable to){
		return add(propertyName, new Range().from(from).to(to));
	}
	
	public ParameterMapBuilder addChoices(String propertyName, Serializable...values){
		Choice choice = new Choice();
		for(Serializable value:values){
			choice.or(value);
		}
		
		return add(propertyName, choice);
	}
	
	public ParameterMapBuilder disjoin(){
		newParameters(DISJUNCTION);
		return this;
	}
	
	public ParameterMapBuilder conjoin(){
		newParameters(CONJUNCTION);
		return this;
	}
	
	public ParameterMapBuilder endjoin(){
		if(!stack.isEmpty()){
			parameters = stack.pop();
		}
		return this;	
	}
	
	public ParameterMapBuilder setMaxResults(int max){
		Map<String, Serializable> parameters = this.parameters;
		if(!stack.isEmpty()){
			parameters = stack.firstElement();
		}
		
		parameters.put(MAXRESULTS, max);
		return this;
	}
	
	private void newParameters(String type){
		HashMap<String, Serializable> newparameters = new HashMap<>();
		parameters.put(type, newparameters);
		stack.push(parameters);
		parameters = newparameters;
	}
	
	public static ParameterMapBuilder createParameters(){
		return new ParameterMapBuilder();
	}
}
