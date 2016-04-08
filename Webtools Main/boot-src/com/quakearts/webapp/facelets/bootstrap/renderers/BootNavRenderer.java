package com.quakearts.webapp.facelets.bootstrap.renderers;

import java.io.IOException;
import java.util.Iterator;

import javax.faces.component.UICommand;
import javax.faces.component.UIComponent;
import javax.faces.component.UIOutput;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import com.quakearts.webapp.facelets.bootstrap.components.BootMenu;
import com.quakearts.webapp.facelets.bootstrap.components.BootNav;
import com.quakearts.webapp.facelets.bootstrap.components.BootNavBar;
import com.quakearts.webapp.facelets.bootstrap.renderkit.Attribute;
import com.quakearts.webapp.facelets.bootstrap.renderkit.AttributeManager;
import com.quakearts.webapp.facelets.bootstrap.renderkit.AttributeManager.Key;
import com.quakearts.webapp.facelets.bootstrap.renderkit.html_basic.HtmlBasicRenderer;

import static com.quakearts.webapp.facelets.bootstrap.renderkit.RenderKitUtils.*;

public class BootNavRenderer extends HtmlBasicRenderer {

	private static final Attribute[] ATTRIBUTES = AttributeManager
			.getAttributes(Key.BOOTNAV);
	
	@Override
	public void encodeBegin(FacesContext context, UIComponent component)
			throws IOException {
        if (context == null)
        	throw new NullPointerException();
        	
        if(!(component instanceof BootNav)) {
    		throw new IOException("Component must be of type "+BootNav.class.getName());
        }
        
        BootNav nav = (BootNav)component;
        
		String type = nav.get("type");

		if(type==null){
        	UIComponent parent = nav.getParent();
        	do{
        		if(parent instanceof BootNavBar){
        			type="bar";
        			break;
        		}
        	} while((parent=parent.getParent())!=null);
        	if(type==null)
        		type="none";
        }

		boolean stacked = Boolean.parseBoolean(nav.get("stacked"));
		boolean justified = Boolean.parseBoolean(nav.get("justified"));
		String styleClass = nav.get("styleClass");
		
		ResponseWriter writer = context.getResponseWriter();		
		writer.write("\n");
		writer.startElement("ul", component);
		writer.writeAttribute("class", "nav"
				+ ("none".equals(type) ? ""
						:("bar".equals(type)?" navbar-nav"
								:("pills".equals(type) ? " nav-pills" : " nav-tabs")
								+ (stacked ? " nav-stacked" : "")+(justified ? " nav-justified" : "")))
				+(styleClass!=null?" "+styleClass:""), null);
		
		String style = nav.get("style");
		if(style!=null){
			writer.writeAttribute("style", style, null);
		}
		
		writeIdAttributeIfNecessary(context, writer, component);
		renderPassThruAttributes(context, writer, component, ATTRIBUTES);
	}
	
	@Override
	public void encodeChildren(FacesContext context, UIComponent component)
			throws IOException {

		BootNav nav = (BootNav)component;
		ResponseWriter writer = context.getResponseWriter();		
        if (component.getChildCount() > 0) {
        	Iterator<UIComponent> kids = component.getChildren().iterator();
        	while (kids.hasNext()) {
        	    UIComponent kid = kids.next();
        	    
        	    if((kid instanceof UIOutput || kid instanceof UICommand) && kid.isRendered()){
        	    	writer.write("\n");
        	    	writer.startElement("li", component);
        	    	StringBuilder classString = new StringBuilder();
        	    	if(kid instanceof BootMenu)
        	    		classString.append("dropdown");
        	    	
        	    	if(kid instanceof UIOutput){
	        	    	if(nav.getValue()!=null && nav.getValue().equals(((UIOutput)kid).getValue()))
	        	    		classString.append(classString.length()>0?" ":"").append("active");
        	    	} else if(kid instanceof UICommand){
	        	    	if(nav.getValue()!=null && nav.getValue().equals(((UICommand)kid).getValue()))
	        	    		classString.append(classString.length()>0?" ":"").append("active");
        	    	}
        	    	
        	    	if(classString.length()>0)
        	    		writer.writeAttribute("class", classString.toString(), null);   	    	
        	    	
        	    	writer.writeAttribute("role", "presentation", null);
        	    	
        	    	writer.write("\n");
            	    kid.encodeAll(context);
            	    UIComponent iconFacet = kid.getFacet("nav-icon");
            	    if(iconFacet !=null){
            	    	writer.startElement("span", component);
            	    	writer.writeAttribute("class", "navitem-icon", null);
            	    	iconFacet.encodeAll(context);
            	    	writer.endElement("span");
            	    }
            	    
            	    writer.endElement("li");
        	    	writer.write("\n");
        	    } else {
        	    	kid.encodeAll(context);
        	    }
        	}
        }
	}	
	
	@Override
	public void encodeEnd(FacesContext context, UIComponent component)
			throws IOException {
        if (context == null || component == null) {
            throw new NullPointerException();
        }
		ResponseWriter writer =context.getResponseWriter();
		writer.endElement("ul");
    	writer.write("\n");
	}
	
	@Override
	public boolean getRendersChildren() {
		return true;
	}
}
