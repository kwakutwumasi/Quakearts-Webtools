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
package org.jboss.gravel.compat.renderer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jboss.gravel.common.renderer.Element;
import org.jboss.gravel.common.renderer.RendererBase;
import org.jboss.gravel.common.renderer.StringCycler;
import org.jboss.gravel.common.ui.UICollectionEntry;
import org.jboss.gravel.compat.ui.UIDataTable;
import org.jboss.gravel.compat.ui.UIDataTableColumn;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

/**
 *
 */
public final class DataTableRenderer extends RendererBase {

    public void encodeBegin(final FacesContext context, final UIComponent uiComponent) throws IOException {
        // use encodeChildren
    }

    public void encodeChildren(final FacesContext context, final UIComponent uiComponent) throws IOException {
        final UIDataTable dataTable = (UIDataTable) uiComponent;
        final Element<UIDataTable> table = writeElement(context, "table", dataTable);

        writeHtmlBasic(table, dataTable);
        table.writeId();

        final List<UIDataTableColumn> outerColumns = new ArrayList<UIDataTableColumn>();

        int headerCount = 0;
        int footerCount = 0;

        for (final UIComponent child : dataTable.getChildren()) {
            if (child instanceof UIDataTableColumn) {
                final UIDataTableColumn column = (UIDataTableColumn) child;
                outerColumns.add(column);
                if (column.getFacets().containsKey("header")) headerCount++;
                if (column.getFacets().containsKey("footer")) footerCount++;
            }
        }

        final UIComponent caption = dataTable.getFacet("caption");
        final UIComponent tableHeader = dataTable.getFacet("header");
        final UIComponent tableFooter = dataTable.getFacet("footer");

        if (caption != null) {
            final Element<UIComponent> captionElem = table.writeElement("caption", caption);
            captionElem.addClass(dataTable.getCaptionClass());
            captionElem.addStyle(dataTable.getCaptionStyle());
            captionElem.writeClass();
            captionElem.writeStyle();
            captionElem.doEncode();
            captionElem.close();
        }

        if (tableHeader != null || headerCount != 0) {
            final Element<UIDataTable> thead = table.writeElement("thead");
            if (tableHeader != null) {
                final Element<UIDataTable> tr = thead.writeElement("tr");
                final Element<UIComponent> th = tr.writeElement("th", tableHeader);
                th.addClass(dataTable.getHeaderClass());
                th.addStyle(dataTable.getHeaderStyle());
                th.writeAttribute("colspan", outerColumns.size());
                th.writeAttribute("scope", "colgroup");
                th.writeClass();
                th.writeStyle();
                th.doEncode();
                th.close();
                tr.close();
            }
            if (headerCount != 0) {
                final Element<UIDataTable> tr = thead.writeElement("tr");
                for (final UIDataTableColumn column : outerColumns) {
                    final UIComponent headerFacet = column.getFacet("header");
                    final Element<UIComponent> th = tr.writeElement("th", headerFacet == null ? column : headerFacet);
                    th.addClass(column.getHeaderClass());
                    th.addStyle(column.getHeaderStyle());
                    th.writeClass();
                    th.writeStyle();
                    th.writeAttribute("scope", "col");
                    if (headerFacet != null) {
                        th.doEncode();
                    }
                    th.close();
                }
                tr.close();
            }
            thead.close();
        }

        if (tableFooter != null || footerCount != 0) {
            final Element<UIDataTable> tfoot = table.writeElement("tfoot");
            if (footerCount != 0) {
                final Element<UIDataTable> tr = tfoot.writeElement("tr");
                for (final UIDataTableColumn column : outerColumns) {
                    final UIComponent footerFacet = column.getFacet("footer");
                    final Element<UIComponent> td = tr.writeElement("td", footerFacet == null ? column : footerFacet);
                    td.addClass(column.getFooterClass());
                    td.addStyle(column.getFooterStyle());
                    td.writeClass();
                    td.writeStyle();
                    if (footerFacet != null) {
                        td.doEncode();
                    }
                    td.close();
                }
                tr.close();
            }
            if (tableFooter != null) {
                final Element<UIDataTable> tr = tfoot.writeElement("tr");
                final Element<UIComponent> td = tr.writeElement("td", tableFooter);
                td.addClass(dataTable.getFooterClass());
                td.addStyle(dataTable.getFooterStyle());
                td.writeClass();
                td.writeStyle();
                td.writeAttribute("colspan", outerColumns.size());
                td.doEncode();
                td.close();
                tr.close();
            }
            tfoot.close();
        }

        final Element<UIDataTable> tbody = table.writeElement("tbody");
        final StringCycler rowClassCycler = stringCycler(dataTable.getRowClasses());
        final StringCycler columnClassCycler = stringCycler(dataTable.getColumnClasses());

        for (final UIComponent child : dataTable.getChildren()) {
            if (child instanceof UICollectionEntry) {
                final UICollectionEntry entry = (UICollectionEntry) child;

                final Element<UICollectionEntry> tr = tbody.writeElement("tr", entry);
                tr.addClass(rowClassCycler);
                tr.writeClass();

                for (final UIComponent rowChild : entry.getChildren()) {
                    if (rowChild instanceof UIDataTableColumn) {
                        final UIDataTableColumn column = (UIDataTableColumn) rowChild;
                        final Element<UIDataTableColumn> td = tr.writeElement("td", column);
                        td.writeId();
                        td.addClass(columnClassCycler);
                        td.addClass(column.getStyleClass());
                        td.addStyle(column.getStyle());
                        td.writeClass();
                        td.writeStyle();
                        td.doEncode();
                        td.close();
                    }
                }
                tr.close();
            }
        }
        tbody.close();
        table.close();
    }

    public void encodeEnd(final FacesContext context, final UIComponent uiComponent) throws IOException {
        // use encodeChildren
    }

    public boolean getRendersChildren() {
        return true;
    }
}
