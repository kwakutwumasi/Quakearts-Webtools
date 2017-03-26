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
package org.jboss.gravel.simple.renderer;

import java.io.IOException;
import org.jboss.gravel.common.renderer.Element;
import org.jboss.gravel.common.renderer.RendererBase;
import org.jboss.gravel.simple.ui.UISimple;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

/**
 *
 */
public class SimpleRenderer<T extends UISimple> extends RendererBase {
    private final String element;

    protected SimpleRenderer(final String element) {
        this.element = element;
    }

    public void encodeBegin(final FacesContext context, final UIComponent uiComponent) throws IOException {
    }

    public void encodeEnd(final FacesContext context, final UIComponent uiComponent) throws IOException {
    }

    @SuppressWarnings ({"unchecked"})
    public void encodeChildren(FacesContext context, UIComponent uiComponent) throws IOException {
        final T uiSimple = (T) uiComponent;
        if (uiSimple.isRendered()) {
            if (uiSimple.isWriteTag()) {
                final Element<T> elem = writeElement(context, element, uiSimple);
                elem.writeId();
                writeAttributes(elem, uiSimple);
                doEncode(context, uiSimple.getChildren());
                elem.close();
            } else {
                doEncode(context, uiSimple.getChildren());
            }
        }
    }

    protected void writeAttributes(final Element<T> elem, final T component) throws IOException {
        writeHtmlBasic(elem, component);
    }

    public boolean getRendersChildren() {
        return true;
    }

    public static SimpleRenderer<UISimple> simpleRenderer(final String element) {
        return new SimpleRenderer<UISimple>(element);
    }
}
