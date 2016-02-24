package com.quakearts.workflowapp.jbpm.tag.validator;

import javax.el.ValueExpression;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public class FileNameValidator implements Validator {

	private static final String filenamePattern = "[a-zA-Z0-9\\-\\.]+(\\.xhtml)";
	
	@Override
	public void validate(FacesContext context, UIComponent component, Object value)
			throws ValidatorException {
		
		if(context == null || component == null || value == null){
			FacesMessage message = new FacesMessage();
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			message.setDetail("FileNameValidator error. "
					+(context == null?"Context is null":(component == null?"Component is null":"Value is null")));
			throw new ValidatorException(message);			
		}
		String fileName;
		if(value instanceof ValueExpression){
			value = ((ValueExpression)value).getValue(context.getELContext());
			if(value==null){
				FacesMessage message = new FacesMessage();
				message.setSeverity(FacesMessage.SEVERITY_ERROR);
				message.setDetail("FileNameValidator error. Value is null");
				throw new ValidatorException(message);				
			}
			fileName = value.toString();
		}else{
			fileName = value.toString();			
		}

		if(!fileName.matches(filenamePattern)){
			FacesMessage message = new FacesMessage();
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			message.setDetail("FileNameValidator error. Value is not a valid file name");
			throw new ValidatorException(message);
		}
		
	}

}
