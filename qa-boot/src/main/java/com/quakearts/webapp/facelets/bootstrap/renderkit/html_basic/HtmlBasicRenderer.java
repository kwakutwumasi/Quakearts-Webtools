/*******************************************************************************
 * Copyright (C) 2017 Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com>.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com> - initial API and implementation
 ******************************************************************************/
package com.quakearts.webapp.facelets.bootstrap.renderkit.html_basic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import javax.faces.component.NamingContainer;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.component.UIParameter;
import javax.faces.component.UIViewRoot;
import javax.faces.component.ValueHolder;
import javax.faces.component.behavior.ClientBehaviorContext;
import javax.faces.component.behavior.ClientBehavior;
import javax.faces.component.behavior.ClientBehaviorHolder;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.render.Renderer;

import static com.quakearts.webapp.facelets.util.UtilityMethods.*;
import static com.quakearts.webapp.facelets.bootstrap.renderkit.RenderKitUtils.*;

public abstract class HtmlBasicRenderer extends Renderer {   

    protected static final Param[] EMPTY_PARAMS = new Param[0];

    public HtmlBasicRenderer() {

        super();

    }

    @Override
    public String convertClientId(FacesContext context, String clientId) {
        return clientId;
    }


    @Override
    public void decode(FacesContext context, UIComponent component) {
       if (!shouldDecode(component)) {
            return;
        }

        String clientId = decodeBehaviors(context, component);

        if (!(component instanceof UIInput)) {
            if (LOGGER.isLoggable(Level.FINE)) {
                LOGGER.log(Level.FINE,
                           "No decoding necessary since the component {0} is not an instance or a sub class of UIInput",
                           component.getId());
            }
            return;
        }

        if (clientId == null) {
            clientId = component.getClientId(context);
        }

        assert(clientId != null);
        Map<String, String> requestMap =
              context.getExternalContext().getRequestParameterMap();
        String newValue = requestMap.get(clientId);
        if (newValue != null) {
            setSubmittedValue(component, newValue);
            if (LOGGER.isLoggable(Level.FINE)) {
                LOGGER.log(Level.FINE,
                           "new value after decoding {0}",
                           newValue);
            }
        }

    }


    @Override
    public void encodeEnd(FacesContext context, UIComponent component)
          throws IOException {

    	if (!shouldEncode(component)) {
            return;
        }

        String currentValue = getCurrentValue(context, component);
        if (LOGGER.isLoggable(Level.FINE)) {
            LOGGER.log(Level.FINE,
                       "Value to be buffer {0}",
                       currentValue);
        }
        getEndTextToRender(context, component, currentValue);

    }


    @Override
    public boolean getRendersChildren() {

        return true;

    }

	protected final String decodeBehaviors(FacesContext context,
			UIComponent component) {

        if (!(component instanceof ClientBehaviorHolder)) {
            return null;
        }

        ClientBehaviorHolder holder = (ClientBehaviorHolder)component;
        Map<String, List<ClientBehavior>> behaviors = holder.getClientBehaviors();
        if (behaviors.isEmpty()) {
            return null;
        }

        ExternalContext external = context.getExternalContext();
        Map<String, String> params = external.getRequestParameterMap();
        String behaviorEvent = params.get("javax.faces.behavior.event");

        if (null != behaviorEvent) {
            List<ClientBehavior> behaviorsForEvent = behaviors.get(behaviorEvent);

            if (behaviors.size() > 0) {
               String behaviorSource = params.get("javax.faces.source");
               String clientId = component.getClientId();
               if (isBehaviorSource(context, behaviorSource, clientId, component)) {
                   for (ClientBehavior behavior: behaviorsForEvent) {
                       behavior.decode(context, component);
                   }
               }

               return clientId;
            }
        }

        return null;
    }

	protected boolean isBehaviorSource(FacesContext ctx,
			String behaviorSourceId, String componentClientId, UIComponent component) {

        return (behaviorSourceId != null 
        		&&(behaviorSourceId.equals(componentClientId)
        		||(behaviorSourceId.startsWith(componentClientId+"_sub"))));

    }

	protected String augmentIdReference(String forValue,
			UIComponent fromComponent) {

        int forSuffix = forValue.lastIndexOf(UIViewRoot.UNIQUE_ID_PREFIX);
        if (forSuffix <= 0) {
            // if the for-value doesn't already have a suffix present
            String id = fromComponent.getId();
            if (id != null) {
                int idSuffix = id.lastIndexOf(UIViewRoot.UNIQUE_ID_PREFIX);
                if (idSuffix > 0) {
                    // but the component's own id does have a suffix
                    if (LOGGER.isLoggable(Level.FINE)) {
                        LOGGER.fine("Augmenting for attribute with " +
                                    id.substring(idSuffix) +
                                    " suffix from Id attribute");
                    }
                    forValue += id.substring(idSuffix);
                }
            }
        }
        return forValue;

    }

   protected void encodeRecursive(FacesContext context, UIComponent component)
          throws IOException {

        if (!component.isRendered()) {
            return;
        }

        component.encodeBegin(context);
        if (component.getRendersChildren()) {
            component.encodeChildren(context);
        } else {
            Iterator<UIComponent> kids = getChildren(component);
            while (kids.hasNext()) {
                UIComponent kid = kids.next();
                encodeRecursive(context, kid);
            }
        }
        component.encodeEnd(context);
    }

    protected Iterator<UIComponent> getChildren(UIComponent component) {

        int childCount = component.getChildCount();
        if (childCount > 0) {
            return component.getChildren().iterator();
        } else {
            return Collections.<UIComponent>emptyList().iterator();
        }

    }

	protected String getCurrentValue(FacesContext context, UIComponent component) {

        if (component instanceof UIInput) {
            Object submittedValue = ((UIInput) component).getSubmittedValue();
            if (submittedValue != null) {
                return submittedValue.toString();
            }
        }

        String currentValue = null;
        Object currentObj = getValue(component);
        if (currentObj != null) {
            currentValue = getFormattedValue(context, component, currentObj);
        }
        return currentValue;

    }

	protected void getEndTextToRender(FacesContext context,
			UIComponent component, String currentValue) throws IOException {

	}

    protected UIComponent getFacet(UIComponent component, String name) {

        UIComponent facet = null;
        if (component.getFacetCount() > 0) {
            facet = component.getFacet(name);
            if ((facet != null) && !facet.isRendered()) {
                facet = null;
            }
        }
        return (facet);

    }

	protected UIComponent getForComponent(FacesContext context,
			String forComponent, UIComponent component) {

        if (null == forComponent || forComponent.length() == 0) {
            return null;
        }

        UIComponent result = null;
        UIComponent currentParent = component;
        try {
            while (currentParent != null) {
                 result = currentParent.findComponent(forComponent);
                if (result != null) {
                    break;
                }
                currentParent = currentParent.getParent();
            }

            if (result == null) {
                result =
                      findUIComponentBelow(context.getViewRoot(), forComponent);
            }
        } catch (Exception e) {
        }
        if (result == null) {
            if (LOGGER.isLoggable(Level.WARNING)) {
                LOGGER.warning("Component with id "+forComponent+" not found in current context.");
            }
        }
        return result;
    }

	@SuppressWarnings("rawtypes")
	protected String getFormattedValue(FacesContext context,
			UIComponent component, Object currentValue, Converter converter)
          throws ConverterException {

        if (!(component instanceof ValueHolder)) {
            if (currentValue != null) {
                return currentValue.toString();
            }
            return null;
        }

        if (converter == null) {
            converter = ((ValueHolder) component).getConverter();
        }

        if (converter == null) {
            if(currentValue == null) {
        	return "";
            }
            if (currentValue instanceof String) {
                return (String) currentValue;
            }
            Class converterType = currentValue.getClass();
            converter = getConverterForClass(converterType, context);

            if (converter == null) {
                return currentValue.toString();
            }
        }

        return converter.getAsString(context, component, currentValue);
    }

	protected String getFormattedValue(FacesContext context,
			UIComponent component, Object currentValue)
          throws ConverterException {
        return getFormattedValue(context, component, currentValue, null);
    }


	@SuppressWarnings("rawtypes")
	protected Iterator getMessageIter(FacesContext context,
			String forComponent, UIComponent component) {

        Iterator messageIter;
        if (null != forComponent) {
            if (forComponent.length() == 0) {
                messageIter = context.getMessages(null);
            } else {
                UIComponent result = getForComponent(context, forComponent,
                                                     component);
                if (result == null) {
                    messageIter = Collections.EMPTY_LIST.iterator();
                } else {
                    messageIter =
                          context.getMessages(result.getClientId(context));
                }
            }
        } else {
            messageIter = context.getMessages();
        }
        return messageIter;

    }

    protected Param[] getParamList(UIComponent command) {

        if (command.getChildCount() > 0) {
            ArrayList<Param> parameterList = new ArrayList<Param>();

            for (UIComponent kid : command.getChildren()) {
                if (kid instanceof UIParameter) {
                    UIParameter uiParam = (UIParameter) kid;
                    if (!uiParam.isDisable()) {
                        Object value = uiParam.getValue();
                        Param param = new Param(uiParam.getName(),
                                                (value == null ? null :
                                                 value.toString()));
                        parameterList.add(param);
                    }
                }
            }
            return parameterList.toArray(new Param[parameterList.size()]);
        } else {
            return EMPTY_PARAMS;
        }


    }

	protected Collection<ClientBehaviorContext.Parameter> getBehaviorParameters(UIComponent command) {

		ArrayList<ClientBehaviorContext.Parameter> params = null;
		int childCount = command.getChildCount();
		if (childCount > 0) {

			for (UIComponent kid : command.getChildren()) {
				if (kid instanceof UIParameter) {
					UIParameter uiParam = (UIParameter) kid;
					String name = uiParam.getName();
					Object value = uiParam.getValue();

					if ((name != null) && (name.length() > 0)) {

						if (params == null) {
							params = new ArrayList<ClientBehaviorContext.Parameter>(childCount);
						}

						params.add(new ClientBehaviorContext.Parameter(name, value));
					}
				}
			}
		}
		return (params == null) ? Collections.<ClientBehaviorContext.Parameter> emptyList() : params;
	}

    protected Object getValue(UIComponent component) {

        if (component instanceof ValueHolder) {
            Object value = ((ValueHolder) component).getValue();
            if (LOGGER.isLoggable(Level.FINE)) {
                LOGGER.fine("component.getValue() returned " + value);
            }
            return value;
        }
        return null;
    }

    protected void setSubmittedValue(UIComponent component, Object value) {
    }


    protected boolean shouldWriteIdAttribute(UIComponent component) {

        String id;
        return (null != (id = component.getId()) &&
                    (!id.startsWith(UIViewRoot.UNIQUE_ID_PREFIX) ||
                        ((component instanceof ClientBehaviorHolder) &&
                          !((ClientBehaviorHolder)component).getClientBehaviors().isEmpty())));
    }


	protected String writeIdAttributeIfNecessary(FacesContext context,
			ResponseWriter writer, UIComponent component) {
        String id = null;
        if (shouldWriteIdAttribute(component)) {
            try {
                writer.writeAttribute("id", id = component.getClientId(context),
                                      "id");
            } catch (IOException e) {
                if (LOGGER.isLoggable(Level.WARNING)) {
                    LOGGER.warning("Exception of type " + e.getClass().getName()
							+ " was thrown. Message is " + e.getMessage()
							+ ". Exception occured whiles writing id attribute");
                }
            }
        }
        return id;

    }

    protected boolean shouldEncode(UIComponent component) {

        if (!component.isRendered()) {
            if (LOGGER.isLoggable(Level.FINE)) {
                LOGGER.log(Level.FINE,
                           "End encoding component {0} since buffer attribute is set to false",
                           component.getId());
            }
            return false;
        }
        return true;

    }


    protected boolean shouldDecode(UIComponent component) {

        if (componentIsDisabledOrReadonly(component)) {
            if (LOGGER.isLoggable(Level.FINE)) {
                LOGGER.log(Level.FINE,
                           "No decoding necessary since the component {0} is disabled or read-only",
                           component.getId());
            }
            return false;
        }
        return true;

    }

    protected boolean shouldEncodeChildren(UIComponent component) {

        if (!component.isRendered()) {
            if (LOGGER.isLoggable(Level.FINE)) {
                LOGGER.log(Level.FINE,
                            "Children of component {0} will not be encoded since this component's buffer attribute is false",
                            component.getId());
            }
            return false;
        }
        return true;

    }

	protected static Map<String, List<ClientBehavior>> getPassThruBehaviors(
			UIComponent component, String domEventName,
			String componentEventName) {

        if (!(component instanceof ClientBehaviorHolder)) {
            return null;
        }

        Map<String, List<ClientBehavior>> behaviors = ((ClientBehaviorHolder)component).getClientBehaviors();

        int size = behaviors.size();

        if ((size == 1) || (size == 2)) {
            boolean hasDomBehavior = behaviors.containsKey(domEventName);
            boolean hasComponentBehavior = behaviors.containsKey(componentEventName);

             if (((size == 1) && (hasDomBehavior || hasComponentBehavior)) ||
                ((size == 2) && hasDomBehavior && hasComponentBehavior)) {
                return null;
            }
        }

        return behaviors;
    }

	private static UIComponent findUIComponentBelow(UIComponent startPoint,
			String forComponent) {

        UIComponent retComp = null;
        if (startPoint.getChildCount() > 0) {
            List<UIComponent> children = startPoint.getChildren();
            for (int i = 0, size = children.size(); i < size; i++) {
                UIComponent comp = children.get(i);

                if (comp instanceof NamingContainer) {
                    try {
                        retComp = comp.findComponent(forComponent);
                    } catch (IllegalArgumentException iae) {
                        continue;
                    }
                }

                if (retComp == null) {
                    if (comp.getChildCount() > 0) {
                        retComp = findUIComponentBelow(comp, forComponent);
                    }
                }

                if (retComp != null) {
                    break;
                }
            }
        }
        return retComp;

    }

    public static class Param {


        public String name;
        public String value;

        public Param(String name, String value) {

            this.name = name;
            this.value = value;

        }

    }

    public static class OptionComponentInfo {

        String disabledClass;
        String enabledClass;
        String selectedClass;
        String unselectedClass;
        boolean disabled;
        boolean hideNoSelection;
        String id;

		public OptionComponentInfo(String disabledClass, String enabledClass,
				boolean disabled, boolean hideNoSelection, String id) {

			this(disabledClass, enabledClass, null, null, disabled,
					hideNoSelection, id);

		}

		public OptionComponentInfo(String disabledClass, String enabledClass,
				String unselectedClass, String selectedClass, boolean disabled,
				boolean hideNoSelection, String id) {

			this.disabledClass = disabledClass;
			this.enabledClass = enabledClass;
			this.unselectedClass = unselectedClass;
			this.selectedClass = selectedClass;
			this.disabled = disabled;
			this.hideNoSelection = hideNoSelection;
			this.id = id;
		}

        public String getDisabledClass() {
            return disabledClass;
        }

        public String getEnabledClass() {
            return enabledClass;
        }

        public boolean isDisabled() {
            return disabled;
        }

        public boolean isHideNoSelection() {
            return hideNoSelection;
        }

        public String getSelectedClass() {
            return selectedClass;
        }

        public String getUnselectedClass() {
            return unselectedClass;
        }
        
        public String getId() {
			return id;
		}
    }
 } 
