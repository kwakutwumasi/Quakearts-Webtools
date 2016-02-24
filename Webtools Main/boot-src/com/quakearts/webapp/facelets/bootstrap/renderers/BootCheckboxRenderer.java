package com.quakearts.webapp.facelets.bootstrap.renderers;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.convert.ConverterException;

import com.quakearts.webapp.facelets.bootstrap.components.BootCheckbox;
import com.quakearts.webapp.facelets.bootstrap.renderkit.Attribute;
import com.quakearts.webapp.facelets.bootstrap.renderkit.AttributeManager;
import com.quakearts.webapp.facelets.bootstrap.renderkit.html_basic.HtmlBasicInputRenderer;

import static com.quakearts.webapp.facelets.bootstrap.renderkit.RenderKitUtils.*;

public class BootCheckboxRenderer extends HtmlBasicInputRenderer {

    private static final Attribute[] ATTRIBUTES =
          AttributeManager.getAttributes(AttributeManager.Key.SELECTBOOLEANCHECKBOX);

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

    // ------------------------------------------------------- Protected Methods


    @Override
    protected void getEndTextToRender(FacesContext context,
                                      UIComponent component,
                                      String currentValue) throws IOException {

    	if (context == null)
        	throw new NullPointerException();
        
        if(!(component instanceof BootCheckbox))
    		throw new IOException("Component must be of type "+BootCheckbox.class.getName());
    	
    	String label = ((BootCheckbox)component).getLabel();
    	
        ResponseWriter writer = context.getResponseWriter();
        assert(writer != null);
        String styleClass;
        String id = component.getClientId(context);
        writer.startElement("div", component);
        writeIdAttributeIfNecessary(context, writer, component);
        writer.writeAttribute("class", "input-group", null);
        writer.write("\n");    	
        writer.startElement("span", component);
        writer.writeAttribute("class", "input-group-addon", null);
        writer.write("\n");    	
        writer.startElement("input", component);
        writer.writeAttribute("type", "checkbox", "type");
        writer.writeAttribute("id", id+"_sub_input", null);      
        writer.writeAttribute("name", id, "clientId");

        if (Boolean.valueOf(currentValue)) { 
            writer.writeAttribute("checked", Boolean.TRUE, "value");
        }
        
        if (null != (styleClass = (String)
        	component.getAttributes().get("styleClass"))) {
        	writer.writeAttribute("class", styleClass, "styleClass");
        }
        
		renderPassThruAttributes(context, writer, component, ATTRIBUTES,
				getNonOnClickSelectBehaviors(component));
        renderXHTMLStyleBooleanAttributes(writer, component);
        renderSelectOnclick(context, component, false);

        writer.endElement("input");
        writer.write("\n");    	
        writer.endElement("span");
        writer.write("\n");    	
        writer.startElement("span", component);
        writer.writeAttribute("class", "form-control", null);
        writer.writeText(label !=null? label:"", null);
        writer.endElement("span");
        writer.write("\n");    	
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
