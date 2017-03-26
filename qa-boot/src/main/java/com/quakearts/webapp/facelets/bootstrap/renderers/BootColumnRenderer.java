package com.quakearts.webapp.facelets.bootstrap.renderers;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import com.quakearts.webapp.facelets.bootstrap.components.BootColumn;
import com.quakearts.webapp.facelets.bootstrap.renderkit.html_basic.HtmlBasicRenderer;

public class BootColumnRenderer extends HtmlBasicRenderer {
	@Override
	public void encodeBegin(FacesContext context, UIComponent component)
			throws IOException {
		if (context == null)
        	throw new NullPointerException();
        
        if(!(component instanceof BootColumn)) {
            throw new IOException("Component must be of type "+BootColumn.class.getName());
        }
        
        BootColumn column = (BootColumn) component;
             
        StringBuilder classString = new StringBuilder();
        if(column.xsSize()>0){
        	classString.append("col-xs-").append(column.xsSize());
        }
        
        if(column.offsetxsSize()>0){
        	if(classString.length()>0)
        		classString.append(" ");
        	
        	classString.append("col-xs-offset-").append(column.offsetxsSize());
        }
        
        if(column.smSize()>0){
        	if(classString.length()>0)
        		classString.append(" ");
        	
        	classString.append("col-sm-").append(column.smSize());
        }
        
        if(column.offsetsmSize()>0){
        	if(classString.length()>0)
        		classString.append(" ");
        	
        	classString.append("col-sm-offset-").append(column.offsetsmSize());
        }

        if(column.mdSize()>0){
        	if(classString.length()>0)
        		classString.append(" ");
        	
        	classString.append("col-md-").append(column.mdSize());
        }

        if(column.offsetmdSize()>0){
        	if(classString.length()>0)
        		classString.append(" ");
        	
        	classString.append("col-md-offset-").append(column.offsetmdSize());
        }

        if(column.lgSize()>0){
        	if(classString.length()>0)
        		classString.append(" ");
        	
        	classString.append("col-lg-").append(column.lgSize());
        }

        if(column.offsetlgSize()>0){
        	if(classString.length()>0)
        		classString.append(" ");
        	
        	classString.append("col-lg-offset-").append(column.offsetlgSize());
        }        
        
        String style = column.get("style");
        String styleClass = column.get("styleClass");
        
		ResponseWriter writer = context.getResponseWriter();
		writer.startElement("div",component);
		writeIdAttributeIfNecessary(context, writer, component);
		writer.writeAttribute("class", classString.append(styleClass != null ? " "+styleClass : "").toString(), null);
		if(style!=null)
			writer.writeAttribute("style", style, null);
		
		context.getResponseWriter().write("\n");
	}
	
	@Override
	public void encodeEnd(FacesContext context, UIComponent component)
			throws IOException {
		context.getResponseWriter().endElement("div");
		context.getResponseWriter().write("\n");
	}
	
	@Override
	public boolean getRendersChildren() {
		return true;
	}
}
