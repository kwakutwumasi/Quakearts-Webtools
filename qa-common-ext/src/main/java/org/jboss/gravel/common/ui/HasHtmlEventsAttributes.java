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

/**
 *
 */
public interface HasHtmlEventsAttributes {

    @TldAttribute(
            description = "Render the HTML onclick attribute.  The onclick event occurs when " +
                    "the pointing device button is clicked over an element. This attribute may be " +
                    "used with most elements.",
            deferredType = String.class
    )
    String getOnclick();

    @TldAttribute(
            description = "Render the HTML ondblclick attribute.  The ondblclick event occurs when " +
                    "the pointing device button is double clicked over an element. This attribute may be " +
                    "used with most elements.",
            deferredType = String.class
    )
    String getOndblclick();

    @TldAttribute(
            description = "Render the HTML onmousedown attribute.  The onmousedown event occurs when " +
                    "the pointing device button is pressed over an element. This attribute may be " +
                    "used with most elements.",
            deferredType = String.class
    )
    String getOnmousedown();

    @TldAttribute(
            description = "Render the HTML onmouseup attribute.  The onmouseup event occurs when the pointing " +
                    "device button is released over an element. This attribute may be used with most elements.",
            deferredType = String.class
    )
    String getOnmouseup();

    @TldAttribute(
            description = "Render the HTML onmouseover attribute.  The onmouseover event occurs when the pointing " +
                    "device is moved onto an element. This attribute may be used with most elements.",
            deferredType = String.class
    )
    String getOnmouseover();

    @TldAttribute(
            description = "Render the HTML onmousemove attribute.  The onmousemove event occurs when the pointing " +
                    "device is moved while it is over an element. This attribute may be used with most elements.",
            deferredType = String.class
    )
    String getOnmousemove();

    @TldAttribute(
            description = "Render the HTML onmouseout attribute.  The onmouseout event occurs when the pointing " +
                    "device is moved away from an element. This attribute may be used with most elements.",
            deferredType = String.class
    )
    String getOnmouseout();

    @TldAttribute(
            description = "Render the HTML onkeypress attribute.  The onkeypress event occurs when a key is " +
                    "pressed and released over an element. This attribute may be used with most elements.",
            deferredType = String.class
    )
    String getOnkeypress();

    @TldAttribute(
            description = "Render the HTML onkeydown attribute.  The onkeydown event occurs when a key is " +
                    "pressed down over an element. This attribute may be used with most elements.",
            deferredType = String.class
    )
    String getOnkeydown();

    @TldAttribute(
            description = "Render the HTML onkeyup attribute.  The onkeyup event occurs when a key is " +
                    "released over an element. This attribute may be used with most elements.",
            deferredType = String.class
    )
    String getOnkeyup();

    @TldAttribute(
            description = "Render the HTML onload attribute.  The onload event occurs when the user agent " +
                    "finishes loading a window or all frames within a FRAMESET. This attribute may be used " +
                    "with BODY and FRAMESET elements.  Though nonstandard, this attribute is widely supported " +
                    "on other attributes as well.",
            deferredType = String.class
    )
    String getOnload();

    @TldAttribute(
            description = "Render the HTML onunload attribute.  The onunload event occurs when the user agent " +
                    "removes a document from a window or frame. This attribute may be used with " +
                    "BODY and FRAMESET elements.",
            deferredType = String.class
    )
    String getOnunload();
}
