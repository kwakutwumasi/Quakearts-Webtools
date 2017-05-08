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

import com.quakearts.webapp.facelets.bootstrap.components.BootFontawesome;
import com.quakearts.webapp.facelets.bootstrap.renderkit.html_basic.HtmlBasicRenderer;

public class BootFontawesomeRenderer extends HtmlBasicRenderer {
	@Override
	protected void getEndTextToRender(FacesContext context,
			UIComponent component, String currentValue) throws IOException {
		if (context == null)
        	throw new NullPointerException();
        
        if(!(component instanceof BootFontawesome))
			throw new IOException("Component must be of type "+BootFontawesome.class.getName());
		
		BootFontawesome bootFontawesome = (BootFontawesome)component;

		String label = bootFontawesome.get("label");
		String style = bootFontawesome.get("style");
		String title = bootFontawesome.get("title");
		String styleClass = bootFontawesome.get("styleClass");
		
		ResponseWriter writer = context.getResponseWriter();
		String type = (String) bootFontawesome.getValue();
		if(type==null)
			throw new IOException("Attribute 'value' is required");
		
		if(!bootFontawesome.isValid(type))
			throw new IOException("Attribute 'value' is not valid: "+type);
		
		writer.startElement("i", component);
		writeIdAttributeIfNecessary(context, writer, component);
		writer.writeAttribute("class", "fa fa-"+type+(styleClass!=null?" "+styleClass:""), null);
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
		writer.endElement("i");
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
