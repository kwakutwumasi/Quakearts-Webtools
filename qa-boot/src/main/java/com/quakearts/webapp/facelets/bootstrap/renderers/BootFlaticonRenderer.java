package com.quakearts.webapp.facelets.bootstrap.renderers;

import java.io.IOException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import com.quakearts.webapp.facelets.bootstrap.components.BootFlaticon;
import com.quakearts.webapp.facelets.bootstrap.renderkit.html_basic.HtmlBasicRenderer;

public class BootFlaticonRenderer extends HtmlBasicRenderer {
	@Override
	protected void getEndTextToRender(FacesContext context,
			UIComponent component, String currentValue) throws IOException {
		if (context == null)
        	throw new NullPointerException();
        
        if(!(component instanceof BootFlaticon))
			throw new IOException("Component must be of type "+BootFlaticon.class.getName());
		
		BootFlaticon BootFlaticon = (BootFlaticon)component;

		String label = BootFlaticon.get("label");
		String style = BootFlaticon.get("style");
		String title = BootFlaticon.get("title");
		String styleClass = BootFlaticon.get("styleClass");
		
		ResponseWriter writer = context.getResponseWriter();
		String type = (String) BootFlaticon.getValue();
		if(type==null)
			throw new IOException("Attribute 'value' is required");
		
		if(!BootFlaticon.isValid(type))
			throw new IOException("Attribute 'value' is not valid: "+type);
		
		writer.startElement("span", component);
		writeIdAttributeIfNecessary(context, writer, component);
		writer.writeAttribute("class", "flaticon-"+type+(styleClass!=null?" "+styleClass:""), null);
		if(label!=null){
			writer.writeAttribute("aria-label",label, null);
		}
		
		if(style!=null){
			writer.writeAttribute("style",style, null);
		}
		
		if(title!=null){
			writer.writeAttribute("title", title, null);
		}
		
		writer.writeAttribute("aria-hidden", "true", null);
		writer.endElement("span");
		writer.write("\n");
	}
	
	@Override
	public boolean getRendersChildren() {
		return true;
	}
	
	@Override
	public void encodeChildren(FacesContext context, UIComponent component)
			throws IOException {
		//Do nothing. no child element
	}
}
