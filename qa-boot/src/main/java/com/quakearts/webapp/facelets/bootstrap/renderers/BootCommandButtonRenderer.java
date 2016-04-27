package com.quakearts.webapp.facelets.bootstrap.renderers;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import javax.faces.application.FacesMessage;
import javax.faces.application.ProjectStage;
import javax.faces.application.Resource;
import javax.faces.application.ResourceHandler;
import javax.faces.component.UICommand;
import javax.faces.component.UIComponent;
import javax.faces.component.behavior.ClientBehavior;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.event.ActionEvent;

import com.quakearts.webapp.facelets.bootstrap.components.BootCommandButton;
import com.quakearts.webapp.facelets.bootstrap.renderkit.Attribute;
import com.quakearts.webapp.facelets.bootstrap.renderkit.AttributeManager;
import com.quakearts.webapp.facelets.bootstrap.renderkit.html_basic.HtmlBasicRenderer;
import static com.quakearts.webapp.facelets.bootstrap.renderkit.RenderKitUtils.*;

public class BootCommandButtonRenderer extends HtmlBasicRenderer {

    private static final Attribute[] ATTRIBUTES =
            AttributeManager.getAttributes(AttributeManager.Key.COMMANDBUTTON);

    @Override
    public void decode(FacesContext context, UIComponent component) {

        if (!shouldDecode(component)) {
            return;
        }

        String clientId = decodeBehaviors(context, component);

        if (wasClicked(context, component, clientId) && !isReset(component)) {
            component.queueEvent(new ActionEvent(component));

            if (LOGGER.isLoggable(Level.FINE)) {
                LOGGER.fine("This command resulted in form submission " +
                            " ActionEvent queued.");
                LOGGER.log(Level.FINE,
                           "End decoding component {0}",
                           component.getId());
            }
        }
    }
    
	@Override
    public void encodeBegin(FacesContext context, UIComponent component)
          throws IOException {

        if(!(component instanceof BootCommandButton)){
        	throw new IOException("Component must be of type "+BootCommandButton.class.getName());
        }
        
        if (!shouldEncode(component)) {
            return;
        }
        
        BootCommandButton command = ((BootCommandButton)component);
        
        String type = command.get("type");

        ResponseWriter writer = context.getResponseWriter();
        assert(writer != null);

        String label = "";
        Object value = ((UICommand) component).getValue();
        if (value != null) {
            label = value.toString();
        }

        String clientId = component.getClientId(context);
        writer.startElement("button", component);
        writeIdAttributeIfNecessary(context, writer, component);
        writer.writeAttribute("type", type, "type");
        writer.writeAttribute("name", clientId, "clientId");
		renderPassThruAttributes(context, writer, component, ATTRIBUTES,
				getNonOnClickBehaviors(component));

		renderXHTMLStyleBooleanAttributes(writer, component);
		
		String displayType = command.get("displayType");
		String styleClass = command.get("styleClass");
		writer.writeAttribute("class", "btn btn-"+displayType+(styleClass!=null?" "+styleClass:""), "styleClass");
		String style = command.get("style");
		if(style!=null)
			writer.writeAttribute("style", style, null);

		
		renderOnclick(context, component, getBehaviorParameters(component));
        writer.write("\n");
        
        String imageSrc = command.get("image");
        String name = command.get("name");
        if (imageSrc != null || name !=null) {
            writer.startElement("img", component);
            writer.writeURIAttribute("src", getImageSource(context, command, imageSrc,name), "image");
            writer.writeAttribute("border", "0", null);
            writer.endElement("img");
        } else {
            writer.write(label);
        }
        writer.write("\n");
    }
	
	private String getImageSource(FacesContext context, BootCommandButton component, String imageSrc, String resName) {
	
	    if (resName != null) {
	        String libName = component.get("library");
	        ResourceHandler handler = context.getApplication().getResourceHandler();
	        Resource res = handler.createResource(resName, libName);
	        if (res == null) {
	            if (context.isProjectStage(ProjectStage.Development)) {
	                String msg = "Unable to find resource " + resName;
					context.addMessage(component.getClientId(context),
							new FacesMessage(FacesMessage.SEVERITY_ERROR, msg,
									msg));
	            }
	            return "RES_NOT_FOUND";
	        } else {
	            return res.getRequestPath();
	        }
	    } else {
	        if (imageSrc.length() == 0) {
	            return "";
	        }
	        if (imageSrc.contains(ResourceHandler.RESOURCE_IDENTIFIER)) {
	            return imageSrc;
	        } else {
	            imageSrc = context.getApplication().getViewHandler().
	                  getResourceURL(context, imageSrc);
	            return (context.getExternalContext().encodeResourceURL(imageSrc));
	        }
	    }
	}
	
    @Override
    public void encodeEnd(FacesContext context, UIComponent component)
          throws IOException {
        ResponseWriter writer = context.getResponseWriter();
        writer.endElement("button");
        writer.write("\n");    	
    }
            
    private static Map<String, List<ClientBehavior>> getNonOnClickBehaviors(UIComponent component) {
        return getPassThruBehaviors(component, "click", "action");
    }

	private static boolean wasClicked(FacesContext context,
			UIComponent component, String clientId) {

		if (clientId == null) {
			clientId = component.getClientId(context);
		}

		Map<String, String> requestParameterMap = context.getExternalContext()
				.getRequestParameterMap();
		if (requestParameterMap.get(clientId) == null) {
		
		    String source = requestParameterMap.get("javax.faces.source");
		    if (!clientId.equals(source)) {
		        return false;
		    }
		
		    String behaviorEvent = requestParameterMap.get("javax.faces.behavior.event");
		    if (null != behaviorEvent) {
		        return ("action".equals(behaviorEvent));
		    }
		
		    String partialEvent = requestParameterMap.get("javax.faces.partial.event");
		
		    if("click".equals(partialEvent))
		    	return true;

			StringBuilder builder = new StringBuilder(clientId);
			String xValue = builder.append(".x").toString();
			builder.setLength(clientId.length());
			String yValue = builder.append(".y").toString();
			return (requestParameterMap.get(xValue) != null && requestParameterMap
					.get(yValue) != null);
		}
		return true;
	}

	private static boolean isReset(UIComponent component) {
		return ("reset".equals(component.getAttributes().get("type")));
	}
}
