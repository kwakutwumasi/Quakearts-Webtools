package com.quakearts.approvalengine.web.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.DateTimeConverter;
import com.quakearts.approvalengine.model.Approval.ApprovalAction;

@Named("approvalapp")
@ViewScoped
public class ApprovalApplication implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 720141328103114282L;
	private static Logger log = Logger.getLogger(ApprovalApplication.class.getName());
	private String mode;
	private transient Converter converter;
	
	public String getMode() {
		return mode;
	}
	
	public void setMode(String mode) {
		this.mode = mode;
	}
	
	public Converter getConverter() {
		if(converter==null){
			converter = new TimeStampConverter();
			((TimeStampConverter)converter).setPattern("dd/MM/yyyy HH:mm:ss");
		}
		return converter;
	}
	
	public static class TimeStampConverter extends DateTimeConverter implements Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = -5292629346379609341L;

		@Override
		public Object getAsObject(FacesContext context, UIComponent component, String dateString) {
			Object result;
			try {
				result = super.getAsObject(context, component, dateString);
				if (result instanceof Date) {
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

	public ApprovalAction[] getApprovalActions() {
		return ApprovalAction.values();
	}
}
