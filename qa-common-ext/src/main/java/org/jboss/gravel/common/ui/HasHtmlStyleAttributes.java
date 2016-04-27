package org.jboss.gravel.common.ui;

import org.jboss.gravel.common.annotation.TldAttribute;

/**
 *
 */
public interface HasHtmlStyleAttributes {
    @TldAttribute(
            description = "The value of the HTML class attribute for this component.",
            deferredType = String.class
    )
    String getStyleClass();

    @TldAttribute(
            description = "The value of the HTML style attribute for this component.",
            deferredType = String.class
    )
    String getStyle();
}
