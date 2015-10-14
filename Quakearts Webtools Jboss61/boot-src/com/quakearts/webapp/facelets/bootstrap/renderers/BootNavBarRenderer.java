package com.quakearts.webapp.facelets.bootstrap.renderers;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import com.quakearts.webapp.facelets.bootstrap.components.BootNavBar;
import com.quakearts.webapp.facelets.bootstrap.renderkit.Attribute;
import com.quakearts.webapp.facelets.bootstrap.renderkit.AttributeManager;
import com.quakearts.webapp.facelets.bootstrap.renderkit.html_basic.HtmlBasicRenderer;
import static com.quakearts.webapp.facelets.bootstrap.renderkit.RenderKitUtils.*;

public class BootNavBarRenderer extends HtmlBasicRenderer {

	private static final Attribute[] ATTRIBUTES = AttributeManager
			.getAttributes(AttributeManager.Key.BOOTNAV);
	
	@Override
	public void encodeBegin(FacesContext context, UIComponent component)
			throws IOException {
		if (context == null)
        	throw new NullPointerException();
        
        if(!(component instanceof BootNavBar)) {
    		throw new IOException("Component must be of type "+BootNavBar.class.getName());
        }
        
        BootNavBar navbar = (BootNavBar)component;
		String type = navbar.get("type");
		String position = navbar.get("position");
		boolean fluid = Boolean.parseBoolean(navbar.get("fluid"));
		boolean responsive = Boolean.parseBoolean(navbar.get("responsive"));
		
        String id = navbar.getClientId(context);
        String idJs = id.replace(":", "_");
        
		ResponseWriter writer = context.getResponseWriter();
		writer.startElement("div", component);
		writer.writeAttribute("class", "navbar navbar-"+type+(position!=null?" navbar-"+position:""), null);
		writer.writeAttribute("role", "navigation", null);
		writer.writeAttribute("id", id, null);
		renderPassThruAttributes(context, writer, component, ATTRIBUTES);
		
		writer.write("\n");
		writer.startElement("div", component);
		writer.writeAttribute("class", "container"+(fluid?"-fluid":""), null);
		writer.write("\n");
		writer.startElement("div", component);
		writer.writeAttribute("class", "navbar-header", null);
		if(responsive){
    		writer.write("\n");
			writer.startElement("button", component);
			writer.writeAttribute("type", "button", null);
			writer.writeAttribute("class", "navbar-toggle collapsed", null);
			writer.writeAttribute("data-toggle", "collapse", null);
			writer.writeAttribute("data-target", "#div_"+idJs, null);
    		writer.write("\n");
			writer.write("<span class=\"icon-bar\"></span><span class=\"icon-bar\"></span><span class=\"icon-bar\"></span>");
    		writer.write("\n");
			writer.endElement("button");			
    		writer.write("\n");
		}
		
		UIComponent facet = navbar.getFacet("header");
		if(facet!=null && facet.isRendered()){
			facet.encodeAll(context);
		}
		
		writer.endElement("div");
		writer.write("\n");
		writer.startElement("div", component);
		if(responsive){
			writer.writeAttribute("class", "navbar-collapse collapse", null);
			writer.writeAttribute("id", "div_"+idJs, null);
		}
		writer.write("\n");
		
	}
		
	@Override
	public void encodeEnd(FacesContext context, UIComponent component)
			throws IOException {
		ResponseWriter writer =context.getResponseWriter();
		writer.endElement("div");
		writer.write("\n");
		writer.endElement("div");
		writer.write("\n");
		writer.endElement("div");
		writer.write("\n");
	}
	
	@Override
	public boolean getRendersChildren() {
		return true;
	}
}
