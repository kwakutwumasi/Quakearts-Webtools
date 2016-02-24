package com.quakearts.webapp.facelets.tag.listener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.el.ELContext;
import javax.el.ValueExpression;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.quakearts.webapp.facelets.tag.BaseListener;

public class RemoveFromMapListener extends BaseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7204269439509012881L;
	private ValueExpression keysExpression, mapExpression, varExpression;
	
	public RemoveFromMapListener(ValueExpression keysExpression,
			ValueExpression mapExpression, ValueExpression varExpression) {
		this.keysExpression = keysExpression;
		this.mapExpression = mapExpression;
		this.varExpression = varExpression;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	protected void continueProcessing(ActionEvent event, FacesContext ctx) {
		ELContext elctx = ctx.getELContext();
		
		try {
			Map map =(Map) mapExpression.getValue(elctx);
			Map newMap = new HashMap();
			Object exceptObject = keysExpression.getValue(elctx);
			
			if(exceptObject instanceof String){
				String except = (String) exceptObject;
				String[] keys= except.split(";");
				for(String key:keys){
					if(map.containsKey(key))
						newMap.put(key, map.remove(key));
				}					
			} else if (exceptObject instanceof List){
				for(Object key:(List)exceptObject){
					if(map.containsKey(key))
						newMap.put(key, map.remove(key));
				}
			}
			setOutcome("success");
			
			if(varExpression!=null)
				varExpression.setValue(elctx, newMap);
			
		} catch (Exception e) {
			addError("Application error", "An error occured while removing elements from variable map", ctx);
			setOutcome("error");
		}

	}

}
