package com.quakearts.webapp.facelets.bootstrap.renderers;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import com.quakearts.webapp.facelets.bootstrap.components.BootContainer;
import com.quakearts.webapp.facelets.bootstrap.renderkit.html_basic.HtmlBasicRenderer;

public class BootContainerRenderer extends HtmlBasicRenderer {
	@Override
	public void encodeBegin(FacesContext context, UIComponent component)
			throws IOException {
		if (context == null)
        	throw new NullPointerException();
        
        if(!(component instanceof BootContainer)) {
            throw new IOException("Component must be of type "+BootContainer.class.getName());
        }
        
        BootContainer container = (BootContainer) component;
        
		super.encodeBegin(context, component);
		
		boolean fluid= Boolean.parseBoolean(container.get("fluid"));
		String styleClass = container.get("styleClass");

		ResponseWriter writer = context.getResponseWriter();
		writer.write("<div class=\""+(fluid?"container-fluid":"container")
				+(styleClass != null ? " " + styleClass : "")+"\""+ (shouldWriteIdAttribute(component) ? " id=\""
						+ component.getClientId(context) + "\"" : "")
						+">\n");
		String style = container.get("style");
		if(style!=null)
			writer.writeAttribute("style", style, null);
	}
	
	@Override
	public void encodeEnd(FacesContext context, UIComponent component)
			throws IOException {
        if (context == null || component == null) {
            throw new NullPointerException();
        }
		super.encodeBegin(context, component);
		context.getResponseWriter().write("</div>\n");
	}
	
	@Override
	public boolean getRendersChildren() {
		return true;
	}
}
