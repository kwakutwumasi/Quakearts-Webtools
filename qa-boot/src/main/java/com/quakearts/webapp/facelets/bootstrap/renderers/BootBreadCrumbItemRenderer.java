package com.quakearts.webapp.facelets.bootstrap.renderers;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlCommandLink;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import com.quakearts.webapp.facelets.bootstrap.components.BootBreadCrumbItem;
import com.quakearts.webapp.facelets.bootstrap.renderkit.Attribute;
import com.quakearts.webapp.facelets.bootstrap.renderkit.AttributeManager;
import com.quakearts.webapp.facelets.bootstrap.renderkit.AttributeManager.Key;
import com.quakearts.webapp.facelets.bootstrap.renderkit.html_basic.HtmlBasicRenderer;
import static com.quakearts.webapp.facelets.bootstrap.renderkit.RenderKitUtils.*;

public class BootBreadCrumbItemRenderer extends HtmlBasicRenderer {
	
	private static final Attribute[] ATTRIBUTES = AttributeManager.getAttributes(Key.BOOTBREADCRUMB);
	
	@Override
	public void encodeBegin(FacesContext context, UIComponent component)
			throws IOException {
	}
	
	@Override
	public void encodeEnd(FacesContext context, UIComponent component)
			throws IOException {
		if(!(component instanceof BootBreadCrumbItem))
			throw new IOException("Component must be of type "+BootBreadCrumbItem.class.getName());

		if(!shouldEncode(component))
			return;
		
		BootBreadCrumbItem item = (BootBreadCrumbItem)component;
		ResponseWriter writer = context.getResponseWriter();
		Object valueObject = item.getValue();
		if(valueObject==null)
			valueObject = ""; //ensure not null

		String label = item.get("label");
		
		if(item.isActive()){
			HtmlCommandLink kid = item.getCommandLink();
			if (kid!=null)
				valueObject = kid.getValue();
			
			writer.writeText(label==null?valueObject:label,null);
		} else {
			HtmlCommandLink kid = item.getCommandLink();
			if (kid!=null) {
		    	kid.encodeAll(context);
	        } else {
				String styleClass = item.get("styleClass");
								
				writer.startElement("a", component);
				writeIdAttributeIfNecessary(context, writer, component);
				writer.writeAttribute("href", valueObject, null);
				if(styleClass!=null)
					writer.writeAttribute("class", styleClass, null);
				renderPassThruAttributes(context, writer, component, ATTRIBUTES);
				writer.writeText(label==null?valueObject:label,null);
				writer.endElement("a");
			}
		}
	}
	
	@Override
	public void encodeChildren(FacesContext context, UIComponent component)
			throws IOException {
	}
		
	@Override
	public boolean getRendersChildren() {
		return true;
	}
	
}
