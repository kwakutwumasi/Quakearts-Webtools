package com.quakearts.webapp.facelets.tag.input.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.StateHolder;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public class FileNameValidator implements Validator, StateHolder {
	
	private boolean isTransient = false;
	private String[] restrictions;
	private String pattern;
	
	@Override
	public void validate(FacesContext ctx, UIComponent comp, Object value)
			throws ValidatorException {
		if(value ==null){
			throw new ValidatorException(getMessage("Null file name", "File name cannot be null"));
		}
		String file = value.toString().trim();
		if(file.contains(".."))
			throw new ValidatorException(getMessage("Illegal characters", "File name contains illegal character '..'"));
		
		if(file.contains("WEB-INF")||file.contains("META-INF")||file.contains("classes"))
			throw new ValidatorException(getMessage("Illegal characters", "File name cannot reference secure directories"));

		if(file.endsWith(".class")||file.endsWith(".jar")||file.endsWith(".jad"))
			throw new ValidatorException(getMessage("Illegal extension", "File name cannot end with .class, .jar or .jad"));			

		if(file.contains("\\"))
			throw new ValidatorException(getMessage("Illegal characters", "File path must use the / switch not \\"));
		
		if(restrictions !=null){
			for(String restriction:restrictions){
				if(file.endsWith(restriction))
					throw new ValidatorException(getMessage("Illegal characters", "File path cannot contain "+restriction));
			}
		}
		if(pattern!=null){
			if(file.matches(pattern))
				throw new ValidatorException(getMessage("Illegal characters", "File path matches pattern unauthorised '"+pattern+"'"));
		}
	}

	private FacesMessage getMessage(String summary,String details){
		FacesMessage message = new FacesMessage();
		message.setSeverity(FacesMessage.SEVERITY_ERROR);
		message.setDetail(details);
		message.setSummary(summary);
		return message;
	}

	public void setRestrictions(String[] restricted) {
		restrictions = restricted;
	}

	@Override
	public boolean isTransient() {
		return isTransient;
	}

	@Override
	public void restoreState(FacesContext ctx, Object state) {
		restrictions = (String[]) state;
	}

	@Override
	public Object saveState(FacesContext ctx) {
		return restrictions;
	}

	@Override
	public void setTransient(boolean isTransient) {
		this.isTransient=isTransient;		
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	public String getPattern() {
		return pattern;
	}
}
