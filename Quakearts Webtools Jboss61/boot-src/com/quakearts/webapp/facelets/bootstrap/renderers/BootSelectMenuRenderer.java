package com.quakearts.webapp.facelets.bootstrap.renderers;

import java.io.CharArrayWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.logging.Level;
import javax.el.ELException;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;
import javax.faces.FacesException;
import javax.faces.component.UIComponent;
import javax.faces.component.UISelectMany;
import javax.faces.component.UISelectOne;
import javax.faces.component.ValueHolder;
import javax.faces.component.behavior.ClientBehaviorContext;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;

import com.quakearts.webapp.facelets.bootstrap.behaviour.AutoCompleteBehavior;
import com.quakearts.webapp.facelets.bootstrap.components.BootSelectManyListbox;
import com.quakearts.webapp.facelets.bootstrap.components.BootSelectManyMenu;
import com.quakearts.webapp.facelets.bootstrap.components.BootSelectOneListbox;
import com.quakearts.webapp.facelets.bootstrap.components.BootSelectOneMenu;
import com.quakearts.webapp.facelets.util.ObjectExtractor;
import com.quakearts.webapp.facelets.bootstrap.renderkit.Attribute;
import com.quakearts.webapp.facelets.bootstrap.renderkit.AttributeManager;
import com.quakearts.webapp.facelets.bootstrap.renderkit.html_basic.HtmlBasicInputRenderer;
import static com.quakearts.webapp.facelets.bootstrap.renderkit.RenderKitUtils.*;
import static com.quakearts.webapp.facelets.util.UtilityMethods.*;
import static com.quakearts.webapp.facelets.bootstrap.common.BootOnLoadComponent.*;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class BootSelectMenuRenderer extends HtmlBasicInputRenderer {

	protected HashMap<Class, Method> methodCache = new HashMap<Class, Method>();
	
	public Object convertSelectManyValue(FacesContext context,
			UISelectMany uiSelectMany, String[] newValues)
			throws ConverterException {

		ValueExpression valueExpression = uiSelectMany
				.getValueExpression("value");

		Object result = newValues;
		boolean throwException = false;

		if (null != valueExpression) {

			Class modelType = valueExpression.getType(context.getELContext());
			if (modelType != null) {
				result = convertSelectManyValuesForModel(context, uiSelectMany,
						modelType, newValues);
			}

			if (result == null) {
				Object value = valueExpression.getValue(context.getELContext());
				if (value != null) {
					result = convertSelectManyValuesForModel(context,
							uiSelectMany, value.getClass(), newValues);
				}
			}
			if (result == null) {
				throwException = true;
			}
		} else {

			result = convertSelectManyValues(context, uiSelectMany,
					Object[].class, newValues);
		}
		if (throwException) {
			StringBuffer values = new StringBuffer();
			if (null != newValues) {
				for (int i = 0; i < newValues.length; i++) {
					if (i == 0) {
						values.append(newValues[i]);
					} else {
						values.append(' ').append(newValues[i]);
					}
				}
			}
			throw new ConverterException("Could not convert "+values.toString()+" for expression "+valueExpression.getExpressionString());
		}

		return result;
	}

	public Object convertSelectOneValue(FacesContext context,
			UISelectOne uiSelectOne, String newValue) throws ConverterException {

		if (NO_VALUE.equals(newValue)) {
			return null;
		}
		if (newValue == null) {
			return null;
		}

		Object convertedValue = super.getConvertedValue(context, uiSelectOne,
				newValue);

		return convertedValue;
	}

	@Override
	public void decode(FacesContext context, UIComponent component) {
		if(!(component instanceof BootSelectManyListbox) 
				&&!(component instanceof BootSelectManyMenu)
				&&!(component instanceof BootSelectOneListbox)
				&&!(component instanceof BootSelectOneMenu))
			return;
		
		if (!shouldDecode(component)) {
			return;
		}

		String clientId = decodeBehaviors(context, component);

		if (clientId == null) {
			clientId = component.getClientId(context);
		}
		assert (clientId != null);

		if (component instanceof UISelectMany) {
			Map<String, String[]> requestParameterValuesMap = context
					.getExternalContext().getRequestParameterValuesMap();
			if (requestParameterValuesMap.containsKey(clientId)) {
				String newValues[] = requestParameterValuesMap.get(clientId);
				setSubmittedValue(component, newValues);
			} else {
				setSubmittedValue(component, new String[0]);
			}
		} else {
			Map<String, String> requestParameterMap = context
					.getExternalContext().getRequestParameterMap();
			if (requestParameterMap.containsKey(clientId)) {
				String newValue = requestParameterMap.get(clientId);
				setSubmittedValue(component, newValue);
			} else {
				setSubmittedValue(component, NO_VALUE);
			}
		}

	}

	@Override
	public void encodeBegin(FacesContext context, UIComponent component)
			throws IOException {
	}

	@Override
	public void encodeEnd(FacesContext context, UIComponent component)
			throws IOException {
		if(!(component instanceof BootSelectManyListbox) 
				&&!(component instanceof BootSelectManyMenu)
				&&!(component instanceof BootSelectOneListbox)
				&&!(component instanceof BootSelectOneMenu))
			throw new IOException("Component must be one of "+
					BootSelectManyListbox.class.getName()+", "+
					BootSelectManyMenu.class.getName()+", "+
					BootSelectOneListbox.class.getName()+", or "+
					BootSelectOneMenu.class.getName());

		if (!shouldEncode(component)) {
			return;
		}

		renderSelect(context, component);

	}

	@Override
	public Object getConvertedValue(FacesContext context,
			UIComponent component, Object submittedValue)
			throws ConverterException {

		if (component instanceof UISelectMany) {
			return convertSelectManyValue(context, ((UISelectMany) component),
					(String[]) submittedValue);
		} else {
			return convertSelectOneValue(context, ((UISelectOne) component),
					(String) submittedValue);
		}

	}

	protected void renderSelect(FacesContext context, UIComponent component)
			throws IOException {
	
		ResponseWriter writer = context.getResponseWriter();
		
		String id = component.getClientId(context), idjs=id.replace(":", "\\\\:");
		boolean isDropDown = component instanceof BootSelectManyMenu || component instanceof BootSelectOneMenu;
		Attribute[] attributes;
		if(isDropDown){
			attributes = AttributeManager
				.getAttributes(AttributeManager.Key.SELECTMANYMENU);
		} else {
			attributes = AttributeManager
					.getAttributes(AttributeManager.Key.SELECTMANYLIST);
		}
		
		boolean componentDisabled = componentIsDisabled(component);
		
		boolean limit = Boolean.parseBoolean(get("limit",component));
		int size = 5;
		if(limit){
			try {
				size = Integer.parseInt(get("size",component));
				if(size<=0)
					size=5;
			} catch (Exception e) {
			}
		}
		
		writer.startElement("div", component);
		writer.writeAttribute("id", id, "clientId");
		if(!componentDisabled)
			renderOnchange(context, component, false);

		boolean opened = Boolean.parseBoolean(get("opened", component));
		boolean hasSuggestions = false;
		
		if(isDropDown){			
			writer.write("\n");
			String label = get("label", component);
			String styleClass = get("styleClass", component);
			String autoClass = get("autoClass", component);
			
			boolean fill = Boolean.parseBoolean(get("fill", component));
			
			AutoCompleteBehavior behavior = findClientBehavior(AutoCompleteBehavior.class, "keyup", component);
			if(behavior!=null){
				behavior.loadFromComponent(component, context);
				behavior.setId(id+"_filter");
				
				writer.startElement("div", component);
				writer.writeAttribute("class", "btn-group"
						+(fill?" fill100":""), null);
				writer.writeAttribute("role", "group", null);
				writer.writeAttribute("aria-label", "Filter", null);
				
				writer.startElement("input", component);
				writer.writeAttribute("class", "btn btn-default menu-input "
						+(autoClass!=null?" "+autoClass:""), null);
				writer.writeAttribute("id", id+"_filter", null);
				writer.writeAttribute("name", id+"_filter", null);
				if(componentDisabled)
					writer.writeAttribute("disabled", "disabled", null);
				else {
					writer.writeAttribute("onkeyup", behavior.getScript(ClientBehaviorContext
							.createClientBehaviorContext(context, component, "keyup", id, null)), null);
					writer.writeAttribute("onfocus", "$(this).select();", null);
				}
				
				if(behavior.hasSuggestion()){
					writer.writeAttribute("value", behavior.getSuggest(), null);
					hasSuggestions = true;
				}
				
				writer.endElement("input");
			}
			
			String onclick = get("onclick", component);
			
			writer.startElement("button", component);
			writer.writeAttribute("id", id+"_button", null);
			writer.writeAttribute("class", "select-many-dropdown btn btn-"
					+ getDisplayType(component, context)
					+ (fill && behavior==null?" btn-block":"")
					+ (styleClass != null ? " " + styleClass : ""), null);
			writer.writeAttribute("onclick", "qaboot.selectManyDropDown('dd_"+idjs+"');"
					+(onclick!=null?onclick:""), null);
			writer.writeAttribute("type", "button", null);
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
			
			writer.writeText(label!=null?label:"",null);
    		writer.write("\n");
    		writer.startElement("span", component);
    		writer.writeAttribute("class", "caret", null);
    		writer.write(" ");
    		writer.endElement("span");
    		writer.write("\n");
    		writer.endElement("button");
    		writer.write("\n");
    		
    		if(behavior!=null){
    			writer.endElement("div");
    		}
		} else {
			renderPassThruAttributes(context, writer, component,
					attributes, getNonOnChangeBehaviors(component));
			renderXHTMLStyleBooleanAttributes(writer, component);
			writer.write("\n");
		}
		
		Iterator<SelectItem> items = getSelectItems(context, component);
		Holder holder = renderOptions(context, component, items,componentDisabled, id);
	
		writer.startElement("div", component);
		writer.writeAttribute("id", "dd_"+id, null);
		opened = opened || !holder.options.isEmpty() || (hasSuggestions && holder.total>0);
		if(limit && holder.total>size){
			writer.writeAttribute("class", (isDropDown && !opened?" collapse":""), null);

    		writer.write("\n");
			writer.startElement("button", component);
			writer.writeAttribute("id", "up_"+id, null);
			writer.writeAttribute("type", "button", null);
			writer.writeAttribute("class", "btn btn-default btn-xs btn-block", null);
			if(holder.options.isEmpty())
				writer.writeAttribute("disabled", "disabled", null);
			writer.writeAttribute("onclick", "qaboot.scrollUp('ddi_"+idjs+"',this,'dwn_"+idjs+"');", null);
			
			writer.startElement("span", component);
			writer.writeAttribute("class", "glyphicon glyphicon-chevron-up", null);
			writer.write(" ");
			writer.endElement("span");
			writer.endElement("button");
						
    		writer.write("\n");
			writer.startElement("div", component);
			writer.writeAttribute("id", "ddi_"+id, null);
			writer.writeAttribute("class", "list-group list-container", null);
			if(size!=5 || !holder.options.isEmpty()){
				StringBuilder styleBuilder = new StringBuilder();
				if(size!=5)
					styleBuilder.append("max-height:").append(size*44).append("px");
				
				if(holder.firstIndex>0)
					addScriptContent("$('#ddi_"+idjs+"').scrollTop("+(holder.firstIndex*44)+");\n", context);

				writer.writeAttribute("style", styleBuilder.toString(), null);
			}
		} else {
			writer.writeAttribute("class", "list-group"+(isDropDown && !opened?" collapse":""), null);
		}
		
		writer.write("\n");	
		writer.write(holder.buffer);

		if(limit && holder.total>size){
			writer.endElement("div");
			writer.write("\n");		
			
    		writer.write("\n");
			writer.startElement("button", component);
			writer.writeAttribute("id", "dwn_"+id, null);
			writer.writeAttribute("class", "btn btn-default btn-xs btn-block", null);
			writer.writeAttribute("type", "button", null);
			writer.writeAttribute("onclick", "qaboot.scrollDown('ddi_"+idjs+"',this,'up_"+idjs+"');", null);
			
			writer.startElement("span", component);
			writer.writeAttribute("class", "glyphicon glyphicon-chevron-down", null);
			writer.write(" ");
			writer.endElement("span");
			writer.endElement("button");
		}

		writer.endElement("div");
		writer.write("\n");
		if(!componentDisabled)
			for(String value:holder.options.values()){
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

	protected class Holder {
		Map<String, String> options;
		char[] buffer;
		int total;
		int firstIndex;
		public Holder(Map<String, String> values, char[] rendered, int total, int firstIndex) {
			this.options = values;
			this.buffer = rendered;
			this.total = total;
			this.firstIndex = firstIndex;
		}		
	}
	
	protected Holder renderOptions(FacesContext context, UIComponent component,
			Iterator<SelectItem> items, boolean componentDisabled, String id) throws IOException {
		
		Map<String, String> values = new HashMap<String,String>();
		CharArrayWriter charWriter = new CharArrayWriter();
		ResponseWriter writer = context.getResponseWriter().cloneWithWriter(charWriter);
		boolean isManySelect = component instanceof BootSelectManyMenu || component instanceof BootSelectManyListbox;
	
		Converter converter = null;
		if (component instanceof ValueHolder) {
			converter = ((ValueHolder) component).getConverter();
		}
		Object currentSelections = getCurrentSelectedValues(component);
		Object[] submittedValues = getSubmittedSelectedValues(component);
		Map<String, Object> attributes = component.getAttributes();
	
		OptionComponentInfo optionInfo = new OptionComponentInfo(
				(String) attributes.get("disabledClass"),
				(String) attributes.get("enabledClass"), componentDisabled,
				isHideNoSelection(component), id);

		int index = 0;
		int firstIndex = -1;
		while (items.hasNext()) {
			SelectItem item = items.next();
	
			if (item instanceof SelectItemGroup) {	
				SelectItem[] itemsArray = ((SelectItemGroup) item)
						.getSelectItems();
				for (int i = 0; i < itemsArray.length; ++i) 
					if(renderOption(context, component, converter, itemsArray[i],
							currentSelections, submittedValues, optionInfo,
							values, isManySelect,writer,index)){
						if(firstIndex==-1&& !values.isEmpty()) firstIndex= index;
						index++;
					}
			} else {
				if(renderOption(context, component, converter, item,
						currentSelections, submittedValues, optionInfo,
						values, isManySelect,writer,index)){
					if(firstIndex==-1&& !values.isEmpty()) firstIndex= index;
					index++;
				}
			}
		}
		return new Holder(values, charWriter.toCharArray(), index, firstIndex);
	}

    private static String getDisplayType(UIComponent button, FacesContext context){
    	String displayType = ObjectExtractor.extractString(button.getValueExpression("displayType"), context.getELContext());
    	if(displayType==null)
    		displayType =(String) button.getAttributes().get("displayType");

    	if(displayType==null || (!displayType.equals("default"))
    		&& (!displayType.equals("primary")) && (!displayType.equals("success"))
    		&& (!displayType.equals("info")) && (!displayType.equals("warning"))
    		&& (!displayType.equals("danger")))
    		displayType = "default";
    	
    	return displayType;
    }
	
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
			labelClass = (optionInfo.getDisabledClass()==null?"":optionInfo.getDisabledClass()+" ")+"disabled";
		} else {
			labelClass = optionInfo.getEnabledClass();
		}	

		writer.startElement("a", component);
		writer.writeAttribute("class", "list-group-item select-list"
				+ (isSelected ? " active" : "")
				+ (labelClass != null ? " " + labelClass : ""), null);	
		
		if (!optionInfo.isDisabled() && !curItem.isDisabled()){
			String function; 
			if(isManySelect)
				function="qaboot.manyListItemSelected";
			else
				function="qaboot.oneListItemSelected";

			writer.writeAttribute("onclick", function+"(this,'"
				+ optionInfo.getJqueryId() + "','" + valueString+"');",
				null);
		}
			
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
			values.put(label, valueString);
		}
		return true;
	}

	protected Object convertSelectManyValuesForModel(FacesContext context,
			UISelectMany uiSelectMany, Class modelType, String[] newValues) {
		if (modelType.isArray()) {
			return convertSelectManyValues(context, uiSelectMany, modelType,
					newValues);
		} else if (Collection.class.isAssignableFrom(modelType)) {
			Object[] values = (Object[]) convertSelectManyValues(context,
					uiSelectMany, Object[].class, newValues);

			Collection targetCollection = null;

			Object collectionTypeHint = uiSelectMany.getAttributes().get(
					"collectionType");
			if (collectionTypeHint != null) {
				targetCollection = createCollectionFromHint(collectionTypeHint);
			} else {

				Collection currentValue = (Collection) uiSelectMany.getValue();
				if (currentValue != null) {
					targetCollection = cloneValue(currentValue);
				}

				if (targetCollection == null) {

					targetCollection = createCollection(currentValue, modelType);
				}

				if (targetCollection == null) {

					targetCollection = bestGuess(modelType, values.length);
				}
			}

			for (Object v : values) {
				targetCollection.add(v);
			}

			return targetCollection;
		} else if (Object.class.equals(modelType)) {
			return convertSelectManyValues(context, uiSelectMany,
					Object[].class, newValues);
		} else {
			throw new FacesException(
					"Target model Type is no a Collection or Array");
		}

	}

	protected Object convertSelectManyValues(FacesContext context,
			UISelectMany uiSelectMany, Class arrayClass, String[] newValues)
			throws ConverterException {
		Object result;
		Converter converter;
		int len = (null != newValues ? newValues.length : 0);

		Class elementType = arrayClass.getComponentType();

		if (elementType.equals(String.class)) {
			return newValues;
		}

		try {
			result = Array.newInstance(elementType, len);
		} catch (Exception e) {
			throw new ConverterException(e);
		}

		if (null == newValues) {
			return result;
		}

		if (null == (converter = uiSelectMany.getConverter())) {

			if (null == (converter = getConverterForClass(elementType,
					context))) {

				if (elementType.equals(Object.class)) {
					return newValues;
				}
				StringBuffer valueStr = new StringBuffer();
				for (int i = 0; i < len; i++) {
					if (i == 0) {
						valueStr.append(newValues[i]);
					} else {
						valueStr.append(' ').append(newValues[i]);
					}
				}
				throw new ConverterException("There are no converters for "+valueStr);
			}
		}

		if (elementType.isPrimitive()) {
			for (int i = 0; i < len; i++) {
				if (elementType.equals(Boolean.TYPE)) {
					Array.setBoolean(result, i, ((Boolean) converter
							.getAsObject(context, uiSelectMany, newValues[i])));
				} else if (elementType.equals(Byte.TYPE)) {
					Array.setByte(result, i, ((Byte) converter.getAsObject(
							context, uiSelectMany, newValues[i])));
				} else if (elementType.equals(Double.TYPE)) {
					Array.setDouble(result, i, ((Double) converter.getAsObject(
							context, uiSelectMany, newValues[i])));
				} else if (elementType.equals(Float.TYPE)) {
					Array.setFloat(result, i, ((Float) converter.getAsObject(
							context, uiSelectMany, newValues[i])));
				} else if (elementType.equals(Integer.TYPE)) {
					Array.setInt(result, i, ((Integer) converter.getAsObject(
							context, uiSelectMany, newValues[i])));
				} else if (elementType.equals(Character.TYPE)) {
					Array.setChar(result, i, ((Character) converter
							.getAsObject(context, uiSelectMany, newValues[i])));
				} else if (elementType.equals(Short.TYPE)) {
					Array.setShort(result, i, ((Short) converter.getAsObject(
							context, uiSelectMany, newValues[i])));
				} else if (elementType.equals(Long.TYPE)) {
					Array.setLong(result, i, ((Long) converter.getAsObject(
							context, uiSelectMany, newValues[i])));
				}
			}
		} else {
			for (int i = 0; i < len; i++) {
				Array.set(result, i, converter.getAsObject(context,
						uiSelectMany, newValues[i]));
			}
		}
		return result;

	}

	protected boolean containsaValue(Object valueArray) {

		if (null != valueArray) {
			int len = Array.getLength(valueArray);
			for (int i = 0; i < len; i++) {
				Object value = Array.get(valueArray, i);
				if (value != null && !(value.equals(NO_VALUE))) {
					return true;
				}
			}
		}
		return false;

	}

	protected Object getCurrentSelectedValues(UIComponent component) {

		if (component instanceof UISelectMany) {
			UISelectMany select = (UISelectMany) component;
			Object value = select.getValue();
			if (value == null) {
				return null;
			} else if (value instanceof Collection) {
				return ((Collection) value).toArray();
			} else if (value.getClass().isArray()) {
				if (Array.getLength(value) == 0) {
					return null;
				}
			}
			return value;
		}

		UISelectOne select = (UISelectOne) component;
		Object val = select.getValue();
		if (val != null) {
			return new Object[] { val };
		}
		return null;

	}

	protected Object[] getSubmittedSelectedValues(UIComponent component) {

		if (component instanceof UISelectMany) {
			UISelectMany select = (UISelectMany) component;
			return (Object[]) select.getSubmittedValue();
		}

		UISelectOne select = (UISelectOne) component;
		Object val = select.getSubmittedValue();
		if (val != null) {
			return new Object[] { val };
		}
		return null;
	}

	protected boolean isSelected(FacesContext context, UIComponent component,
			Object itemValue, Object valueArray, Converter converter) {

		if (itemValue == null && valueArray == null) {
			return true;
		}
		if (null != valueArray) {
			if (!valueArray.getClass().isArray()) {
				return valueArray.equals(itemValue);
			}
			int len = Array.getLength(valueArray);
			for (int i = 0; i < len; i++) {
				Object value = Array.get(valueArray, i);
				if (value == null && itemValue == null) {
					return true;
				} else {
					if ((value == null) ^ (itemValue == null)) {
						continue;
					}
					Object compareValue;
					if (converter == null) {
						compareValue = coerceToModelType(context, itemValue,
								value.getClass());
					} else {
						compareValue = itemValue;
						if (compareValue instanceof String
								&& !(value instanceof String)) {
							compareValue = converter.getAsObject(context,
									component, (String) compareValue);
						}
					}

					if (value.equals(compareValue)) {
						return (true);
					}
				}
			}
		}
		return false;

	}

	protected Object coerceToModelType(FacesContext ctx, Object value,
			Class itemValueType) {

		Object newValue;
		try {
			ExpressionFactory ef = ctx.getApplication().getExpressionFactory();
			newValue = ef.coerceToType(value, itemValueType);
		} catch (ELException ele) {
			newValue = value;
		} catch (IllegalArgumentException iae) {
			newValue = value;
		}

		return newValue;

	}

	protected Collection createCollection(Collection collection,
			Class<? extends Collection> fallBackType) {

		Class<? extends Collection> lookupClass = ((collection != null) ? collection
				.getClass() : fallBackType);

		if (!lookupClass.isInterface()
				&& !Modifier.isAbstract(lookupClass.getModifiers())) {
			try {
				return lookupClass.newInstance();
			} catch (Exception e) {
				if (LOGGER.isLoggable(Level.SEVERE)) {
					LOGGER.log(Level.SEVERE,
							"Unable to create new Collection instance for type "
									+ lookupClass.getName(), e);
				}
			}
		}

		return null;

	}

	protected Collection cloneValue(Object value) {

		if (value instanceof Cloneable) {
			Method clone = lookupMethod(value.getClass(),
					"clone");
			if (clone != null) {
				try {
					Collection c = (Collection) clone.invoke(value);
					c.clear();
					return c;
				} catch (Exception e) {
					if (LOGGER.isLoggable(Level.SEVERE)) {
						LOGGER.log(Level.SEVERE,
								"Unable to clone collection type: {0}", value
										.getClass().getName());
						LOGGER.log(Level.SEVERE, e.toString(), e);
					}
				}
			}
		}
		return null;
	}

	private Method lookupMethod(Class<? extends Object> clazz, String methodName) {
		Method method;
		if((method=methodCache.get(clazz))!=null)
			return method;
		else{
			for(Method clazzMethod: clazz.getMethods()){
				if(clazzMethod.getName().equals(methodName)){
					methodCache.put(clazz, method);
					return method;
				}
			}
			return null;
		}
	}

	protected Collection bestGuess(Class<? extends Collection> type,
			int initialSize) {

		if (SortedSet.class.isAssignableFrom(type)) {
			return new TreeSet();
		} else if (Queue.class.isAssignableFrom(type)) {
			return new LinkedList();
		} else if (Set.class.isAssignableFrom(type)) {
			return new HashSet(initialSize);
		} else {
			return new ArrayList(initialSize);
		}

	}

	protected Collection createCollectionFromHint(Object collectionTypeHint) {

		Class<? extends Collection> collectionType;
		if (collectionTypeHint instanceof Class) {
			collectionType = (Class<? extends Collection>) collectionTypeHint;
		} else if (collectionTypeHint instanceof String) {
			try {
				collectionType = (Class<? extends Collection>) Class.forName((String) collectionTypeHint, true, Thread.currentThread().getContextClassLoader());
			} catch (ClassNotFoundException cnfe) {
				throw new FacesException(cnfe);
			}
		} else {
			throw new FacesException(
					"'collectionType' should resolve to type String or Class.  Found: "
							+ collectionTypeHint.getClass().getName());
		}

		Collection c = createCollection(null, collectionType);
		if (c == null) {
			throw new FacesException("Unable to create collection type "
					+ collectionType);
		}
		return c;

	}

	protected boolean isHideNoSelection(UIComponent component) {
		Object result = component.getAttributes().get("hideNoSelectionOption");
		return ((result != null) ? (Boolean) result : false);
	}
	
	public String get(String attribute, UIComponent component) {
		String attributeValue = ObjectExtractor
				.extractString(component.getValueExpression(attribute), FacesContext.getCurrentInstance()
						.getELContext());
		
		if (attributeValue == null)
			attributeValue = (String) component.getAttributes().get(attribute);
		
		return attributeValue;
	}
}
