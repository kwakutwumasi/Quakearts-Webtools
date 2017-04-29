package com.quakearts.webapp.orm.query;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Choice implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 388266923206203510L;
	private List<Serializable> choices = new ArrayList<>();
	
	public Choice() {
	}

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