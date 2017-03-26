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
package org.jboss.gravel.compat.ui;

import java.io.Serializable;
import org.jboss.gravel.common.annotation.TldAttribute;
import org.jboss.gravel.common.annotation.TldTag;
import org.jboss.gravel.common.ui.HasHtmlStyleAttributes;
import org.jboss.gravel.common.ui.HasJsfCoreAttributes;
import org.jboss.gravel.common.ui.UIGravelBase;

import javax.faces.context.FacesContext;

/**
 *
 */
@TldTag(
        name = "column",
        description = "Equivalent of JSF standard h:column using the Gravel improved collection model."
)
public final class UIDataTableColumn extends UIGravelBase implements HasHtmlStyleAttributes, HasJsfCoreAttributes {

    public static final String COMPONENT_TYPE = "gravel.compat.DataTableColumn";
    public static final String RENDERER_TYPE = null;
    public static final String COMPONENT_FAMILY = "gravel.compat";

    private static final long serialVersionUID = 1L;

    public UIDataTableColumn() {
        setRendererType(RENDERER_TYPE);
    }

    public String getFamily() {
        return COMPONENT_FAMILY;
    }

    // ================ Properties ================

    private String styleClass;
    private String style;

    public String getStyleClass() {
        return getAttributeValue("styleClass", styleClass);
    }

    public void setStyleClass(final String styleClass) {
        this.styleClass = styleClass;
    }

    public String getStyle() {
        return getAttributeValue("style", style);
    }

    public void setStyle(final String style) {
        this.style = style;
    }

    // class and style

    private String footerClass;
    private String footerStyle;
    private String headerClass;
    private String headerStyle;

    @TldAttribute(
            description = "The style class that will be applied to the footer.",
            deferredType = String.class
    )
    public String getFooterClass() {
        return getAttributeValue("footerClass", footerClass);
    }

    public void setFooterClass(final String footerClass) {
        this.footerClass = footerClass;
    }

    @TldAttribute(
            description = "The CSS style that will be applied to the footer.",
            deferredType = String.class
    )
    public String getFooterStyle() {
        return getAttributeValue("footerStyle", footerStyle);
    }

    public void setFooterStyle(final String footerStyle) {
        this.footerStyle = footerStyle;
    }

    @TldAttribute(
            description = "The style class that will be applied to the header.",
            deferredType = String.class
    )
    public String getHeaderClass() {
        return getAttributeValue("headerClass", headerClass);
    }

    public void setHeaderClass(final String headerClass) {
        this.headerClass = headerClass;
    }

    @TldAttribute(
            description = "The CSS style that will be applied to the footer.",
            deferredType = String.class
    )
    public String getHeaderStyle() {
        return getAttributeValue("headerStyle", headerStyle);
    }

    public void setHeaderStyle(final String headerStyle) {
        this.headerStyle = headerStyle;
    }

    // ================ State management ================

    private State state;

    public Object saveState(final FacesContext context) {
        if (state == null) {
            state = new State();
        }
        state.superState = super.saveState(context);
        state.styleClass = styleClass;
        state.style = style;
        state.headerClass = headerClass;
        state.headerStyle = headerStyle;
        state.footerClass = footerClass;
        state.footerStyle = footerStyle;

        return state;
    }

    public void restoreState(final FacesContext context, final Object object) {
        state = (State) object;
        styleClass = state.styleClass;
        style = state.style;
        headerClass = state.headerClass;
        headerStyle = state.headerStyle;
        footerClass = state.footerClass;
        footerStyle = state.footerStyle;

        super.restoreState(context, state.superState);
    }

    public static final class State implements Serializable {
        private static final long serialVersionUID = 1L;

        private Object superState;

        private String styleClass;
        private String style;

        private String headerClass;
        private String headerStyle;
        private String footerClass;
        private String footerStyle;
    }
}
