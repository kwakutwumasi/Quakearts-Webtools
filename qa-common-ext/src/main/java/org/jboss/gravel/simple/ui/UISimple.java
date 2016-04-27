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
package org.jboss.gravel.simple.ui;

import java.io.Serializable;
import org.jboss.gravel.common.annotation.TldAttribute;
import org.jboss.gravel.common.annotation.TldTag;
import org.jboss.gravel.common.annotation.TldTags;
import org.jboss.gravel.common.ui.HasHtmlBasicAttributes;
import org.jboss.gravel.common.ui.UIGravelBase;

import javax.faces.context.FacesContext;

/**
 *
 */
@TldTags({
    @TldTag(name = "div", description = "Render an HTML div element."),
    @TldTag(name = "span", description = "Render an HTML span element."),
    @TldTag(name = "dl", description = "Render an HTML dl element."),
    @TldTag(name = "ul", description = "Render an HTML ul element."),
    @TldTag(name = "ol", description = "Render an HTML ol element."),
    @TldTag(name = "li", description = "Render an HTML li element."),
    @TldTag(name = "dd", description = "Render an HTML dd element."),
    @TldTag(name = "dt", description = "Render an HTML dt element."),
    @TldTag(name = "h1", description = "Render an HTML h1 element."),
    @TldTag(name = "h2", description = "Render an HTML h2 element."),
    @TldTag(name = "h3", description = "Render an HTML h3 element."),
    @TldTag(name = "h4", description = "Render an HTML h4 element."),
    @TldTag(name = "h5", description = "Render an HTML h5 element."),
    @TldTag(name = "h6", description = "Render an HTML h6 element."),
    @TldTag(name = "address", description = "Render an HTML address element."),
    @TldTag(name = "p", description = "Render an HTML p element."),
    @TldTag(name = "em", description = "Render an HTML em element."),
    @TldTag(name = "strong", description = "Render an HTML strong element."),
    @TldTag(name = "dfn", description = "Render an HTML dfn element."),
    @TldTag(name = "code", description = "Render an HTML code element."),
    @TldTag(name = "samp", description = "Render an HTML samp element."),
    @TldTag(name = "kbd", description = "Render an HTML kbd element."),
    @TldTag(name = "var", description = "Render an HTML var element."),
    @TldTag(name = "cite", description = "Render an HTML cite element."),
    @TldTag(name = "abbr", description = "Render an HTML abbr element."),
    @TldTag(name = "acronym", description = "Render an HTML acronym element."),
    @TldTag(name = "pre", description = "Render an HTML pre element."),
    @TldTag(name = "sub", description = "Render an HTML sub element."),
    @TldTag(name = "sup", description = "Render an HTML sup element."),
    @TldTag(name = "tt", description = "Render an HTML tt element."),
    @TldTag(name = "i", description = "Render an HTML i element."),
    @TldTag(name = "b", description = "Render an HTML b element."),
    @TldTag(name = "big", description = "Render an HTML big element."),
    @TldTag(name = "small", description = "Render an HTML small element."),
    @TldTag(name = "caption", description = "Render an HTML caption element.")
})
public class UISimple extends UIGravelBase implements HasHtmlBasicAttributes {

    public static final String COMPONENT_TYPE = "gravel.Simple";
    public static final String RENDERER_TYPE = "gravel.Simple";
    public static final String COMPONENT_FAMILY = "gravel.simple";

    private static final long serialVersionUID = 1L;

    public UISimple() {
        setRendererType(RENDERER_TYPE);
    }

    public String getFamily() {
        return COMPONENT_FAMILY;
    }

    // ================ Properties ================

    // specific properties

    private boolean writeTag;
    private boolean writeTagSet;

    @TldAttribute (
        description = "Determine whether the start and end tags should be rendered for this element.  Defaults " +
            "to <code>true</code>, meaning the tags should be rendered."
    )
    public boolean isWriteTag() {
        return getAttributeValue("writeTag", writeTag, writeTagSet, true);
    }

    public void setWriteTag(final boolean writeTag) {
        this.writeTag = writeTag;
        writeTagSet = true;
    }

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

    // ================ State management ================

    private State state;

    public Object saveState(final FacesContext context) {
        if (state == null) {
            state = new State();
        }
        state.superState = super.saveState(context);
        state.writeTag = writeTag;
        state.writeTagSet = writeTagSet;
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

        return state;
    }

    public void restoreState(final FacesContext context, final Object object) {
        state = (State) object;
        writeTag = state.writeTag;
        writeTagSet = state.writeTagSet;
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

        super.restoreState(context, state.superState);
    }

    public static final class State implements Serializable {
        private static final long serialVersionUID = 1L;

        private Object superState;

        private boolean writeTag;
        private boolean writeTagSet;

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

    }

}
