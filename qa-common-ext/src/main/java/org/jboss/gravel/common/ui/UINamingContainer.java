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

import javax.faces.component.NamingContainer;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

/**
 *
 */
public abstract class UINamingContainer extends UIGravelBase implements FriendlyNamingContainer {

    /**
	 * 
	 */
	private static final long serialVersionUID = -6965383548402046070L;

	protected UINamingContainer(final boolean prependIdDefault) {
        this.prependIdDefault = prependIdDefault;
    }

    // ================ Client ID management ================

    public String getContainerClientId(final FacesContext context) {
        if (isPrependId()) {
            return super.getContainerClientId(context);
        } else {
            for (UIComponent parent = getParent(); parent != null; parent = parent.getParent()) {
                if (parent instanceof NamingContainer) {
                    return parent.getContainerClientId(context);
                }
            }
            return null;
        }
    }

    // ================ Properties ================

    // NamingContainer properties

    private boolean prependIdDefault;
    private boolean prependId;
    private boolean prependIdSet;

    public boolean isPrependId() {
        return getAttributeValue("prependId", prependId, prependIdSet, prependIdDefault);
    }

    public void setPrependId(final boolean prependId) {
        this.prependId = prependId;
        prependIdSet = true;
    }

    // ================ State management ================

    private State state;

    public Object saveState(final FacesContext context) {
        if (state == null) {
            state = new State();
        }
        state.superState = super.saveState(context);
        state.prependId = prependId;
        state.prependIdSet = prependIdSet;
        return state;
    }

    public void restoreState(final FacesContext context, final Object object) {
        state = (State) object;
        prependId = state.prependId;
        prependIdSet = state.prependIdSet;
        super.restoreState(context, state.superState);
    }

    public static final class State implements Serializable {
        private static final long serialVersionUID = 1L;

        private Object superState;

        private boolean prependId;
        private boolean prependIdSet;
    }
}
