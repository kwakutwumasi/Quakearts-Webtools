/*
    Gravel - Component library for JSF
    Copyright (C) 2007  David M. Lloyd

    This library is free software; you can redistribute it and/or
    modify it under the terms of the GNU Lesser General Public
    License as published by the Free Software Foundation; either
    version 2.1 of the License, or (at your option) any later version.

    This library is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
    Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public
    License along with this library; if not, write to the Free Software
    Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */
package org.jboss.gravel.common.ui;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.jboss.gravel.common.annotation.TldAttribute;

import javax.faces.context.FacesContext;

/**
 *
 */
public abstract class UICollection extends UINamingContainer implements CollectionComponent {
    /**
	 * 
	 */
	private static final long serialVersionUID = -8539044526489078895L;
	private int first;
    private boolean firstSet;
    private int limit;
    private boolean limitSet;
    private List<?> valueList;
    private Object value;
    private String var;
    private String idVar;

    public UICollection() {
        super(true);
    }

    public UICollection(final boolean prependIdDefault) {
        super(prependIdDefault);
    }

    public int getFirst() {
        return getAttributeValue("first", first, firstSet);
    }

    public void setFirst(final int first) {
        this.first = first;
        firstSet = true;
    }

    public int getLimit() {
        return getAttributeValue("limit", limit, limitSet);
    }

    public void setLimit(final int limit) {
        this.limit = limit;
        limitSet = true;
    }

    @SuppressWarnings("unchecked")
    public List<?> getValueList() {
        if (valueList == null) {
            final Object value = getValue();
            if (value == null) {
                valueList = Collections.emptyList();
            } else if (value instanceof List) {
                valueList = (List<?>) value;
            } else if (value.getClass().isArray()) {
                valueList = Arrays.asList((Object[])value);
            } else if (value instanceof Collection) {
                final Collection<?> collection = ((Collection<Object>) value);
                final ArrayList<Object> arrayList = new ArrayList<Object>(collection.size());
                arrayList.addAll(collection);
                valueList = arrayList;
            } else if (value instanceof Map) {
                final Map<?, ?> map = (Map<Object,Object>) value;
                final ArrayList<Object> arrayList = new ArrayList<Object>(map.size());
                arrayList.addAll(map.entrySet());
                valueList = arrayList;
            } else if (value instanceof Iterable) {
                final Iterable<?> iterable = ((Iterable<Object>) value);
                final ArrayList<Object> arrayList = new ArrayList<Object>();
                for (final Object o : iterable) {
                    arrayList.add(o);
                }
                valueList = arrayList;
            } else if (value != null) {
                valueList = Collections.singletonList(value);
            }
        }
        return valueList;
    }

    @TldAttribute(
            description = "The collection value to iterate over. The value may be " +
                    "an instance of <code>Iterable</code>, or it may be an array.  If " +
                    "it is neither of these types, a singleton list will be created around the " +
                    "value.",
            deferredType = Object.class
    )
    public Object getValue() {
        return getAttributeValue("value", value);
    }

    public void setValue(final Object value) {
        this.value = value;
        valueList = null;
    }

    public String getVar() {
        return getAttributeValue("var", var);
    }

    public void setVar(final String var) {
        this.var = var;
    }

    public String getIdVar() {
        return getAttributeValue("idVar", idVar);
    }

    public void setIdVar(final String idVar) {
        this.idVar = idVar;
    }

    // ================ State management ================

    private State state;

    public Object saveState(final FacesContext context) {
        if (state == null) {
            state = new State();
        }
        state.superState = super.saveState(context);

        state.first = first;
        state.firstSet = firstSet;
        state.limit = limit;
        state.limitSet = limitSet;
        state.value = value;
        state.var = var;

        return state;
    }

    public void restoreState(final FacesContext context, final Object object) {
        state = (State) object;

        first = state.first;
        firstSet = state.firstSet;
        limit = state.limit;
        limitSet = state.limitSet;
        value = state.value;
        var = state.var;

        super.restoreState(context, state.superState);
    }

    public static final class State implements Serializable {
        private static final long serialVersionUID = 1L;

        private Object superState;

        private int first;
        private boolean firstSet;
        private int limit;
        private boolean limitSet;
        private Object value;
        private String var;
    }
}
