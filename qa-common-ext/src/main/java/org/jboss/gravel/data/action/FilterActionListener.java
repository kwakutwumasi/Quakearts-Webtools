package org.jboss.gravel.data.action;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import org.jboss.gravel.Filter;

import javax.el.ELContext;
import javax.el.ELResolver;
import javax.el.FunctionMapper;
import javax.el.ValueExpression;
import javax.el.VariableMapper;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 */
public final class FilterActionListener implements ActionListener, Serializable {
    private final ValueExpression sourceExpression;
    private final ValueExpression targetExpression;
    private final String entryVarName;
    private final ValueExpression testExpression;
    private final ValueExpression filterExpression;
    private final ValueExpression filterArgumentExpression;
    private final ValueExpression ifExpression;
    private final FacesContext facesContext;
    private final ELContext elContext;
    
    private static final long serialVersionUID = 1L;

    public FilterActionListener(final ValueExpression sourceExpression, final ValueExpression targetExpression, final String entryVarName, final ValueExpression testExpression, final ValueExpression filterExpression, final ValueExpression filterArgumentExpression, final ValueExpression ifExpression) {
        this.sourceExpression = sourceExpression;
        this.targetExpression = targetExpression;
        this.entryVarName = entryVarName;
        this.testExpression = testExpression;
        this.filterExpression = filterExpression;
        this.filterArgumentExpression = filterArgumentExpression;
        this.ifExpression = ifExpression;
        this.facesContext = FacesContext.getCurrentInstance();
        this.elContext = facesContext.getELContext();
        if (log.isLoggable(Level.FINE)) {
            log.fine("Created new filter action listener: entry var is '" + entryVarName + "'");
        }
    }

    @SuppressWarnings("unchecked")
    public void processAction(ActionEvent event) throws AbortProcessingException {
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
        
        Object oldVar;
        //To set entry var as a request scoped variable
        //you may end up overwriting an existing request variable
        //Get out any var set up as entry var
        oldVar = facesContext.getExternalContext().getRequestMap().remove(entryVarName);
        
        if (sourceValue instanceof SortedMap) {
            SortedMap<?, ?> sourceSortedMap = (SortedMap<?, ?>) sourceValue;
            SortedMap<Object, Object> targetSortedMap = new TreeMap<Object, Object>((Comparator<? super Object>)sourceSortedMap.comparator());
            for (Map.Entry<?,?> entry : sourceSortedMap.entrySet()) {
                if (checkItem(entry)) {
                    targetSortedMap.put(entry.getKey(), entry.getValue());
                }
            }
            targetExpression.setValue(elContext, targetSortedMap);
        } else if (sourceValue instanceof Map) {
            Map<?, ?> sourceMap = (Map<?, ?>) sourceValue;
            Map<Object, Object> targetMap = new HashMap<Object, Object>();
            for (Map.Entry<?,?> entry : sourceMap.entrySet()) {
                if (checkItem(entry)) {
                    targetMap.put(entry.getKey(), entry.getValue());
                }
            }
            targetExpression.setValue(elContext, targetMap);
        } else if (sourceValue instanceof SortedSet) {
            SortedSet<?> sourceSet = (SortedSet<?>) sourceValue;
            SortedSet<Object> targetSet = new TreeSet<Object>((Comparator<? super Object>)sourceSet.comparator());
            for (Object entry : sourceSet) {
                if (checkItem(entry)) {
                    targetSet.add(entry);
                }
            }
            targetExpression.setValue(elContext, targetSet);
        } else if (sourceValue instanceof Set) {
            Set<?> sourceSet = (Set<?>) sourceValue;
            Set<Object> targetSet = new HashSet<Object>();
            for (Object entry : sourceSet) {
                if (checkItem(entry)) {
                    targetSet.add(entry);
                }
            }
            targetExpression.setValue(elContext, targetSet);
        } else if (sourceValue instanceof List) {
            List<?> sourceList = (List<?>) sourceValue;
            List<Object> targetList = new ArrayList<Object>();
            for(Object entry : sourceList) {
                if (checkItem(entry)) {
                    targetList.add(entry);
                }
            }
            targetExpression.setValue(elContext, targetList);
        } else if (sourceValue instanceof Collection) {
            Collection<?> sourceCollection = (Collection<?>) sourceValue;
            Collection<Object> targetCollection = new ArrayList<Object>();
            for (Object entry : sourceCollection) {
                if (checkItem(entry)) {
                    targetCollection.add(entry);
                }
            }
            targetExpression.setValue(elContext, targetCollection);
        } else if (sourceValue.getClass().isArray()) {
            final Class<? extends Object> sourceValueClass = sourceValue.getClass();
            final int sourceLength = Array.getLength(sourceValue);
            Object targetArray = Array.newInstance(sourceValueClass, sourceLength);
            int srcIdx, targetIdx;
            for (srcIdx = 0, targetIdx = 0; srcIdx < sourceLength; srcIdx ++) {
                final Object value = Array.get(sourceValue, srcIdx);
                if (checkItem(value)) {
                    Array.set(targetArray, targetIdx++, value);
                }
            }
            if (targetIdx < sourceLength) {
                final Object newTargetArray = Array.newInstance(sourceValueClass, targetIdx);
                System.arraycopy(targetArray, 0, newTargetArray, 0, targetIdx);
                targetArray = newTargetArray;
            }
            targetExpression.setValue(elContext, targetArray);
        } else {
            if (checkItem(sourceValue)) {
                targetExpression.setValue(elContext, sourceValue);
            } else {
                targetExpression.setValue(elContext, null);
            }
        }
        
        //Restore entry var request scoped
        facesContext.getExternalContext().getRequestMap().put(entryVarName, oldVar);
    }

    private boolean checkItem(final Object item) {
        final boolean result;
        facesContext.getExternalContext().getRequestMap().put(entryVarName, item);
        if (testExpression != null) {
            final Object testValue = testExpression.getValue(elContext);
            if (testValue != null) {
                if (testValue instanceof Boolean) {
                    result = ((Boolean)testValue).booleanValue();
                } else {
                    result = Boolean.valueOf(testValue.toString());
                }
            } else {
                result = false;
            }
            return result;
        } else if (filterExpression != null) {
            final Filter filterValue = (Filter) filterExpression.getValue(elContext);
            if (filterValue != null) {
                final Object filterArgument;
                if (filterArgumentExpression != null) {
                    filterArgument = filterArgumentExpression.getValue(elContext);
                } else {
                    filterArgument = item;
                }
                return filterValue.testEntry(filterArgument);
            } else {
                return true;
            }
        } else {
            return true;
        }
    }

    private static final Logger log = Logger.getLogger("org.jboss.gravel.data.action.FilterActionListener");
}
