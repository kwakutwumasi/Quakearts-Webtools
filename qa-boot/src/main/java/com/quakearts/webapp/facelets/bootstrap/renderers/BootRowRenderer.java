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
import java.util.Iterator;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import com.quakearts.webapp.facelets.bootstrap.components.BootRow;
import com.quakearts.webapp.facelets.bootstrap.renderkit.html_basic.HtmlBasicRenderer;

public class BootRowRenderer extends HtmlBasicRenderer {
	@Override
	public void encodeBegin(FacesContext context, UIComponent component)
			throws IOException {
		if (context == null)
        	throw new NullPointerException();
        
        if(!(component instanceof BootRow)) {
    		throw new IOException("Component must be of type "+BootRow.class.getName());
        }
		
        String styleClass = ((BootRow)component).get("styleClass");
		String style = ((BootRow)component).get("style");
		
		ResponseWriter writer = context.getResponseWriter();
		writer.startElement("div", component); 
		writer.writeAttribute("class", "row"+(styleClass != null ? " " + styleClass : ""), null);
		if(style!=null)
			writer.writeAttribute("style", style, null);
			
		writeIdAttributeIfNecessary(context, writer, component);
		writer.write("\n");
	}
	
	@Override
	public void encodeChildren(FacesContext context, UIComponent component)
			throws IOException {
        
        if (component.getChildCount() > 0) {
        	Iterator<UIComponent> kids = component.getChildren().iterator();
        	while (kids.hasNext()) {
        	    UIComponent kid = kids.next();        	    
           	    kid.encodeAll(context);
        	}
        }
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
