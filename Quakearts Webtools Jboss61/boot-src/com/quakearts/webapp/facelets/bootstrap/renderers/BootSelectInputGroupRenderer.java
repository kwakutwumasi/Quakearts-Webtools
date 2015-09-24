package com.quakearts.webapp.facelets.bootstrap.renderers;

import static com.quakearts.webapp.facelets.bootstrap.renderkit.RenderKitUtils.*;
import static com.quakearts.webapp.facelets.util.UtilityMethods.componentIsDisabled;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import javax.faces.component.UIComponent;
import javax.faces.component.behavior.ClientBehaviorContext;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.convert.Converter;
import javax.faces.model.SelectItem;
import com.quakearts.webapp.facelets.bootstrap.behaviour.AutoCompleteBehavior;
import com.quakearts.webapp.facelets.bootstrap.components.BootSelectManyMenu;
import com.quakearts.webapp.facelets.bootstrap.renderkit.Attribute;
import com.quakearts.webapp.facelets.bootstrap.renderkit.AttributeManager;


public class BootSelectInputGroupRenderer extends BootSelectMenuRenderer {
	@Override
	protected void renderSelect(FacesContext context, UIComponent component)
			throws IOException {
		
		if(component instanceof BootSelectManyMenu)
			throw new IOException("Component cannot be of type "+BootSelectManyMenu.class.getName());

		Attribute[] attributes = AttributeManager
				.getAttributes(AttributeManager.Key.SELECTINPUTGROUP);

		ResponseWriter writer = context.getResponseWriter();
		
		String id = component.getClientId(context);
		boolean componentDisabled = componentIsDisabled(component);
		AutoCompleteBehavior autocompleteBehavior = findClientBehavior(AutoCompleteBehavior.class, "keyup", component);
		
		writer.startElement("div", component);
		writer.writeAttribute("id", id, "clientId");
		if(!componentDisabled)
			renderOnchange(context, component, false);
		
		renderPassThruAttributes(context, writer, component,
				attributes, getNonOnChangeBehaviors(component));
		renderXHTMLStyleBooleanAttributes(writer, component);

		String keyup = get("onkeyup", component);
		if(keyup!=null)
			writer.writeAttribute("onkeyup", keyup, null);			

		String keydown = get("onkeydown", component);
		if(keydown!=null)
			writer.writeAttribute("onkeydown", keydown, null);			

		String keypressed = get("onkeypressed", component);
		if(keypressed!=null)
			writer.writeAttribute("onkeypressed", keypressed, null);
		
		writer.write("\n");
		String label = (String) component.getAttributes().get("label");
		String styleClass = (String) component.getAttributes().get("styleClass");
		
		Iterator<SelectItem> items = getSelectItems(context, component);
		Holder holder = renderOptions(context, component, items,componentDisabled,id);
		String display = holder.options.size()>0?holder.options.keySet().iterator().next():null;
		String value = display!=null ? holder.options.get(display):null;

		writer.startElement("div", component);
		writer.writeAttribute("id", id+"_group", null);
		writer.writeAttribute("class", "input-group"
				+(styleClass != null ? " " + styleClass : "")
				+ (componentDisabled ? " disabled" : ""), null);
				
		writer.startElement("span", component);
		writer.writeAttribute("class", "input-group-addon", null);
    	writer.writeText(label!=null?label:" ",null);
		writer.endElement("span");
		
		writer.startElement("div", component);
		writer.writeAttribute("class", "form-control form-select" +(componentDisabled?" disabled":""), null);
		writer.writeAttribute("style", "z-index: auto;", null);
		if(!componentDisabled)
			writer.writeAttribute("onclick", "qaboot.selectInputDropDown('dd_"+id.replace(":", "\\\\:")+"');", null);
		
		String element = autocompleteBehavior!=null && !componentDisabled?"input":"span";
		writer.startElement(element, component);
		if(autocompleteBehavior!=null && !componentDisabled){
			autocompleteBehavior.loadFromComponent(component, context);
			autocompleteBehavior.setId(id+"_display");
			writer.writeAttribute("class", "auto-complete", null);
			writer.writeAttribute("autocomplete", "off", null);
			writer.writeAttribute("onkeyup",
					autocompleteBehavior.getScript(
							ClientBehaviorContext.createClientBehaviorContext(context, component, "keyup", id, null)),
					null);
			writer.writeAttribute("onfocus", "$(this).select();", null);
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
		writer.writeAttribute("id", "dd_"+id, null);
		writer.writeAttribute("class", "dropdown-menu input-list-group", null);
		if(autocompleteBehavior!=null && autocompleteBehavior.hasSuggestion() && !componentDisabled)
			writer.writeAttribute("style", "display: inline-block;", null);
			
		writer.writeAttribute("role", "menu", null);
		
		writer.write("\n");	
		if(!componentDisabled)
			writer.write(holder.buffer);
		
		writer.endElement("div");
		writer.write("\n");
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
	
	@Override
	protected boolean renderOption(FacesContext context, UIComponent component,
			Converter converter, SelectItem curItem, Object currentSelections,
			Object[] submittedValues, OptionComponentInfo optionInfo,
			Map<String, String> values, boolean isManySelect, 
			ResponseWriter writer, int index)
			throws IOException {
	
		Object valuesArray;
		Object itemValue;
		String valueString = getFormattedValue(context, component,
				curItem.getValue(), converter);
		boolean containsValue;
		if (submittedValues != null) {
			containsValue = containsaValue(submittedValues);
			if (containsValue) {
				valuesArray = submittedValues;
				itemValue = valueString;
			} else {
				valuesArray = currentSelections;
				itemValue = curItem.getValue();
			}
		} else {
			valuesArray = currentSelections;
			itemValue = curItem.getValue();
		}
	
		boolean isSelected = isSelected(context, component, itemValue,
				valuesArray, converter);
		if (optionInfo.isHideNoSelection() && curItem.isNoSelectionOption()
				&& currentSelections != null && !isSelected) {
			return false;
		}
	
		String labelClass;
		if (optionInfo.isDisabled() || curItem.isDisabled()) {
			labelClass = optionInfo.getDisabledClass()+" disabled";
		} else {
			labelClass = optionInfo.getEnabledClass();
		}
		
		String jqId = component.getClientId(context).replace(":", "\\\\:");

		writer.startElement("a", component);
		writer.writeAttribute("class", "list-group-item select-list"
				+ (isSelected ? " active" : "")
				+ (labelClass != null ? " " + labelClass : ""), null);	
		
		if(!curItem.isDisabled() && !optionInfo.isDisabled())
			writer.writeAttribute("onclick", "qaboot.oneListItemSelected(this,'"
				+ jqId + "','" + valueString+"','"+jqId+"_display');", null);
			
		String label = curItem.getLabel();
		if (label == null) {
			label = valueString;
		}
		
		if (curItem.isEscape()) {
			writer.writeText(label, component, "label");
		} else {
			writer.write(label);
		}
		writer.endElement("a");
		writer.writeText("\n", component, null);
		if (isSelected) {
			values.put(label,valueString);
		}
		return false;
	}
}
