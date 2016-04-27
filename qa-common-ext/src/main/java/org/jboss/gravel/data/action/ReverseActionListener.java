package org.jboss.gravel.data.action;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.el.ELContext;
import javax.el.ValueExpression;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;
import java.util.logging.Logger;

/**
 *
 */
public final class ReverseActionListener implements ActionListener, Serializable {
    private final ValueExpression sourceExpression;
    private final ValueExpression targetExpression;
    private final ValueExpression ifExpression;

    private static final long serialVersionUID = 1L;

    public ReverseActionListener(final ValueExpression sourceExpression, final ValueExpression targetExpression, final ValueExpression ifExpression) {
        this.sourceExpression = sourceExpression;
        this.targetExpression = targetExpression;
        this.ifExpression = ifExpression;
    }

    @SuppressWarnings("unchecked")
    public void processAction(ActionEvent event) throws AbortProcessingException {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        final ELContext elContext = facesContext.getELContext();
        if (ifExpression != null) {
            Object ifValue = ifExpression.getValue(elContext);
            if (! Boolean.TRUE.equals(ifValue)) {
                return;
            }
        }
        final Object sourceValue = sourceExpression.getValue(elContext);
        if (sourceValue == null) {
            targetExpression.setValue(elContext, null);
            return;
        }
        if (sourceValue instanceof Collection) {
            Collection<?> sourceCollection = (Collection<?>) sourceValue;
            List<?> targetList = new ArrayList<Object>(sourceCollection);
            Collections.reverse(targetList);
            targetExpression.setValue(elContext, targetList);
        } else if (sourceValue.getClass().isArray()) {
            final Class<? extends Object> sourceValueClass = sourceValue.getClass();
            final int sourceLength = Array.getLength(sourceValue);
            final Object targetArray = Array.newInstance(sourceValueClass, sourceLength);
            int srcIdx;
            for (srcIdx = 0; srcIdx < sourceLength; srcIdx ++) {
                final Object value = Array.get(sourceValue, srcIdx);
                Array.set(targetArray, sourceLength - srcIdx - 1, value);
            }
            targetExpression.setValue(elContext, targetArray);
        } else {
            targetExpression.setValue(elContext, sourceValue);
        }
    }

    @SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger("org.jboss.gravel.data.action.ReverseActionListener");
}
