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
			CONJUNCTION = "com.quakearts.orm.searchparams.CONJUNCTION";
		
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
	
	public ParameterMapBuilder add(String name, Serializable value){
		parameters.put(name, value);
		return this;
	}
	
	public ParameterMapBuilder addVariableString(String name, String value){
		return add(name, new VariableString(value));
	}
	
	public ParameterMapBuilder addRange(String name, Serializable from, Serializable to){
		return add(name, new Range().from(from).to(to));
	}
	
	public ParameterMapBuilder addChoices(String name, Serializable...values){
		Choice choice = new Choice();
		for(Serializable value:values){
			choice.or(value);
		}
		
		return add(name, choice);
	}
	
	public ParameterMapBuilder disjoin(){
		newParameters(DISJUNCTION);
		return this;
	}
	
	public ParameterMapBuilder conjoin(){
		newParameters(CONJUNCTION);
		return this;
	}
	
	private void newParameters(String type){
		HashMap<String, Serializable> newparameters = new HashMap<>();
		parameters.put(type,parameters);
		stack.push(parameters);
		parameters = newparameters;
	}
}
