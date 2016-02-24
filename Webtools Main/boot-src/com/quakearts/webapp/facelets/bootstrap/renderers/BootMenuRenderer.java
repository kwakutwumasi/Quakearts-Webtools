package com.quakearts.webapp.facelets.bootstrap.renderers;

import java.io.IOException;
import java.util.Iterator;

import javax.faces.component.UIComponent;
import javax.faces.component.UIOutput;
import javax.faces.component.html.HtmlCommandLink;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import com.quakearts.webapp.facelets.bootstrap.components.BootButtonGroup;
import com.quakearts.webapp.facelets.bootstrap.components.BootMenu;
import com.quakearts.webapp.facelets.bootstrap.components.BootNav;
import com.quakearts.webapp.facelets.bootstrap.renderkit.html_basic.HtmlBasicRenderer;

public class BootMenuRenderer extends HtmlBasicRenderer {

	private static final String COM_QUAKEARTS_BOOTSTRAP_DROPDOWNTYPE = "com.quakearts.bootstrap.DROPDOWNTYPE";
	private static final String COM_QUAKEARTS_BOOTSTRAP_NOGROUP = "com.quakearts.bootstrap.NOGROUP";

	@Override
	public void encodeBegin(FacesContext context, UIComponent component)
			throws IOException {
		if (context == null)
        	throw new NullPointerException();
        
        if(!(component instanceof BootMenu)) {
            throw new IOException("Component must be of type "+BootMenu.class.getName());
        }
        BootMenu menu = (BootMenu)component;
        String style =menu.get("style");
        String type =menu.get("type");        
		if(type==null){
        	UIComponent parent = menu.getParent();
        	do{
        		if(parent instanceof BootNav){
        			type="navbar";
        			break;
        		}
        	} while((parent=parent.getParent())!=null);
        	if(type==null)
        		type="default";
        } else {
	        if(!menu.isValid(type))
	        	throw new IOException("Invalid type parameter "+type);
        }
				
		menu.getAttributes().put(COM_QUAKEARTS_BOOTSTRAP_DROPDOWNTYPE, type);
		Object value = menu.getValue();
        
		ResponseWriter writer = context.getResponseWriter();
		if(type.equals("default")){			
			String nogroupAttr = menu.get("no-group");
			boolean nogroup = nogroupAttr!=null && Boolean.parseBoolean(nogroupAttr);
			if(!nogroup && nogroupAttr==null){
	        	UIComponent parent = menu.getParent();
	        	do{
	        		if(parent instanceof BootButtonGroup){
	        			nogroup=true;
	        			break;
	        		}
	        	} while((parent=parent.getParent())!=null);
			}
			if(nogroup)
				menu.getAttributes().put(COM_QUAKEARTS_BOOTSTRAP_NOGROUP, "nogroup");
			else{
				writer.startElement("div", component);
				String dropup = Boolean.parseBoolean(menu.get("dropup"))?" dropup":"";
				
				writer.writeAttribute("class", "btn-group"+dropup, null);
		        if(style!=null)
		        	writer.writeAttribute("stlye", style, null);
		        writer.write("\n");
			}
			
			writer.startElement("button", component);
			writer.writeAttribute("class", "btn btn-"+
					menu.get("displayType")
					+" dropdown-toggle", null);
			writer.writeAttribute("data-toggle", "dropdown", null);
			writer.writeAttribute("aria-expanded", "false", null);
			if(value!=null)
				writer.write(value.toString());
			else {
				UIComponent header = menu.getFacet("header");
				if(header!=null)
					header.encodeAll(context);
			}
	        writer.write("\n");
			writer.startElement("span", component);
			writer.writeAttribute("class", "caret", null);
			writer.write(" ");
			writer.endElement("span");
	        writer.write("\n");    	
			writer.endElement("button");
	        writer.write("\n");    
		} else {
			writer.startElement("a", component);
			writer.writeAttribute("class", "dropdown-toggle", null);
			writer.writeAttribute("data-toggle", "dropdown", null);
			writer.writeAttribute("aria-expanded", "false", null);
			writer.writeAttribute("role", "button", null);
			writer.writeAttribute("href", "#", null);
			writer.write(value!=null?value.toString():"");
			writer.startElement("span", component);
			writer.writeAttribute("class", "caret", null);
			writer.write(" ");
			writer.endElement("span");
			writer.endElement("a");
	        writer.write("\n");    	
		}
		writer.startElement("ul", component);
		writer.writeAttribute("class", "dropdown-menu", null);
		writer.writeAttribute("role", "menu", null);
	}
	
	@Override
	public void encodeChildren(FacesContext context, UIComponent component)
			throws IOException {
        if (component.getChildCount() > 0) {
        	Iterator<UIComponent> kids = component.getChildren().iterator();
        	while (kids.hasNext()) {
        	    UIComponent kid = kids.next();
        	    if(kid.isRendered()){
	        	    if(kid instanceof UIOutput ||  kid instanceof HtmlCommandLink){
	        	    	context.getResponseWriter().write("\n");    	
	        	        context.getResponseWriter().startElement("li", component);
	            	    kid.encodeAll(context);
	            		context.getResponseWriter().endElement("li");
	        	    	context.getResponseWriter().write("\n");    	
	        	    } else {
	        	    	kid.encodeAll(context);
	        	    }
        	    }
        	}
        }
	}	
	
	@Override
	public void encodeEnd(FacesContext context, UIComponent component)
			throws IOException {
		ResponseWriter writer =context.getResponseWriter();
		writer.endElement("ul");
		writer.write("\n");    	

		String type = (String) component.getAttributes().get(COM_QUAKEARTS_BOOTSTRAP_DROPDOWNTYPE);
		if(type.equals("default") && component.getAttributes().get(COM_QUAKEARTS_BOOTSTRAP_NOGROUP)==null){
			writer.endElement("div");
			writer.write("\n");    	
		}
	}
	
	@Override
	public boolean getRendersChildren() {
		return true;
	}	
}
