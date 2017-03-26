package org.jboss.gravel.common.ui;

import org.jboss.gravel.common.annotation.TldAttribute;

/**
 *
 */
public interface HasJsfBasicFacetStyles {
    @TldAttribute(
            description = "Space-separated list of CSS style class(es) that will be applied to any header generated " +
                    "for this table."
    )
    String getHeaderClass();

    @TldAttribute(
            description = "Space-separated list of CSS style(s) that will be applied to any header generated for " +
                    "this table."
    )
    String getHeaderStyle();

    @TldAttribute(
            description = "Space-separated list of CSS style class(es) that will be applied to any footer generated " +
                    "for this table."
    )
    String getFooterClass();

    @TldAttribute(
            description = "Space-separated list of CSS style(s) that will be applied to any footer generated " +
                    "for this table."
    )
    String getFooterStyle();
}
