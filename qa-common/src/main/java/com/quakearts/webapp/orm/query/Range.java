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