package com.quakearts.webapp.facelets.bootstrap.renderers;

import java.io.IOException;
import java.util.Iterator;
import java.util.Stack;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlCommandLink;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import com.quakearts.webapp.facelets.bootstrap.components.BootBreadCrumb;
import com.quakearts.webapp.facelets.bootstrap.components.BootBreadCrumbItem;
import com.quakearts.webapp.facelets.bootstrap.renderkit.Attribute;
import com.quakearts.webapp.facelets.bootstrap.renderkit.AttributeManager;
import com.quakearts.webapp.facelets.bootstrap.renderkit.AttributeManager.Key;
import com.quakearts.webapp.facelets.bootstrap.renderkit.html_basic.HtmlBasicRenderer;
import static com.quakearts.webapp.facelets.bootstrap.renderkit.RenderKitUtils.*;

public class BootBreadCrumbRenderer extends HtmlBasicRenderer {

	private static final Attribute[] ATTRIBUTES = AttributeManager.getAttributes(Key.BOOTBREADCRUMB);
	
	@Override
	public void encodeBegin(FacesContext context, UIComponent component)
			throws IOException {
        if (context == null)
        	throw new NullPointerException();
        
        if(!(component instanceof BootBreadCrumb)) {
            throw new IOException("Component must be of type "+BootBreadCrumb.class.getName());
        }
        
		ResponseWriter writer = context.getResponseWriter();
				
		writer.write("\n");
		writer.startElement("ol", component);
		String styleClass =  ((BootBreadCrumb)component).get("styleClass");				
		writer.writeAttribute("class", "breadcrumb"+(styleClass!=null?" "+styleClass:""), null);
		String style = ((BootBreadCrumb)component).get("style");
		if(style!=null)
			writer.writeAttribute("style", style, null);
			
		writeIdAttributeIfNecessary(context, writer, component);
		renderPassThruAttributes(context, writer, component, ATTRIBUTES);

	}
	
	@Override
	public void encodeChildren(FacesContext context, UIComponent component)
			throws IOException {
        if(!shouldEncode(component))
        	return;
		
        if(component.getChildCount()<=0)
        	return;

        ResponseWriter writer = context.getResponseWriter();        
        Stack<BootBreadCrumbItem> items = new Stack<BootBreadCrumbItem>();
        getChildrenToRender(context, component, items);
        while (!items.isEmpty()) {
        	BootBreadCrumbItem kid = items.pop();
			
			writer.startElement("li", component);
			if(kid.isActive()){
				writer.writeAttribute("class", "active", null);
			} 
			kid.encodeAll(context);
			writer.endElement("li");
		}
	}	
	
	@Override
	public void encodeEnd(FacesContext context, UIComponent component)
			throws IOException {
	    if(!shouldEncode(component))
	    	return;
		
	    ResponseWriter writer = context.getResponseWriter();
		writer.endElement("ol");
		writer.write("\n");
	}

	@Override
	public boolean getRendersChildren() {
		return true;
	}

	private boolean getChildrenToRender(FacesContext context, UIComponent component, Stack<BootBreadCrumbItem> items) {
        if (component.getChildCount() > 0) {
        	Iterator<UIComponent> kids = component.getChildren().iterator();
        	BootBreadCrumbItem item;
        	while (kids.hasNext()) {
        	    UIComponent kid = kids.next();
        	    if(kid instanceof BootBreadCrumbItem){
	        	    if(getChildrenToRender(context, kid, items)){
	        	    	if(!((BootBreadCrumbItem)kid).isActive())//already added
	        	    		items.push((BootBreadCrumbItem)kid);
	        	    	return true;
	        	    }
				} else if (kid instanceof HtmlCommandLink
						&& component instanceof BootBreadCrumbItem
						&& (item = (BootBreadCrumbItem) component)
								.getCommandLink() == null) {
					//This is a command link used to render this BootBreadCrumbItem
        	    	item.setCommandLink((HtmlCommandLink)kid);
        	    }
        	}
        }
        
        BootBreadCrumbItem item;
        if(component instanceof BootBreadCrumbItem){
        	item = (BootBreadCrumbItem)component;
    		item.checkActive(context);
    		if(item.isActive()){
    			items.push(item);
    			return true;
    		}
        }
        return false;
	}
	
}
