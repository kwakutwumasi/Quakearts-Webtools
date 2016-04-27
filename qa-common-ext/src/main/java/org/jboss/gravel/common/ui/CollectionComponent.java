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

import java.util.List;
import org.jboss.gravel.common.annotation.TldAttribute;

/**
 *
 */
public interface CollectionComponent extends FriendlyNamingContainer {
    @TldAttribute(
            description = "The index of the first index to render in the value list.  The index is zero-based. " +
                    "If the value is greater than the size of the value list, no entries will be rendered.",
            deferredType = int.class
    )
    int getFirst();

    @TldAttribute(
            description = "The count of elements to render in the value list.",
            deferredType = int.class
    )
    int getLimit();

    List<?> getValueList();

    @TldAttribute(
            description = "The variable that should contain the current element in the collection.",
            deferredType = String.class
    )
    String getVar();

    @TldAttribute(
            description = "The variable that should contain the current index in the collection.",
            deferredType = String.class
    )
    String getIdVar();
}
