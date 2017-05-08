/*******************************************************************************
 * Copyright (C) 2017 Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com>.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com> - initial API and implementation
 ******************************************************************************/
package com.quakearts.webapp.facelets.tag.input.converter;

import java.util.Date;
import java.util.logging.Logger;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.ConverterException;
import javax.faces.convert.DateTimeConverter;

public class TimeStampConverter extends DateTimeConverter {
	private static final Logger log = Logger.getLogger(TimeStampConverter.class.getName());

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String dateString) {
		Object result;
		try {
			result = super.getAsObject(context, component, dateString);
			if (result instanceof Date) {
				//make it a Timestamp, because that is what jBPM will make of it anyway
				result = new java.sql.Timestamp(((Date) result).getTime());
			}
		} catch (ConverterException ex) {
			log.severe("Exception of type " + ex.getClass().getName()
					+ " was thrown. Message is " + ex.getMessage());
			return null;
		}
		return result;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object dateObject) {
		String result = null;
		try {
			result = super.getAsString(context, component, dateObject);
		} catch (ConverterException ex) {
			return null;
		}
		return result;
	}
}
