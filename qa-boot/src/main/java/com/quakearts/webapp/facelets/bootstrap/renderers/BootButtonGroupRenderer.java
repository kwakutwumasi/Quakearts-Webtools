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

import com.quakearts.webapp.facelets.bootstrap.components.BootButtonGroup;
import com.quakearts.webapp.facelets.bootstrap.renderkit.html_basic.HtmlBasicRenderer;

public class BootButtonGroupRenderer extends HtmlBasicRenderer {
	@Override
	public void encodeBegin(FacesContext context, UIComponent component)
			throws IOException {
		if (context == null)
        	throw new NullPointerException();
        
        if(!(component instanceof BootButtonGroup)) {
            throw new IOException("Component must be of type "+BootButtonGroup.class.getName());
        }
        
        BootButtonGroup group = (BootButtonGroup) component;
        
        String styleClass= group.get("styleClass");
        String label = group.get("label");        
		String style =group.get("style");

        ResponseWriter writer = context.getResponseWriter();
        
        writer.startElement("div", component);
        writeIdAttributeIfNecessary(context, writer, component);
        writer.writeAttribute("class", "btn-group"+(styleClass!=null?" "+styleClass:""), null);
        writer.writeAttribute("role", "group", null);
        writer.writeAttribute("aria-label", (label!=null?label:"button group"), null);
        if(style!=null)
        	writer.writeAttribute("style", style, null);        
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
