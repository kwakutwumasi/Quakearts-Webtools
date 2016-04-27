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
import org.jboss.gravel.common.ui.HasHtmlBasicAttributes;
import org.jboss.gravel.common.ui.HasJsfBasicFacetStyles;
import org.jboss.gravel.common.ui.UICollection;

import javax.faces.context.FacesContext;

/**
 *
 */
@TldTag(
        name = "dataTable",
        description = "Equivalent of JSF standard h:dataTable using the Gravel improved collection model."
)
public final class UIDataTable extends UICollection implements HasHtmlBasicAttributes, HasJsfBasicFacetStyles {
    public static final String COMPONENT_TYPE = "gravel.compat.DataTable";
    public static final String RENDERER_TYPE = "gravel.compat.DataTable";
    public static final String COMPONENT_FAMILY = "gravel.compat";

    private static final long serialVersionUID = 1L;

    public UIDataTable() {
        super(true);
        setRendererType(RENDERER_TYPE);
    }

    public String getFamily() {
        return COMPONENT_FAMILY;
    }

    // ================ Properties ================

    // htmlCore properties

    private String styleClass;
    private String style;
    private String title;

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

    public String getTitle() {
        return getAttributeValue("title", title);
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    // htmlI18n properties

    private String lang;
    private String dir;

    public String getLang() {
        return getAttributeValue("lang", lang);
    }

    public void setLang(final String lang) {
        this.lang = lang;
    }

    public String getDir() {
        return getAttributeValue("dir", dir);
    }

    public void setDir(final String dir) {
        this.dir = dir;
    }

    // htmlEvents properties

    private String onclick;
    private String ondblclick;
    private String onmousedown;
    private String onmouseup;
    private String onmouseover;
    private String onmousemove;
    private String onmouseout;
    private String onkeypress;
    private String onkeydown;
    private String onkeyup;
    private String onload;
    private String onunload;

    public String getOnclick() {
        return getAttributeValue("onclick", onclick);
    }

    public void setOnclick(final String onclick) {
        this.onclick = onclick;
    }

    public String getOndblclick() {
        return getAttributeValue("ondblclick", ondblclick);
    }

    public void setOndblclick(final String ondblclick) {
        this.ondblclick = ondblclick;
    }

    public String getOnmousedown() {
        return getAttributeValue("onmousedown", onmousedown);
    }

    public void setOnmousedown(final String onmousedown) {
        this.onmousedown = onmousedown;
    }

    public String getOnmouseup() {
        return getAttributeValue("onmouseup", onmouseup);
    }

    public void setOnmouseup(final String onmouseup) {
        this.onmouseup = onmouseup;
    }

    public String getOnmouseover() {
        return getAttributeValue("onmouseover", onmouseover);
    }

    public void setOnmouseover(final String onmouseover) {
        this.onmouseover = onmouseover;
    }

    public String getOnmousemove() {
        return getAttributeValue("onmousemove", onmousemove);
    }

    public void setOnmousemove(final String onmousemove) {
        this.onmousemove = onmousemove;
    }

    public String getOnmouseout() {
        return getAttributeValue("onmouseout", onmouseout);
    }

    public void setOnmouseout(final String onmouseout) {
        this.onmouseout = onmouseout;
    }

    public String getOnkeypress() {
        return getAttributeValue("onkeypress", onkeypress);
    }

    public void setOnkeypress(final String onkeypress) {
        this.onkeypress = onkeypress;
    }

    public String getOnkeydown() {
        return getAttributeValue("onkeydown", onkeydown);
    }

    public void setOnkeydown(final String onkeydown) {
        this.onkeydown = onkeydown;
    }

    public String getOnkeyup() {
        return getAttributeValue("onkeyup", onkeyup);
    }

    public void setOnkeyup(final String onkeyup) {
        this.onkeyup = onkeyup;
    }

    public String getOnload() {
        return getAttributeValue("onload", onload);
    }

    public void setOnload(final String onload) {
        this.onload = onload;
    }

    public String getOnunload() {
        return getAttributeValue("onunload", onunload);
    }

    public void setOnunload(final String onunload) {
        this.onunload = onunload;
    }

    // DataTable properties

    private String rowClasses;
    private String headerClass;
    private String headerStyle;
    private String footerClass;
    private String footerStyle;
    private String columnClasses;
    private String captionClass;
    private String captionStyle;

    @TldAttribute(
            description = "Comma-delimited list of CSS style classes that will be applied to the rows of this " +
                    "table. A space separated list of classes may also be specified for any individual row. These " +
                    "styles are applied, in turn, to each row in the table.",
            deferredType = String.class
    )
    public String getRowClasses() {
        return getAttributeValue("rowClasses", rowClasses);
    }

    public void setRowClasses(final String rowClasses) {
        this.rowClasses = rowClasses;
    }

    public String getHeaderClass() {
        return getAttributeValue("headerClass", headerClass);
    }

    public void setHeaderClass(final String headerClass) {
        this.headerClass = headerClass;
    }

    public String getHeaderStyle() {
        return getAttributeValue("headerStyle", headerStyle);
    }

    public void setHeaderStyle(final String headerStyle) {
        this.headerStyle = headerStyle;
    }

    public String getFooterClass() {
        return getAttributeValue("footerClass", footerClass);
    }

    public void setFooterClass(final String footerClass) {
        this.footerClass = footerClass;
    }

    public String getFooterStyle() {
        return getAttributeValue("footerStyle", footerStyle);
    }

    public void setFooterStyle(final String footerStyle) {
        this.footerStyle = footerStyle;
    }

    @TldAttribute(
            description = "Comma-delimited list of CSS style classes that will be applied to the columns of this " +
                    "table. A space separated list of classes may also be specified for any individual column. These " +
                    "styles are applied, in turn, to each column in the table.",
            deferredType = String.class
    )
    public String getColumnClasses() {
        return getAttributeValue("columnClasses", columnClasses);
    }

    public void setColumnClasses(final String columnClasses) {
        this.columnClasses = columnClasses;
    }

    @TldAttribute(
            description = "Space-separated list of CSS style class(es) that will be applied to any caption generated " +
                    "for this table.",
            deferredType = String.class
    )
    public String getCaptionClass() {
        return getAttributeValue("captionClass", captionClass);
    }

    public void setCaptionClass(final String captionClass) {
        this.captionClass = captionClass;
    }

    @TldAttribute(
            description = "Space-separated list of CSS style(s) that will be applied to any caption generated " +
                    "for this table.",
            deferredType = String.class
    )
    public String getCaptionStyle() {
        return getAttributeValue("captionStyle", captionStyle);
    }

    public void setCaptionStyle(final String captionStyle) {
        this.captionStyle = captionStyle;
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
        state.title = title;
        state.lang = lang;
        state.dir = dir;
        state.onclick = onclick;
        state.ondblclick = ondblclick;
        state.onmousedown = onmousedown;
        state.onmouseup = onmouseup;
        state.onmouseover = onmouseover;
        state.onmousemove = onmousemove;
        state.onmouseout = onmouseout;
        state.onkeypress = onkeypress;
        state.onkeydown = onkeydown;
        state.onkeyup = onkeyup;
        state.onload = onload;
        state.onunload = onunload;
        state.rowClasses = rowClasses;
        state.headerClass = headerClass;
        state.footerClass = footerClass;
        state.columnClasses = columnClasses;
        state.captionClass = captionClass;
        state.captionStyle = captionStyle;
        state.headerStyle = headerStyle;
        state.footerStyle = footerStyle;

        return state;
    }

    public void restoreState(final FacesContext context, final Object object) {
        state = (State) object;
        styleClass = state.styleClass;
        style = state.style;
        title = state.title;
        lang = state.lang;
        dir = state.dir;
        onclick = state.onclick;
        ondblclick = state.ondblclick;
        onmousedown = state.onmousedown;
        onmouseup = state.onmouseup;
        onmouseover = state.onmouseover;
        onmousemove = state.onmousemove;
        onmouseout = state.onmouseout;
        onkeypress = state.onkeypress;
        onkeydown = state.onkeydown;
        onkeyup = state.onkeyup;
        onload = state.onload;
        onunload = state.onunload;
        rowClasses = state.rowClasses;
        headerClass = state.headerClass;
        footerClass = state.footerClass;
        columnClasses = state.columnClasses;
        captionClass = state.captionClass;
        captionStyle = state.captionStyle;
        headerStyle = state.headerStyle;
        footerStyle = state.footerStyle;

        super.restoreState(context, state.superState);
    }

    public static final class State implements Serializable {
        private static final long serialVersionUID = 1L;

        private Object superState;

        private String styleClass;
        private String style;
        private String title;

        private String lang;
        private String dir;

        private String onclick;
        private String ondblclick;
        private String onmousedown;
        private String onmouseup;
        private String onmouseover;
        private String onmousemove;
        private String onmouseout;
        private String onkeypress;
        private String onkeydown;
        private String onkeyup;
        private String onload;
        private String onunload;

        private String rowClasses;
        private String headerClass;
        private String footerClass;
        private String columnClasses;
        private String captionClass;
        private String captionStyle;
        private String headerStyle;
        private String footerStyle;
    }

}
