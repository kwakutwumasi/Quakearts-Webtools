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
import java.util.Iterator;
import java.util.NoSuchElementException;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.component.UIViewRoot;

/**
 *
 */
public abstract class UIGravelBase extends UIComponentBase implements Serializable {

    private static final long serialVersionUID = 1L;

    protected <T> T getAttributeValue(final String attr, final T fieldValue) {
        return getAttributeValue(attr, fieldValue, null);
    }

    @SuppressWarnings("unchecked")
    protected <T> T getAttributeValue(final String attr, final T fieldValue, final T defaultValue) {
        if (fieldValue != null) {
            return fieldValue;
        }
        final ValueExpression valueExpression = getValueExpression(attr);
        if (valueExpression != null) {
            return (T) valueExpression.getValue(getFacesContext().getELContext());
        } else {
            return defaultValue;
        }
    }

    protected boolean getAttributeValue(final String attr, final boolean fieldVal, final boolean isSet) {
        return getAttributeValue(attr, fieldVal, isSet, false);
    }

    protected boolean getAttributeValue(final String attr, final boolean fieldVal, final boolean isSet, final boolean defaultVal) {
        if (isSet) {
            return fieldVal;
        }
        final ValueExpression valueExpression = getValueExpression(attr);
        return valueExpression == null ? defaultVal : Boolean.TRUE.equals(valueExpression.getValue(getFacesContext().getELContext()));
    }

    protected int getAttributeValue(final String attr, final int fieldValue, final boolean isSet) {
        return getAttributeValue(attr, fieldValue, isSet, -1);
    }

    protected int getAttributeValue(final String attr, final int fieldValue, final boolean isSet, final int defaultValue) {
        if (isSet) {
            return fieldValue;
        }
        final ValueExpression valueExpression = getValueExpression(attr);
        if (valueExpression == null) {
            return defaultValue;
        }
        final Object value = valueExpression.getValue(getFacesContext().getELContext());
        if (value == null) {
            return defaultValue;
        } else if (value instanceof Integer) {
            return ((Integer)value).intValue();
        } else if (value instanceof String) {
            return Integer.parseInt((String) value);
        } else {
            return Integer.parseInt(value.toString());
        }
    }

    protected long getAttributeValue(final String attr, final long fieldValue, final boolean isSet) {
        return getAttributeValue(attr, fieldValue, isSet, -1);
    }

    protected long getAttributeValue(final String attr, final long fieldValue, final boolean isSet, final long defaultValue) {
        if (isSet) {
            return fieldValue;
        }
        final ValueExpression valueExpression = getValueExpression(attr);
        if (valueExpression == null) {
            return defaultValue;
        }
        final Object value = valueExpression.getValue(getFacesContext().getELContext());
        if (value == null) {
            return defaultValue;
        } else if (value instanceof Long) {
            return ((Long)value).longValue();
        } else if (value instanceof String) {
            return Long.parseLong((String) value);
        } else {
            return Long.parseLong(value.toString());
        }
    }

    public boolean isGeneratedId() {
        final String id = getId();
        return id == null || id.startsWith(UIViewRoot.UNIQUE_ID_PREFIX);
    }

    /**
     * Get immediate children of the given type, or the immediate children of the given
     * collection type, which itself must be an immediate child of this component.
     *
     * @param claxx the class to search for
     * @param collectionClaxx the type of collection to include in the search, or {@code null} for none
     * @return an iterator that finds the children of the given type.
     */
    public <T extends UIComponent, S extends UICollection> Iterator<T> getChildrenOfType(Class<T> claxx, final Class<S> collectionClaxx) {
        return new ChildIterator<T, S>(claxx, collectionClaxx);
    }

    /**
     * Get immediate children of the given type.
     *
     * @param claxx the class to search for
     * @return an iterator that finds the children of the given type.
     */
    public <T extends UIComponent> Iterator<T> getChildrenOfType(Class<T> claxx) {
        return new ChildIterator<T, UICollection>(claxx, null);
    }

    private final class ChildIterator<T extends UIComponent, S extends UICollection> implements Iterator<T> {
        private final Class<T> claxx;
        private final Class<S> collectionClaxx;
        private final Iterator<UIComponent> main;
        private Iterator<T> child = null;
        private T next = null;

        private ChildIterator(final Class<T> claxx, final Class<S> collectionClaxx) {
            this.claxx = claxx;
            this.collectionClaxx = collectionClaxx;
            main = getChildren().iterator();
        }

        public boolean hasNext() {
            queueNext();
            return next != null;
        }

        @SuppressWarnings ({"unchecked"})
        private void queueNext() {
            while (next == null) {
                if (child != null) {
                    if (child.hasNext()) {
                        next = child.next();
                        return;
                    } else {
                        child = null;
                    }
                }
                if (main.hasNext()) {
                    UIComponent c = main.next();
                    if ((c instanceof UICollectionEntry) || collectionClaxx != null && collectionClaxx.isAssignableFrom(collectionClaxx)) {
                        child = ((UIGravelBase)c).getChildrenOfType(claxx, null);
                    } else if (claxx.isAssignableFrom(c.getClass())) {
                        next = (T) c;
                        return;
                    }

                } else {
                    // done
                    return;
                }
            }
        }

        @SuppressWarnings ({"unchecked"})
        public T next() {
            try {
                queueNext();
                if (next == null) {
                    throw new NoSuchElementException("next() called past end of iterator");
                }
                return next;
            } finally {
                next = null;
            }
        }

        public void remove() {
            throw new UnsupportedOperationException("remove() not allowed");
        }
    }
}
