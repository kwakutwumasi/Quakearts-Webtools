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
package org.jboss.gravel.data.tree;

import java.util.List;

/**
 * A tree structure.
 */
public interface Tree<T> {
    /**
     * Get all the subtree elements for this point on the tree.
     *
     * @return the list of subtree elements
     */
    List<Tree<T>> getChildren();

    /**
     * Get the datum corresponding to this point on the tree.
     *
     * @return the data
     */
    T getNode();
}
