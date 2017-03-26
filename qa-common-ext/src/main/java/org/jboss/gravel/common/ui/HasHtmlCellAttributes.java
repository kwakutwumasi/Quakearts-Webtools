package org.jboss.gravel.common.ui;

import org.jboss.gravel.common.annotation.TldAttribute;

/**
 *
 */
public interface HasHtmlCellAttributes extends HasHtmlCellAlignAttributes {

    @TldAttribute (
        description = "This attribute should be used to provide an abbreviated form of the cell's content, and may be " +
            "rendered by user agents when appropriate in place of the cell's content."
    )
    String getAbbr();

    @TldAttribute (
        description = "This attribute may be used to place a cell into conceptual categories that can be considered " +
            "to form axes in an n-dimensional space."
    )
    String getAxis();

    @TldAttribute (
        description = "This attribute specifies a space-separated list of IDs of header cells that provide header " +
            "information for the current data cell.  JSF relative or absolute IDs may be specified, and will be " +
            "translated into the correct ID upon rendering."
    )
    String getHeaders();

    @TldAttribute (
        description = "This attribute specifies the set of data cells for which the current header cell provides " +
            "header information."
    )
    String getScope();

    @TldAttribute (
        description = "This attribute specifies the number of rows spanned by the current cell."
    )
    int getRowspan();

    @TldAttribute (
        description = "This attribute specifies the number of columns spanned by the current cell."
    )
    int getColspan();
}
