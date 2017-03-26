package org.jboss.gravel.simple.renderer;

import java.io.IOException;
import org.jboss.gravel.common.renderer.Element;
import org.jboss.gravel.simple.ui.UITableRow;

/**
 *
 */
public class TableRowRenderer<T extends UITableRow> extends SimpleRenderer<T> {
    protected TableRowRenderer(final String element) {
        super(element);
    }

    protected void writeAttributes(final Element<T> elem, final T component) throws IOException {
        super.writeAttributes(elem, component);
        writeHtmlCellAlign(elem, component);
    }

    public static TableRowRenderer<UITableRow> tableRowRenderer(final String element) {
        return new TableRowRenderer<UITableRow>(element);
    }
}
