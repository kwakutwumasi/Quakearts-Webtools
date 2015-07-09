package com.quakearts.webapp.facelets.tag.input.converter;

import java.util.Date;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.ConverterException;
import javax.faces.convert.DateTimeConverter;

import org.apache.log4j.Logger;

public class TimeStampConverter extends DateTimeConverter {
	private static final Logger log = Logger.getLogger(TimeStampConverter.class);

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
			log.error("Exception of type " + ex.getClass().getName()
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
