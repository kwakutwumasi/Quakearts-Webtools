package com.quakearts.webapp.facelets.bootstrap.renderers;

import java.io.IOException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import com.quakearts.webapp.facelets.bootstrap.components.BootGlyph;
import com.quakearts.webapp.facelets.bootstrap.renderkit.html_basic.HtmlBasicRenderer;

public class BootGlyphRenderer extends HtmlBasicRenderer {
	@Override
	protected void getEndTextToRender(FacesContext context,
			UIComponent component, String currentValue) throws IOException {
		if (context == null)
        	throw new NullPointerException();
        
        if(!(component instanceof BootGlyph))
			throw new IOException("Component must be of type "+BootGlyph.class.getName());
		
		BootGlyph bootGlyph = (BootGlyph)component;

		String label = bootGlyph.get("label");
		String style = bootGlyph.get("style");
		String title = bootGlyph.get("title");
		
		ResponseWriter writer = context.getResponseWriter();
		String type = (String) bootGlyph.getValue();
		if(type==null)
			throw new IOException("Attribute 'value' is required");
		
		if(!bootGlyph.isValid(type))
			throw new IOException("Attribute 'value' is not valid: "+type);
		
		writer.startElement("span", component);
		writeIdAttributeIfNecessary(context, writer, component);
		writer.writeAttribute("class", "glyphicon glyphicon-"+type, null);
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
