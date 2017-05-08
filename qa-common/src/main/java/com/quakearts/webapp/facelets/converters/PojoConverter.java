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

public class PojoConverter implements Converter, StateHolder, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6641054285920618374L;
	private HashMap<String, Object> map = new HashMap<String, Object>();
	private ValueExpression collectionExpression;
	private boolean hasBeenLoaded = false;

	public void setCollectionExpression(ValueExpression collectionExpression) {
		this.collectionExpression = collectionExpression;
	}
	
	@Override
	public Object getAsObject(FacesContext ctx, UIComponent comp, String value) {
		return getObject(value, ctx);
	}

	@SuppressWarnings("rawtypes")
	private Object getObject(Object value, FacesContext ctx){
		if(value == null)
			return null;
		
		if(!hasBeenLoaded && collectionExpression!=null){
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
			hasBeenLoaded = true;
			return asObject;
		} 
		
		return map.get(value);
	}
	
	@Override
	public String getAsString(FacesContext ctx, UIComponent comp, Object object) {
		if(object!=null){
			if(collectionExpression==null){
				map.put(object.toString(), object);
			}
			return object.toString();
		} else {
			return "";
		}
	}

	@Override
	public Object saveState(FacesContext context) {
		return new Object[] {collectionExpression, map};
	}

	@SuppressWarnings("unchecked")
	@Override
	public void restoreState(FacesContext context, Object state) {
		Object[] oldstate = (Object[]) state;
		collectionExpression =(ValueExpression) oldstate[0];
		map = (HashMap<String, Object>) oldstate[1];
	}

	@Override
	public boolean isTransient() {
		return false;
	}

	@Override
	public void setTransient(boolean newTransientValue) {
	}

}
