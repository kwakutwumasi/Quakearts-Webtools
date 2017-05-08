/*******************************************************************************
* Copyright (C) 2016 Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com>.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com> - initial API and implementation
 ******************************************************************************/
package com.quakearts.webapp.facelets.bootstrap.renderers;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import com.quakearts.webapp.facelets.bootstrap.components.BootOutputPanel;
import com.quakearts.webapp.facelets.bootstrap.renderkit.Attribute;
import com.quakearts.webapp.facelets.bootstrap.renderkit.AttributeManager;
import com.quakearts.webapp.facelets.bootstrap.renderkit.AttributeManager.Key;
import com.quakearts.webapp.facelets.bootstrap.renderkit.html_basic.HtmlBasicRenderer;
import static com.quakearts.webapp.facelets.bootstrap.renderkit.RenderKitUtils.*;

public class BootOutputPanelRenderer extends HtmlBasicRenderer {

	private static final Attribute[] ATTRIBUTES = AttributeManager.getAttributes(Key.BOOTPANEL);
	
	@Override
	public void encodeBegin(FacesContext context, UIComponent component)
			throws IOException {
		if (context == null)
        	throw new NullPointerException();
        
        if(!(component instanceof BootOutputPanel)) {
    		throw new IOException("Component must be of type "+BootOutputPanel.class.getName());
        }
        
        BootOutputPanel panel = (BootOutputPanel)component;
 
		String type = panel.get("type");
		String styleClass = panel.get("styleClass");
		boolean isPopup = Boolean.parseBoolean(panel.get("popup"));
		
		if(isPopup){
			Object onclick = panel.getAttributes().get("onclick");			
			onclick = (onclick==null?"":onclick.toString()+"; ")+"qab.pclk(event)";
			panel.getAttributes().put("onclick", onclick);
			
			Object onmousemove = panel.getAttributes().get("onmousemove");
			onmousemove = (onmousemove==null?"":onmousemove+"; ")+"qab.pmv(event)";
			panel.getAttributes().put("onmousemove", onmousemove);
		}
		
		ResponseWriter writer = context.getResponseWriter();
		writer.startElement("div", component);
		writer.writeAttribute("class", "panel panel-" + type
				+ (styleClass != null ? " "+styleClass : "")+(isPopup?" popup":""), null);
		
		String style = panel.get("style");
		if(style!=null){
			writer.writeAttribute("style", style, null);
		}
		
		writeIdAttributeIfNecessary(context, writer, component);
		renderPassThruAttributes(context, writer, component, ATTRIBUTES);
		writer.write("\n");
		
		UIComponent facet = panel.getFacet("header");
		if((facet!=null && facet.isRendered()) || isPopup){
			writer.startElement("div", component);
			writer.writeAttribute("class", "panel-heading", null);
			if(isPopup)
				writer.writeAttribute("onclick", "qab.hclk(event)", null);
			
			if(facet!=null)
				facet.encodeAll(context);
			else{
				writer.write("&nbsp");
			}
			if(isPopup){
				writer.write("<span class=\"glyphicon glyphicon-remove close-btn\" onclick=\"qab.cls(event);\"></span>");
			}
		   	writer.write("\n");
			writer.endElement("div");
 		}
		
    	writer.write("\n");
		writer.startElement("div", component);
		writer.writeAttribute("class", "panel-body", null);

	}
		
	@Override
	public void encodeEnd(FacesContext context, UIComponent component)
			throws IOException {
		ResponseWriter writer =context.getResponseWriter();
    	writer.write("\n");
		writer.endElement("div");
		
        BootOutputPanel panel = (BootOutputPanel)component;
		
		UIComponent facet = panel.getFacet("footer");
		if(facet!=null && facet.isRendered()){
	    	writer.write("\n");
			writer.startElement("div", component);
			writer.writeAttribute("class", "panel-footer", null);
			facet.encodeAll(context);
			writer.endElement("div");
	    	writer.write("\n");
		}
		writer.endElement("div");
    	writer.write("\n");
	}
	
	@Override
	public boolean getRendersChildren() {
		return true;
	}
}
