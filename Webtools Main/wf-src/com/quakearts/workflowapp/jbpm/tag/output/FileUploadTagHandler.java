package com.quakearts.workflowapp.jbpm.tag.output;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.view.facelets.ComponentConfig;
import javax.faces.view.facelets.ComponentHandler;
import javax.faces.view.facelets.FaceletContext;
import javax.faces.view.facelets.FaceletException;
import javax.faces.view.facelets.TagAttribute;

public class FileUploadTagHandler extends ComponentHandler {

	private TagAttribute varAttribute = getRequiredAttribute("var"), tidAttribute=getRequiredAttribute("tid"), 
	widthAttribute = getAttribute("width"), 
	heightaAttribute= getAttribute("height");
	
	@Override
	public void onComponentCreated(FaceletContext ctx, UIComponent c,
			UIComponent parent) {
		
		if(!(c instanceof FileUploadComponent))
			throw new FaceletException(FileUploadTagHandler.class.getName()+" must be applied to components of type "+FileUploadComponent.class.getName());
	
		FileUploadComponent fileUC = (FileUploadComponent) c;
		
		if(fileUC.getVar()==null){
			ValueExpression varExpression = varAttribute.getValueExpression(ctx, String.class);
			Object varObject = varExpression.getValue(ctx);
			if(varObject !=null){
				fileUC.setVar(varObject.toString());
			}else{
				throw new FaceletException("var attribute cannot be null");			
			}
		}
		
		if(fileUC.getTid()<=0){
			ValueExpression tidExpression = tidAttribute.getValueExpression(ctx, Long.class); 
			Object tidObject = tidExpression.getValue(ctx);
			if(tidObject instanceof Long)
				fileUC.setTid(((Long)tidObject).longValue());
			else if(tidObject !=null)
				fileUC.setTid(Long.parseLong(tidObject.toString()));
			else
				throw new FaceletException("var attribute cannot be null");			
		}
		
		if(widthAttribute != null){
			try {
				fileUC.setWidth(Integer.parseInt(widthAttribute.getValueExpression(ctx, Object.class).getValue(ctx).toString()));
			} catch (Exception e) {
			}
		}
		
		if(heightaAttribute != null){
			try {
				fileUC.setHeight(Integer.parseInt(heightaAttribute.getValueExpression(ctx, Object.class).getValue(ctx).toString()));
			} catch (Exception e) {
			}			
		}
	}

	public FileUploadTagHandler(ComponentConfig config) {
		super(config);
	}

}
