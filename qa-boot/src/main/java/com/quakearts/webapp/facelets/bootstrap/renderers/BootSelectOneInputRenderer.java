package com.quakearts.webapp.facelets.bootstrap.renderers;

import static com.quakearts.webapp.facelets.bootstrap.renderkit.RenderKitUtils.findClientBehavior;
import static com.quakearts.webapp.facelets.bootstrap.renderkit.RenderKitUtils.getSelectItems;
import static com.quakearts.webapp.facelets.bootstrap.renderkit.RenderKitUtils.renderOnchange;
import static com.quakearts.webapp.facelets.bootstrap.renderkit.RenderKitUtils.renderPassThruAttributes;
import static com.quakearts.webapp.facelets.bootstrap.renderkit.RenderKitUtils.renderXHTMLStyleBooleanAttributes;
import static com.quakearts.webapp.facelets.util.UtilityMethods.componentIsDisabled;

import java.io.IOException;
import java.util.Iterator;

import javax.faces.component.UIComponent;
import javax.faces.component.behavior.ClientBehaviorContext;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.model.SelectItem;

import com.quakearts.webapp.facelets.bootstrap.behaviour.AutoCompleteBehavior;
import com.quakearts.webapp.facelets.bootstrap.components.BootSelectManyMenu;
import com.quakearts.webapp.facelets.bootstrap.renderkit.Attribute;
import com.quakearts.webapp.facelets.bootstrap.renderkit.AttributeManager;

public class BootSelectOneInputRenderer extends BootSelectInputGroupRenderer {
	@Override
	protected void renderSelect(FacesContext context, UIComponent component)
			throws IOException {
		
		if(component instanceof BootSelectManyMenu)
			throw new IOException("Component cannot be of type "+BootSelectManyMenu.class.getName());

		Attribute[] attributes = AttributeManager
				.getAttributes(AttributeManager.Key.SELECTINPUT);

		ResponseWriter writer = context.getResponseWriter();
		
		String id = component.getClientId(context);
		boolean componentDisabled = componentIsDisabled(component);
		AutoCompleteBehavior autocompleteBehavior = findClientBehavior(AutoCompleteBehavior.class, "keyup", component);
		
		writer.startElement("div", component);
		writer.writeAttribute("id", id, "clientId");
		if(!componentDisabled)
			renderOnchange(context, component);
		writeAttributeIfPresent("mainClass", "class", component, writer);
		writeAttributeIfPresent("mainStyle", "style", component, writer);
		
		renderPassThruAttributes(context, writer, component,
				attributes, getNonOnChangeBehaviors(component));
		renderXHTMLStyleBooleanAttributes(writer, component);		
		writer.write("\n");
		
		Iterator<SelectItem> items = getSelectItems(context, component);
		Holder holder = renderOptions(context, component, items,componentDisabled,id);
		String display = holder.options.size()>0?holder.options.keySet().iterator().next():null;
		String value = display!=null ? holder.options.get(display):null;

		writer.startElement("div", component);
		writer.writeAttribute("id", id+"_group", null);
		
		String wrapClass = get("wrapClass", component);
		writer.writeAttribute("class","select-one-input form-control"+(wrapClass!=null?" "+wrapClass:"")
				+ (componentDisabled ? " disabled" : "")+(autocompleteBehavior!=null 
				&& autocompleteBehavior.hasSuggestion()?" form-select-focus":""), null);
		String wrapStyle = get("wrapStyle", component);
		if(wrapStyle!=null)
			writer.writeAttribute("style", wrapStyle, null);
		writer.writeAttribute("data-dropdown", id+"_drop", null);
		if(!componentDisabled){
			writer.writeAttribute("onclick","qab.ssdd(this)", null);
			if(autocompleteBehavior==null){
				writer.writeAttribute("onmouseenter", "qab.ssime(this,true)", null);
				writer.writeAttribute("onmouseleave", "qab.ssime(this,false)", null);
			}
		}
		
		String element = autocompleteBehavior!=null && !componentDisabled?"input":"span";
		writer.startElement(element, component);
		writer.writeAttribute("class", "auto-complete", null);
		if(autocompleteBehavior!=null && !componentDisabled){
			autocompleteBehavior.loadFromComponent(component, context);
			autocompleteBehavior.setId(id+"_display");
			writer.writeAttribute("autocomplete", "off", null);
			writer.writeAttribute("onkeyup",
					autocompleteBehavior.getScript(
							ClientBehaviorContext.createClientBehaviorContext(context, component, "keyup", id, null)),
					null);
			writer.writeAttribute("onfocus", "$(this).select(); qab.ssime(this,true,event);", null);
			writer.writeAttribute("onblur", "qab.ssime(this,false,event);", null);
			writer.writeAttribute("onmouseenter", "qab.ssime(this,true,event)", null);
			writer.writeAttribute("onmouseleave", "qab.ssime(this,false,event)", null);
			writer.writeAttribute("name", id+"_display", null);
		}
		
		writer.writeAttribute("id", id+"_display", null);
		if(autocompleteBehavior==null || componentDisabled)
			writer.write(display!=null?display:" ");
		else {
			writer.writeAttribute("value", (autocompleteBehavior.hasSuggestion()
					? autocompleteBehavior.getSuggest() : (display != null ? display : "")), null);
		}
		writer.endElement(element);
		
		writer.write("\n");
		writer.startElement("span", component);
		writer.writeAttribute("class", "caret input-caret", null);
		writer.write(" ");
		writer.endElement("span");
		writer.write("\n");
	
		writer.startElement("div", component);
		writer.writeAttribute("id", id+"_drop", null);
		String dropClass = get("dropClass", component);
		writer.writeAttribute("class", (dropClass!=null?dropClass:"dropdown-menu input-list-group"), null);
		String dropStyle = get("dropStyle", component);
		
		if(autocompleteBehavior!=null && autocompleteBehavior.hasSuggestion() && !componentDisabled)
			dropStyle = (dropStyle!=null?dropStyle+";":"")+"display: inline-block;";
		
		if(dropStyle!=null)
			writer.writeAttribute("style", dropStyle, null);
			
		writer.writeAttribute("role", "menu", null);
		
		writer.write("\n");	
		if(!componentDisabled)
			writer.write(holder.buffer);
		
		writer.endElement("div");
		writer.write("\n");				
		writer.endElement("div");
		writer.write("\n");
		if(value!=null && !componentDisabled){
			writer.startElement("input", component);
			writer.writeAttribute("name", id, "clientId");
			writer.writeAttribute("type", "hidden", null);
			writer.writeAttribute("value", value, null);
			writer.endElement("input");
			writer.write("\n");
		}
		writer.endElement("div");
		writer.write("\n");
	}
}
