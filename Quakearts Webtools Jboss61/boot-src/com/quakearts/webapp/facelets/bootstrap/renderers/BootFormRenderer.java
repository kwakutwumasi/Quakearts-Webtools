package com.quakearts.webapp.facelets.bootstrap.renderers;

import java.io.IOException;
import java.util.ListIterator;
import java.util.Map;
import java.util.logging.Level;
import javax.faces.component.UIComponent;
import javax.faces.component.UIForm;
import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import com.quakearts.webapp.facelets.bootstrap.components.BootForm;
import com.quakearts.webapp.facelets.bootstrap.components.BootNavBar;
import com.quakearts.webapp.facelets.bootstrap.renderkit.Attribute;
import com.quakearts.webapp.facelets.bootstrap.renderkit.AttributeManager;
import static com.quakearts.webapp.facelets.bootstrap.renderkit.RenderKitUtils.*;
import com.quakearts.webapp.facelets.bootstrap.renderkit.html_basic.HtmlBasicRenderer;

public class BootFormRenderer extends HtmlBasicRenderer {

	private boolean writeStateAtEnd;
	private static final Attribute[] ATTRIBUTES = AttributeManager
			.getAttributes(AttributeManager.Key.FORMFORM);

	@Override
	public void decode(FacesContext context, UIComponent component) {
		String clientId = decodeBehaviors(context, component);

		if (clientId == null) {
			clientId = component.getClientId(context);
		}

		Map<String, String> requestParameterMap = context.getExternalContext()
				.getRequestParameterMap();
		if (requestParameterMap.containsKey(clientId)) {
			if (LOGGER.isLoggable(Level.FINE)) {
				LOGGER.log(Level.FINE, "UIForm with client ID {0}, submitted",
						clientId);
			}
			((UIForm) component).setSubmitted(true);
		} else {
			((UIForm) component).setSubmitted(false);
		}

	}

	@Override
	public void encodeBegin(FacesContext context, UIComponent component)
			throws IOException {

		if (context == null)
        	throw new NullPointerException();
        
        if(!(component instanceof BootForm)) {
    		throw new IOException("Component must be of type "+BootForm.class.getName());
		}

        BootForm form = (BootForm) component;
        
        if (!shouldEncode(component)) {
			return;
		}
		
		String type=form.get("type");

		if(type==null){
			UIComponent parent = component.getParent();
			do{
				if(parent instanceof BootNavBar){
					type = "navbar";
					break;
				}
			} while((parent=parent.getParent())!=null);
			if(type==null)
				type = "horizontal";
						
		} else if(!((BootForm)component).isValid(type))
			type = "horizontal";
		
		String styleClass = form.get("styleClass");
		
		ResponseWriter writer = context.getResponseWriter();
		assert (writer != null);
		String clientId = component.getClientId(context);
		writer.write('\n');
		writer.startElement("form", component);
		writer.writeAttribute("id", clientId, "clientId");
		writer.writeAttribute("name", clientId, "name");
		writer.writeAttribute("method", "post", null);
		writer.writeAttribute("class", type.equals("inline")? "form-inline"
				: (type.equals("horizontal") ? "form-horizontal"
						: "navbar-form")
						+ (styleClass != null ? " " + styleClass : ""), null);
		writer.writeAttribute("action", getActionStr(context), null);
		
		String acceptcharset = (String) component.getAttributes().get(
				"acceptcharset");
		if (acceptcharset != null) {
			writer.writeAttribute("accept-charset", acceptcharset,
					"acceptcharset");
		}

		renderPassThruAttributes(context, writer, component, ATTRIBUTES);
		writer.writeText("\n", component, null);

		writer.startElement("input", component);
		writer.writeAttribute("type", "hidden", "type");
		writer.writeAttribute("name", clientId, "clientId");
		writer.writeAttribute("value", clientId, "value");
		writer.endElement("input");
		writer.write('\n');

		String viewId = context.getViewRoot().getViewId();
		String actionURL = context.getApplication().getViewHandler()
				.getActionURL(context, viewId);
		ExternalContext externalContext = context.getExternalContext();
		String encodedActionURL = externalContext.encodeActionURL(actionURL);
		String encodedPartialActionURL = externalContext
				.encodePartialActionURL(actionURL);
		if (encodedPartialActionURL != null) {
			if (!encodedPartialActionURL.equals(encodedActionURL)) {
				writer.startElement("input", component);
				writer.writeAttribute("type", "hidden", "type");
				writer.writeAttribute("name", "javax.faces.encodedURL", null);
				writer.writeAttribute("value", encodedPartialActionURL, "value");
				writer.endElement("input");
				writer.write('\n');
			}
		}

		if (!writeStateAtEnd) {
			context.getApplication().getViewHandler().writeState(context);
			writer.write('\n');
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void encodeEnd(FacesContext context, UIComponent component)
			throws IOException {
		if (!shouldEncode(component)) {
			return;
		}

		ResponseWriter writer = context.getResponseWriter();
		assert (writer != null);

		UIViewRoot viewRoot = context.getViewRoot();
		ListIterator iter = (viewRoot.getComponentResources(context, "form"))
				.listIterator();
		while (iter.hasNext()) {
			UIComponent resource = (UIComponent) iter.next();
			resource.encodeAll(context);
		}

		if (writeStateAtEnd) {
			context.getApplication().getViewHandler().writeState(context);
		}
		writer.writeText("\n", component, null);
		writer.endElement("form");
		writer.write("\n");
	}

	private static String getActionStr(FacesContext context) {

		String viewId = context.getViewRoot().getViewId();
		String actionURL = context.getApplication().getViewHandler()
				.getActionURL(context, viewId);
		return (context.getExternalContext().encodeActionURL(actionURL));

	}

}
