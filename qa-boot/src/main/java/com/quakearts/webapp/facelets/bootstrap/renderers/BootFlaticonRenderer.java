/*******************************************************************************
 * Copyright (C) 2017 Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com>.
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
		
		BootFlaticon bootFlaticon = (BootFlaticon)component;

		String label = bootFlaticon.get("label");
		String style = bootFlaticon.get("style");
		String title = bootFlaticon.get("title");
		String styleClass = bootFlaticon.get("styleClass");
		
		ResponseWriter writer = context.getResponseWriter();
		String type = (String) bootFlaticon.getValue();
		if(type==null)
			throw new IOException("Attribute 'value' is required");
		
		if(!bootFlaticon.isValid(type))
			throw new IOException("Attribute 'value' is not valid: "+type);
		
		String typeString = bootFlaticon.getTypeName(type);
		
		writer.startElement("span", component);
		writeIdAttributeIfNecessary(context, writer, component);
		writer.writeAttribute("class", "flaticon-"+typeString+(styleClass!=null?" "+styleClass:""), null);
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
