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
package org.jboss.gravel.common.renderer;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

/**
 *
 */
public interface Element<C extends UIComponent> {
    /**
     * Write ID to the id attribute, only if it was specified.
     *
     * @return this element
     * @throws IOException if an error occurs
     */
    Element<C> writeId() throws IOException;

    /**
     * Write the ID to the specified attribute.
     *
     * @param attribute the attribute
     * @param onlyIfSpecified {@code true} if it should only be written if specified
     * @return this element
     * @throws IOException if an error occurs
     */
    Element<C> writeId(String attribute, boolean onlyIfSpecified) throws IOException;

    /**
     * Add a CSS style class to the current element.
     *
     * @param className the style class name
     * @return this element
     */
    Element<C> addClass(String className);

    /**
     * Add a CSS style class to the current element from the current
     * string of a string cycler.
     *
     * @param stringCycler the string cycler
     * @return this element
     */
    Element<C> addClass(StringCycler stringCycler);

    /**
     * Add a CSS style to the current element.
     *
     * @param style the style to add
     * @return this element
     */
    Element<C> addStyle(String style);

    /**
     * Write the class attribute, if any style classes have been added.
     *
     * @return this element
     * @throws IOException if an error occurs
     */
    Element<C> writeClass() throws IOException;

    /**
     * Write the style attribute, if any styles have been added.
     *
     * @return this element
     * @throws IOException if an error occurs
     */
    Element<C> writeStyle() throws IOException;

    /**
     * Write out an attriubte on this element.
     *
     * @param attribute the attribute
     * @param value the value
     * @return this element
     * @throws IOException if an error occurs
     */
    Element<C> writeAttribute(String attribute, String value) throws IOException;

    /**
     * Write out an attriubte on this element.
     *
     * @param attribute the attribute
     * @param value the value
     * @return this element
     * @throws IOException if an error occurs
     */
    Element<C> writeAttribute(String attribute, int value) throws IOException;

    /**
     * Write out an attriubte on this element.  If value is false,
     * no attribute is written.
     *
     * @param attribute the attribute
     * @param value the value
     * @return this element
     * @throws IOException if an error occurs
     */
    Element<C> writeAttribute(final String attribute, final boolean value) throws IOException;

    /**
     * Encode the element's component.
     *
     * @return this element
     * @throws IOException if an error occurs
     */
    Element<C> doEncode() throws IOException;

    /**
     * Close out this element.  Renders the close
     * tag.
     *
     * @throws IOException if an error occurs
     */
    void close() throws IOException;

    /**
     * Create a nested element for the same component.
     *
     * @param name the name of the element
     * @return the new element
     * @throws IOException if an error occurs
     */
    Element<C> writeElement(String name) throws IOException;

    /**
     * Create a nested element for a different component.
     *
     * @param name the name of the element
     * @param component the associated component
     * @return the new element
     * @throws IOException if an error occurs
     */
    <I extends UIComponent> Element<I> writeElement(String name, I component) throws IOException;

    /**
     * Get the component associated with this element.
     *
     * @return the component
     */
    C getComponent();

    /**
     * Get the faces context associated with this element.
     *
     * @return the faces context
     */
    FacesContext getFacesContext();
}
