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
package com.quakearts.webapp.facelets.bootstrap.renderkit;

import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;
import javax.faces.component.behavior.*;
import javax.faces.component.behavior.ClientBehaviorContext.Parameter;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.model.SelectItem;

import com.quakearts.webapp.facelets.bootstrap.handlers.HTML5DataRule;

import javax.el.ValueExpression;
import javax.faces.component.*;
import static com.quakearts.webapp.facelets.util.UtilityMethods.*;

public class RenderKitUtils {

    private static final String RETURN_FALSE = ";return false";
	private static final String ACTION = "action";
	private static final String CLICK = "click";
	private static final String XHTML_ATTR_PREFIX = "xml:";
    public static final String HTML_CONTENT_TYPE = "text/html";
    public static final String XHTML_CONTENT_TYPE = "application/xhtml+xml";
    public static final String APPLICATION_XML_CONTENT_TYPE = "application/xml";
    public static final String TEXT_XML_CONTENT_TYPE = "text/xml";
    public static final Logger LOGGER = Logger.getLogger("com.quakearts.bootstrap.logger");
    public static final Object NO_VALUE = "";

    private static final String[] BOOLEAN_ATTRIBUTES = {
          "disabled", "ismap", "readonly"
    };

    private static final String[] XHTML_PREFIX_ATTRIBUTES = {
          "lang"
    };

    private static final String ATTRIBUTES_THAT_ARE_SET_KEY =
        UIComponentBase.class.getName() + ".attributesThatAreSet";
    
    private RenderKitUtils() {
    }

	public static Iterator<SelectItem> getSelectItems(FacesContext context,
			UIComponent component) {

    	if(component == null || context==null){
    		throw new NullPointerException("Component and FacesContext cannot be null");
    	}
        return new SelectItemsIterator(context, component);
    }

	public static void renderPassThruAttributes(FacesContext context,
			ResponseWriter writer, UIComponent component, Attribute[] attributes)
    throws IOException {

        Map<String, List<ClientBehavior>> behaviors = null;

        if (component instanceof ClientBehaviorHolder) {
            behaviors = ((ClientBehaviorHolder)component).getClientBehaviors();
        }

        if ((null != behaviors) && 
            (behaviors.size() > 0) &&
            componentIsDisabled(component)) {
            behaviors = null;
        }

        renderPassThruAttributes(context, writer, component, attributes, behaviors);
    }

    @SuppressWarnings("unchecked")
	public static void renderPassThruAttributes(FacesContext context,
			ResponseWriter writer, UIComponent component,
			Attribute[] attributes, Map<String, List<ClientBehavior>> behaviors)
			throws IOException {

        if (behaviors == null) {
            behaviors = Collections.emptyMap();
        }

        if ((behaviors.size() < 2)) {
            List<String> setAttributes = (List<String>)
              component.getAttributes().get(ATTRIBUTES_THAT_ARE_SET_KEY);
            if (setAttributes != null) {
				renderPassThruAttributesOptimized(context, writer, component,
						attributes, setAttributes, behaviors);
			}
        } else {
			renderPassThruAttributesUnoptimized(context, writer, component,
					attributes, behaviors);
        }        
    }

    @SuppressWarnings("rawtypes")
	public static void renderOnchange(FacesContext context, UIComponent component, String sourceId)
        throws IOException {
    	
    	final String handlerName = "onchange";
        final Object userHandler = component.getAttributes().get(handlerName);
        String behaviorEventName = "valueChange";
        if (component instanceof ClientBehaviorHolder) {
            Map behaviors = ((ClientBehaviorHolder)component).getClientBehaviors();
            if (null != behaviors && behaviors.containsKey("change")) {
                behaviorEventName = "change";
            }
        }

		renderHandler(context, component, Collections.<Parameter> emptyList(), handlerName, userHandler,
				behaviorEventName, sourceId);
    }

    @SuppressWarnings("rawtypes")
	public static void renderSelectOnclick(FacesContext context, UIComponent component)
        throws IOException {

        final String handlerName = "onclick";
        final Object userHandler = component.getAttributes().get(handlerName);
        String behaviorEventName = "valueChange";
        if (component instanceof ClientBehaviorHolder) {
            Map behaviors = ((ClientBehaviorHolder)component).getClientBehaviors();
            if (null != behaviors && behaviors.containsKey(CLICK)) {
                behaviorEventName = CLICK;
            }
        }

		renderHandler(context, component, Collections.<Parameter> emptyList(), handlerName, userHandler,
				behaviorEventName, null);
    }

    @SuppressWarnings("rawtypes")
	public static void renderOnclick(FacesContext context, UIComponent component, 
			Collection<ClientBehaviorContext.Parameter> params) throws IOException {

        final String handlerName = "onclick";
        final Object userHandler = component.getAttributes().get(handlerName);
        String behaviorEventName = ACTION;
        if (component instanceof ClientBehaviorHolder) {
            Map behaviors = ((ClientBehaviorHolder)component).getClientBehaviors();
            if (null != behaviors && behaviors.containsKey(CLICK)) {
                behaviorEventName = CLICK;
            }
        }

		renderHandler(context, component, params, handlerName, userHandler,
				behaviorEventName, null);
    }

    @SuppressWarnings("unchecked")
	public static void renderHTML5DataAttributes(FacesContext context, UIComponent component) throws IOException{
		List<String> html5PassThru = (List<String>) component.getAttributes().get(HTML5DataRule.HTML5DATAATTRIBUTES);
		if(html5PassThru!=null){
			for(String attribute:html5PassThru){
				String value = null;
				Object attributeValue = component.getAttributes().get(attribute);
				if(attributeValue instanceof ValueExpression){
					Object valueObject = ((ValueExpression)attributeValue).getValue(context.getELContext());
					value = valueObject!=null?valueObject.toString():null;
				} else if(attributeValue!=null){
					value = attributeValue.toString();
				}
				
				if(value!=null){
					context.getResponseWriter().writeAttribute(attribute, value, null);
				}
			}
		}

    }

    
    public static String prefixAttribute(final String attrName,
                                         boolean isXhtml) {
        if (isXhtml) {
            if (Arrays.binarySearch(XHTML_PREFIX_ATTRIBUTES, attrName) > -1) {
                return XHTML_ATTR_PREFIX + attrName;
            } else {
                return attrName;
            }
        } else {
            return attrName;
        }

    }

    @SuppressWarnings("rawtypes")
	public static void renderXHTMLStyleBooleanAttributes(ResponseWriter writer, UIComponent component)
          throws IOException {

        Map attrMap = component.getAttributes();
        for (String attrName : BOOLEAN_ATTRIBUTES) {
            Object val = attrMap.get(attrName);
            if (val == null) {
                continue;
            }

            if (Boolean.valueOf(val.toString())) {
				writer.writeAttribute(attrName, true, attrName);
            }
        }

    }
    
	public static String getFormClientId(UIComponent component,
			FacesContext context) {
	
	    UIComponent parent = component.getParent();
	    while (parent != null) {
	        if (parent instanceof UIForm) {
	            break;
	        }
	        parent = parent.getParent();
	    }
	    
	    UIForm form = (UIForm) parent;
	    if (form != null) {
	        return form.getClientId(context);
	    }
	
	    return null;
	}

	public static void appendProperty(StringBuilder builder, String name,
			Object value) {
	    appendProperty(builder, name, value, true);
	}

	public static void appendProperty(StringBuilder builder, String name,
			Object value, boolean quoteValue) {
	
	    if ((null == name) || (name.length() == 0))
	        throw new IllegalArgumentException();
	
	
	    char lastChar = builder.charAt(builder.length() - 1);
	    if ((lastChar != ',') && (lastChar != '{'))
	        builder.append(',');
	
	    appendQuotedValue(builder, name);
	    builder.append(":");
	
	    if (value == null) {
	        builder.append("''");
	    } else if (quoteValue) {
	        appendQuotedValue(builder, value.toString());
	    } else {
	        builder.append(value.toString());
	    }
	}

	public static<T extends ClientBehavior> T findClientBehavior(Class<T> behaviorClass, String eventName, UIComponent component){
		if(!(component instanceof ClientBehaviorHolder))
			return null;
		
		T foundBehavior = null;
		
		Map<String, List<ClientBehavior>> behaviorsMap = ((ClientBehaviorHolder)component).getClientBehaviors();
		if(behaviorsMap!=null && behaviorsMap.size()>0){
			List<ClientBehavior> behaviors = behaviorsMap.get(eventName);
			if(behaviors!=null)
				for(ClientBehavior behavior:behaviors){
					if(behaviorClass.isInstance(behavior)){
						foundBehavior = behaviorClass.cast(behavior);
						break;
					}	
				}
		}
		
		return foundBehavior;
	}

	
	private static void renderPassThruAttributesOptimized(FacesContext context,
			ResponseWriter writer, UIComponent component,
			Attribute[] knownAttributes, List<String> setAttributes,
			Map<String, List<ClientBehavior>> behaviors) throws IOException {

        String behaviorEventName = getSingleBehaviorEventName(behaviors);
        boolean renderedBehavior = false;

        Collections.sort(setAttributes);
        boolean isXhtml =
              XHTML_CONTENT_TYPE.equals(writer.getContentType());
        Map<String, Object> attrMap = component.getAttributes();
        for (String name : setAttributes) {

            int index = Arrays.binarySearch(knownAttributes, Attribute.attr(name));
            if (index >= 0) {
                Object value =
                      attrMap.get(name);
                if (value != null && shouldRenderAttribute(value)) {

                    Attribute attr = knownAttributes[index];

                    if (isBehaviorEventAttribute(attr, behaviorEventName)) {
						renderHandler(context, component, null, name, value,
								behaviorEventName, null);

                        renderedBehavior = true;
                    } else {
						writer.writeAttribute(prefixAttribute(name, isXhtml),
								value, name);
                    }
                }
            }
        }

        if ((behaviorEventName != null) && !renderedBehavior) {

            for (int i = 0; i < knownAttributes.length; i++) {
                Attribute attr = knownAttributes[i];
                String[] events = attr.getEvents();
                if ((events != null) &&
                    (events.length > 0) &&
                    (behaviorEventName.equals(events[0]))) {

					renderHandler(context, component, null, attr.getName(),
							null, behaviorEventName, null);
                }
            }
 

        }
    }

	private static void renderPassThruAttributesUnoptimized(
			FacesContext context, ResponseWriter writer, UIComponent component,
			Attribute[] knownAttributes,
			Map<String, List<ClientBehavior>> behaviors) throws IOException {

        boolean isXhtml = XHTML_CONTENT_TYPE.equals(writer.getContentType());

        Map<String, Object> attrMap = component.getAttributes();

        for (Attribute attribute : knownAttributes) {
            String attrName = attribute.getName();
            String[] events = attribute.getEvents();
            boolean hasBehavior = ((events != null) &&
                                   (events.length > 0) &&
                                   (behaviors.containsKey(events[0])));

            Object value = attrMap.get(attrName);

            if (value != null && shouldRenderAttribute(value) && !hasBehavior) {
				writer.writeAttribute(prefixAttribute(attrName, isXhtml),
						value, attrName);
            } else if (hasBehavior) {

				renderHandler(context, component, null, attrName, value,
						events[0], null);
            }
        }
    }

    private static boolean shouldRenderAttribute(Object attributeVal) {

        if (attributeVal instanceof String) {
            return true;
        } else if (attributeVal instanceof Boolean &&
            Boolean.FALSE.equals(attributeVal)) {
            return false;
        } else if (attributeVal instanceof Integer &&
                   (Integer) attributeVal == Integer.MIN_VALUE) {
            return false;
        } else if (attributeVal instanceof Double &&
                   (Double) attributeVal == Double.MIN_VALUE) {
            return false;
        } else if (attributeVal instanceof Character &&
                   (Character) attributeVal == Character.MIN_VALUE) {
            return false;
        } else if (attributeVal instanceof Float &&
                   (Float) attributeVal == Float.MIN_VALUE) {
            return false;
        } else if (attributeVal instanceof Short &&
                   (Short) attributeVal == Short.MIN_VALUE) {
            return false;
        } else if (attributeVal instanceof Byte &&
                   (Byte) attributeVal == Byte.MIN_VALUE) {
            return false;
        } else if (attributeVal instanceof Long &&
                   (Long) attributeVal == Long.MIN_VALUE) {
            return false;
        }
        return true;

    }

    private static void appendScriptToChain(StringBuilder builder, 
                                               String script) {

        if ((script == null) || (script.length() == 0)) {
            return;
        }

        if (builder.charAt(builder.length() - 1) != ',')
            builder.append(',');

        appendQuotedValue(builder, script);
    }

    private static void appendQuotedValue(StringBuilder builder, 
                                          String script) {

        builder.append("'");

        int length = script.length();

        for (int i = 0; i < length; i++) {
            char c = script.charAt(i);

            if (c == '\'' || c == '\\') {
                builder.append('\\');
            } 

            builder.append(c);
        }

        builder.append("'");
    }

	private static boolean appendBehaviorsToChain(StringBuilder builder,
			FacesContext context, UIComponent component,
			List<ClientBehavior> behaviors, String behaviorEventName, 
			String sourceId, Collection<ClientBehaviorContext.Parameter> params) {

        if ((behaviors == null) || (behaviors.isEmpty())) {
            return false;
        }

		ClientBehaviorContext bContext = createClientBehaviorContext(context,
				component, behaviorEventName, sourceId, params);

        boolean submitting = false;

        for (ClientBehavior behavior : behaviors) {
            String script = behavior.getScript(bContext);
            if ((script != null) && (script.length() > 0)) {
                appendScriptToChain(builder, script);

                if (isSubmitting(behavior)) {
                    submitting = true;
               }
            }
        }

        return submitting;
    }

    private static String getSingleBehaviorEventName(Map<String, List<ClientBehavior>> behaviors) {
        assert(behaviors != null);

        int size = behaviors.size();
        if (size == 0) {
            return null;
        }

        assert(size == 1);

        Iterator<String> keys = behaviors.keySet().iterator();
        assert(keys.hasNext());

        return keys.next();
    }

    private static boolean isBehaviorEventAttribute(Attribute attr,
                                                    String behaviorEventName) {

      String[] events = attr.getEvents();

      return ((behaviorEventName != null) &&
              (events != null) &&
              (events.length > 0) &&
              (behaviorEventName.equals(events[0])));
    }

    private static String getNonEmptyUserHandler(Object handlerObject) {

        String handler = null;

        if (null != handlerObject) {
            handler = handlerObject.toString();
            handler = handler.trim();

            if (handler.length() == 0)
                handler = null;
        }

        return handler;
    }

    private static List<ClientBehavior> getClientBehaviors(UIComponent component,
                                               String behaviorEventName) {

        if (component instanceof ClientBehaviorHolder) {
            ClientBehaviorHolder bHolder = (ClientBehaviorHolder)component;
            Map <String, List <ClientBehavior>> behaviors = bHolder.getClientBehaviors();
            if (null != behaviors && behaviors.containsKey(behaviorEventName)) {
                return behaviors.get(behaviorEventName);
            }
        }

        return Collections.emptyList();
    }

	private static String getSubmitHandler(FacesContext context,
			UIComponent component, Collection<ClientBehaviorContext.Parameter> params, 
			boolean preventDefault) {

        StringBuilder builder = new StringBuilder(256);

        String formClientId = getFormClientId(component, context);
        String componentClientId = component.getClientId(context);

        builder.append("qab.sf(document.getElementById('");
        builder.append(formClientId);
        builder.append("'),{");

        appendProperty(builder, componentClientId, componentClientId);

        if ((null != params) && (!params.isEmpty())) {
            for (ClientBehaviorContext.Parameter param : params) {
                appendProperty(builder, param.getName(), param.getValue());
            }
        }

        builder.append("})");

        if (preventDefault) {
            builder.append(RETURN_FALSE);
        }

        return builder.toString();
    }

	private static String getChainedHandler(FacesContext context,
			UIComponent component, List<ClientBehavior> behaviors,
			Collection<ClientBehaviorContext.Parameter> params,
			String behaviorEventName, String sourcId, String userHandler) {

        StringBuilder builder = new StringBuilder(100);
        builder.append("jsf.util.chain(this,event,");

        appendScriptToChain(builder, userHandler);

		boolean submitting = appendBehaviorsToChain(builder, context,
				component, behaviors, behaviorEventName, sourcId, params);    

		boolean hasParams = ((null != params) && !params.isEmpty());

        if (!submitting && hasParams) {
			String submitHandler = getSubmitHandler(context, component, params, false);

            appendScriptToChain(builder, submitHandler);

            submitting = true;
        }

        builder.append(")");

        if (submitting &&
                (ACTION.equals(behaviorEventName) ||
                 CLICK.equals(behaviorEventName))) {
            builder.append(RETURN_FALSE);
        }

        return builder.toString();
    }

	private static String getSingleBehaviorHandler(FacesContext context,
			UIComponent component, ClientBehavior behavior,
			Collection<ClientBehaviorContext.Parameter> params,
			String behaviorEventName, String sourceId) {

		ClientBehaviorContext bContext = createClientBehaviorContext(context,
				component, behaviorEventName, sourceId, params);

        String script = behavior.getScript(bContext);

        boolean preventDefault = ((isSubmitting(behavior)) &&
                                  (ACTION.equals(behaviorEventName) || CLICK.equals(behaviorEventName)));

        if (preventDefault) {
             script = script +  RETURN_FALSE;
         }

         return script;
    }

	private static ClientBehaviorContext createClientBehaviorContext(
			FacesContext context, UIComponent component,
			String behaviorEventName, String sourceId,
			Collection<ClientBehaviorContext.Parameter> params) {

		return ClientBehaviorContext.createClientBehaviorContext(context,
				component, behaviorEventName, sourceId, params);
    }

    private static boolean isSubmitting(ClientBehavior behavior) {
        return behavior.getHints().contains(ClientBehaviorHint.SUBMITTING);
    }

	private static void renderHandler(FacesContext context,
			UIComponent component,
			Collection<ClientBehaviorContext.Parameter> params,
			String handlerName, Object handlerValue, String behaviorEventName,
			String sourceId)
        throws IOException {

        ResponseWriter writer = context.getResponseWriter();
        String userHandler = getNonEmptyUserHandler(handlerValue);
        List<ClientBehavior> behaviors = getClientBehaviors(component, behaviorEventName);

        if (behaviors.isEmpty() && 
             componentIsDisabled(component)) {
            return;
        }

        if (params == null) {
            params = Collections.emptyList();
        }
        String handler = null;
        switch (getHandlerType(behaviors, params, userHandler)) {
        
            case USER_HANDLER_ONLY:
                handler = userHandler;
                break;

            case SINGLE_BEHAVIOR_ONLY:
			handler = getSingleBehaviorHandler(context, component,
					behaviors.get(0), params, behaviorEventName, sourceId);
               break;

            case SUBMIT_ONLY:
			handler = getSubmitHandler(context, component, params, true);
               break;

            case CHAIN:
			handler = getChainedHandler(context, component, behaviors, params,
					behaviorEventName, sourceId, userHandler);
                break;
            default:
                assert(false);
        }

        writer.writeAttribute(handlerName, handler, null);
    }

	private static HandlerType getHandlerType(List<ClientBehavior> behaviors,
			Collection<ClientBehaviorContext.Parameter> params,
			String userHandler) {

        if ((behaviors == null) || (behaviors.isEmpty())) {

            if (params.isEmpty())
                return HandlerType.USER_HANDLER_ONLY;

            return (userHandler == null) ? HandlerType.SUBMIT_ONLY:HandlerType.CHAIN;
        }

        if ((behaviors.size() == 1) && (userHandler == null)) {
            ClientBehavior behavior = behaviors.get(0);

            if (isSubmitting(behavior) || params.isEmpty())
                return HandlerType.SINGLE_BEHAVIOR_ONLY;            
        }

        return HandlerType.CHAIN;
    }

    private enum HandlerType {
        USER_HANDLER_ONLY,
        SINGLE_BEHAVIOR_ONLY,
        SUBMIT_ONLY,
        CHAIN
    }
    
    public static String findClientIds(String ids, UIComponent component, FacesContext context) throws IOException{
    	if(ids==null)
    		return null;
    	
    	StringBuilder clientIds = new StringBuilder();
    	String[] idlist = ids.split("[\\s]");
    	for(String id:idlist){
    		if(!id.startsWith("@")){	
	    		UIComponent found = component.findComponent(id);
	    		if(found!=null){
	    			if(clientIds.length()>0)
	    				clientIds.append(" ");
	    			clientIds.append(found.getClientId(context));
	    		} else {
	    			throw new IOException("Cannot find id "+id+" within components NamingContainer");
	    		}
    		}
    	}
    	
    	return clientIds.toString();
    }
    
}
