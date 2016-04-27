package org.jboss.gravel.simple.renderer;

import java.io.IOException;
import org.jboss.gravel.common.renderer.Element;
import org.jboss.gravel.simple.ui.UITableCell;

/**
 *
 */
public class TableCellRenderer<T extends UITableCell> extends TableRowRenderer<T> {

    protected TableCellRenderer(final String element) {
        super(element);
    }

    protected void writeAttributes(final Element<T> elem, final T component) throws IOException {
        super.writeAttributes(elem, component);
        writeHtmlCell(elem, component);
    }

    public static TableCellRenderer<UITableCell> tableCellRenderer(final String element) {
        return new TableCellRenderer<UITableCell>(element);
    }
}
