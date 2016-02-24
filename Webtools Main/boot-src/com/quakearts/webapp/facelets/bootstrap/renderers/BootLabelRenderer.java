package com.quakearts.webapp.facelets.bootstrap.renderers;

import java.io.IOException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import com.quakearts.webapp.facelets.bootstrap.components.BootLabel;
import com.quakearts.webapp.facelets.bootstrap.renderkit.html_basic.HtmlBasicRenderer;

public class BootLabelRenderer extends HtmlBasicRenderer {
	
	@Override
	protected void getEndTextToRender(FacesContext context,
			UIComponent component, String currentValue) throws IOException {
		ResponseWriter writer = context.getResponseWriter();
		
		if(!(component instanceof BootLabel)){
			throw new IOException("Component must be of type "+BootLabel.class.getName());
		}
		
		String type =((BootLabel)component).get("type");
		String style = ((BootLabel)component).get("style");
		
		writer.startElement("span", component);
		writeIdAttributeIfNecessary(context, writer, component);
		writer.writeAttribute("class", "label label-"+type, null);
		writer.writeAttribute("style", style, null);
		writer.write(currentValue);
		writer.endElement("span");
	}
	
	public boolean getRendersChildren() {
		return true;
	}
	
	public void encodeChildren(FacesContext context, UIComponent component) throws IOException {
		//Do nothing
	};
}
