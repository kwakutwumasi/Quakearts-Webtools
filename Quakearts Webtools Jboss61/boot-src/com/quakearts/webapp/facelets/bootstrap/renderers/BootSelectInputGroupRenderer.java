package com.quakearts.webapp.facelets.bootstrap.renderers;

import static com.quakearts.webapp.facelets.bootstrap.renderkit.RenderKitUtils.*;
import static com.quakearts.webapp.facelets.util.UtilityMethods.componentIsDisabled;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.component.behavior.ClientBehavior;
import javax.faces.component.behavior.ClientBehaviorContext;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.convert.Converter;
import javax.faces.model.SelectItem;

import com.quakearts.webapp.facelets.bootstrap.behaviour.AutoCompleteBehavior;
import com.quakearts.webapp.facelets.bootstrap.components.BootSelectManyMenu;
import com.quakearts.webapp.facelets.util.ObjectExtractor;


public class BootSelectInputGroupRenderer extends BootSelectMenuRenderer {
	@Override
	protected void renderSelect(FacesContext context, UIComponent component)
			throws IOException {
		
		if(component instanceof BootSelectManyMenu)
			throw new IOException("Component cannot be of type "+BootSelectManyMenu.class.getName());
		
		ResponseWriter writer = context.getResponseWriter();
		
		String id = component.getClientId(context);
		boolean componentDisabled = componentIsDisabled(component);
		AutoCompleteBehavior autocompleteBehavior = null;
		Map<String, List<ClientBehavior>> nonAutoCompleteMap = new HashMap<>();
		Map<String, List<ClientBehavior>> behaviorsMap = getNonOnChangeBehaviors(component);
		if(behaviorsMap!=null && behaviorsMap.size()>0)
			for(String key:behaviorsMap.keySet()){
				List<ClientBehavior> behaviors = behaviorsMap.get(key);
				if(key.equals("keyup")){
					List<ClientBehavior> nonAutocompleteList = new ArrayList<>();
					for(ClientBehavior behavior:behaviors){
						if(behavior instanceof AutoCompleteBehavior){
							autocompleteBehavior = (AutoCompleteBehavior) behavior;
						} else {
							nonAutocompleteList.add(behavior);
						}
					}
					if(autocompleteBehavior==null)
						nonAutoCompleteMap.put(key, nonAutocompleteList);
				} else {
					nonAutoCompleteMap.put(key, behaviors);
				}
			}
		
		writer.startElement("div", component);
		writer.writeAttribute("id", id, "clientId");
		renderOnchange(context, component, false);
		renderPassThruAttributes(context, writer, component,
				ATTRIBUTES,nonAutoCompleteMap);
		renderXHTMLStyleBooleanAttributes(writer, component);
		writer.write("\n");
		String label = (String) component.getAttributes().get("label");
		String styleClass = (String) component.getAttributes().get("styleClass");
		
		Iterator<SelectItem> items = getSelectItems(context, component);
		Holder holder = renderOptions(context, component, items,componentDisabled);
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
		writer.writeAttribute("style", "z-index: auto;cursor:pointer;", null);
		writer.writeAttribute("onclick", "qaboot.selectInputDropDown('dd_"+id.replace(":", "\\\\:")+"');", null);

		String element = autocompleteBehavior!=null?"input":"span";
		writer.startElement(element, component);
		if(autocompleteBehavior!=null){
			String render = ObjectExtractor.extractString(component.getValueExpression("render"), context.getELContext());
			if(render==null)
				render = (String) component.getAttributes().get("render");
			
			String execute = ObjectExtractor.extractString(component.getValueExpression("execute"), context.getELContext());
			if(execute==null)
				execute = (String) component.getAttributes().get("execute");

			String onerror = ObjectExtractor.extractString(component.getValueExpression("onerror"), context.getELContext());
			if(onerror==null)
				onerror = (String) component.getAttributes().get("onerror");

			String onevent = ObjectExtractor.extractString(component.getValueExpression("onevent"), context.getELContext());
			if(onevent==null)
				onevent = (String) component.getAttributes().get("onevent");
			
			ValueExpression delayExpression;
			if((delayExpression=component.getValueExpression("delay"))!=null){
				autocompleteBehavior.setDelay(ObjectExtractor.extractInteger(delayExpression, context.getELContext()));
			} else {
				try {
					autocompleteBehavior.setDelay(Integer.parseInt((String)component.getAttributes().get("delay")));
				} catch (Exception e) {
				}
			}
			autocompleteBehavior.setRender(render);
			autocompleteBehavior.setExecute(execute);
			autocompleteBehavior.setOnerror(onerror);
			autocompleteBehavior.setOnevent(onevent);

			autocompleteBehavior.setId(id+"_display");
			writer.writeAttribute("class", "auto-complete", null);
			writer.writeAttribute("autocomplete", "off", null);
			writer.writeAttribute("onkeyup",
					autocompleteBehavior.getScript(
							ClientBehaviorContext.createClientBehaviorContext(context, component, "keyup", id, null)),
					null);
			writer.writeAttribute("onfocus", "$(this).select();", null);
		}
		writer.writeAttribute("id", id+"_display", null);
		writer.writeAttribute("name", id+"_display", null);
		if(autocompleteBehavior==null)
			writer.write(display!=null?display:" ");
		else {
			writer.writeAttribute("value", (autocompleteBehavior != null && autocompleteBehavior.hasSuggestion()
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
		if(autocompleteBehavior!=null && autocompleteBehavior.hasSuggestion())
			writer.writeAttribute("style", "display: inline-block;", null);
			
		writer.writeAttribute("role", "menu", null);
		
		writer.write("\n");	
		writer.write(holder.buffer);
		writer.endElement("div");
		writer.write("\n");
		writer.endElement("div");
		writer.write("\n");
				
		writer.endElement("div");
		writer.write("\n");
		if(value!=null){
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
	protected void renderOption(FacesContext context, UIComponent component,
			Converter converter, SelectItem curItem, Object currentSelections,
			Object[] submittedValues, OptionComponentInfo optionInfo,
			Map<String, String> values, boolean isManySelect, ResponseWriter writer)
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
			return;
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
				+ (labelClass != null ? " " + labelClass : "")
				+((!optionInfo.isDisabled()) && curItem.isDisabled()?" disabled":""), null);		
		writer.writeAttribute("onclick", "qaboot.oneListItemSelected(this,'"
				+ jqId + "','" + valueString+"','"+jqId+"_display');",
				null);
			
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
	}
}
