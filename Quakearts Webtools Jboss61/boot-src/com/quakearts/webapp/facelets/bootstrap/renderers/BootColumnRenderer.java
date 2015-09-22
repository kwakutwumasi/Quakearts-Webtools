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
        
        boolean offset = Boolean.parseBoolean(column.get("offset"));
        
        StringBuilder classString = new StringBuilder();
        boolean space =false;
        if(column.xsSize()>0){
        	classString.append("col-xs-"+(offset?"offset-":"")+column.xsSize());
        	space=true;
        }

        if(column.smSize()>0){
        	if(!space) space=true;
        	else classString.append(" ");
        	
        	classString.append("col-sm-"+(offset?"offset-":"")+column.smSize());
        }

        if(column.mdSize()>0){
        	if(!space) space=true;
        	else classString.append(" ");
        	
        	classString.append("col-md-"+(offset?"offset-":"")+column.mdSize());
        }

        if(column.lgSize()>0){
        	if(!space) space=true;
        	else classString.append(" ");
        	
        	classString.append("col-lg-"+(offset?"offset-":"")+column.lgSize());
        }
        
        String style = ((BootColumn)component).get("style");
        String styleClass = ((BootColumn)component).get("styleClass");
        
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
