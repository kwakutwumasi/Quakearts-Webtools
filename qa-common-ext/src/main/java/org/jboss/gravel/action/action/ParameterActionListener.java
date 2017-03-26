package org.jboss.gravel.action.action;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.io.Serializable;

import javax.faces.event.ActionListener;
import javax.faces.event.ActionEvent;
import javax.faces.event.AbortProcessingException;
import javax.faces.component.UIComponent;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;
import javax.el.ValueExpression;
import javax.el.ELContext;

/**
 *
 */
public final class ParameterActionListener implements ActionListener, Serializable {
    private ValueExpression requiredExpression;
    private ValueExpression converterMessageExpression;
    private ValueExpression validatorMessageExpression;
    private ValueExpression requiredMessageExpression;
    private ValueExpression targetExpression;
    private ValueExpression defaultExpression;
    private String name;
    private boolean required;
    private Converter converter;
    private List<Validator> validators = new ArrayList<Validator>();
    private UIComponent pseudoComponent;

    private static final long serialVersionUID = 1L;

    public Converter getConverter() {
        return converter;
    }

    public void setConverter(final Converter converter) {
        this.converter = converter;
    }

    public List<Validator> getValidators() {
        return validators;
    }

    public void addValidator(Validator validator) {
        validators.add(validator);
    }

    public ValueExpression getConverterMessageExpression() {
        return converterMessageExpression;
    }

    public void setConverterMessageExpression(final ValueExpression converterMessageExpression) {
        this.converterMessageExpression = converterMessageExpression;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(final boolean required) {
        this.required = required;
    }

    public ValueExpression getRequiredExpression() {
        return requiredExpression;
    }

    public void setRequiredExpression(final ValueExpression requiredExpression) {
        this.requiredExpression = requiredExpression;
    }

    public ValueExpression getRequiredMessageExpression() {
        return requiredMessageExpression;
    }

    public void setRequiredMessageExpression(final ValueExpression requiredMessageExpression) {
        this.requiredMessageExpression = requiredMessageExpression;
    }

    public ValueExpression getTargetExpression() {
        return targetExpression;
    }

    public void setTargetExpression(final ValueExpression targetExpression) {
        this.targetExpression = targetExpression;
    }

    public ValueExpression getValidatorMessageExpression() {
        return validatorMessageExpression;
    }

    public void setValidatorMessageExpression(final ValueExpression validatorMessageExpression) {
        this.validatorMessageExpression = validatorMessageExpression;
    }

    public ValueExpression getDefaultExpression() {
        return defaultExpression;
    }

    public void setDefaultExpression(final ValueExpression defaultExpression) {
        this.defaultExpression = defaultExpression;
    }

    public UIComponent getPseudoComponent() {
        return pseudoComponent;
    }

    public void setPseudoComponent(final UIComponent pseudoComponent) {
        this.pseudoComponent = pseudoComponent;
    }

    public void processAction(ActionEvent event) throws AbortProcessingException {
        final FacesContext facesContext = FacesContext.getCurrentInstance();
        final ELContext elContext = facesContext.getELContext();
        String converterMessage = null;
        String validatorMessage = null;
        String requiredMessage = null;
        if (converterMessageExpression != null) {
            converterMessage = String.valueOf(converterMessageExpression.getValue(elContext));
        }
        if (validatorMessageExpression != null) {
            validatorMessage = String.valueOf(validatorMessageExpression.getValue(elContext));
        }
        if (requiredMessageExpression != null) {
            requiredMessage = String.valueOf(requiredMessageExpression.getValue(elContext));
        }
        final Map<String, String[]> map = facesContext.getExternalContext().getRequestParameterValuesMap();
        final String[] values = map.get(name);
        if (values != null && values.length > 0) {
        	ArrayList<Object> targetList = new ArrayList<Object>();
            for (String value : values) {
                try {
                    final Object targetValue;
                    if (converter != null) {
                        targetValue = converter.getAsObject(facesContext, pseudoComponent, value);
                    } else {
                        targetValue = value;
                    }
                    for (Validator validator : validators) {
                        validator.validate(facesContext, pseudoComponent, targetValue);
                    }
                    targetList.add(targetValue);
                } catch (ConverterException ex) {
                    final FacesMessage facesMessage = ex.getFacesMessage();
                    if (converterMessage != null) {
                        facesMessage.setSummary(converterMessage);
                        facesMessage.setDetail(null);
                    }
                    facesContext.addMessage(null, facesMessage);
                    throw new AbortProcessingException("Error converting param "+name+". Illegal value "+value,ex);
                } catch (ValidatorException ex) {
                    final FacesMessage facesMessage = ex.getFacesMessage();
                    if (validatorMessage != null) {
                        facesMessage.setSummary(validatorMessage);
                        facesMessage.setDetail(null);
                    }
                    facesContext.addMessage(null, facesMessage);
                    throw new AbortProcessingException("Error validating param "+name+". Illegal value "+value,ex);
                }
            }
            if(targetList.size()==1)
            	targetExpression.setValue(elContext, targetList.get(0));
            else
            	targetExpression.setValue(elContext, targetList);

        } else if (required) {
            final FacesMessage facesMessage;
            if (requiredMessage != null) {
                facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, requiredMessage, null);
            } else {
                facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Required request parameter '" + name + "' missing", null);
            }
            facesContext.addMessage(null, facesMessage);
        } else if (defaultExpression != null) {
            if (defaultExpression != null) {
                targetExpression.setValue(elContext, defaultExpression.getValue(elContext));
            }
        }
    }
}
