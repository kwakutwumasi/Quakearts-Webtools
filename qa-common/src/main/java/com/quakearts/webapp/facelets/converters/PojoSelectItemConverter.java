package com.quakearts.webapp.facelets.converters;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import javax.el.ValueExpression;
import javax.faces.component.StateHolder;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

public class PojoSelectItemConverter implements Converter, StateHolder, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6641054285920618374L;
	private HashMap<String, Object> map;
	private ValueExpression collectionExpression;
	
	public void setCollectionExpression(ValueExpression collectionExpression) {
		this.collectionExpression = collectionExpression;
	}
	
	@Override
	public Object getAsObject(FacesContext ctx, UIComponent comp, String value) {
		return getObject(value, ctx);
	}

	@SuppressWarnings("rawtypes")
	private Object getObject(Object value, FacesContext ctx){
		if(value == null || collectionExpression==null)
			return null;
		
		if(map == null) {
			map = new HashMap<String, Object>();
			Object collectionObject = collectionExpression.getValue(ctx.getELContext()), asObject=null;
			if(collectionObject instanceof Object[]){
				for(Object object:((Object[])collectionObject)){
					if(object==null)
						continue;
					
					map.put(object.toString(), object);
					if(value.toString().equals(object.toString()))
						asObject = object;
				}
			} else if(collectionObject instanceof Collection) {
				Iterator it = ((Collection)collectionObject).iterator();
				while (it.hasNext()) {
					Object object = it.next();					
					map.put(object.toString(), object);
					if(value.toString().equals(object.toString()))
						asObject = object;					
				}
			} else {
				if(collectionObject!=null)
					map.put(collectionObject.toString(), collectionObject);
			}
			return asObject;
		} else {
			return map.get(value);
		}	
	}
	
	@Override
	public String getAsString(FacesContext ctx, UIComponent comp, Object object) {
		return object!=null?object.toString():"";
	}

	@Override
	public Object saveState(FacesContext context) {
		return collectionExpression;
	}

	@Override
	public void restoreState(FacesContext context, Object state) {
		collectionExpression =(ValueExpression) state;		
	}

	@Override
	public boolean isTransient() {
		return false;
	}

	@Override
	public void setTransient(boolean newTransientValue) {
	}

}
