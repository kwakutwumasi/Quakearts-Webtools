package com.quakearts.webapp.facelets.bootstrap.renderers;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import com.quakearts.webapp.facelets.bootstrap.components.BootFormGroup;
import com.quakearts.webapp.facelets.bootstrap.renderkit.html_basic.HtmlBasicRenderer;

public class BootFormGroupRenderer extends HtmlBasicRenderer {
	@Override
	public void encodeBegin(FacesContext context, UIComponent component)
			throws IOException {
		if (context == null)
        	throw new NullPointerException();
        
        if(!(component instanceof BootFormGroup)) {
    		throw new IOException("Component must be of type "+BootFormGroup.class.getName());
        }
        
        BootFormGroup group = (BootFormGroup) component;
		String styleClass = group.get("styleClass");
        String style = group.get("style");

		ResponseWriter writer = context.getResponseWriter();
		
        writer.startElement("div", component);
        writeIdAttributeIfNecessary(context, writer, component);
        writer.writeAttribute("class", "form-group"+(styleClass!=null?" "+styleClass:""), null);
        if(style!=null)
        	writer.writeAttribute("stlye", style, null);
        writer.write("\n");
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
