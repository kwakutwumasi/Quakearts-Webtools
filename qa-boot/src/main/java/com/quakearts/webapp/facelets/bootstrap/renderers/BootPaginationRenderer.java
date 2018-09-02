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

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.component.UIData;
import javax.faces.component.UIForm;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import com.quakearts.webapp.facelets.bootstrap.beans.PaginationBean;
import com.quakearts.webapp.facelets.bootstrap.components.BootPagination;
import com.quakearts.webapp.facelets.bootstrap.renderkit.Attribute;
import com.quakearts.webapp.facelets.bootstrap.renderkit.AttributeManager;
import com.quakearts.webapp.facelets.bootstrap.renderkit.AttributeManager.Key;
import com.quakearts.webapp.facelets.bootstrap.renderkit.html_basic.HtmlBasicRenderer;

import static com.quakearts.webapp.facelets.bootstrap.renderkit.RenderKitUtils.*;

public class BootPaginationRenderer extends HtmlBasicRenderer {
	
	private static final Attribute[] ATTRIBUTES = AttributeManager.getAttributes(Key.PAGINATION);
	
	@Override
	public void decode(FacesContext context, UIComponent component) {
		if (context == null)
        	throw new NullPointerException();
        
        if(!(component instanceof BootPagination))
			return;

        if (!shouldDecode(component)) {
            return;
        }

        String clientId = component.getClientId(context);
		String newValue = context.getExternalContext().getRequestParameterMap()
				.get(clientId);
        if (newValue != null && newValue.matches("[\\d]+")) {
        	((BootPagination)component).setValue(newValue);
        }
	}
	
	@Override
	protected void getEndTextToRender(FacesContext context,
			UIComponent component, String currentValue) throws IOException {
		if (context == null)
        	throw new NullPointerException();
        
        if(!(component instanceof BootPagination))
			throw new IOException("Component must be of type "+BootPagination.class.getName());
		
		BootPagination pagination = (BootPagination) component;
		
		UIComponent parentForm = pagination.getParent();
		boolean foundForm = false;
		while(parentForm != null){
			if(parentForm instanceof UIForm){
				foundForm=true;
				break;
			}
			parentForm = parentForm.getParent();			
		}
		
		if(!foundForm)
			throw new IOException("Component must be nested within a component of type "+UIForm.class.getName());

		
		ValueExpression forExpression = component.getValueExpression("for");
		if(forExpression==null)
			throw new IOException("Attribute for is required");
		
		Object forObject = forExpression.getValue(context.getELContext());
		if(!(forObject instanceof PaginationBean)&&(!(forObject instanceof UIData)))
			throw new IOException("Attribute for must be of type "+PaginationBean.class.getName()
					+" or "+UIData.class.getName());
		
		String tableId = "";
		UIComponent dataComponent;
		
		if(forObject instanceof UIData){
			dataComponent = (UIComponent) forObject;
		} else {
			dataComponent = ((PaginationBean)forObject).getDataComponent();
			if(dataComponent == null)
				throw new IOException(
						"PaginationBean attribute dataComponent must be bound to the component for renderering");
		}
		
		tableId = dataComponent.getClientId(context);
		
		int currentPage = pagination.currentPage();
		
		int totalPages = pagination.calculatedTotalPages();		
		int maxPages = pagination.calculatedMaxPages();
		int startPage = totalPages>0?1:0, endPage=totalPages<maxPages?totalPages:maxPages;
		
		boolean doDots = false;
		if(totalPages>maxPages && currentPage>maxPages){
			startPage = currentPage - (maxPages/2);
			endPage = startPage+maxPages;
			doDots = true;
		}
		
		if(endPage>totalPages)
			endPage = totalPages;
		
		String onevent = pagination.get("onevent");
		String onerror = pagination.get("onerror");
		String render = pagination.get("render");
		if(!"@all".equals(render) && !"@form".equals(render) && !"@this".equals(render))
			render = findClientIds(render, dataComponent, context);

		String execute = pagination.get("execute");
		if(!"@all".equals(execute) && !"@form".equals(execute) && !"@this".equals(execute))
			execute = findClientIds(execute, component, context);
		
        String style = pagination.get("style");
        String id = pagination.getClientId(context);
		
		ResponseWriter writer = context.getResponseWriter();
		String styleClass = pagination.getStyleClass();
		
		writer.startElement("div", component);
		writer.writeAttribute("id", id, null);
        if(style!=null)
        	writer.writeAttribute("style", style, null);
    	writer.write("\n");
		writer.startElement("ul", component);
		writer.writeAttribute("class", "pagination"+(styleClass!=null?" "+styleClass:""), null);
    	writer.write("\n");
		writer.startElement("li", component);
		if(currentPage==1)
			writer.writeAttribute("class", "disabled",null);
		else {
			writer.writeAttribute("onclick", "qab.pp(this)", null);
			writer.writeAttribute("data-page-input", id+"_input", null);
		}
		
    	writer.write("\n");
		writer.startElement("a", component);
		writer.writeAttribute("aria-label", "Previous", null);
		writer.startElement("span", component);
		writer.writeAttribute("aria-hidden", "true", null);
		writer.write("&laquo;");
		writer.endElement("span");
    	writer.write("\n");
		writer.endElement("a");
    	writer.write("\n");
		writer.endElement("li");
    	writer.write("\n");
		
		if(doDots){
			writer.startElement("li", component);
			writer.writeAttribute("class", "disabled",null);
	    	writer.write("\n");
			writer.startElement("a", component);
			writer.writeAttribute("aria-label", "...", null);
			writer.startElement("span", component);
			writer.write("...");
			writer.endElement("span");
			writer.endElement("a");
	    	writer.write("\n");
			writer.endElement("li");
	    	writer.write("\n");
		}
		
		if(totalPages!=0)
			for(int i=startPage;i<=endPage;i++){
				writer.startElement("li", component);
				if(i==currentPage)
					writer.writeAttribute("class","active",null);
				else {
					writer.writeAttribute("onclick","qab.gp(this)",null);
					writer.writeAttribute("data-page-input", id+"_input", null);
					writer.writeAttribute("data-page-value", i, null);
				}
				
		    	writer.write("\n");
				writer.startElement("a", component);
				writer.writeText(""+i, null);
				writer.endElement("a");
		    	writer.write("\n");
				writer.endElement("li");
		    	writer.write("\n");
			}
		
		if(totalPages>maxPages && endPage<totalPages){
			writer.startElement("li", component);
			writer.writeAttribute("class", "disabled",null);
	    	writer.write("\n");
			writer.startElement("a", component);
			writer.writeAttribute("aria-label", "...", null);
			writer.startElement("span", component);
			writer.write("...");
			writer.endElement("span");
			writer.endElement("a");
	    	writer.write("\n");
			writer.endElement("li");
	    	writer.write("\n");
		}
		
		writer.startElement("li", component);
		writer.writeAttribute("aria-label", "Next", null);
		if(currentPage==totalPages || totalPages==0)
			writer.writeAttribute("class", "disabled",null);
		else {
			writer.writeAttribute("onclick","qab.np(this)",null);
			writer.writeAttribute("data-page-input", id+"_input", null);
		}
		
    	writer.write("\n");
		writer.startElement("a", component);
		writer.startElement("span", component);
		writer.writeAttribute("aria-hidden", "true", null);
		writer.write("&raquo;");
		writer.endElement("span");
		writer.endElement("a");
    	writer.write("\n");
		writer.endElement("li");
    	writer.write("\n");
		writer.endElement("ul");
    	writer.write("\n");
		writer.startElement("input", component);
		writer.writeAttribute("type", "hidden", null);
		writer.writeAttribute("value", currentPage+"", null);
		writer.writeAttribute("id", id+"_input", null);
		writer.writeAttribute("name", id, null);
		writer.writeAttribute("onchange", "jsf.ajax.request(this, event, {execute:'"+id+(execute!=null?" "+execute:"")+"',render:'"+id+" "
				+tableId+(render!=null?" "+render:"")+"'"
				+(onevent!=null&&onevent.length()>0? ",onevent: "+onevent:"")
				+(onerror!=null&&onerror.length()>0?",onerror: "+onerror:"")+"});", null);
		renderOverlayTarget(context, component);
		renderPassThruAttributes(context, writer, component, ATTRIBUTES);
		writer.endElement("input");
    	writer.write("\n");
		writer.endElement("div");
    	writer.write("\n");
	}

	@Override
	public boolean getRendersChildren() {
		return true;
	}
	
	@Override
	protected String getCurrentValue(FacesContext context, UIComponent component) {
		Object currentValue= ((BootPagination)component).getValue();
		return currentValue!=null?currentValue.toString():"";
	}
	
	@Override
	public void encodeChildren(FacesContext context, UIComponent component)
			throws IOException {
		//No children
	}
}
