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

import org.jboss.gravel.common.annotation.TldAttribute;

import javax.faces.component.NamingContainer;

/**
 *
 */
public interface FriendlyNamingContainer extends NamingContainer {

    @TldAttribute(
            description = "Boolean attribute to determine whether this component should prepend its id " +
                    "to its descendent components' IDs during the clientId generation process."
    )
    boolean isPrependId();

    void setPrependId(boolean prependId);
}
