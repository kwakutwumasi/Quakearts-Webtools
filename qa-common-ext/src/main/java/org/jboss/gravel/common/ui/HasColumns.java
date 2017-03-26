package org.jboss.gravel.common.ui;

import org.jboss.gravel.common.annotation.TldAttribute;

/**
 *
 */
public interface HasColumns {
    @TldAttribute (
        description = "Flag that indicates whether a padding cell should be generated to the left and right of " +
            "each data cell.  If not given, the default value specified by the <code>gravel.Data.PAD_COLUMNS</code> " +
            "context init parameter will be used.  If that value is not specified, no padding will be generated."
    )
    boolean isPadColumns(boolean defaultVal);

    @TldAttribute (
        description = "The name of a style class that will be applied to every left padding cell.  If no name is given, " +
            "the value of the <code>gravel.Data.LEFT_PAD_CELL_CLASS</code> context init parameter will be used.  If that " +
            "value is not specified, no style class will be used."
    )
    String getLeftPadCellClass();

    @TldAttribute (
        description = "The name of a style class that will be applied to every center (non-padding) data cell.  " +
            "If no name is given, the value of the <code>gravel.Data.CENTER_CELL_CLASS</code> context init parameter " +
            "will be used.  If that value is not specified, no style class will be used."
    )
    String getCenterCellClass();

    @TldAttribute (
        description = "The name of a style class that will be applied to every right padding cell.  If no name is given, " +
            "the value of the <code>gravel.Data.RIGHT_PAD_CELL_CLASS</code> context init parameter will be used.  If that " +
            "value is not specified, no style class will be used."
    )
    String getRightPadCellClass();

    @TldAttribute (
        description = "The name of a style class that will be applied to any cell that is in the first column of the row. " +
            "If no name is given, the value of the <code>gravel.Data.FIRST_CELL_CLASS</code> context init " +
            "parameter will be used.  If that value is not specified, no style class will be used."
    )
    String getFirstCellClass();

    @TldAttribute (
        description = "The name of a style class that will be applied to any cell that is in the last column of the row. " +
            "If no name is given, the value of the <code>gravel.Data.LAST_CELL_CLASS</code> context init " +
            "parameter will be used.  If that value is not specified, no style class will be used."
    )
    String getLastCellClass();

    @TldAttribute (
        description = "The name of a style class that will be applied to any cell that is NOT in the first column of the row. " +
            "If no name is given, the value of the <code>gravel.Data.NON_FIRST_CELL_CLASS</code> context init " +
            "parameter will be used.  If that value is not specified, no style class will be used."
    )
    String getNonFirstCellClass();

    @TldAttribute (
        description = "The name of a style class that will be applied to any cell that is NOT in the last column of the row. " +
            "If no name is given, the value of the <code>gravel.Data.NON_LAST_CELL_CLASS</code> context init " +
            "parameter will be used.  If that value is not specified, no style class will be used."
    )
    String getNonLastCellClass();
}
