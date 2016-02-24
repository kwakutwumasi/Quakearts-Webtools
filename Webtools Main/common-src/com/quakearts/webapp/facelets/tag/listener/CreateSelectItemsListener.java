package com.quakearts.webapp.facelets.tag.listener;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.el.ValueExpression;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import com.quakearts.webapp.facelets.tag.BaseListener;
import com.quakearts.webapp.facelets.util.ObjectExtractor;

public class CreateSelectItemsListener extends BaseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3285661054880506492L;
	private ValueExpression listExpression, varExpression;
	
	public CreateSelectItemsListener(ValueExpression listExpression,
			ValueExpression varExpression) {
		this.listExpression = listExpression;
		this.varExpression = varExpression;
	}

	@Override
	protected void continueProcessing(ActionEvent event, FacesContext ctx) {
		String list = ObjectExtractor.extractString(listExpression, ctx.getELContext());
		if(list == null)
			throw new AbortProcessingException("List must not be null");
			
		List<SelectItem> items = new ArrayList<SelectItem>();
		StringTokenizer tokenizer = new StringTokenizer(list,";",false);
		while(tokenizer.hasMoreTokens()){
			String token=tokenizer.nextToken();
			int i=token.lastIndexOf(':');
			String value= i==-1||i==token.length()-1?token:token.substring(0,i);
			String label= i==-1||i==token.length()-1?token:token.substring(i+1,token.length());
			items.add(new SelectItem(value.trim(),label.trim()));
		}
		
		varExpression.setValue(ctx.getELContext(), items);
	}

}
