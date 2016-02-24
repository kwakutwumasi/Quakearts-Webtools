package com.quakearts.webapp.facelets.bootstrap.renderers;

import java.io.IOException;
import java.util.Iterator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIMessages;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import com.quakearts.webapp.facelets.bootstrap.components.BootMessages;
import com.quakearts.webapp.facelets.bootstrap.renderkit.Attribute;
import com.quakearts.webapp.facelets.bootstrap.renderkit.AttributeManager;
import com.quakearts.webapp.facelets.bootstrap.renderkit.RenderKitUtils;
import com.quakearts.webapp.facelets.bootstrap.renderkit.html_basic.HtmlBasicRenderer;

public class BootMessagesRenderer extends HtmlBasicRenderer {

	private static final Attribute[] ATTRIBUTES = AttributeManager
			.getAttributes(AttributeManager.Key.MESSAGESMESSAGES);

	@Override
	public void encodeBegin(FacesContext context, UIComponent component)
			throws IOException {
	}

	@Override
	public void encodeEnd(FacesContext context, UIComponent component)
			throws IOException {
		
		if (context == null)
        	throw new NullPointerException();
        
        if(!(component instanceof BootMessages))
			throw new IOException("Component must be of type "+BootMessages.class.getName());

        if (!shouldEncode(component)) {
			return;
		}

		boolean mustRender = shouldWriteIdAttribute(component);

		BootMessages messages = (BootMessages) component;
		ResponseWriter writer = context.getResponseWriter();

		String clientId = ((UIMessages) component).getFor();

		if (clientId == null) {
			if (messages.isGlobalOnly()) {
				clientId = "";
			}
		}

		Iterator<?> messageIter = getMessageIter(context, clientId, component);

		if (!messageIter.hasNext()) {
			if (mustRender) {
				if ("javax_faces_developmentstage_messages".equals(component
						.getId())) {
					return;
				}
				writer.startElement("div", component);
				writeIdAttributeIfNecessary(context, writer, component);
	    		writer.write("\n");
				writer.endElement("div");
	    		writer.write("\n");
			}
			return;
		}

		boolean showSummary = messages.isShowSummary();
		boolean showDetail = messages.isShowDetail();
		String styleClass = messages.get("styleClass");

		writer.startElement("div", component);
		writeIdAttributeIfNecessary(context, writer, component);
		if (null != styleClass) {
			writer.writeAttribute("class", styleClass, "styleClass");
		}

		RenderKitUtils.renderPassThruAttributes(context, writer, component,
				ATTRIBUTES);

		while (messageIter.hasNext()) {
			FacesMessage curMessage = (FacesMessage) messageIter.next();
			if (curMessage.isRendered() && !messages.isRedisplay()) {
				continue;
			}
			curMessage.rendered();

			String severityStyle = null;
			String severityStyleClass = null;
			String defaultStyle = null;

			String summary = (null != (summary = curMessage.getSummary())) ? summary
					: "";

			String detail = (null != (detail = curMessage.getDetail())) ? detail
					: summary;
			
			boolean successMessage = Boolean.parseBoolean(messages.get("successMessage"));

			if (curMessage.getSeverity() == FacesMessage.SEVERITY_INFO) {
				severityStyle = messages.get("infoStyle");
				severityStyleClass = messages.get("infoClass");
				defaultStyle = successMessage?"alert-success" : "alert-info";
			} else if (curMessage.getSeverity() == FacesMessage.SEVERITY_WARN) {
				severityStyle = messages.get("warnStyle");
				severityStyleClass = messages.get("warnClass");
				defaultStyle = "alert-warning";
			} else if (curMessage.getSeverity() == FacesMessage.SEVERITY_ERROR) {
				severityStyle = messages.get("errorStyle");
				severityStyleClass = messages.get("errorClass");
				defaultStyle = "alert-danger";
			} else if (curMessage.getSeverity() == FacesMessage.SEVERITY_FATAL) {
				severityStyle = messages.get("fatalStyle");
				severityStyleClass = messages.get("fatalClass");
				defaultStyle = "alert-danger";
			}

    		writer.write("\n");
			writer.startElement("div", component);
			
			if (severityStyle != null) {
				writer.writeAttribute("style", severityStyle, "style");
			}
			
			writer.writeAttribute("class", "alert "+defaultStyle+(severityStyleClass!=null?" "+severityStyleClass:""), null);
			writer.writeAttribute("role", "alert", null);

			Object val = component.getAttributes().get("tooltip");
			boolean isTooltip = (val != null)
					&& Boolean.valueOf(val.toString());

			boolean wroteTooltip = false;
			if (showSummary && showDetail && isTooltip) {
				String title = (String) component.getAttributes().get("title");
				if (title == null || title.length() == 0) {
					writer.writeAttribute("title", summary, "title");
				}
				wroteTooltip = true;
			}
			
			if (!wroteTooltip && showSummary) {
	    		writer.write("\n");
				writer.startElement("strong", component);
				writer.writeText(summary, component, null);
				writer.endElement("strong");
	    		writer.write("\n");
			}
			
			if (showDetail) {
	    		writer.write("\n");
				writer.writeText(detail, component, null);
	    		writer.write("\n");
			}
			
			boolean dismissible = Boolean.parseBoolean(messages.get("dismissible"));
			
			if(dismissible){
	    		writer.write("\n");
				writer.startElement("button", component);
				writer.writeAttribute("type", "button", null);
				writer.writeAttribute("class", "close", null);
				writer.writeAttribute("data-dismiss", "alert", null);
				writer.writeAttribute("aria-label", "Close", null);
				writer.startElement("span", component);
				writer.writeAttribute("aria-hidden", "true", null);
				writer.write("&times;");
				writer.endElement("span");
	    		writer.write("\n");
				writer.endElement("button");
	    		writer.write("\n");
			}
			
			writer.endElement("div");
    		writer.write("\n");
		}

		writer.endElement("div");
		writer.write("\n");
	}
}
