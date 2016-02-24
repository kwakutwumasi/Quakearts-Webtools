package com.quakearts.webapp.facelets.bootstrap.listeners;

import javax.faces.component.UIComponent;
import javax.faces.event.ActionEvent;

public class PaginationEvent extends ActionEvent {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8110646160489696844L;
	private int start;
	private int end;
	private int count;
	private Object value;

	public PaginationEvent(int start, int end, int count, Object value, UIComponent comp) {
		super(comp);
		this.start = start;
		this.end = end;
		this.count = count;
		this.value = value;
	}

	public int getStart() {
		return start;
	}

	public int getCount() {
		return count;
	}

	public Object getValue() {
		return value;
	}
	
	public int getEnd() {
		return end;
	}
}
