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
import java.util.Collection;
import java.util.List;
import org.jboss.gravel.common.ui.HasHtmlAccessAttributes;
import org.jboss.gravel.common.ui.HasHtmlBasicAttributes;
import org.jboss.gravel.common.ui.HasHtmlCellAlignAttributes;
import org.jboss.gravel.common.ui.HasHtmlCellAttributes;
import org.jboss.gravel.common.ui.HasHtmlChangeFocusEventsAttributes;
import org.jboss.gravel.common.ui.HasHtmlCoreAttributes;
import org.jboss.gravel.common.ui.HasHtmlEventsAttributes;
import org.jboss.gravel.common.ui.HasHtmlFocusEventsAttributes;
import org.jboss.gravel.common.ui.HasHtmlI18nAttributes;
import org.jboss.gravel.common.ui.HasHtmlSelectEventsAttributes;
import org.jboss.gravel.common.ui.HasHtmlStyleAttributes;
import org.jboss.gravel.common.util.DelimitedStringList;

import javax.el.ValueExpression;
import javax.faces.component.NamingContainer;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.Renderer;

/**
 *
 */
public abstract class RendererBase extends Renderer {

    protected String getStringAttribute(final FacesContext facesContext, final UIComponent component, final String name) {
        final ValueExpression valueExpression = component.getValueExpression(name);
        if (valueExpression == null) {
            return null;
        }
        final Object value = valueExpression.getValue(facesContext.getELContext());
        if (value == null) {
            return null;
        }
        return value.toString();
    }

    protected StringCycler stringCycler(final String stringList) {
      return new StringListStringCycler(stringList);
    }

    protected StringCycler stringCycler(final StringCycler first, final StringCycler second) {
      return new CompositeStringCycler(first, second);
    }

    protected <C extends UIComponent> Element<C> writeElement(final FacesContext context, final String name, final C component) throws IOException {
      return new ElementImpl<C>(context, name, component);
    }

    protected void doEncode(final FacesContext facesContext, final UIComponent uiComponent) throws IOException {
        if (uiComponent == null || ! uiComponent.isRendered()) {
            return;
        }
        uiComponent.encodeBegin(facesContext);
        if (uiComponent.getRendersChildren()) {
            uiComponent.encodeChildren(facesContext);
        } else {
            final List<UIComponent> childList = uiComponent.getChildren();
            if (childList == null) {
                return;
            }
            doEncode(facesContext, childList);
        }
        uiComponent.encodeEnd(facesContext);
    }

    protected void doEncode(final FacesContext facesContext, final Collection<UIComponent> uiComponentCollection) throws IOException {
        if (uiComponentCollection == null) {
            return;
        }
        for (final UIComponent component : uiComponentCollection) {
            doEncode(facesContext, component);
        }
    }

    protected void writeHtmlStyle(final Element<?> e, final HasHtmlStyleAttributes comp) throws IOException {
        e.addClass(comp.getStyleClass());
        e.addStyle(comp.getStyle());
        e.writeClass();
        e.writeStyle();
    }

    protected void writeHtmlCore(final Element<?> e, final HasHtmlCoreAttributes comp) throws IOException {
        writeHtmlStyle(e, comp);
        e.writeAttribute("title", comp.getTitle());
    }

    protected void writeHtmlI18n(final Element<?> e, final HasHtmlI18nAttributes comp) throws IOException {
        e.writeAttribute("lang", comp.getLang());
        e.writeAttribute("dir", comp.getDir());
    }

    protected void writeHtmlEvents(final Element<?> e, final HasHtmlEventsAttributes comp) throws IOException {
        e.writeAttribute("onclick", comp.getOnclick());
        e.writeAttribute("ondblclick", comp.getOndblclick());
        e.writeAttribute("onmousedown", comp.getOnmousedown());
        e.writeAttribute("onmouseup", comp.getOnmouseup());
        e.writeAttribute("onmouseover", comp.getOnmouseover());
        e.writeAttribute("onmousemove", comp.getOnmousemove());
        e.writeAttribute("onmouseout", comp.getOnmouseout());
        e.writeAttribute("onkeypress", comp.getOnkeypress());
        e.writeAttribute("onkeydown", comp.getOnkeydown());
        e.writeAttribute("onkeyup", comp.getOnkeyup());
        e.writeAttribute("onload", comp.getOnload());
        e.writeAttribute("onunload", comp.getOnunload());
    }

    protected void writeHtmlBasic(final Element<?> e, final HasHtmlBasicAttributes comp) throws IOException {
        writeHtmlCore(e, comp);
        writeHtmlI18n(e, comp);
        writeHtmlEvents(e, comp);
    }

    protected void writeHtmlFocus(final Element<?> e, final HasHtmlFocusEventsAttributes comp) throws IOException {
        e.writeAttribute("onfocus", comp.getOnfocus());
        e.writeAttribute("onblur", comp.getOnblur());
    }

    protected void writeHtmlChangeFocus(final Element<?> e, final HasHtmlChangeFocusEventsAttributes comp) throws IOException {
        writeHtmlFocus(e, comp);
        e.writeAttribute("onchange", comp.getOnchange());
    }

    protected void writeHtmlSelect(final Element<?> e, final HasHtmlSelectEventsAttributes comp) throws IOException {
        writeHtmlChangeFocus(e, comp);
        e.writeAttribute("onselect", comp.getOnselect());
    }

    protected void writeHtmlCellAlign(final Element<?> e, final HasHtmlCellAlignAttributes comp) throws IOException {
        e.writeAttribute("align", comp.getAlign());
        e.writeAttribute("valign", comp.getValign());
        e.writeAttribute("char", comp.getAlignChar());
        e.writeAttribute("charoff", comp.getCharoff());
    }

    protected void writeHtmlCell(final Element<?> e, final HasHtmlCellAttributes comp) throws IOException {
        e.writeAttribute("abbr", comp.getAbbr());
        e.writeAttribute("axis", comp.getAxis());
        final String headers = comp.getHeaders();
        if (headers != null) {
            StringBuilder builder = new StringBuilder();
            for (String s : new DelimitedStringList(headers)) {
                if (s.trim().length() > 0) {
                    final UIComponent component = ((UIComponent) comp).findComponent(s);
                    if (component != null) {
                        if (builder.length() > 0) {
                            builder.append(' ');
                        }
                        builder.append(component.getClientId(FacesContext.getCurrentInstance()));
                    }
                }
            }
            e.writeAttribute("headers", builder.toString());
        }
        e.writeAttribute("scope", comp.getScope());

        final int colspan = comp.getColspan();
        if (colspan != 1) {
            e.writeAttribute("colspan", colspan);
        }
        final int rowspan = comp.getRowspan();
        if (rowspan != 1) {
            e.writeAttribute("rowspan", rowspan);
        }
    }

    protected void writeHtmlAccess(final Element<?> e, final HasHtmlAccessAttributes comp) throws IOException {
        e.writeAttribute("accesskey", comp.getAccesskey());
    }

    private static final class StringListStringCycler implements StringCycler {
        private final String[] array;
        private int i = 0;

        public StringListStringCycler(final String string) {
            if (string == null) {
                array = null;
            } else {
                array = string.split("\\s*,\\s*");
            }
        }

        public void appendNext(final StringBuilder StringBuilder) {
            if (array != null && array.length > 0) {
                if (StringBuilder.length() > 0) {
                    StringBuilder.append(' ');
                }
                StringBuilder.append(array[i++]);
                i %= array.length;
            }
        }
    }

    private static final class CompositeStringCycler implements StringCycler {

        private final StringCycler first, second;

        public CompositeStringCycler(final StringCycler first, final StringCycler second) {
            this.first = first;
            this.second = second;
        }

        public void appendNext(final StringBuilder StringBuilder) {
            first.appendNext(StringBuilder);
            second.appendNext(StringBuilder);
        }
    }

    private final class ElementImpl<C extends UIComponent> implements Element<C> {
        private final FacesContext context;
        private final ResponseWriter responseWriter;
        private final String name;
        private final C component;
        private final StringBuilder classBuffer = new StringBuilder();
        private final StringBuilder styleBuffer = new StringBuilder();

        public ElementImpl(final FacesContext context, final String name, final C component) throws IOException {
            this.context = context;
            this.name = name;
            this.component = component;
            responseWriter = context.getResponseWriter();
            responseWriter.startElement(name, component);
        }

        public Element<C> writeId() throws IOException {
            return writeId("id", true);
        }

        @SuppressWarnings("deprecation")
		public Element<C> writeId(final String attribute, final boolean onlyIfSpecified) throws IOException {
            final String clientId = component.getClientId(context);
            if (clientId == null) {
                return this;
            }
            final String lastPart = clientId.substring(clientId.lastIndexOf(NamingContainer.SEPARATOR_CHAR) + 1);
            if (onlyIfSpecified && lastPart.startsWith(UIViewRoot.UNIQUE_ID_PREFIX)) {
                return this;
            }
            responseWriter.writeAttribute(attribute, clientId, null);
            return this;
        }

        public Element<C> addClass(final String className) {
            if (className != null) {
                if (classBuffer.length() > 0) {
                    classBuffer.append(' ');
                }
                classBuffer.append(className);
            }
            return this;
        }

        public Element<C> addClass(final StringCycler stringCycler) {
            if (stringCycler != null) {
                stringCycler.appendNext(classBuffer);
            }
            return this;
        }

        public Element<C> writeClass() throws IOException {
            if (classBuffer.length() == 0) {
                return this;
            }
            try {
                return writeAttribute("class", classBuffer.toString());
            } finally {
                classBuffer.setLength(0);
            }
        }

        public Element<C> addStyle(final String style) {
            if (style != null) {
                if (styleBuffer.length() > 0) {
                    styleBuffer.append(';');
                }
                styleBuffer.append(style);
            }
            return this;
        }

        public Element<C> writeStyle() throws IOException {
            if (styleBuffer.length() == 0) {
                return this;
            }
            try {
                return writeAttribute("style", styleBuffer.toString());
            } finally {
                classBuffer.setLength(0);
            }
        }

        public Element<C> writeAttribute(final String attribute, final String value) throws IOException {
            if (value != null) {
                responseWriter.writeAttribute(attribute, value, null);
            }
            return this;
        }

        public Element<C> writeAttribute(final String attribute, final int value) throws IOException {
            if (value != -1) {
                responseWriter.writeAttribute(attribute, Integer.toString(value), null);
            }
            return this;
        }

        public Element<C> writeAttribute(final String attribute, final boolean value) throws IOException {
            if (value) {
                responseWriter.writeAttribute(attribute, "true", null);
            }
            return this;
        }

        public Element<C> doEncode() throws IOException {
            RendererBase.this.doEncode(context, component);
            return this;
        }

        public void close() throws IOException {
            responseWriter.endElement(name);
        }

        public Element<C> writeElement(final String name) throws IOException {
            return new ElementImpl<C>(context, name, component);
        }

        public <I extends UIComponent> Element<I> writeElement(final String name, final I component) throws IOException {
            return new ElementImpl<I>(context, name, component);
        }

        public C getComponent() {
            return component;
        }

        public FacesContext getFacesContext() {
            return context;
        }
    }
}
