package org.jboss.gravel.common.ui;

import org.jboss.gravel.common.annotation.TldAttribute;

/**
 *
 */
public interface HasHtmlCellAlignAttributes extends HasHtmlBasicAttributes {

    @TldAttribute (
        description = "This attribute specifies the alignment of data and the justification of text in a cell."
    )
    String getAlign();

    @TldAttribute (
        description = "This attribute specifies a single character within a text fragment to act as an " +
            "axis for alignment."
    )
    String getAlignChar();

    @TldAttribute (
        description = "When present, this attribute specifies the offset to the first occurrence of the " +
            "alignment character on each line."
    )
    String getCharoff();

    @TldAttribute (
        description = "This attribute specifies the vertical position of data within a cell."
    )
    String getValign();
}
