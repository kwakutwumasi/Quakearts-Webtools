package org.jboss.gravel.common.ui;

import org.jboss.gravel.common.annotation.TldAttribute;

/**
 *
 */
public interface HasRows {
    @TldAttribute (
        description = "A comma-separated list of names of style classes that will be applied in rotation to every row. " +
            "If no name is given, the value of the <code>gravel.Data.MIDDLE_ROW_CLASS</code> context init " +
            "parameter will be used.  If that value is not specified, no style class will be used."
    )
    String getRowClasses();

    @TldAttribute (
        description = "The name of a style class that will be applied to any row that is the first row in the row " +
            "group. " +
            "If no name is given, the value of the <code>gravel.Data.FIRST_ROW_CLASS</code> context init " +
            "parameter will be used.  If that value is not specified, no style class will be used."
    )
    String getFirstRowClass();

    @TldAttribute (
        description = "The name of a style class that will be applied to any row that is the last row in the row " +
            "group. " +
            "If no name is given, the value of the <code>gravel.Data.LAST_ROW_CLASS</code> context init " +
            "parameter will be used.  If that value is not specified, no style class will be used."
    )
    String getLastRowClass();

    @TldAttribute (
        description = "The name of a style class that will be applied to any row that is NOT the first row in the row " +
            "group. " +
            "If no name is given, the value of the <code>gravel.Data.NON_FIRST_ROW_CLASS</code> context init " +
            "parameter will be used.  If that value is not specified, no style class will be used."
    )
    String getNonFirstRowClass();

    @TldAttribute (
        description = "The name of a style class that will be applied to any row that is NOT the last row in the row " +
            "group. " +
            "If no name is given, the value of the <code>gravel.Data.NON_LAST_ROW_CLASS</code> context init " +
            "parameter will be used.  If that value is not specified, no style class will be used."
    )
    String getNonLastRowClass();
}
