package com.quakearts.webapp.facelets.bootstrap.beans;
import java.util.Collection;

import javax.faces.component.UIComponent;

public class PaginationBean {
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
