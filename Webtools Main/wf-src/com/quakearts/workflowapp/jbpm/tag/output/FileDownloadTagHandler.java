package com.quakearts.workflowapp.jbpm.tag.output;

import java.util.Collection;
import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.view.facelets.ComponentConfig;
import javax.faces.view.facelets.ComponentHandler;
import javax.faces.view.facelets.FaceletContext;
import javax.faces.view.facelets.FaceletException;
import javax.faces.view.facelets.TagAttribute;

public class FileDownloadTagHandler extends ComponentHandler {

	private TagAttribute varAttribute = getRequiredAttribute("var"), 
	widthAttribute = getAttribute("width"), 
	heightaAttribute= getAttribute("height"),tidAttribute=getRequiredAttribute("tid");
	
	@SuppressWarnings("rawtypes")
	@Override
	public void onComponentCreated(FaceletContext ctx, UIComponent c,
			UIComponent parent) {
		if(!(c instanceof FileDownloadComponent))
			throw new FaceletException(FileDownloadTagHandler.class.getName()+" must be applied to components of type "+FileDownloadComponent.class.getName());
		
		FileDownloadComponent fileDC = (FileDownloadComponent) c;
		
		if(fileDC.getVar()==null){
			ValueExpression varExpression = varAttribute.getValueExpression(ctx, Collection.class);
	
			Object varObject = varExpression.getValue(ctx);
			if(varObject instanceof Collection){
				fileDC.setVar((Collection)varObject);
			}else{
				throw new FaceletException("link attribute cannot be null");			
			}
		}
		
		if(widthAttribute != null){
			try {
				fileDC.setWidth(Integer.parseInt(widthAttribute.getValueExpression(ctx, Object.class).getValue(ctx).toString()));
			} catch (Exception e) {
			}
		}
		
		if(heightaAttribute != null){
			try {
				fileDC.setHeight(Integer.parseInt(heightaAttribute.getValueExpression(ctx, Object.class).getValue(ctx).toString()));
			} catch (Exception e) {
			}			
		}
		
		if(fileDC.getTid()<=0){
			ValueExpression tidExpression = tidAttribute.getValueExpression(ctx, Long.class);
			
			Object tidObject = tidExpression.getValue(ctx);
			
			if(tidObject instanceof Long){
				fileDC.setTid((Long)tidObject);
			}else if(tidObject !=null){
				fileDC.setTid(Long.parseLong(tidObject.toString()));
			}else
				throw new FaceletException("tid attribute cannot be null");			
		}
		
		super.onComponentCreated(ctx, c, parent);
	}

	public FileDownloadTagHandler(ComponentConfig config) {
		super(config);
	}

}
