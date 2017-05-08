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
package com.quakearts.webapp.facelets.bootstrap.renderkit.html_basic;

import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import javax.el.ValueExpression;
import javax.faces.application.Application;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.component.ValueHolder;
import javax.faces.component.behavior.ClientBehavior;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import static com.quakearts.webapp.facelets.bootstrap.renderkit.RenderKitUtils.*;

/**
 * <B>HtmlBasicInputRenderer</B> is a base class for implementing renderers
 * that support UIInput type components
 */

public abstract class HtmlBasicInputRenderer extends HtmlBasicRenderer {


    private boolean hasStringConverter = false;

    private boolean hasStringConverterSet = false;

    // ---------------------------------------------------------- Public Methods


    @SuppressWarnings("rawtypes")
	@Override
    public Object getConvertedValue(FacesContext context, UIComponent component,
                                    Object submittedValue)
          throws ConverterException {

        String newValue = (String) submittedValue;
        // if we have no local value, try to get the valueExpression.
        ValueExpression valueExpression = component.getValueExpression("value");
        Converter converter = null;

        // If there is a converter attribute, use it to to ask application
        // instance for a converter with this identifer.
        if (component instanceof ValueHolder) {
            converter = ((ValueHolder) component).getConverter();
        }

        if (null == converter && null != valueExpression) {
            Class converterType = valueExpression.getType(context.getELContext());
            // if converterType is null, assume the modelType is "String".
            if (converterType == null ||
                converterType == Object.class) {
                if (LOGGER.isLoggable(Level.FINE)) {
                    LOGGER.log(Level.FINE,
                               "No conversion necessary for value {0} of component {1}",
                               new Object[]{
                                     submittedValue,
                                     component.getId() });
                }
                return newValue;
            }

            // If the converterType is a String, and we don't have a 
            // converter-for-class for java.lang.String, assume the type is 
            // "String".
            if (converterType == String.class && !hasStringConverter(context)) {
                if (LOGGER.isLoggable(Level.FINE)) {
                    LOGGER.log(Level.FINE,
                               "No conversion necessary for value {0} of component {1}",
                               new Object[]{
                                     submittedValue,
                                     component.getId()});
                }
                return newValue;
            }
            // if getType returns a type for which we support a default
            // conversion, acquire an appropriate converter instance.

            try {
                Application application = context.getApplication();
                converter = application.createConverter(converterType);
                if (LOGGER.isLoggable(Level.FINE)) {
                    LOGGER.log(Level.FINE,
                               "Created converter ({0}) for type {1} for component {2}.",
                               new Object[] {
                                     converter.getClass().getName(),
                                     converterType.getClass().getName(),
                                     component.getId() });
                }
            } catch (Exception e) {
                if (LOGGER.isLoggable(Level.SEVERE)) {
                    LOGGER.log(Level.SEVERE,
                               "Could not instantiate converter for type {0}: {1}",
                               new Object[] {
                                     converterType,
                                     e.toString() });
                    LOGGER.log(Level.SEVERE, "", e);
                }
                return (null);
            }
        } else if (converter == null) {
            // if there is no valueExpression and converter attribute set,
            // assume the modelType as "String" since we have no way of
            // figuring out the type. So for the selectOne and
            // selectMany, converter has to be set if there is no
            // valueExpression attribute set on the component.
            if (LOGGER.isLoggable(Level.FINE)) {
                LOGGER.log(Level.FINE,
                            "No conversion necessary for value {0} of component {1}",
                            new Object[] {
                                  submittedValue,
                                  component.getId() });
                LOGGER.fine(" since there is no explicitly registered converter "
                            + "and the component value is not bound to a model property");
            }
            return newValue;
        }

        if (converter != null) {
            return converter.getAsObject(context, component, newValue);
        } else {
            throw new ConverterException("Null converter for newValue "+newValue);
        }

    }


    @Override
    public void setSubmittedValue(UIComponent component, Object value) {

        if (component instanceof UIInput) {
            ((UIInput) component).setSubmittedValue(value);
            if (LOGGER.isLoggable(Level.FINE)) {
                LOGGER.fine("Set submitted value " + value + " on component ");
            }
        }

    }

    protected static Map<String, List<ClientBehavior>> getNonOnChangeBehaviors(UIComponent component) {
        return getPassThruBehaviors(component, "change", "valueChange");
    }

    protected static Map<String, List<ClientBehavior>> getNonOnClickSelectBehaviors(UIComponent component) {
        return getPassThruBehaviors(component, "click", "valueChange");
    }

    private boolean hasStringConverter(FacesContext context) {
        if (!hasStringConverterSet) {
            hasStringConverter = (null !=
                                  context.getApplication()
                                        .createConverter(String.class));
            hasStringConverterSet = true;
        }
        return hasStringConverter;
    }
    
} 