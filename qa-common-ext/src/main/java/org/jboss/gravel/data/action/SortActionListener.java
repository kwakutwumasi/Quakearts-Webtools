package org.jboss.gravel.data.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.el.ELContext;
import javax.el.ValueExpression;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;

/**
 *
 */
public final class SortActionListener implements ActionListener, Serializable {
    private final ValueExpression sourceExpression;
    private final ValueExpression targetExpression;
    private final String entryVarName;
    private final ValueExpression comparatorExpression;
    private final ValueExpression argumentExpression;
    private final ValueExpression ifExpression;
    private final ValueExpression reverseExpression;

    private static final long serialVersionUID = 1L;

    public SortActionListener(final ValueExpression sourceExpression, final ValueExpression targetExpression, final String entryVarName, final ValueExpression comparatorExpression, final ValueExpression argumentExpression, final ValueExpression ifExpression, final ValueExpression reverseExpression) {
        this.sourceExpression = sourceExpression;
        this.targetExpression = targetExpression;
        this.entryVarName = entryVarName;
        this.comparatorExpression = comparatorExpression;
        this.argumentExpression = argumentExpression;
        this.ifExpression = ifExpression;
        this.reverseExpression = reverseExpression;
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
        
        Object oldVar;
        //To set entry var as a request scoped variable
        //you may end up overwriting an existing request variable
        //Get out any var set up as entry var
        oldVar = facesContext.getExternalContext().getRequestMap().remove(entryVarName);

        final Object sourceValue = sourceExpression.getValue(elContext);
        if (sourceValue == null) {
            targetExpression.setValue(elContext, null);
            return;
        }
        final Comparator comparator = new ELComparator(elContext, facesContext);

        if (sourceValue instanceof Map) {
            final Map<?, ?> sourceMap = (Map<?, ?>) sourceValue;
            final Map<Object, Object> targetMap = new LinkedHashMap<Object, Object>();
            final Set<? extends Map.Entry<?, ?>> entrySet = sourceMap.entrySet();
            final List<? extends Map.Entry<?, ?>> entryList = new ArrayList<Map.Entry<?, ?>>(entrySet);
            Collections.sort(entryList, comparator);
            for (Map.Entry<?, ?> entry : entryList) {
                targetMap.put(entry.getKey(), entry.getValue());
            }
            targetExpression.setValue(elContext, targetMap);
        } else if (sourceValue instanceof Set) {
            Set<?> sourceSet = (Set<?>) sourceValue;
            Set<Object> targetSet = new LinkedHashSet<Object>();
            final List<?> entryList = new ArrayList<Object>(sourceSet);
            Collections.sort(entryList, comparator);
            for (Object entry : entryList) {
                targetSet.add(entry);
            }
            targetExpression.setValue(elContext, targetSet);
        } else if (sourceValue instanceof Collection) {
            Collection<?> sourceCollection = (Collection<?>) sourceValue;
            List<Object> targetList = new ArrayList<Object>(sourceCollection);
            Collections.sort(targetList, comparator);
            targetExpression.setValue(elContext, targetList);
        } else if (sourceValue instanceof Object[]) {
            Object[] sourceArray = (Object[]) sourceValue;
            final int length = sourceArray.length;
            Object[] targetArray = new Object[length];
            System.arraycopy(targetArray, 0, sourceArray, 0, length);
            Arrays.sort(targetArray, comparator);
            targetExpression.setValue(elContext, targetArray);
        } else {
            targetExpression.setValue(elContext, sourceValue);
        }
        
        //Restore entry var request scoped
        facesContext.getExternalContext().getRequestMap().put(entryVarName, oldVar);
    }

    // This class is never serialized with the enclosing class
    private final class ELComparator<T> implements Comparator<T> {
        private final ELContext elContext;
        private final FacesContext facesContext;
        private final Comparator<T> nestedComparator;
        private final boolean reverse;

        @SuppressWarnings ({"unchecked"})
        public ELComparator(final ELContext elContext, final FacesContext facesContext) {
            this.elContext = elContext;
            this.facesContext = facesContext;
            nestedComparator = comparatorExpression == null ? null : (Comparator<T>)comparatorExpression.getValue(elContext);
            reverse = reverseExpression == null ? false : ((Boolean)reverseExpression.getValue(elContext)).booleanValue();
        }

        @SuppressWarnings ({"unchecked"})
        public int compare(final T o1, final T o2) {
            final T v1;
            final T v2;
            if (argumentExpression != null) {
                facesContext.getExternalContext().getRequestMap().put(entryVarName, o1);
                v1 = (T) argumentExpression.getValue(elContext);
                facesContext.getExternalContext().getRequestMap().put(entryVarName, o2);
                v2 = (T) argumentExpression.getValue(elContext);
            } else {
                v1 = o1;
                v2 = o2;
            }
            if (nestedComparator != null) {
                return reverse ? nestedComparator.compare(v2, v1) : nestedComparator.compare(v1, v2);
            } else {
                int res;
                if (v1 == null) {
                    if (v2 == null) {
                        return 0;
                    } else {
                    	try {
                            res = -((Comparable<T>)v2).compareTo(null);
						} catch (Exception e) {
							res = -1;
						}
                    }
                } else {
                    if (v2 == null) {
                    	try {
                            res = -((Comparable<T>)v1).compareTo(null);
						} catch (Exception e) {
							return 1;
						}
                    } else {
                        res = ((Comparable<T>)v1).compareTo(v2);
                    }
                }
                return reverse ? -res : res;
            }
        }
    }
}
