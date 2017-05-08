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
package com.quakearts.webapp.facelets.tag.listener;

import java.io.InputStream;

import javax.el.ValueExpression;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.quakearts.webapp.facelets.tag.BaseListener;
import com.quakearts.webapp.facelets.util.ObjectExtractor;

public class GetBytesListener extends BaseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5962539502686632722L;
	private ValueExpression varExpression;
	private ValueExpression streamExpression;
	private ValueExpression maxSizeExpression;

	public GetBytesListener(ValueExpression streamExpression,
			ValueExpression varExpression, ValueExpression maxSizeExpression) {
		this.varExpression = varExpression;
		this.streamExpression = streamExpression;
		this.maxSizeExpression = maxSizeExpression;
	}

	@Override
	protected void continueProcessing(ActionEvent event, FacesContext ctx) {
		InputStream stream = null;
		int maxSize=0;
		if(maxSizeExpression != null)
			try {
				maxSize = ObjectExtractor.extractInteger(maxSizeExpression, ctx.getELContext());
			} catch (Exception e) {
				addError("Application error", "Error reading maxSize attribute", ctx);
				setOutcome("error");
				return;
			}
		
		try {
			stream = (InputStream) streamExpression.getValue(ctx.getELContext());
			
			if(maxSize>0 && stream.available()>maxSize){
				String maxString;
				if(maxSize>1048576)
					maxString = (maxSize / 1048576 + ((double) (maxSize % 1048576)/1048576d)) +" MB";
				else if(maxSize>1024)
					maxString = (maxSize / 1024 + ((double) (maxSize % 1024)/1024d)) +" KB";
				else
					maxString = maxSize +" bytes";

				addError("Application error", "File exceeds maximum size. File must not be larger than "+maxString, ctx);
				setOutcome("error");
				return;
			}
			
			byte[] bites = new byte[stream.available()];
			stream.read(bites);
			varExpression.setValue(ctx.getELContext(), bites);
			setOutcome("success");
			addMessage("Success", "File was successfully uploaded.", ctx);
		} catch (Exception e) {
			setOutcome("error");
			addError("Application error", "Could not upload file", ctx);
		} finally{
			try {				
				stream.close();
			} catch (Exception e2) {
			}
		}

	}

}
