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
import com.quakearts.webapp.facelets.bootstrap.components.BootInputText;
import com.quakearts.webapp.facelets.bootstrap.renderkit.Attribute;
import com.quakearts.webapp.facelets.bootstrap.renderkit.AttributeManager;
import com.quakearts.webapp.facelets.bootstrap.renderkit.html_basic.HtmlBasicInputRenderer;
import com.quakearts.webapp.facelets.util.UtilityMethods;

import static com.quakearts.webapp.facelets.bootstrap.renderkit.RenderKitUtils.*;

public class BootInputRenderer extends HtmlBasicInputRenderer {

    private static final Attribute[] INPUT_ATTRIBUTES =
          AttributeManager.getAttributes(AttributeManager.Key.INPUTTEXT);
    @Override
    public void encodeBegin(FacesContext context, UIComponent component)
          throws IOException {
    }

    @Override
    protected void getEndTextToRender(FacesContext context,
                                      UIComponent component,
                                      String currentValue) throws IOException {

    	if(!(component instanceof BootInputText))
    		throw new IOException("Component must be of type "+BootInputText.class.getName());

    	BootInputText text = (BootInputText) component;
    	
        ResponseWriter writer = context.getResponseWriter();
        assert(writer != null);

        String styleClass = text.get("styleClass");
        String type = text.get("type");
		String style = text.get("style");
        
        String id = component.getClientId(context);
        writer.startElement("div", component);
        writeIdAttributeIfNecessary(context, writer, component);
        if(! UtilityMethods.componentIsDisabled(component)){
        	renderOnchange(context, component);
        }
		renderHTML5DataAttributes(context, component);
        
        if(style!=null)
        	writer.writeAttribute("style", style, null);        
        writer.writeAttribute("class", "input-group", null);
		writer.write("\n");
        writer.startElement("span", component);
        writer.writeAttribute("class", "input-group-addon", null);
        writer.writeAttribute("id", "span_"+id, null);
        UIComponent labelFacet = component.getFacet("label");
        if(labelFacet!=null){
        	labelFacet.encodeAll(context);
        } else {
    		String label = text.get("label");
    		writer.write(label!=null?label:"");
        }
        
        writer.endElement("span");
		writer.write("\n");
        writer.startElement("input", component);
        writer.writeAttribute("type", type, null);
        writer.writeAttribute("name", id, "clientId");
        writer.writeAttribute("id", component.getClientId()+"_sub_input", null);
        writer.writeAttribute("aria-describedby", "span_"+id, null);
		String placeholder = text.get("placeholder");
        if( placeholder!=null){
        	writer.writeAttribute("placeholder", placeholder, null);
        }
        
        if ("off".equals(component.getAttributes().get("autocomplete"))) {
            writer.writeAttribute("autocomplete", "off", "autocomplete");
        }

        if (currentValue != null) {
            writer.writeAttribute("value", currentValue, "value");
        }
        
        writer.writeAttribute("class", "form-control"+(styleClass!=null?" "+styleClass:""), "styleClass");
        writer.writeAttribute("onchange", "$('#"+id+"').change()", null);
        
        renderPassThruAttributes(context, writer, component, INPUT_ATTRIBUTES, getNonOnChangeBehaviors(component));
        renderXHTMLStyleBooleanAttributes(writer, component);
        writer.endElement("input");
		writer.write("\n");
        writer.endElement("div");
		writer.write("\n");
    }

    @Override
    public boolean getRendersChildren() {
        return true;
    }

}
