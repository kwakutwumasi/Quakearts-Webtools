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

import java.io.IOException;
import java.io.Serializable;

import javax.faces.component.NamingContainer;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

/**
 *
 */
public final class UICollectionEntry extends UINamingContainer {
    public static final String COMPONENT_TYPE = "gravel.Entry";
    public static final String RENDERER_TYPE = null;
    public static final String COMPONENT_FAMILY = "gravel";

    private static final long serialVersionUID = 1L;

    public String getFamily() {
        return COMPONENT_FAMILY;
    }

    private int rowId;

    public UICollectionEntry() {
        this(true);
    }

    public UICollectionEntry(final boolean prependIdDefault) {
        super(prependIdDefault);
        setRendererType(RENDERER_TYPE);
    }

    // Client ID management

    private String clientId;

    @SuppressWarnings("deprecation")
	public String getClientId(final FacesContext context) {
        // if the clientId is not yet set, initialize it
        if (clientId == null) {
            final UIComponent parent = getParent();
            if (! (parent instanceof NamingContainer)) {
                throw new IllegalStateException("Parent of collection entry must be a NamingContainer!");
            }

            final String parentId = parent.getContainerClientId(context);
            // parentId could be null if all parents were set to not prepend ID
            if (parentId != null) {
                final StringBuilder builder = new StringBuilder(parentId.length() + 20);
                builder.append(parentId);
                builder.append(SEPARATOR_CHAR);
                builder.append(rowId);
                clientId = builder.toString();
            } else {
                clientId = Integer.toString(rowId);
            }
        }
        return clientId;
    }

    public String getId() {
        // Return the string representation of this rowId so findComponent can find us
        return Integer.toString(rowId);
    }

    public void setId(final String string) {
        // No-op!
    }

    public int getRowId() {
        return rowId;
    }

    public void setRowId(final int rowId) {
        this.rowId = rowId;
    }

    // Encoding

    public void encodeBegin(final FacesContext context) throws IOException {
        // nothing
    }

    public void encodeChildren(final FacesContext context) throws IOException {
        // nothing
    }

    public void encodeEnd(final FacesContext context) throws IOException {
        // nothing
    }

    public boolean getRendersChildren() {
        return false;
    }

    // State management

    private State state;

    public Object saveState(final FacesContext context) {
        if (state == null) {
            state = new State();
        }
        state.superState = super.saveState(context);
        state.rowId = rowId;
        return state;
    }

    public void restoreState(final FacesContext context, final Object object) {
        state = (State) object;
        rowId = state.rowId;
        super.restoreState(context, state.superState);
    }

    public static final class State implements Serializable {
        private Object superState;
        private int rowId;

        private static final long serialVersionUID = 1L;
    }
}
