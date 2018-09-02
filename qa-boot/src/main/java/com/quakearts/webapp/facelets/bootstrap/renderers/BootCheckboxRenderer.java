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
import java.util.Map;
import java.util.logging.Level;

import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.convert.ConverterException;

import com.quakearts.webapp.facelets.bootstrap.components.BootCheckbox;
import com.quakearts.webapp.facelets.bootstrap.renderkit.Attribute;
import com.quakearts.webapp.facelets.bootstrap.renderkit.AttributeManager;
import com.quakearts.webapp.facelets.bootstrap.renderkit.html_basic.HtmlBasicInputRenderer;
import com.quakearts.webapp.facelets.util.UtilityMethods;

import static com.quakearts.webapp.facelets.bootstrap.renderkit.RenderKitUtils.*;

public class BootCheckboxRenderer extends HtmlBasicInputRenderer {

    private static final Attribute[] ATTRIBUTES =
          AttributeManager.getAttributes(AttributeManager.Key.SELECTBOOLEANCHECKBOX);
	private static final String HASINPUTCHILD = "com.quakearts.bootstrap.HASINPUTCHILD";

    @Override
    public void decode(FacesContext context, UIComponent component) {

        if (!shouldDecode(component)) {
            return;
        }

        String clientId = decodeBehaviors(context, component);

        if (clientId == null) {
            clientId = component.getClientId(context);
        }
 
        Map<String, String> requestParameterMap = context.getExternalContext()
              .getRequestParameterMap();
        boolean isChecked = isChecked(requestParameterMap.get(clientId));
        Iterator<UIComponent> children = getChildren(component);
        while (children.hasNext()) {
			UIComponent child = (UIComponent) children.next();
			if(child instanceof UIInput){
				if(isChecked){
					child.getAttributes().put("disabled", false);
				} else {
					child.getAttributes().put("disabled", true);
				}
			}
		}
        
        setSubmittedValue(component, isChecked);
        if (LOGGER.isLoggable(Level.FINE)) {
            LOGGER.log(Level.FINE,
                       "new value after decoding: {0}",
                       isChecked);
        }

    }


    @Override
    public void encodeBegin(FacesContext context, UIComponent component)
          throws IOException {
    	if (!shouldEncode(component)) {
            return;
        }

        String currentValue = getCurrentValue(context, component);
                
    	if (context == null)
        	throw new NullPointerException();
        
        if(!(component instanceof BootCheckbox))
    		throw new IOException("Component must be of type "+BootCheckbox.class.getName());
    	    	
        BootCheckbox box = ((BootCheckbox)component);
        
        ResponseWriter writer = context.getResponseWriter();
        assert(writer != null);
        String id = component.getClientId(context);
        writer.startElement("div", component);
        writeIdAttributeIfNecessary(context, writer, component);
        if(!UtilityMethods.componentIsDisabled(component)){
	        renderSelectOnclick(context, component);
        }
        renderHTML5DataAttributes(context, component);
        
        String mainClass = box.get("mainClass");
        writer.writeAttribute("class", "input-group"+(mainClass!=null?" "+mainClass:""), null);
        String mainStyle = box.get("mainStyle");
        if(mainStyle!=null){
        	writer.writeAttribute("style", mainStyle, null);
        }
        
        writer.write("\n");    	
        writer.startElement("span", component);
        writer.writeAttribute("class", "input-group-addon", null);
        writer.write("\n");    	
        writer.startElement("input", component);

        Iterator<UIComponent> children = getChildren(component);
        while (children.hasNext()) {
			UIComponent child = (UIComponent) children.next();
			if(child instanceof UIInput){
				component.getAttributes().put(HASINPUTCHILD, "true");
				String inputEnableHandler = "qab.icbe(this)";
				Object userHandler = component.getAttributes().get("onclick");
				if(userHandler!=null){
					userHandler = inputEnableHandler+"; "+userHandler;
				} else {
					userHandler = inputEnableHandler;
				}
				component.getAttributes().put("onclick", userHandler);
				writer.writeAttribute("data-input-control", child.getClientId(context), null);
				String childClass = (String) child.getAttributes().get("styleClass");
				childClass = (childClass!=null?childClass+" ":"")+"form-control";
				
				if(!isChecked(currentValue)){
					child.getAttributes().put("disabled", true);
				}
				break;
			}
		}
        
        writer.writeAttribute("type", "checkbox", "type");
        writer.writeAttribute("id", id+"_sub_input", null);      
        writer.writeAttribute("name", id, "clientId");
        if (isChecked(currentValue)) {
            writer.writeAttribute("checked", Boolean.TRUE, "value");
        }
        
        String styleClass;
        if (null != (styleClass = box.get("styleClass"))) {
        	writer.writeAttribute("class", styleClass, "styleClass");
        }
        String style;
        if (null != (style = box.get("style"))) {
        	writer.writeAttribute("style", style, null);
        }
        
		renderPassThruAttributes(context, writer, component, ATTRIBUTES,
				getNonOnClickSelectBehaviors(component));
        renderXHTMLStyleBooleanAttributes(writer, component);
        writer.writeAttribute("onchange", "$('#"+id+"').change()", null);

        writer.endElement("input");
        writer.write("\n");    	
        writer.endElement("span");
        writer.write("\n");    	
   	
    }


    @Override
    public Object getConvertedValue(FacesContext context,
                                    UIComponent component,
                                    Object submittedValue)
    throws ConverterException {

        return ((submittedValue instanceof Boolean)
                ? submittedValue
                : Boolean.valueOf(submittedValue.toString()));

    }

    @Override
    public boolean getRendersChildren() {
    	return true;
    }

    @Override
    public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
    	String label = ((BootCheckbox)component).getLabel();
        if(!(component instanceof BootCheckbox))
    		throw new IOException("Component must be of type "+BootCheckbox.class.getName());

        ResponseWriter writer = context.getResponseWriter();
        assert(writer != null);

        if(!component.getAttributes().containsKey(HASINPUTCHILD)){
	        writer.startElement("span", component);
	        writer.writeAttribute("class", "form-control", null);
	        writer.writeText(label !=null? label:"", null);
	        writer.endElement("span");
	        writer.write("\n");    	
        }
        
        writer.endElement("div");
        writer.write("\n");    	

    }
    
    /**
     * @param value the submitted value
     * @return "true" if the component was checked, otherise "false"
     */
    private static boolean isChecked(String value) {

        return "on".equalsIgnoreCase(value)
               || "yes".equalsIgnoreCase(value)
               || "true".equalsIgnoreCase(value);

    }

}
