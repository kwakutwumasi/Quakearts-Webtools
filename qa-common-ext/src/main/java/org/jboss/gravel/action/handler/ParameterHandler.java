package org.jboss.gravel.action.handler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jboss.gravel.common.annotation.TldAttribute;
import org.jboss.gravel.common.annotation.TldTag;
import org.jboss.gravel.action.action.ParameterActionListener;

import javax.faces.view.facelets.ComponentHandler;
import javax.faces.view.facelets.FaceletContext;
import javax.faces.view.facelets.TagAttribute;
import javax.faces.view.facelets.TagConfig;
import javax.faces.view.facelets.TagHandler;
import javax.faces.view.facelets.TagException;
import javax.el.ELException;
import javax.el.ValueExpression;
import javax.faces.FacesException;
import javax.faces.component.EditableValueHolder;
import javax.faces.component.UIComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.component.ActionSource;
import javax.faces.convert.Converter;
import javax.faces.el.MethodBinding;
import javax.faces.event.ValueChangeListener;
import javax.faces.validator.Validator;

/**
 *
 */
@SuppressWarnings ({"deprecation"})
@TldTag (
    name = "parameter",
    description = "Create an action listener that reads a value from the request pararmeter map.  The value will be " +
        "stored in the target.  If the parameter occurs more than once, the target will be updated once for each " +
        "value.  You may optinally register a converter to translate the parameter data before it is stored into " +
        "the target.",
    attributes = {
    @TldAttribute (
        name = "converter",
        description = "Converter instance registered with this component.",
        deferredType = Converter.class
    ),
    @TldAttribute (
        name = "converterMessage",
        description = "A ValueExpression enabled attribute that, if present, will be used as the text of the " +
            "converter message, replacing any message that comes from the converter."
    ),
    @TldAttribute (
        name = "name",
        description = "The name of the request parameter to read.",
        required = true
    ),
    @TldAttribute (
        name = "target",
        description = "The target expression into which the value will be stored.",
        required = true,
        deferredType = Object.class
    ),
    @TldAttribute (
        name = "validatorMessage",
        description = "A ValueExpression enabled attribute that, if present, will be used as the text of the " +
            "validator message, replacing any message that comes from the validator."
    ),
    @TldAttribute (
        name = "required",
        description = "Flag indicating that the user is required to provide a value for the corresponding request " +
            "parameter.",
        deferredType = boolean.class
    ),
    @TldAttribute (
        name = "requiredMessage",
        description = "A ValueExpression enabled attribute that, if present, will be used as the text of the " +
            "validation message for the \"required\" facility, if the \"required\" facility is used."
    ),
    @TldAttribute (
        name = "default",
        description = "An EL expression that will be used as the default value, if no value is given.",
        deferredType = Object.class
    )
}
)
public final class ParameterHandler extends TagHandler {
    private final TagAttribute converterAttribute;
    private final TagAttribute converterMessageAttribute;
    private final TagAttribute nameAttribute;
    private final TagAttribute targetAttribute;
    private final TagAttribute validatorMessageAttribute;
    private final TagAttribute requiredAttribute;
    private final TagAttribute requiredMessageAttribute;
    private final TagAttribute defaultAttribute;

    public ParameterHandler(final TagConfig config) {
        super(config);
        converterAttribute = getAttribute("converter");
        converterMessageAttribute = getAttribute("converterMessage");
        nameAttribute = getRequiredAttribute("name");
        targetAttribute = getRequiredAttribute("target");
        validatorMessageAttribute = getAttribute("validatorMessage");
        requiredAttribute = getAttribute("required");
        requiredMessageAttribute = getAttribute("requiredMessage");
        defaultAttribute = getAttribute("default");
    }

    public void apply(FaceletContext ctx, UIComponent parent) throws IOException, FacesException, ELException {
        try {
            if (ComponentHandler.isNew(parent)) {
                if (! (parent instanceof ActionSource)) {
                    throw new TagException(tag, "Parent component is not an ActionSource");
                }
                Component c = new Component();
                ParameterActionListener actionListener = new ParameterActionListener();
                actionListener.setPseudoComponent(c);
                if (converterAttribute != null) {
                    final ValueExpression converterExpression = converterAttribute.getValueExpression(ctx, Converter.class);
                    if (converterExpression != null) {
                        final Object converterObject = converterExpression.getValue(ctx);
                        if (converterObject != null && converterObject instanceof Converter) {
                            actionListener.setConverter((Converter)converterObject);
                            c.setConverter((Converter)converterObject);
                        }
                    }
                }
                if (converterMessageAttribute != null) {
                    actionListener.setConverterMessageExpression(converterMessageAttribute.getValueExpression(ctx, String.class));
                }
                actionListener.setName((String) nameAttribute.getValueExpression(ctx, String.class).getValue(ctx));
                actionListener.setTargetExpression(targetAttribute.getValueExpression(ctx, Object.class));
                if (validatorMessageAttribute != null) {
                    actionListener.setValidatorMessageExpression(validatorMessageAttribute.getValueExpression(ctx, String.class));
                }
                if (requiredAttribute != null) {
                    final boolean value;
                    final ValueExpression valueExpression = requiredAttribute.getValueExpression(ctx, Boolean.class);
                    if (valueExpression != null) {
                        final Object valueObject = valueExpression.getValue(ctx);
                        if (valueObject instanceof Boolean) {
                            value = ((Boolean)valueObject).booleanValue();
                        } else if (valueObject instanceof String) {
                            value = Boolean.valueOf((String)valueObject);
                        } else {
                            value = valueObject != null && Boolean.valueOf(valueObject.toString());
                        }
                    } else {
                        value = false;
                    }
                    actionListener.setRequired(value);
                }
                if (requiredMessageAttribute != null) {
                    actionListener.setRequiredMessageExpression(requiredMessageAttribute.getValueExpression(ctx, String.class));
                }
                if (defaultAttribute != null) {
                    actionListener.setDefaultExpression(defaultAttribute.getValueExpression(ctx, Object.class));
                }
                nextHandler.apply(ctx, c);
                for (Validator validator : c.getValidatorList()) {
                    actionListener.addValidator(validator);
                }
                if (c.getConverter() != null) {
                    actionListener.setConverter(c.getConverter());
                }
                ((ActionSource)parent).addActionListener(actionListener);
            }
        } catch (TagException tex) {
            throw tex;
        } catch (RuntimeException rex) {
            TagException tex = new TagException(tag, "An exception of type " + rex.getClass().getName() + " occurred: " + rex.getMessage());
            tex.setStackTrace(rex.getStackTrace());
            throw tex;
        }
    }

    private final class Component extends UIComponentBase implements EditableValueHolder {

        private Converter converter;
        private List<Validator> validators = new ArrayList<Validator>();
        private boolean required;

        public String getFamily() {
            return "no.family";
        }

        public Object getLocalValue() {
            return null;
        }

        public Object getValue() {
            return null;
        }

        public void setValue(Object value) {
        }

        public Converter getConverter() {
            return converter;
        }

        public void setConverter(final Converter converter) {
            this.converter = converter;
        }

        public Object getSubmittedValue() {
            return null;
        }

        public void setSubmittedValue(Object submittedValue) {
        }

        public boolean isLocalValueSet() {
            return false;
        }

        public void setLocalValueSet(boolean localValueSet) {
        }

        public boolean isValid() {
            return false;
        }

        public void setValid(boolean valid) {
        }

        public boolean isRequired() {
            return required;
        }

        public void setRequired(boolean required) {
            this.required = required;
        }

        public boolean isImmediate() {
            return false;
        }

        public void setImmediate(boolean immediate) {
        }

        public MethodBinding getValidator() {
            return null;
        }

        public void setValidator(MethodBinding validatorBinding) {
        }

        public MethodBinding getValueChangeListener() {
            return null;
        }

        public void setValueChangeListener(MethodBinding valueChangeMethod) {
        }

        public void addValidator(Validator validator) {
            validators.add(validator);
        }

        public Validator[] getValidators() {
            Validator[] rv = new Validator[validators.size()];
            return validators.toArray(rv);
        }

        public void removeValidator(Validator validator) {
            validators.remove(validator);
        }

        public void addValueChangeListener(ValueChangeListener listener) {
        }

        public ValueChangeListener[] getValueChangeListeners() {
            return null;
        }

        public void removeValueChangeListener(ValueChangeListener listener) {
        }

        public List<Validator> getValidatorList() {
            return validators;
        }

		@Override
		public void resetValue() {
			// TODO Auto-generated method stub
			
		}

    }
}
