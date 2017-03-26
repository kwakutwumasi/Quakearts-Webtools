package org.jboss.gravel.common.ui;

import org.jboss.gravel.common.annotation.TldAttribute;

/**
 *
 */
public interface HasHtmlFocusEventsAttributes extends HasHtmlEventsAttributes {
    @TldAttribute (
        description = "Render the HTML onfocus attribute.  The onfocus event occurs when " +
            "an element receives focus either by the pointing device or by tabbing navigation.",
        deferredType = String.class
    )
    String getOnfocus();

    @TldAttribute (
        description = "Render the HTML onblur attribute.  The onfocus event occurs when " +
            "an element loses focus either by the pointing device or by tabbing navigation.",
        deferredType = String.class
    )
    String getOnblur();
}
